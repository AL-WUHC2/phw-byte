package com.ailk.phw.frombytes;

import java.util.ArrayList;
import java.util.List;

import com.ailk.phw.iface.FromBytes;
import com.ailk.phw.utils.FieldAnnoAttr;
import com.ailk.phw.utils.JCConvertUtils;

public class ListFromBytes extends FromBytes<List> {

    @Override
    public List fromBytes(byte[] b, int length, byte fill) {
        return null;
    }

    @Override
    public List fromBytes(byte[] bytes, int length, byte fill, Class genericType) {
        int size = JCConvertUtils.getArrayLen(bytes, getOffset(), getLenBytes());
        setOffset(getOffset() + JCConvertUtils.getArrayLenOffset(getLenBytes()));
        ArrayList list = new ArrayList(size);
        for (short i = 0; i < size; ++i) {
            FromBytes fromBytes = FromBytesUtils.getPrimitiveFromBytes(genericType);
            if (fromBytes != null) {
                FieldAnnoAttr attr = new FieldAnnoAttr(null, length, 1, fill, getCharset());
                list.add(fromBytesWithType(fromBytes.setLenBytes(getLenBytes()), bytes, attr, genericType));
                continue;
            }
            list.add(objectFromBytes(new ObjectFromBytes(), bytes, genericType));
        }
        return list;
    }
}
