package com.framework.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.framework.common.StaticFinal;
import com.framework.log.LogGather;
import com.wx.entity.common.LogBean;
import com.wx.entity.sys.User;

/**
 * controller基类，controller的基本方法可以写在该类，子controller继承该类。 继承日志功能和功共方法。
 * 
 * @author fh
 */

public class BaseController {
	public Logger log = Logger.getLogger(this.getClass());
	private static final int LOG_TYPE_SYS = 1;
	private static final int LOG_TYPE_USER = 2;

	// json返回参数
	protected Object resultJsonObj = null;
	
	protected User user;

	protected User getUser(HttpSession session) {
		// if( session.getAttribute(StaticFinal.CONFIG_DISPATCHER) == null){
		// session.setAttribute(StaticFinal.SESSION_KEY_USER,new User());
		// }

		User user = (User) session.getAttribute(StaticFinal.SESSION_KEY_USER);
		return user;

	}

	/**
	 * 获取当前类和函数名 。格式如package.package.class[method]
	 * 
	 * @return 获取当前类和函数名
	 */
	protected String moduleName() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		String methodName = e.getMethodName();
		String className = e.getClassName();
		return className + "[" + methodName + "]";
	}

	/**
	 * 往日志队列中压入日志
	 * 
	 * @param logType
	 *            日志类型 1：系统日志，：用户操作日志
	 * @param use_id
	 *            用户id
	 * @param module
	 *            模块名，请调用 moduleName() 获取
	 * @param target
	 *            操作对象
	 * @param result
	 *            操作结果
	 * @param reason
	 *            导致结果的原因
	 */
	private void pushLog(Integer logType, Integer use_id, String module,
			String event, String target, String result, String reason) {
		LogBean lBean = new LogBean();
		lBean.setLog_type(logType);
		lBean.setUse_id(use_id);
		lBean.setModule(module);
		lBean.setTarget(target);
		lBean.setEvent(event);
		lBean.setResult(result);
		lBean.setReason(reason);
		LogGather.putLog(lBean);
	}

	/**
	 * 往日志队列中压入系统日志
	 * 
	 * @param use_id
	 *            用户id
	 * @param module
	 *            模块名，请调用 moduleName() 获取
	 * @param event
	 *            事件
	 * @param target
	 *            操作对象
	 * @param result
	 *            操作结果
	 * @param reason
	 *            导致结果的原因
	 */
	protected void pushSysLog(Integer use_id, String module, String event,
			String target, String result, String reason) {

		pushLog(LOG_TYPE_SYS, use_id, module, event, target, result, reason);
	}

	/**
	 * 往日志队列中压入系统日志
	 * 
	 * @param use_id
	 *            用户id
	 * @param module
	 *            模块名，请调用 moduleName() 获取
	 * @param event
	 *            事件
	 * @param target
	 *            操作对象
	 * @param result
	 *            操作结果
	 */
	protected void pushSysLog(Integer use_id, String module, String event,
			String target, String result) {

		pushLog(LOG_TYPE_SYS, use_id, module, event, target, result, null);

	}

	/**
	 * 往日志队列中压入用户日志
	 * 
	 * @param use_id
	 *            用户id
	 * @param module
	 *            模块名，请调用 moduleName() 获取
	 * @param event
	 *            事件
	 * @param target
	 *            操作对象
	 * @param result
	 *            操作结果
	 * @param reason
	 *            导致结果的原因
	 */
	protected void pushUserLog(Integer use_id, String module, String event,
			String target, String result, String reason) {
		pushLog(LOG_TYPE_USER, use_id, module, event, target, result, reason);
	}

	/**
	 * 往日志队列中压入用户日志
	 * 
	 * @param use_id
	 *            用户id
	 * @param module
	 *            模块名，请调用 moduleName() 获取
	 * @param event
	 *            事件
	 * @param target
	 *            操作对象
	 * @param result
	 *            操作结果
	 */
	protected void pushUserLog(Integer use_id, String module, String event,
			String target, String result) {
		pushLog(LOG_TYPE_USER, use_id, module, event, target, result, null);
	}

	protected JSONObject getJsonObjectFromObj(Object obj) {
		return JSONObject.fromObject(obj);
	}

	protected JSONArray getJsonArrayFromObj(Object obj) {
		return JSONArray.fromObject(obj);
	}

	protected JSONObject retEmptyJsonObj() {
		return getJsonObjectFromObj("{'total':0,'rows':[]}");
	}

	protected Object getResultJsonObj() {
		return resultJsonObj;
	}


	protected User getUser() {
		return user;
	}

	protected void setUser(User user) {
		this.user = user;
	}
	
	@ModelAttribute("express")  
	protected void getCommonData(HttpSession session) {  
		this.user= getUser(session);
	}  

	protected void setResultJsonObj(Object row,int total) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", row);
		map.put("total", total);
		this.resultJsonObj = map;
	}
	
	protected JSONObject getJsonResult(List<?> list){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("total", list.size());
		jsonObject.put("rows", JSONArray.fromObject(list));
		return jsonObject;
	}
	
	
	
}
