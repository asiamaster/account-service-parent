package com.dili.account.common.annotation;

import com.dili.account.validator.AtLeastFieldNotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验至少类中有一个字段不为空
 * @Auther: miaoguoxin
 * @Date: 2020/8/10 09:44
 */
@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AtLeastFieldNotNullValidator.class)
public @interface AtLeastFieldNotNull {

    /**
    * 需要校验不能为空的字段名
    * @param
    * @return
    * @author miaoguoxin
    * @date 2020/8/10
    */
    String[] includeFieldNames() default {};

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
