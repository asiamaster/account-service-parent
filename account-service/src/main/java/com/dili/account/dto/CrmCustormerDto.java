package com.dili.account.dto;

import java.util.Date;

public class CrmCustormerDto {

	private String name; // 用户姓名

	private Long crmCustormerId; // CRM系统客户ID

	private Integer custormerType; // 客户类型（个人，对公）

	private Integer gender; // 性别

	private String credentialType; // 证件类型

	private String credentialNo; // 证件号

	private Date validityDate; // 证件有效期

	private Integer authStatus; // 认证状态（1已认证2未认证）

	private String mobile; // 手机号

	private String telphone; // 固定电话

	private String address; // 联系地址

	private String province; // 省

	private String city; // 市

	private String county; // 县

	private String businessCategory; // 主营业务

	private Integer customerArea; // 客户区域

	private String legalName; // 法人

	private String legalCredentialType; // 法人证件类型

	private String legalNo; // 法人证件号

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCrmCustormerId() {
		return crmCustormerId;
	}

	public void setCrmCustormerId(Long crmCustormerId) {
		this.crmCustormerId = crmCustormerId;
	}

	public Integer getCustormerType() {
		return custormerType;
	}

	public void setCustormerType(Integer custormerType) {
		this.custormerType = custormerType;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}

	public Integer getCustomerArea() {
		return customerArea;
	}

	public void setCustomerArea(Integer customerArea) {
		this.customerArea = customerArea;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalCredentialType() {
		return legalCredentialType;
	}

	public void setLegalCredentialType(String legalCredentialType) {
		this.legalCredentialType = legalCredentialType;
	}

	public String getLegalNo() {
		return legalNo;
	}

	public void setLegalNo(String legalNo) {
		this.legalNo = legalNo;
	}

}
