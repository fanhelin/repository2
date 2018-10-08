package com.framework.exception;

public class RunTimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/**
     * 错误码
     */
    private Integer errorCode;

    public RunTimeException(String message) {
        super(message);
    }

    public RunTimeException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RunTimeException(Integer errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return (errorCode != null ? "errorCode: " + errorCode + ", " : "") + super.toString();
    }
}
