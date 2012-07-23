package com.ailk.phw.iface;

import java.lang.reflect.Field;

import com.ailk.phw.annotations.JCTransient;

public abstract class FieldIterator {

    protected void iterateFields(Class<?> beanClass) {
        Field[] declaredFields = beanClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(JCTransient.class) == null) {
                processField(field);
            }
        }
    }

    protected abstract void processField(Field field);

}
