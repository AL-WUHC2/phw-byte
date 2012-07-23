package com.ailk.phw.utils;

import com.google.common.base.Throwables;

public class ObjectUtils {

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T newInstance(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (T) newInstance(clazz);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

}
