package com.ailk.phw.iface;

import com.ailk.phw.frombytes.ObjectFromBytes;
import com.ailk.phw.utils.ConstantUtils;
import com.ailk.phw.utils.FieldAnnoAttr;

public abstract class FromBytes<T> implements IFromBytes<T> {

    private String charset = ConstantUtils.CHARSET_UTF8;

    private int offset = 0;

    private int lenBytes = 1;

    public FromBytes<T> setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public FromBytes<T> setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public FromBytes<T> setLenBytes(int lenBytes) {
        this.lenBytes = lenBytes;
        return this;
    }

    public int getLenBytes() {
        return lenBytes;
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
        Object object = objectFromBytes.setOffset(getOffset()).fromBytes(bytes, clazz);
        setOffset(objectFromBytes.getOffset());
        return object;
    }

    protected Object fromBytesWithType(FromBytes fromBytes, byte[] bytes, FieldAnnoAttr anno, Class clazz) {
        fromBytes.setCharset(anno.getCharset()).setLenBytes(anno.getLenBytes()).setOffset(getOffset());
        Object object = fromBytes.fromBytes(bytes, anno.getLength(), anno.getFillByte(), clazz);
        setOffset(fromBytes.getOffset());
        return object;
    }

}
