package com.dili.account.validator;

import com.dili.account.common.annotation.AtLeastOneNotNull;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/8/10 09:36
 * @Description:
 */
public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNull, Object> {

    private String[] includeFieldNames;

    @Override
    public void initialize(AtLeastOneNotNull constraintAnnotation) {
        includeFieldNames = constraintAnnotation.includeFieldNames();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        //只要有一个不为空即可
        return Arrays.stream(includeFieldNames)
                .map(beanWrapper::getPropertyValue)
                .anyMatch(Objects::nonNull);
    }
}
