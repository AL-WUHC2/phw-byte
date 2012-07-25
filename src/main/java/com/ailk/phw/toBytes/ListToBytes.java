package com.ailk.phw.toBytes;

import java.util.List;

import com.ailk.phw.iface.ToBytes;
import com.ailk.phw.utils.FieldAttrUtils;
import com.ailk.phw.utils.JCConvertUtils;

public class ListToBytes extends ToBytes<List> {

    @Override
    public byte[] toBytes(List list, int length, byte fill) {
        StringBuilder builder = new StringBuilder();
        int size = list.size();
        byte[] result = JCConvertUtils.getLenArray(size, getLenType());
        builder.append("[");
        for (Object obj : list) {
            ToBytes toBytes = ToBytesUtils.getToBytes(obj.getClass());
            if (toBytes != null) {
                FieldAttrUtils attr = new FieldAttrUtils(getType(), getLenType(), length, fill, getCharset());
                result = JCConvertUtils.mergeByteArray(result, toBytesWithAttr(toBytes, obj, attr, builder, ", "));
                continue;
            }
            result = JCConvertUtils.mergeByteArray(result, objectToBytes(new ObjectToBytes(), obj, builder, ", "));
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
