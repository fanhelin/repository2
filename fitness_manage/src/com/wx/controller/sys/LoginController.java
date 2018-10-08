package com.wx.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.log.LogGather;
import com.wx.entity.common.LogBean;
import com.util.FunHelper;

@Controller
public class LoginController extends BaseController{
	
 @RequestMapping("/doLogin.do")
public String doLogin(){
	  
	 System.out.println("in action doLogin"); 
	 return "mainFrame" ;
  }
}
