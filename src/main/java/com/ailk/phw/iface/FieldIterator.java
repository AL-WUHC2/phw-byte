package com.ailk.phw.iface;

import java.lang.reflect.Field;

public abstract class FieldIterator {

    protected void iterateFields(Class<?> beanClass) {
        Field[] declaredFields = beanClass.getDeclaredFields();
        for (Field field : declaredFields) {
            processField(field);
        }
    }

    protected abstract void processField(Field field);

}
