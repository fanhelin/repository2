package com.framework.exception;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
     * 错误码
     */
    private Integer errorCode;
    
    public BaseException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "errorCode: " + errorCode + ", " + super.toString();
    }
}
