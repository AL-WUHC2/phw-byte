package com.ailk.phw.frombytes;

import java.lang.reflect.Field;
import java.util.List;

import com.ailk.phw.annotations.JCFromByter;
import com.ailk.phw.iface.FromBytes;
import com.ailk.phw.utils.FieldAnnoAttr;
import com.ailk.phw.utils.FieldUtils;
import com.ailk.phw.utils.ObjectUtils;

public class FieldFromBytes extends FromBytes<Field> {

    @Override
    public Field fromBytes(byte[] b, int length, byte fill) {
        return null;
    }

    public Object fromBytes(Field field, Object bean, byte[] bytes, boolean nullable) {
        if (bytes.length == getOffset()) {
            return null;
        }

        Class fieldType = field.getType();
        FieldAnnoAttr attr = new FieldAnnoAttr(field);
        JCFromByter jcFromByter = field.getAnnotation(JCFromByter.class);
        if (jcFromByter != null) {
            return objectFromBytes(ObjectUtils.newInstance(jcFromByter.value()), bytes, fieldType);
        }

        if (List.class.isAssignableFrom(fieldType)) {
            Class genericType = FieldUtils.getFieldGenericType(field);
            if (genericType == Void.class) {
                throw new RuntimeException("Unkown Item Class for field " + field.getName());
            }
            return fromBytesWithType(new ListFromBytes(), bytes, attr, genericType);
        }

        FromBytes fromBytes = FromBytesUtils.getPrimitiveFromBytes(fieldType);
        if (fromBytes != null) {
            return fromBytesWithType(fromBytes, bytes, attr, fieldType);
        }

        return objectFromBytes(new ObjectFromBytes(), bytes, fieldType);
    }

}
