/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htp.annotations.Column;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author htphu
 */
public class JdbcUtil {

    private static Connection connection;
    private static final ObjectMapper mapper = new ObjectMapper();

    private static boolean isReady() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection openConnection() {
        var driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        var dburl = "jdbc:sqlserver://localhost:1433;databaseName=QLHV;encrypt=true;trustServerCertificate=true;";
        var username = "sa";
        var password = "20012000";
        try {
            if (!isReady()) {
                Class.forName(driver);
                connection = DriverManager.getConnection(dburl, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connect database: " + e.getMessage(), e);
        }
        return connection;
    }

    private static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection has been closed.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while closing the connection: " + e.getMessage(), e);
        }
    }

    private static Object convertType(Object value, Field targetField) throws JsonProcessingException {
        Class<?> targetType = targetField.getType();

        if (value == null) {
            return null;
        }

        // Trường hợp đúng kiểu rồi → return luôn
        if (targetType.isInstance(value)) {
            return value;
        }

        String str = value.toString().trim();

        // Enum
        if (targetType.isEnum()) {
            return Enum.valueOf((Class<Enum>) targetType, str.toUpperCase());
        }

        // Integer
        if (targetType == Integer.class || targetType == int.class) {
            return Integer.valueOf(str);
        }

        // Boolean
        if (targetType == Boolean.class || targetType == boolean.class) {
            return Boolean.valueOf(str);
        }

        // Double
        if (targetType == Double.class || targetType == double.class) {
            return Double.valueOf(str);
        }

        // Long
        if (targetType == Long.class || targetType == long.class) {
            return Long.valueOf(str);
        }

        // LocalDate (ISO format)
        if (targetType == LocalDate.class) {
            return LocalDate.parse(str, DateTimeFormatter.ISO_DATE);
        }

        // Timestamp
        if (targetType == Timestamp.class) {
            return Timestamp.valueOf(str);
        }

        // List (từ JSON string)
        if (List.class.isAssignableFrom(targetType)) {
            Type genericType = targetField.getGenericType();
            if (genericType instanceof ParameterizedType pt) {
                Type elementType = pt.getActualTypeArguments()[0];
                if (elementType instanceof Class<?> elementClass) {
                    return mapper.readValue(str,
                            mapper.getTypeFactory().constructCollectionType(List.class, elementClass));
                }
            }
        }

        // Default → trả lại String
        return str;
    }

    private static <T> T readBean(ResultSet rs, Class<T> clazz) throws Exception {
        T bean = clazz.getDeclaredConstructor().newInstance();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            String column = field.getAnnotation(Column.class).name();
            if (column.isEmpty()) {
                column = field.getName();
            }

            try {
                Object dbValue = rs.getObject(column);
                Object converted = convertType(dbValue, field);
                field.setAccessible(true);
                field.set(bean, converted);
            } catch (JsonProcessingException | IllegalAccessException | IllegalArgumentException | SQLException e) {
                System.out.printf("Column '%s' not found or could not be mapped.\n", column);
            }
        }

        return bean;
    }

    public static int executeUpdate(String sql, Object... params) {
        try (Connection conn = openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error while update data: " + e.getMessage());
            return -1;
        }
    }

    public static <T> List<T> executeQueryList(String sql, Class<T> clazz, Object... params) {
        List<T> resultList = new ArrayList<>();

        try (Connection conn = openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultList.add(readBean(rs, clazz));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return resultList;
    }

    public static <T> Optional<T> executeQueryOne(String sql, Class<T> clazz, Object... params) {
        List<T> list = executeQueryList(sql, clazz, params);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

}
