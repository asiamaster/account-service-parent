package com.dili.account.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户电子登录账号
 * @author bob
 */
public class LoginUserDo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Long id; 
	/** 客户ID */
	private Long custormerId; 
	/** 登录名 */
	private String loginName; 
	/** 登陆密码 */
	private String password; 
	/** 昵称 */
	private String nickName; 
	/** 头像URL */
	private String headUrl; 
	/** 正常、锁定、注销 */
	private Integer state; 
	/** 禁用状态（管理员使用:1-启用2-禁用） */
	private Integer disableState; 
	/** 最近登录时间 */
	private LocalDateTime loginTime; 
	/** 数据版本号 */
	private Integer version; 
	/** 商户ID */
	private String firmId; 
	/** 商户名称 */
	private String firmName; 
	/** 创建时间 */
	private LocalDateTime createTime; 
	/** 修改时间 */
	private LocalDateTime modifiyTime; 
    /**
     * LoginUserEntity constructor
     */
	public LoginUserDo() {
		super();
	}

    /**
     * setter for 主键ID
     */
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * getter for 主键ID
     */
	public Long getId() {
		return id;
	}

    /**
     * setter for 客户ID
     */
	public void setCustormerId(Long custormerId) {
		this.custormerId = custormerId;
	}

    /**
     * getter for 客户ID
     */
	public Long getCustormerId() {
		return custormerId;
	}

    /**
     * setter for 登录名
     */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

    /**
     * getter for 登录名
     */
	public String getLoginName() {
		return loginName;
	}

    /**
     * setter for 登陆密码
     */
	public void setPassword(String password) {
		this.password = password;
	}

    /**
     * getter for 登陆密码
     */
	public String getPassword() {
		return password;
	}

    /**
     * setter for 昵称
     */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

    /**
     * getter for 昵称
     */
	public String getNickName() {
		return nickName;
	}

    /**
     * setter for 头像URL
     */
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

    /**
     * getter for 头像URL
     */
	public String getHeadUrl() {
		return headUrl;
	}

    /**
     * setter for 正常、锁定、注销
     */
	public void setState(Integer state) {
		this.state = state;
	}

    /**
     * getter for 正常、锁定、注销
     */
	public Integer getState() {
		return state;
	}

    /**
     * setter for 禁用状态（管理员使用:1-启用2-禁用）
     */
	public void setDisableState(Integer disableState) {
		this.disableState = disableState;
	}

    /**
     * getter for 禁用状态（管理员使用:1-启用2-禁用）
     */
	public Integer getDisableState() {
		return disableState;
	}

    /**
     * setter for 最近登录时间
     */
	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

    /**
     * getter for 最近登录时间
     */
	public LocalDateTime getLoginTime() {
		return loginTime;
	}

    /**
     * setter for 数据版本号
     */
	public void setVersion(Integer version) {
		this.version = version;
	}

    /**
     * getter for 数据版本号
     */
	public Integer getVersion() {
		return version;
	}

    /**
     * setter for 商户ID
     */
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}

    /**
     * getter for 商户ID
     */
	public String getFirmId() {
		return firmId;
	}

    /**
     * setter for 商户名称
     */
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

    /**
     * getter for 商户名称
     */
	public String getFirmName() {
		return firmName;
	}

    /**
     * setter for 创建时间
     */
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

    /**
     * getter for 创建时间
     */
	public LocalDateTime getCreateTime() {
		return createTime;
	}

    /**
     * setter for 修改时间
     */
	public void setModifiyTime(LocalDateTime modifiyTime) {
		this.modifiyTime = modifiyTime;
	}

    /**
     * getter for 修改时间
     */
	public LocalDateTime getModifiyTime() {
		return modifiyTime;
	}

    /**
     * LoginUserEntity.toString()
     */
    @Override
    public String toString() {
        return "LoginUserEntity{" +
               "id='" + id + '\'' +
               ", custormerId='" + custormerId + '\'' +
               ", loginName='" + loginName + '\'' +
               ", password='" + password + '\'' +
               ", nickName='" + nickName + '\'' +
               ", headUrl='" + headUrl + '\'' +
               ", state='" + state + '\'' +
               ", disableState='" + disableState + '\'' +
               ", loginTime='" + loginTime + '\'' +
               ", version='" + version + '\'' +
               ", firmId='" + firmId + '\'' +
               ", firmName='" + firmName + '\'' +
               ", createTime='" + createTime + '\'' +
               ", modifiyTime='" + modifiyTime + '\'' +
               '}';
    }

}
