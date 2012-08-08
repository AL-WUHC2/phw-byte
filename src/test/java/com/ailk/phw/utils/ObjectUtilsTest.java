package com.ailk.phw.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.phw.core.lang.Collections;

public class ObjectUtilsTest {

    @Test
    public void testStringClassName() {
        StringBean strBean = ObjectUtils.populateBean(Collections.asMap(
                "str1", "hello", "str2", "world"), "com.ailk.phw.utils.StringBean");
        assertEquals("hello", strBean.getStr1());
        assertEquals("world", strBean.getStr2());
    }

}
