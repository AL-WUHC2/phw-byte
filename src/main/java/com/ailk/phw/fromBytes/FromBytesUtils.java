package com.ailk.phw.fromBytes;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.ClassUtils;
import org.apache.hadoop.hbase.util.Bytes;

import com.ailk.phw.iface.FromBytes;
import com.ailk.phw.utils.ConstantUtils;
import com.ailk.phw.utils.JCConvertUtils;
import com.google.common.base.Throwables;

public class FromBytesUtils<T> {

    private static HashMap<Class, FromBytes> convertMap = new HashMap();

    public static <T> FromBytes<T> getFromBytes(Class<T> clazz) {
        if (clazz.isPrimitive()) {
            return convertMap.get(ClassUtils.primitiveToWrapper(clazz));
        }
        return convertMap.get(clazz);
    }

    public static void addFromBytes(Class clazz, FromBytes toByter) {
        convertMap.put(clazz, toByter);
    }

    static {
        convertMap.put(String.class, new FromBytes<String>() {
            public String fromBytes(byte[] b, int length, byte fill) {
                if (length == ConstantUtils.DEFAULT_FIELD_LENGTH) {
                    length = b[getOffset()];
                    setOffset(getOffset() + 1);
                }
                try {
                    byte[] bytes = JCConvertUtils.splitFixedByteArray(b, getOffset(), length, fill);
                    String result = new String(bytes, getCharset());
                    setOffset(getOffset() + length);
                    return result;
                } catch (UnsupportedEncodingException e) {
                    throw Throwables.propagate(e);
                }
            }
        });

        convertMap.put(Byte.class, new FromBytes<Byte>() {
            public Byte fromBytes(byte[] b, int length, byte fill) {
                Byte result = b[getOffset()];
                setOffset(getOffset() + ConstantUtils.PrimitiveOffset.BYTE_OFFSET);
                return result;
            }
        });

        convertMap.put(Short.class, new FromBytes<Short>() {
            public Short fromBytes(byte[] b, int length, byte fill) {
                Short result = Bytes.toShort(b, getOffset());
                setOffset(getOffset() + ConstantUtils.PrimitiveOffset.SHORT_OFFSET);
                return result;
            }
        });

        convertMap.put(Integer.class, new FromBytes<Integer>() {
            public Integer fromBytes(byte[] b, int length, byte fill) {
                Integer result = Bytes.toInt(b, getOffset());
                setOffset(getOffset() + ConstantUtils.PrimitiveOffset.INT_OFFSET);
                return result;
            }
        });

        convertMap.put(Long.class, new FromBytes<Long>() {
            public Long fromBytes(byte[] b, int length, byte fill) {
                Long result = Bytes.toLong(b, getOffset());
                setOffset(getOffset() + ConstantUtils.PrimitiveOffset.LONG_OFFSET);
                return result;
            }
        });

        convertMap.put(Float.class, new FromBytes<Float>() {
            public Float fromBytes(byte[] b, int length, byte fill) {
                Float result = Bytes.toFloat(b, getOffset());
                setOffset(getOffset() + ConstantUtils.PrimitiveOffset.FLOAT_OFFSET);
                return result;
            }
        });

        convertMap.put(Double.class, new FromBytes<Double>() {
            public Double fromBytes(byte[] b, int length, byte fill) {
                Double result = Bytes.toDouble(b, getOffset());
                setOffset(getOffset() + ConstantUtils.PrimitiveOffset.DOUBLE_OFFSET);
                return result;
            }
        });

        convertMap.put(Boolean.class, new FromBytes<Boolean>() {
            public Boolean fromBytes(byte[] b, int length, byte fill) {
                Boolean result = Bytes.toBoolean(new byte[] { b[getOffset()] });
                setOffset(getOffset() + ConstantUtils.PrimitiveOffset.BOOLEAN_OFFSET);
                return result;
            }
        });
    }

}
