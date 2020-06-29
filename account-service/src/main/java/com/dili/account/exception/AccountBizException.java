package com.dili.account.exception;

/**
 * @description： 业务异常
 *
 * @author ：WangBo
 * @time ：2019年6月22日下午4:22:55
 */
public class AccountBizException extends RuntimeException {
	/** 错误code码 */
	private String code;

	/** */
	private static final long serialVersionUID = 4248326393464652492L;

	/**
	 * Constructor for AopConfigException.
	 *
	 * @param msg the detail message
	 */
	public AccountBizException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public AccountBizException(String msg) {
		super(msg);
		this.code = ErrorCode.GENERAL_CODE + "";
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

	public String getCode() {
		return code;
	}
}
