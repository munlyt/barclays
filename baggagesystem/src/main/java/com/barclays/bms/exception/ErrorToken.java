package com.barclays.bms.exception;

/**
 * This class holds all the Error Codes and messages for handling Business
 * exceptions
 * 
 * @author Chandra Panday
 *
 */
public enum ErrorToken {
	ERROR_DEF("ERROR_DEF", "Something went wrong. Please contact the administration."),
	ERROR_001("ERROR_001", "The input xml has missing elements %s"), 
	ERROR_002("ERROR_002", "Gate info is missing for bag %s"),
	ERROR_003("ERROR_003", "The baggage %s cannot be routed to its destination.");

	private String code;
	private String message;

	private ErrorToken(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
