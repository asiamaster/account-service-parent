package com.dili.account.validator;

import com.dili.account.common.annotation.SqlXss;
import com.dili.account.common.xss.SQLFilter;
import com.dili.account.exception.AccountBizException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/8/10 09:36
 * @Description: 校验sql xss攻击
 */
public class SqlXssValidator implements ConstraintValidator<SqlXss, String> {


    @Override
    public void initialize(SqlXss sqlXss) {

    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            SQLFilter.sqlInject(value);
            return true;
        } catch (AccountBizException e) {
            return false;
        }
    }
}
