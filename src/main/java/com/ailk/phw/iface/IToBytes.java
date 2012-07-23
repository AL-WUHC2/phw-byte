package com.ailk.phw.iface;

public interface IToBytes<T> {

    public byte[] toBytes(T obj);

    public byte[] toBytes(T obj, int length);

    public byte[] toBytes(T obj, int length, byte fill);

}
