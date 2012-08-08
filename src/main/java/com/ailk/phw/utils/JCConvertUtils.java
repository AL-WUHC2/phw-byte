package com.ailk.phw.utils;

import java.util.Arrays;

import org.apache.hadoop.hbase.util.Bytes;

public class JCConvertUtils {

    public static byte[] getLenArray(int length, int lenBytes) {
        if (lenBytes == 1) {
            return new byte[] { (byte) length };
        } else if (lenBytes == 2) {
            return Bytes.toBytes((short) length);
        } else {
            return Bytes.toBytes(length);
        }
    }

    public static int getArrayLen(byte[] bytes, int offset, int lenBytes) {
        if (lenBytes == 1) {
            return bytes[offset];
        } else if (lenBytes == 2) {
            return Bytes.toShort(bytes, offset);
        } else {
            return Bytes.toInt(bytes, offset);
        }
    }

    public static int getArrayLenOffset(int lenBytes) {
        if (lenBytes != 1 && lenBytes != 2) {
            return ConstantUtils.PrimitiveOffset.INT_OFFSET;
        }
        return lenBytes;
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

    public static byte[] addBytes(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static byte[] subBytes(final byte[] arr, int offset) {
        return subBytes(arr, offset, arr.length - offset);
    }

    public static byte[] subBytes(final byte[] arr, int offset, int length) {
        byte[] result = new byte[length];
        System.arraycopy(arr, offset, result, 0, length);
        return result;
    }

    public static byte[] splitFixedByteArray(final byte[] arr, int offset, int length, byte fill) {
        byte[] bytes = subBytes(arr, offset, length);
        int fixedLength = bytes.length;
        for (; fixedLength > 0; fixedLength--) {
            if (bytes[fixedLength - 1] != fill) {
                break;
            }
        }
        return subBytes(bytes, 0, fixedLength);
    }

}
