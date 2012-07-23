package com.ailk.phw.fromBytes;

import java.lang.reflect.Field;
import java.util.List;

import com.ailk.phw.annotations.JCFromByter;
import com.ailk.phw.iface.FromBytes;
import com.ailk.phw.utils.FieldAttrUtils;
import com.ailk.phw.utils.FieldUtils;
import com.ailk.phw.utils.ObjectUtils;

public class FieldFromBytes extends FromBytes<Field> {

    @Override
    public Field fromBytes(byte[] b, int length, byte fill) {
        return null;
    }

    public Object fromBytes(Field field, Object bean, byte[] bytes) {
        if (bytes.length == getOffset()) {
            return null;
        }
        Class fieldType = field.getType();
        FieldAttrUtils attr = new FieldAttrUtils(field);
        JCFromByter jcFromByter = field.getAnnotation(JCFromByter.class);
        if (jcFromByter != null) {
            return objectFromBytes(ObjectUtils.newInstance(jcFromByter.value()), bytes, fieldType);
        }
        if (List.class.isAssignableFrom(fieldType)) {
            Class genericType = FieldUtils.getFieldGenericType(field);
            if (genericType == Void.class) {
                throw new RuntimeException("Unkown Item Class for field " + field.getName());
            }
            return fromBytesWithType(new ListFromBytes(), bytes, attr.getLength(), attr.getFillByte(), genericType);
        }
        FromBytes fromBytes = FromBytesUtils.getFromBytes(fieldType);
        if (fromBytes != null) {
            return fromBytesWithType(fromBytes, bytes, attr.getLength(), attr.getFillByte(), fieldType);
        }
        return objectFromBytes(new ObjectFromBytes(), bytes, fieldType);
    }

}
