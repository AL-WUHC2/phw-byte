package com.ailk.phw.iface;

import com.ailk.phw.enums.JCExpType;
import com.ailk.phw.toBytes.ObjectToBytes;
import com.ailk.phw.utils.ConstantUtils;
import com.ailk.phw.utils.DescUtils;
import com.ailk.phw.utils.FieldAttrUtils;

public abstract class ToBytes<T> implements IToBytes<T> {

    private String charset = ConstantUtils.CHARSET_UTF8;

    private String desc;

    private JCExpType type = JCExpType.Hex;

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCharset() {
        return charset;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setType(JCExpType type) {
        this.type = type;
    }

    public JCExpType getType() {
        return type;
    }

    public byte[] toBytes(T obj) {
        return toBytes(obj, -1);
    }

    public byte[] toBytes(T obj, int length) {
        return toBytes(obj, length, (byte) 0xF);
    }

    protected byte[] objectToBytes(ObjectToBytes objectToBytes, Object obj,
            StringBuilder builder, String... strings) {
        byte[] result = objectToBytes.toBytes(obj);
        builder.append(objectToBytes.getDesc());
        DescUtils.appends(builder, strings);
        return result;
    }

    protected byte[] toBytesWithAttr(ToBytes toBytes, Object obj, FieldAttrUtils attr,
            StringBuilder builder, String... strings) {
        toBytes.setType(attr.getType());
        toBytes.setCharset(attr.getCharset());
        byte[] result = toBytes.toBytes(obj, attr.getLength(), attr.getFillByte());
        builder.append(toBytes.getDesc());
        DescUtils.appends(builder, strings);
        return result;
    }

}
