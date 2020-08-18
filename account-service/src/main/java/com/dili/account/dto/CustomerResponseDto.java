package com.dili.account.dto;

import java.io.Serializable;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/17 10:50
 * @Description: 客户信息
 */
public class CustomerResponseDto implements Serializable {
    /** */
	private static final long serialVersionUID = 166080921451941277L;
	private Long userInfoId;
    /** 用户姓名 */
    private String name;
    /** 客户类型（个人，对公） */
    private Integer customerType;
    /** 证件号 */
    private String credentialNo;
    /** 手机号 */
    private String mobile;
    /** 1-未认证 2已认证 */
    private Integer authStatus;

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }
}
