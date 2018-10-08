package com.framework.common;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * 
 * @todo TODO
 *
 * @author fhr
 * @dateTime 2016 2016-11-21 下午2:19:37
 */
public class Notify implements Serializable{
	
	 private static String type = "notify" ;
		private static final long serialVersionUID = 33L;

		/**
	     * 是否成功
	     */
	    private boolean success = true ;

	    /**
	     * 消息
	     */
	    private String message = "" ;

	    /**
	     * 错误码
	     */
	    private String retCode ;

	    /**
	     * 数据
	     */
	    private Object data;
	    
	    private Notify(boolean success, String message, Object data, String retCode) {
	        this.success = success;
	        this.message = message;
	        this.data = data;
	        this.retCode = retCode;
	    }
	    
	    public static JSONObject NotifyJson( String retCode,String message, Object data){
	    	JSONObject object = new JSONObject();
	    	
	    	object.put("type", Notify.type) ;
	    	object.put("success", true) ;
	    	object.put("retCode", retCode) ;
	    	object.put("message", message) ;
	    	object.put("data", data) ;
	    	object.put("retCode", retCode) ;
	    	
	    	return object ;
	    }

	    public static String NotifyJsonStr( String retCode,String message, Object data){
	    	JSONObject object = new JSONObject();
	    	
	    	object.put("type", Notify.type) ;
	    	object.put("success", true) ;
	    	object.put("retCode", retCode) ;
	    	object.put("message", message) ;
	    	object.put("data", data) ;
	    	object.put("retCode", retCode) ;
	    	
	    	return object.toString() ;
	    }
	     
}
