/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.htp.annotations.Column;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author htphu
 */
public class JdbcUtils {

    private static Connection connection;

    public static boolean isReady() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection openConnection() {
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
            throw new RuntimeException("❌ Lỗi kết nối CSDL: " + e.getMessage(), e);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Đã đóng kết nối CSDL.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Lỗi khi đóng kết nối: " + e.getMessage(), e);
        }
    }

    public static <T> T readBean(ResultSet rs, Class<T> clazz) throws Exception {
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
                Object converted = TypeConverter.convertType(dbValue, field);
                field.setAccessible(true);
                field.set(bean, converted);
            } catch (JsonProcessingException | IllegalAccessException | IllegalArgumentException | SQLException e) {
                System.out.printf("⚠️ Cột '%s' không tìm thấy hoặc không ánh xạ được.\n", column);
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
            System.out.println("❌ Lỗi khi update: " + e.getMessage());
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
