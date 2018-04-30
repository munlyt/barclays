package com.barclays.bms.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -139258973465596901L;
	private String errorCode;
	private String errorMessage;

	public BusinessException(String errorMessage) {
		// Call constructor of parent Exception
		super(errorMessage);
	}

	public BusinessException(String errorCode, String errorMessage) {
		super();
		this.setErrorCode(errorCode);
		this.setErrorMessage(errorMessage);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
