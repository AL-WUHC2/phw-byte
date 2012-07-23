package com.ailk.phw.bean;

import com.ailk.phw.annotations.JCBytes;
import com.ailk.phw.enums.JCExpType;

public class SimpleBean {

    @JCBytes(name = "title")
    private String string;
    @JCBytes(type = JCExpType.ASCII, length = 20)
    private String stringFixed;
    private byte b;
    private short s;
    private int i;
    private long l;
    private float f;
    private double d;
    private boolean n;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getStringFixed() {
        return stringFixed;
    }

    public void setStringFixed(String stringFixed) {
        this.stringFixed = stringFixed;
    }

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public short getS() {
        return s;
    }

    public void setS(short s) {
        this.s = s;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public long getL() {
        return l;
    }

    public void setL(long l) {
        this.l = l;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public boolean isN() {
        return n;
    }

    public void setN(boolean n) {
        this.n = n;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + b;
        long temp;
        temp = Double.doubleToLongBits(d);
        result = prime * result + (int) (temp ^ temp >>> 32);
        result = prime * result + Float.floatToIntBits(f);
        result = prime * result + i;
        result = prime * result + (int) (l ^ l >>> 32);
        result = prime * result + (n ? 1231 : 1237);
        result = prime * result + s;
        result = prime * result + (string == null ? 0 : string.hashCode());
        result = prime * result + (stringFixed == null ? 0 : stringFixed.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SimpleBean other = (SimpleBean) obj;
        if (b != other.b) {
            return false;
        }
        if (Double.doubleToLongBits(d) != Double.doubleToLongBits(other.d)) {
            return false;
        }
        if (Float.floatToIntBits(f) != Float.floatToIntBits(other.f)) {
            return false;
        }
        if (i != other.i) {
            return false;
        }
        if (l != other.l) {
            return false;
        }
        if (n != other.n) {
            return false;
        }
        if (s != other.s) {
            return false;
        }
        if (string == null) {
            if (other.string != null) {
                return false;
            }
        } else if (!string.equals(other.string)) {
            return false;
        }
        if (stringFixed == null) {
            if (other.stringFixed != null) {
                return false;
            }
        } else if (!stringFixed.equals(other.stringFixed)) {
            return false;
        }
        return true;
    }

}
