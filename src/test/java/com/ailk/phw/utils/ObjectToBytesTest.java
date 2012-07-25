package com.ailk.phw.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.phw.core.lang.Collections;

import com.ailk.phw.fromBytes.ObjectFromBytes;
import com.ailk.phw.toBytes.ObjectToBytes;

public class ObjectToBytesTest {

    @Test
    public void testDemoBean() {
        Map properties = Collections.asMap("message", "你好世界", "messages", Arrays.asList("你好", "世界"));
        DemoBean test = ObjectUtils.populateBean(properties, DemoBean.class);
        ObjectToBytes toBytes = new ObjectToBytes();
        byte[] bytes = toBytes.toBytes(test);
        // Assert.assertEquals("{message:E4BDA0E5A5BDE4B896E7958C, messages:[E4BDA0E5A5BD, E4B896E7958C]}",
        // toBytes.getDesc());
        ObjectFromBytes fromBytes = new ObjectFromBytes();
        DemoBean test2 = fromBytes.fromBytes(bytes, DemoBean.class);
        Assert.assertEquals(test, test2);
    }

    @Test
    public void testSimpleBean() {
        Map properties = Collections.asMap("string", "ABCDE", "stringFixed", "12345",
                "b", (byte) 0, "s", (short) 1, "i", 2, "l", 3, "f", 4, "d", 5, "n", true);
        SimpleBean simpleBean = ObjectUtils.populateBean(properties, SimpleBean.class);
        ObjectToBytes objectToBytes = new ObjectToBytes();
        SimpleBean simpleBean2 = new ObjectFromBytes().fromBytes(objectToBytes.toBytes(simpleBean), SimpleBean.class);
        assertEquals(simpleBean, simpleBean2);
        // assertEquals("{title:4142434445, stringFixed:0x31 0x32 0x33 0x34 0x35 0xFF 0xFF 0xFF 0xFF 0xFF" +
        // " 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF, b:00, s:0001, i:00000002," +
        // " l:0000000000000003, f:40800000, d:4014000000000000, n:FF}",
        // objectToBytes.getDesc());
    }

    @Test
    public void testStringBean() {
        StringBean strBean = ObjectUtils.populateBean(Collections.asMap("str1", "你好世界", "str2", "Hello"),
                StringBean.class);
        ObjectToBytes objectToBytes = new ObjectToBytes();
        byte[] bytes = objectToBytes.toBytes(strBean);
        StringBean strBean2 = new ObjectFromBytes().fromBytes(bytes, StringBean.class);
        Assert.assertEquals(strBean, strBean2);
        // Assert.assertEquals("{str1:E4BDA0E5A5BDE4B896E7958C, FixedString:Hello\0\0\0\0\0}", objectToBytes.getDesc());
    }

    @Test
    public void testCompBean() {
        StringBean strBean1 = ObjectUtils.populateBean(Collections.asMap("str1", "你好世界", "str2", "哈哈"),
                StringBean.class);
        StringBean strBean2 = ObjectUtils.populateBean(Collections.asMap("str1", "HelloWorld", "str2", "LOL"),
                StringBean.class);
        SimpleBean simpleBean = ObjectUtils.populateBean(Collections.asMap("string", "ABCDE", "stringFixed", "12345",
                "b", (byte) 0, "s", (short) 1, "i", 2, "l", 3, "f", 4, "d", 5, "n", true),
                SimpleBean.class);
        CompBean bean = ObjectUtils.populateBean(Collections.asMap("strLs", Arrays.asList("Hello", "世界"),
                "beanLs", Arrays.asList(strBean1, strBean2), "integer", 21, "simple", simpleBean), CompBean.class);

        ObjectToBytes objectToBytes = new ObjectToBytes();
        byte[] bytes = objectToBytes.toBytes(bean);
        CompBean bean2 = new ObjectFromBytes().fromBytes(bytes, CompBean.class);
        Assert.assertEquals(bean, bean2);
        // Assert.assertEquals("{strLs:[48656C6C6F, E4B896E7958C]," +
        // " beanLs:[{str1:E4BDA0E5A5BDE4B896E7958C, FixedString:哈哈\0\0\0\0}," +
        // " {str1:48656C6C6F576F726C64, FixedString:LOL\0\0\0\0\0\0\0}]," +
        // " integer:0x00 0x00 0x00 0x15, simple:{title:4142434445," +
        // " stringFixed:0x31 0x32 0x33 0x34 0x35 0xFF 0xFF 0xFF 0xFF 0xFF" +
        // " 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF, b:00," +
        // " s:0001, i:00000002, l:0000000000000003, f:40800000, d:4014000000000000, n:FF}}",
        // objectToBytes.getDesc());
    }

}
