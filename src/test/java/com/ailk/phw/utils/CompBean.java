package com.ailk.phw.utils;

import java.util.List;

import com.ailk.phw.annotations.JCBytes;
import com.ailk.phw.enums.JCExpType;

public class CompBean {

    private List<String> strLs;

    private List<StringBean> beanLs;

    @JCBytes(type = JCExpType.ASCII)
    private int integer;

    private SimpleBean simple;

    public void setStrLs(List<String> strLs) {
        this.strLs = strLs;
    }

    public List<String> getStrLs() {
        return strLs;
    }

    public void setBeanLs(List<StringBean> beanLs) {
        this.beanLs = beanLs;
    }

    public List<StringBean> getBeanLs() {
        return beanLs;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public int getInteger() {
        return integer;
    }

    public void setSimple(SimpleBean simple) {
        this.simple = simple;
    }

    public SimpleBean getSimple() {
        return simple;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (beanLs == null ? 0 : beanLs.hashCode());
        result = prime * result + integer;
        result = prime * result + (simple == null ? 0 : simple.hashCode());
        result = prime * result + (strLs == null ? 0 : strLs.hashCode());
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
        CompBean other = (CompBean) obj;
        if (beanLs == null) {
            if (other.beanLs != null) {
                return false;
            }
        } else if (!beanLs.equals(other.beanLs)) {
            return false;
        }
        if (integer != other.integer) {
            return false;
        }
        if (simple == null) {
            if (other.simple != null) {
                return false;
            }
        } else if (!simple.equals(other.simple)) {
            return false;
        }
        if (strLs == null) {
            if (other.strLs != null) {
                return false;
            }
        } else if (!strLs.equals(other.strLs)) {
            return false;
        }
        return true;
    }

}
