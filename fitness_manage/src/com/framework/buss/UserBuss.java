package com.framework.buss;

import com.framework.common.ContextUtil;
import com.wx.entity.sys.User;
import com.wx.service.sys.UserService;
/**
 * 
 * @todo 用户业务类，用于普通类访问service
 *
 * @author fhr
 * @dateTime 2016 2016-11-21 下午2:22:16
 */
public class UserBuss {
   public void setUserStatus(User user){
	   
	   UserService userService = (UserService)ContextUtil.Instance().getBean("userService") ;
	   
	   
	   try {
		userService.updateUser(user) ;
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
