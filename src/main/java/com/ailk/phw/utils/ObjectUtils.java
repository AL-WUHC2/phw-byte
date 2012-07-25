package com.ailk.phw.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

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

    public static <T> T populateBean(Map properties, Class<T> clazz) {
        try {
            T bean = newInstance(clazz);
            BeanUtils.populate(bean, properties);
            return bean;
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T populateBean(Map properties, String className) {
        try {
            T bean = newInstance(className);
            BeanUtils.populate(bean, properties);
            return bean;
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

}
