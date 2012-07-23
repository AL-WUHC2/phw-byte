package com.ailk.phw.iface;

import com.ailk.phw.fromBytes.ObjectFromBytes;
import com.ailk.phw.utils.ConstantUtils;

public abstract class FromBytes<T> implements IFromBytes<T> {

    private String charset = ConstantUtils.CHARSET_UTF8;
    private int offset = 0;

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCharset() {
        return charset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public T fromBytes(byte[] b) {
        return fromBytes(b, ConstantUtils.DEFAULT_FIELD_LENGTH);
    }

    public T fromBytes(byte[] b, int length) {
        return fromBytes(b, length, ConstantUtils.DEFAULT_FIELD_FILL);
    }

    public T fromBytes(byte[] b, int length, byte fill, Class clazz) {
        return fromBytes(b, length, fill);
    }

    protected Object objectFromBytes(ObjectFromBytes objectFromBytes, byte[] bytes, Class clazz) {
        objectFromBytes.setOffset(getOffset());
        Object object = objectFromBytes.fromBytes(bytes, clazz);
        setOffset(objectFromBytes.getOffset());
        return object;
    }

    protected Object fromBytesWithType(FromBytes fromBytes, byte[] bytes, int length, byte fill, Class clazz) {
        fromBytes.setOffset(getOffset());
        Object object = fromBytes.fromBytes(bytes, length, fill, clazz);
        setOffset(fromBytes.getOffset());
        return object;
    }

}
