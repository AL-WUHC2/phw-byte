package com.ailk.phw.utils;

public class ConstantUtils {

    public static final String DEFAULT_FIELD_NAME = "";

    public static final int DEFAULT_LEN_BYTES = 1;

    public static final int DEFAULT_FIELD_LENGTH = -1;

    public static final byte DEFAULT_FIELD_FILL = (byte) 0xFF;

    public static final String CHARSET_UNICODE = "UTF-16BE";

    public static final String CHARSET_UTF8 = "UTF-8";

    public static final String CHARSET_ASCII = "US-ASCII";

    public class PrimitiveOffset {

        public static final int BYTE_OFFSET = 1;

        public static final int SHORT_OFFSET = 2;

        public static final int INT_OFFSET = 4;

        public static final int LONG_OFFSET = 8;

        public static final int FLOAT_OFFSET = 4;

        public static final int DOUBLE_OFFSET = 8;

        public static final int BOOLEAN_OFFSET = 1;

    }

    public class FillByte {

        public static final byte EMPTY_BYTE = (byte) '\0';

        public static final byte FULL_BYTE = (byte) 0xFF;

    }

}
