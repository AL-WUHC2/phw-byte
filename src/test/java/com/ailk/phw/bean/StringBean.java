package com.ailk.phw.bean;

import com.ailk.phw.annotations.JCBytes;
import com.ailk.phw.enums.JCExpType;

public class StringBean {

    private String str1;

    @JCBytes(name = "FixedString", type = JCExpType.Octet, length = 10)
    private String str2;

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr2() {
        return str2;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (str1 == null ? 0 : str1.hashCode());
        result = prime * result + (str2 == null ? 0 : str2.hashCode());
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
        StringBean other = (StringBean) obj;
        if (str1 == null) {
            if (other.str1 != null) {
                return false;
            }
        } else if (!str1.equals(other.str1)) {
            return false;
        }
        if (str2 == null) {
            if (other.str2 != null) {
                return false;
            }
        } else if (!str2.equals(other.str2)) {
            return false;
        }
        return true;
    }

}
