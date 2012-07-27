package com.ailk.phw.fromBytes;

import java.util.ArrayList;
import java.util.List;

import com.ailk.phw.enums.JCLenType;
import com.ailk.phw.iface.FromBytes;
import com.ailk.phw.utils.FieldAttrUtils;
import com.ailk.phw.utils.JCConvertUtils;

public class ListFromBytes extends FromBytes<List> {

    @Override
    public List fromBytes(byte[] b, int length, byte fill) {
        return null;
    }

    @Override
    public List fromBytes(byte[] bytes, int length, byte fill, Class genericType) {
        int size = JCConvertUtils.getArrayLen(bytes, getOffset(), getLenType());
        setOffset(getOffset() + JCConvertUtils.getArrayLenOffset(getLenType()));
        ArrayList list = new ArrayList(size);
        for (short i = 0; i < size; ++i) {
            FromBytes fromBytes = FromBytesUtils.getFromBytes(genericType);
            if (fromBytes != null) {
                fromBytes.setLenType(getLenType());
                FieldAttrUtils attr = new FieldAttrUtils(null, JCLenType.Byte, length, fill, getCharset());
                list.add(fromBytesWithType(fromBytes, bytes, attr, genericType));
                continue;
            }
            list.add(objectFromBytes(new ObjectFromBytes(), bytes, genericType));
        }
        return list;
    }
}
