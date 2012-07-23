package com.ailk.phw.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ailk.phw.enums.JCExpType;
import com.ailk.phw.utils.ConstantUtils;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JCBytes {

    public String name() default ConstantUtils.DEFAULT_FIELD_NAME;

    public JCExpType type() default JCExpType.Hex;

    public int length() default ConstantUtils.DEFAULT_FIELD_LENGTH;

    public boolean useDefaultByte() default true;

    public byte fillByte() default ConstantUtils.DEFAULT_FIELD_FILL;

    public String charset() default ConstantUtils.CHARSET_UTF8;

}
