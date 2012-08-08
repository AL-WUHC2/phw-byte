package com.ailk.phw.tobytes;

import java.util.List;

import com.ailk.phw.iface.ToBytes;
import com.ailk.phw.utils.FieldAnnoAttr;
import com.ailk.phw.utils.JCConvertUtils;

public class ListToBytes extends ToBytes<List> {

    @Override
    public byte[] toBytes(List list, int length, byte fill) {
        StringBuilder builder = new StringBuilder();
        int size = list.size();
        byte[] result = JCConvertUtils.getLenArray(size, getLenBytes());
        builder.append("[");
        for (Object obj : list) {
            ToBytes toBytes = ToBytesUtils.getPrimitiveToBytes(obj.getClass());
            if (toBytes != null) {
                FieldAnnoAttr attr = new FieldAnnoAttr(getType(), length, getLenBytes(), fill, getCharset());
                result = JCConvertUtils.addBytes(result, toBytesWithAttr(toBytes, obj, attr, builder, ", "));
                continue;
            }
            result = JCConvertUtils.addBytes(result, objectToBytes(new ObjectToBytes(), obj, builder, ", "));
        }
        if (size > 0) {
            builder.replace(builder.length() - 2, builder.length(), "]");
        } else {
            builder.append("]");
        }
        setDesc(builder.toString());
        return result;
    }

}
