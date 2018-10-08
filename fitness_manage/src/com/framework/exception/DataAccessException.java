package com.framework.exception;


public class DataAccessException  extends BaseException{

	private static final long serialVersionUID = 1L;

	public DataAccessException(int errorCode, String message) {
		super(errorCode, message);
	}

    public DataAccessException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}

