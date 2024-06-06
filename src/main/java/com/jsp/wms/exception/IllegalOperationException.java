package com.jsp.wms.exception;


public class IllegalOperationException  extends RuntimeException{

	private String message;

	public String getMessage() {
		return message;
	}

	public IllegalOperationException(String message) {
		super();
		this.message = message;
	}
	
	

}
