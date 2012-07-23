package com.ailk.phw.utils;

import java.util.Arrays;

public class JCConvertUtils {

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
