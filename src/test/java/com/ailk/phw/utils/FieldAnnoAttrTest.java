package com.ailk.phw.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ailk.phw.enums.JCExpType;

public class FieldAnnoAttrTest {

    @Test
    public void testConstructor() {
        FieldAnnoAttr fieldAnnoAttr = new FieldAnnoAttr(JCExpType.ASCII, 10, 1, "FF", ConstantUtils.CHARSET_UTF8);
        assertEquals(-1, fieldAnnoAttr.getFillByte());
    }

}
