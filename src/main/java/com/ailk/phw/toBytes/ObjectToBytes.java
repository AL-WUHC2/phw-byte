package com.ailk.phw.tobytes;

import java.lang.reflect.Field;

import com.ailk.phw.annotations.JCTransient;
import com.ailk.phw.iface.FieldIterator;
import com.ailk.phw.iface.ToBytes;
import com.ailk.phw.utils.JCConvertUtils;

public class ObjectToBytes extends FieldIterator {

    private byte[] result;
    private Object object;
    private StringBuilder builder;
    private String desc;

    public byte[] toBytes(Object obj) {
        if (obj == null) {
            throw new RuntimeException("Object is Null");
        }

        result = new byte[0];
        builder = new StringBuilder();
        builder.append("{");
        ToBytes toBytes = ToBytesUtils.getPrimitiveToBytes(obj.getClass());
        if (toBytes != null) {
            result = toBytes.toBytes(obj);
            builder.append(toBytes.getDesc());
        } else {
            object = obj;
            iterateFields(obj.getClass());
        }

        setDesc(builder.append("}").toString());
        return result;
    }

    @Override
    protected void processField(Field field, boolean nullable) {
        if (field.getAnnotation(JCTransient.class) != null) {
            return;
        }

        FieldToBytes fieldToBytes = new FieldToBytes();
        byte[] bytes = fieldToBytes.toBytes(field, object, nullable);
        builder.append(builder.length() > 1 ? ", " : "").append(fieldToBytes.getDesc());
        result = JCConvertUtils.addBytes(result, bytes);
    }

    public ObjectToBytes setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getDesc() {
        return desc;
    }

}
