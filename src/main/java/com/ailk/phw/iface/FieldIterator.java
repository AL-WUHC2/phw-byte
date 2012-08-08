package com.ailk.phw.iface;

import java.lang.reflect.Field;

import org.apache.commons.lang.ArrayUtils;

public abstract class FieldIterator {

    protected void iterateFields(Class<?> beanClass) {
        Field[] declaredFields = beanClass.getDeclaredFields();
        Field[] fields = declaredFields;
        for (Field field : declaredFields) {
            if (field.getName().startsWith("$") || field.getName().equals("serialVersionUID")) {
                fields = (Field[]) ArrayUtils.removeElement(fields, field);
            }
        }
        for (int i = 0; i < fields.length; i++) {
            Field field = declaredFields[i];
            boolean nullable = i == fields.length - 1 ? true : false;
            processField(field, nullable);
        }
    }

    protected abstract void processField(Field field, boolean nullable);

}
