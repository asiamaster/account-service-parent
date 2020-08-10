package com.dili.account.validator;

import cn.hutool.core.util.ReflectUtil;
import com.dili.account.common.annotation.AtLeastFieldNotNull;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/8/10 09:36
 * @Description:
 */
public class AtLeastFieldNotNullValidator implements ConstraintValidator<AtLeastFieldNotNull, Object> {

    private String[] includeFieldNames;

    @Override
    public void initialize(AtLeastFieldNotNull constraintAnnotation) {
        includeFieldNames = constraintAnnotation.includeFieldNames();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        //没有include就校验所有字段
        if (includeFieldNames == null || includeFieldNames.length == 0) {
            Field[] fields = ReflectUtil.getFields(value.getClass());
            return Arrays.stream(fields)
                    .map(field -> beanWrapper.getPropertyValue(field.getName()))
                    .anyMatch(Objects::nonNull);
        }
        //只要有一个不为空即可
        return Arrays.stream(includeFieldNames)
                .map(beanWrapper::getPropertyValue)
                .anyMatch(Objects::nonNull);
    }
}
