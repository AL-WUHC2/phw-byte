package com.ailk.phw.toBytes;

import java.lang.reflect.Field;
import java.util.List;

import com.ailk.phw.annotations.JCToByter;
import com.ailk.phw.iface.ToBytes;
import com.ailk.phw.utils.FieldAttrUtils;
import com.ailk.phw.utils.FieldUtils;
import com.ailk.phw.utils.ObjectUtils;

public class FieldToBytes extends ToBytes<Field> {

    @Override
    public byte[] toBytes(Field obj, int length, byte fill) {
        return null;
    }

    public byte[] toBytes(Field field, Object bean) {
        Object fieldValue = FieldUtils.getFieldValue(field, bean);
        if (fieldValue == null) {
            throw new RuntimeException("Object is Null");
        }
        byte[] result = new byte[0];
        StringBuilder builder = new StringBuilder();
        FieldAttrUtils attr = new FieldAttrUtils(field);
        builder.append(attr.getName()).append(":");
        JCToByter jcToByter = field.getAnnotation(JCToByter.class);
        if (jcToByter != null) {
            result = objectToBytes(ObjectUtils.newInstance(jcToByter.value()), fieldValue, builder);
            setDesc(builder.toString());
            return result;
        }
        ToBytes toBytes = List.class.isAssignableFrom(field.getType()) ? new ListToBytes()
                : ToBytesUtils.getToBytes(field.getType());
        if (toBytes != null) {
            result = toBytesWithAttr(toBytes, fieldValue, attr, builder);
            setDesc(builder.toString());
            return result;
        }
        result = objectToBytes(new ObjectToBytes(), fieldValue, builder);
        setDesc(builder.toString());
        return result;
    }

}
