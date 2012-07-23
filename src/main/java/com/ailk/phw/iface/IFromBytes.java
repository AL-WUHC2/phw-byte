package com.ailk.phw.iface;

public interface IFromBytes<T> {

    public T fromBytes(byte[] b);

    public T fromBytes(byte[] b, int length);

    public T fromBytes(byte[] b, int length, byte fill);

}
