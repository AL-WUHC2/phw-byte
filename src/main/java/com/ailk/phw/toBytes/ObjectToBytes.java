package com.ailk.phw.toBytes;

import java.lang.reflect.Field;

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
        ToBytes toBytes = ToBytesUtils.getToBytes(obj.getClass());
        if (toBytes != null) {
            result = toBytes.toBytes(obj);
            setDesc(builder.append(toBytes.getDesc()).append("}").toString());
            return result;
        }
        object = obj;
        iterateFields(obj.getClass());
        setDesc(builder.append("}").toString());
        return result;
    }

    @Override
    protected void processField(Field field) {
        FieldToBytes fieldToBytes = new FieldToBytes();
        byte[] bytes = fieldToBytes.toBytes(field, object);
        builder.append(builder.length() > 1 ? ", " : "").append(fieldToBytes.getDesc());
        result = JCConvertUtils.mergeByteArray(result, bytes);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
