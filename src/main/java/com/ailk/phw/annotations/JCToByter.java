package com.ailk.phw.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ailk.phw.toBytes.ObjectToBytes;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JCToByter {
    public Class<? extends ObjectToBytes> value();
}
