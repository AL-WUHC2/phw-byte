package com.ailk.phw.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ailk.phw.fromBytes.ObjectFromBytes;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JCFromByter {
    public Class<? extends ObjectFromBytes> value();
}
