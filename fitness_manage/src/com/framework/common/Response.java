package com.framework.common;

import java.io.Serializable;

import com.framework.exception.BaseException;
import com.framework.exception.RunTimeException;


/**
 * 消息返回类
 * @author fhr
 *
 */
public class Response implements Serializable {
    private String type = "response" ;
	private static final long serialVersionUID = 1L;

	/**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 消息
     */
    private String message = "";

    /**
     * 错误码
     */
    private String retCode;

    /**
     * 数据
     */
    private Object data;


    private Response(boolean success, String message, Object data, String retCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.retCode = retCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    /**
     * 返回SSSResponse实例
     * @return SSSResponse
     */
    public static Response success() {
        return new Response(true, "", null, null);
    }

    /**
     * 返回SSSResponse实例
     * @param message 成功信息
     * @return
     */
    public static Response success(String message) {
        return new Response(true, message, null, null);
    }

    /**
     * 返回SSSResponse实例
     * @param message 成功信息
     * @param data 数据
     * @return
     */
    public static Response success(String message, Object data) {
        return new Response(true, message, data, null);
    }
    
    /**
     * 
     * @param message 提示消息
     * @param data 数据
     * @param retCode 成功
     * @return
     */
    public static Response success(String message, Object data,String retCode) {
        return new Response(true, message, data, retCode);
    }


    /**
     * 返回SSSResponse实例
     * @param message 失败信息
     * @return
     */
    public static Response error(String message) {
        return new Response(false, message, null, null);
    }

    /**
     * 返回SSSResponse实例
     * @param message 失败信息
     * @param data 数据
     * @return
     */
    public static Response error(String message, Object data) {
        return new Response(false, message, data, null);
    }
    
    /**
     * 
     * @param message 提示消息
     * @param data 业务数据
     * @param retCode 返回码
     * @return
     */
    public static Response error(String message, Object data,String retCode) {
        return new Response(false, message, data, retCode);
    }


    /**
     * 返回SSSResponse实例
     * @param sssBaseException 异常
     * @return
     */
    public static Response error(BaseException baseException) {
        return new Response(false, baseException.getMessage(), null, ""+baseException.getErrorCode());
    }

    /**
     * 返回SSSResponse实例
     * @param sssRunTimeException 异常
     * @return
     */
    public static Response error(RunTimeException runTimeException) {
        return new Response(false, runTimeException.getMessage(), null, ""+runTimeException.getErrorCode());
    }

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
    
    
}
