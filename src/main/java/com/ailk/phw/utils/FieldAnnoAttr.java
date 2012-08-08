package com.ailk.phw.utils;

import java.lang.reflect.Field;

import javax.xml.bind.DatatypeConverter;

import com.ailk.phw.annotations.JCBytes;
import com.ailk.phw.enums.JCExpType;

public class FieldAnnoAttr {

    private String name;
    private JCExpType type;
    private int lenBytes;
    private int length;
    private byte fillByte;
    private String charset;

    public FieldAnnoAttr(JCExpType type, int length, int lenBytes, byte fillByte, String charset) {
        name = "";
        this.type = type;
        this.lenBytes = lenBytes;
        this.length = length;
        this.fillByte = fillByte;
        this.charset = charset;
    }

    public FieldAnnoAttr(JCExpType type, int length, int lenBytes, String fillByte, String charset) {
        this(type, length, lenBytes, getFillByte(type, fillByte), charset);
    }

    public FieldAnnoAttr(Field field) {
        JCBytes jcBytes = field.getAnnotation(JCBytes.class);
        if (jcBytes == null) {
            name = ConstantUtils.DEFAULT_FIELD_NAME;
            type = JCExpType.Hex;
            lenBytes = ConstantUtils.DEFAULT_LEN_BYTES;
            length = ConstantUtils.DEFAULT_FIELD_LENGTH;
            fillByte = ConstantUtils.DEFAULT_FIELD_FILL;
            charset = ConstantUtils.CHARSET_UTF8;
        } else {
            name = jcBytes.name();
            type = jcBytes.type();
            lenBytes = jcBytes.lenBytes();
            length = jcBytes.length();
            fillByte = getFillByte(type, jcBytes.fillByte());
            charset = jcBytes.charset();
        }
        name = name.isEmpty() ? field.getName() : name;
    }

    private static byte getFillByte(JCExpType type, String fillByte) {
        byte[] fillBytes = DatatypeConverter.parseHexBinary(fillByte);
        return fillBytes.length > 0 ? fillBytes[0] :
                type.equals(JCExpType.Hex) ? ConstantUtils.FillByte.FULL_BYTE : ConstantUtils.FillByte.EMPTY_BYTE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(JCExpType type) {
        this.type = type;
    }

    public JCExpType getType() {
        return type;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLenBytes(int lenBytes) {
        this.lenBytes = lenBytes;
    }

    public int getLenBytes() {
        return lenBytes;
    }

    public void setFillByte(byte fillByte) {
        this.fillByte = fillByte;
    }

    public byte getFillByte() {
        return fillByte;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCharset() {
        return charset;
    }

}
