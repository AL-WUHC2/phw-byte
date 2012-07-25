package com.ailk.phw.utils;

import java.lang.reflect.Field;

import com.ailk.phw.annotations.JCBytes;
import com.ailk.phw.enums.JCExpType;
import com.ailk.phw.enums.JCLenType;

public class FieldAttrUtils {

    private String name;
    private JCExpType type;
    private JCLenType lenType;
    private int length;
    private boolean useDefaultByte;
    private byte fillByte;
    private String charset;

    public FieldAttrUtils(JCExpType type, JCLenType lenType, int length, byte fillByte, String charset) {
        name = "";
        this.type = type;
        this.lenType = lenType;
        this.length = length;
        this.fillByte = fillByte;
        this.charset = charset;
    }

    public FieldAttrUtils(Field field) {
        JCBytes jcBytes = field.getAnnotation(JCBytes.class);
        if (jcBytes == null) {
            name = ConstantUtils.DEFAULT_FIELD_NAME;
            type = JCExpType.Hex;
            lenType = JCLenType.Byte;
            length = ConstantUtils.DEFAULT_FIELD_LENGTH;
            useDefaultByte = true;
            fillByte = ConstantUtils.DEFAULT_FIELD_FILL;
            charset = ConstantUtils.CHARSET_UTF8;
        } else {
            name = jcBytes.name();
            type = jcBytes.type();
            lenType = jcBytes.lenType();
            length = jcBytes.length();
            useDefaultByte = jcBytes.useDefaultByte();
            fillByte = useDefaultByte ? DescUtils.getDefaultFill(type) : jcBytes.fillByte();
            charset = jcBytes.charset();
        }
        name = name.isEmpty() ? field.getName() : name;
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

    public void setLenType(JCLenType lenType) {
        this.lenType = lenType;
    }

    public JCLenType getLenType() {
        return lenType;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setUseDefaultByte(boolean useDefaultByte) {
        this.useDefaultByte = useDefaultByte;
    }

    public boolean getUseDefaultByte() {
        return useDefaultByte;
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
