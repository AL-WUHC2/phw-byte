package com.ailk.phw.frombytes;

import java.lang.reflect.Field;

import com.ailk.phw.annotations.JCTransient;
import com.ailk.phw.iface.FieldIterator;
import com.ailk.phw.iface.FromBytes;
import com.ailk.phw.utils.FieldUtils;
import com.ailk.phw.utils.ObjectUtils;

public class ObjectFromBytes extends FieldIterator {

    private byte[] bytes;
    private Object bean;
    private int offset = 0;

    public <T> T fromBytes(byte[] bytes, Class<T> clazz) {
        if (bytes.length == offset) {
            return null;
        }
        FromBytes fromBytes = FromBytesUtils.getPrimitiveFromBytes(clazz);
        if (fromBytes != null) {
            T object = (T) fromBytes.setOffset(offset).fromBytes(bytes);
            offset = fromBytes.getOffset();
            return object;
        }
        this.bytes = bytes;
        bean = ObjectUtils.newInstance(clazz);
        iterateFields(clazz);
        return (T) bean;
    }

    @Override
    protected void processField(Field field, boolean nullable) {
        if (field.getAnnotation(JCTransient.class) != null) {
            return;
        }
        FieldFromBytes fieldFromBytes = new FieldFromBytes();
        fieldFromBytes.setOffset(offset);
        FieldUtils.setFieldValue(field, bean, fieldFromBytes.fromBytes(field, bean, bytes, nullable));
        offset = fieldFromBytes.getOffset();
    }

    public ObjectFromBytes setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public int getOffset() {
        return offset;
    }

}
