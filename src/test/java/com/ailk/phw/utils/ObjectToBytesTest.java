package com.ailk.phw.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.ailk.phw.bean.CompBean;
import com.ailk.phw.bean.SimpleBean;
import com.ailk.phw.bean.StringBean;
import com.ailk.phw.bean.TestBean;
import com.ailk.phw.fromBytes.ObjectFromBytes;
import com.ailk.phw.toBytes.ObjectToBytes;

public class ObjectToBytesTest {

    @Test
    public void testTestBean() {
        TestBean test = new TestBean();
        test.setMessage("你好世界");
        test.setMessages(Arrays.asList("你好", "世界"));
        ObjectToBytes toBytes = new ObjectToBytes();
        byte[] bytes = toBytes.toBytes(test);
        // Assert.assertEquals("{message:E4BDA0E5A5BDE4B896E7958C, messages:[E4BDA0E5A5BD, E4B896E7958C]}",
        // toBytes.getDesc());
        ObjectFromBytes fromBytes = new ObjectFromBytes();
        TestBean test2 = fromBytes.fromBytes(bytes, TestBean.class);
        Assert.assertEquals(test, test2);
    }

    @Test
    public void testSimpleBean() {
        SimpleBean simpleBean = new SimpleBean();
        simpleBean.setString("ABCDE");
        simpleBean.setStringFixed("12345");
        simpleBean.setB((byte) 0);
        simpleBean.setS((short) 1);
        simpleBean.setI(2);
        simpleBean.setL(3);
        simpleBean.setF(4);
        simpleBean.setD(5);
        simpleBean.setN(true);
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
        StringBean strBean = new StringBean();
        strBean.setStr1("你好世界");
        strBean.setStr2("哈哈");
        ObjectToBytes objectToBytes = new ObjectToBytes();
        byte[] bytes = objectToBytes.toBytes(strBean);
        StringBean strBean2 = new ObjectFromBytes().fromBytes(bytes, StringBean.class);
        Assert.assertEquals(strBean, strBean2);
        // Assert.assertEquals("{str1:E4BDA0E5A5BDE4B896E7958C, FixedString:哈哈\0\0\0\0}", objectToBytes.getDesc());
    }

    @Test
    public void testCompBean() {
        StringBean strBean1 = new StringBean();
        strBean1.setStr1("你好世界");
        strBean1.setStr2("哈哈");
        StringBean strBean2 = new StringBean();
        strBean2.setStr1("HelloWorld");
        strBean2.setStr2("LOL");
        SimpleBean simpleBean = new SimpleBean();
        simpleBean.setString("ABCDE");
        simpleBean.setStringFixed("12345");
        simpleBean.setB((byte) 0);
        simpleBean.setS((short) 1);
        simpleBean.setI(2);
        simpleBean.setL(3);
        simpleBean.setF(4);
        simpleBean.setD(5);
        simpleBean.setN(true);
        CompBean bean = new CompBean();
        bean.setStrLs(Arrays.asList("Hello", "世界"));
        bean.setBeanLs(Arrays.asList(strBean1, strBean2));
        bean.setInteger(21);
        bean.setSimple(simpleBean);

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
