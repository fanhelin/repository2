package com.wx.entity.common;

import com.util.FunHelper;
/**
 * 
 * @todo 日志实体
 *
 * @author fhr
 * @dateTime 2016 2016-11-9 上午11:50:30
 */
public class LogBean {
	Integer id; 
	
	String module; 
	
	String datetime = FunHelper.getCurrentTime("-", " ", ":",true);
	
	Integer use_id; 
	
	String target; 
	
	String result; 
	
	Integer log_type ;
	
	String userName ;
	
	String reason ;
	
	String event ;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {

		this.datetime = datetime;
	}

	public Integer getUse_id() {
		return use_id;
	}

	public void setUse_id(Integer use_id) {
		this.use_id = use_id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getLog_type() {
		return log_type;
	}

	public void setLog_type(Integer log_type) {
		this.log_type = log_type;
	}

  
}
