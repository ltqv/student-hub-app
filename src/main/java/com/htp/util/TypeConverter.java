/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author htphu
 */
public class TypeConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Object convertType(Object value, Field targetField) throws JsonProcessingException {
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
}
