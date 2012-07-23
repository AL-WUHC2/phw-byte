package com.ailk.phw.utils;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

import com.ailk.phw.enums.JCExpType;
import com.google.common.base.Throwables;

public class DescUtils {

    public static void appends(StringBuilder builder, String... strings) {
        for (String str : strings) {
            builder.append(str);
        }
    }

    public static String toDesc(byte[] bytes, JCExpType type, String charset) {
        if (JCExpType.Hex == type) {
            return DescHexUtils.toDesc(bytes, charset);
        } else if (JCExpType.ASCII == type) {
            return DescASCIIUtils.toDesc(bytes, charset);
        } else {
            return DescOctetUtils.toDesc(bytes, charset);
        }
    }

    public static byte getDefaultFill(JCExpType type) {
        return JCExpType.Octet == type ? ConstantUtils.FillByte.EMPTY_BYTE : ConstantUtils.FillByte.FULL_BYTE;
    }

    static class DescHexUtils {
        public static String toDesc(byte[] bytes, String charset) {
            return DatatypeConverter.printHexBinary(bytes);
        }
    }

    static class DescOctetUtils {
        public static String toDesc(byte[] bytes, String charset) {
            try {
                return new String(bytes, charset);
            } catch (UnsupportedEncodingException e) {
                throw Throwables.propagate(e);
            }
        }
    }

    static class DescASCIIUtils {
        private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

        public static String toDesc(byte[] bytes, String charset) {
            StringBuilder r = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                r.append(r.length() > 0 ? " 0x" : "0x");
                r.append(hexCode[b >> 4 & 0xF]);
                r.append(hexCode[(b & 0xF)]);
            }
            return r.toString();
        }
    }

}
