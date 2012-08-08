package com.ailk.phw.iface;

import com.ailk.phw.enums.JCExpType;
import com.ailk.phw.tobytes.ObjectToBytes;
import com.ailk.phw.utils.ConstantUtils;
import com.ailk.phw.utils.DescUtils;
import com.ailk.phw.utils.FieldAnnoAttr;

public abstract class ToBytes<T> implements IToBytes<T> {

    private String charset = ConstantUtils.CHARSET_UTF8;

    private String desc;

    private JCExpType type = JCExpType.Hex;

    private int lenBytes = 1;

    public ToBytes<T> setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public ToBytes<T> setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ToBytes<T> setType(JCExpType type) {
        this.type = type;
        return this;
    }

    public JCExpType getType() {
        return type;
    }

    public ToBytes<T> setLenBytes(int lenBytes) {
        this.lenBytes = lenBytes;
        return this;
    }

    public int getLenBytes() {
        return lenBytes;
    }

    public byte[] toBytes(T obj) {
        return toBytes(obj, -1);
    }

    public byte[] toBytes(T obj, int length) {
        return toBytes(obj, length, (byte) 0xF);
    }

    protected byte[] objectToBytes(ObjectToBytes objectToBytes, Object obj, StringBuilder builder, String... strings) {
        byte[] result = objectToBytes.toBytes(obj);
        builder.append(objectToBytes.getDesc());
        DescUtils.appends(builder, strings);
        return result;
    }

    protected byte[] toBytesWithAttr(ToBytes toBytes, Object obj, FieldAnnoAttr anno,
            StringBuilder builder, String... describe) {
        toBytes.setType(anno.getType()).setLenBytes(anno.getLenBytes()).setCharset(anno.getCharset());
        byte[] result = toBytes.toBytes(obj, anno.getLength(), anno.getFillByte());
        builder.append(toBytes.getDesc());
        DescUtils.appends(builder, describe);
        return result;
    }

}
