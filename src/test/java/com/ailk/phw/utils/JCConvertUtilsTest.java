package com.ailk.phw.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JCConvertUtilsTest {

    @Test
    public void testLenArray() {
        assertEquals(10, JCConvertUtils.getArrayLen(JCConvertUtils.getLenArray(10, 1), 0, 1));
        assertEquals(20, JCConvertUtils.getArrayLen(JCConvertUtils.getLenArray(20, 2), 0, 2));
        assertEquals(40, JCConvertUtils.getArrayLen(JCConvertUtils.getLenArray(40, 4), 0, 4));
    }

}
