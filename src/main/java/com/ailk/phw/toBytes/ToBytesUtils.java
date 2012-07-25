package com.ailk.phw.toBytes;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.ClassUtils;
import org.apache.hadoop.hbase.util.Bytes;

import com.ailk.phw.iface.ToBytes;
import com.ailk.phw.utils.ConstantUtils;
import com.ailk.phw.utils.DescUtils;
import com.ailk.phw.utils.JCConvertUtils;
import com.google.common.base.Throwables;

public class ToBytesUtils<T> {

    private static HashMap<Class, ToBytes> convertMap = new HashMap();

    public static <T> ToBytes<T> getToBytes(Class<T> clazz) {
        if (clazz.isPrimitive()) {
            return convertMap.get(ClassUtils.primitiveToWrapper(clazz));
        }
        return convertMap.get(clazz);
    }

    public static void addToBytes(Class clazz, ToBytes toByter) {
        convertMap.put(clazz, toByter);
    }

    static {
        convertMap.put(String.class, new ToBytes<String>() {
            public byte[] toBytes(String obj, int length, byte fill) {
                byte[] objBytes = new byte[0];
                try {
                    objBytes = obj.getBytes(getCharset());
                } catch (UnsupportedEncodingException e) {
                    throw Throwables.propagate(e);
                }
                objBytes = JCConvertUtils.toBytesArray(objBytes, length, fill);
                setDesc(DescUtils.toDesc(objBytes, getType(), getCharset()));
                if (length == ConstantUtils.DEFAULT_FIELD_LENGTH) {
                    int len = objBytes.length;
                    objBytes = JCConvertUtils.mergeByteArray(JCConvertUtils.getLenArray(len, getLenType()), objBytes);
                }
                return objBytes;
            }
        });

        convertMap.put(Byte.class, new ToBytes<Byte>() {
            public byte[] toBytes(Byte obj, int length, byte fill) {
                byte[] objBytes = new byte[] { obj };
                setDesc(DescUtils.toDesc(objBytes, getType(), getCharset()));
                return JCConvertUtils.toBytesArray(objBytes, length, fill);
            }
        });

        convertMap.put(Short.class, new ToBytes<Short>() {
            public byte[] toBytes(Short obj, int length, byte fill) {
                byte[] objBytes = Bytes.toBytes(obj);
                setDesc(DescUtils.toDesc(objBytes, getType(), getCharset()));
                return JCConvertUtils.toBytesArray(objBytes, length, fill);
            }
        });

        convertMap.put(Integer.class, new ToBytes<Integer>() {
            public byte[] toBytes(Integer obj, int length, byte fill) {
                byte[] objBytes = Bytes.toBytes(obj);
                setDesc(DescUtils.toDesc(objBytes, getType(), getCharset()));
                return JCConvertUtils.toBytesArray(objBytes, length, fill);
            }
        });

        convertMap.put(Long.class, new ToBytes<Long>() {
            public byte[] toBytes(Long obj, int length, byte fill) {
                byte[] objBytes = Bytes.toBytes(obj);
                setDesc(DescUtils.toDesc(objBytes, getType(), getCharset()));
                return JCConvertUtils.toBytesArray(objBytes, length, fill);
            }
        });

        convertMap.put(Float.class, new ToBytes<Float>() {
            public byte[] toBytes(Float obj, int length, byte fill) {
                byte[] objBytes = Bytes.toBytes(obj);
                setDesc(DescUtils.toDesc(objBytes, getType(), getCharset()));
                return JCConvertUtils.toBytesArray(objBytes, length, fill);
            }
        });

        convertMap.put(Double.class, new ToBytes<Double>() {
            public byte[] toBytes(Double obj, int length, byte fill) {
                byte[] objBytes = Bytes.toBytes(obj);
                setDesc(DescUtils.toDesc(objBytes, getType(), getCharset()));
                return JCConvertUtils.toBytesArray(objBytes, length, fill);
            }
        });

        convertMap.put(Boolean.class, new ToBytes<Boolean>() {
            public byte[] toBytes(Boolean obj, int length, byte fill) {
                byte[] objBytes = Bytes.toBytes(obj);
                setDesc(DescUtils.toDesc(objBytes, getType(), getCharset()));
                return JCConvertUtils.toBytesArray(objBytes, length, fill);
            }
        });
    }

}
