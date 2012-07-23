package com.ailk.phw.fromBytes;

import java.util.ArrayList;
import java.util.List;

import com.ailk.phw.iface.FromBytes;

public class ListFromBytes extends FromBytes<List> {

    @Override
    public List fromBytes(byte[] b, int length, byte fill) {
        return null;
    }

    @Override
    public List fromBytes(byte[] bytes, int length, byte fill, Class genericType) {
        byte size = bytes[getOffset()];
        setOffset(getOffset() + 1);
        ArrayList list = new ArrayList(size);
        for (short i = 0; i < size; ++i) {
            FromBytes fromBytes = FromBytesUtils.getFromBytes(genericType);
            if (fromBytes != null) {
                list.add(fromBytesWithType(fromBytes, bytes, length, fill, genericType));
                continue;
            }
            list.add(objectFromBytes(new ObjectFromBytes(), bytes, genericType));
        }
        return list;
    }
}
