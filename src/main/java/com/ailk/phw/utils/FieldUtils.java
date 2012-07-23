package com.ailk.phw.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import com.google.common.base.Throwables;

public class FieldUtils {

    public static Class getFieldGenericType(Field field) {
        if (ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass())) {
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();
            return (Class) genericType.getActualTypeArguments()[0];
        }
        return Void.class;
    }

    public static void setFieldValue(Field field, Object bean, Object value) {
        try {
            field.setAccessible(true);
            field.set(bean, value);
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }

    public static Object getFieldValue(Field field, Object bean) {
        try {
            field.setAccessible(true);
            return field.get(bean);
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }

}
