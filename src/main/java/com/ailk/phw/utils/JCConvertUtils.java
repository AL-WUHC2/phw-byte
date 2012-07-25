package com.ailk.phw.utils;

import java.util.Arrays;

import org.apache.hadoop.hbase.util.Bytes;

import com.ailk.phw.enums.JCLenType;

public class JCConvertUtils {

    public static byte[] getLenArray(int length, JCLenType type) {
        if (type == JCLenType.Byte) {
            return new byte[] { (byte) length };
        } else if (type == JCLenType.Short) {
            return Bytes.toBytes((short) length);
        } else {
            return Bytes.toBytes(length);
        }
    }

    public static int getArrayLen(byte[] bytes, int offset, JCLenType type) {
        if (type == JCLenType.Byte) {
            return bytes[offset];
        } else if (type == JCLenType.Short) {
            return Bytes.toShort(bytes, offset);
        } else {
            return Bytes.toInt(bytes, offset);
        }
    }

    public static int getArrayLenOffset(JCLenType type) {
        if (type == JCLenType.Byte) {
            return ConstantUtils.PrimitiveOffset.BYTE_OFFSET;
        } else if (type == JCLenType.Short) {
            return ConstantUtils.PrimitiveOffset.SHORT_OFFSET;
        } else {
            return ConstantUtils.PrimitiveOffset.INT_OFFSET;
        }
    }

    public static byte[] toBytesArray(byte[] bytes, int length, byte fill) {
        if (length == ConstantUtils.DEFAULT_FIELD_LENGTH) {
            return bytes;
        }
        byte[] result = new byte[length];
        Arrays.fill(result, fill);
        System.arraycopy(bytes, 0, result, 0, bytes.length < length ? bytes.length : length);
        return result;
    }

    public static byte[] mergeByteArray(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static byte[] splitByteArray(final byte[] arr, int offset) {
        return splitByteArray(arr, offset, arr.length - offset);
    }

    public static byte[] splitByteArray(final byte[] arr, int offset, int length) {
        byte[] result = new byte[length];
        System.arraycopy(arr, offset, result, 0, length);
        return result;
    }

    public static byte[] splitFixedByteArray(final byte[] arr, int offset, int length, byte fill) {
        byte[] bytes = splitByteArray(arr, offset, length);
        int fixedLength = bytes.length;
        for (; fixedLength > 0; fixedLength--) {
            if (bytes[fixedLength - 1] != fill) {
                break;
            }
        }
        return splitByteArray(bytes, 0, fixedLength);
    }

}
