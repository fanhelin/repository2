package com.wx.controller.sys;


import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;

import com.framework.common.Response;
import com.framework.common.StaticFinal;


import com.wx.entity.sys.AppInfo;

import com.wx.entity.sys.User;
import com.wx.entity.sys.Group;
import com.wx.entity.sys.UserGroup;

import com.wx.service.sys.UserService;
import com.util.FunHelper;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	 @Autowired
	 private UserService userService;
	 

     
	 @RequestMapping(value="/verify.do" ,method=RequestMethod.POST)
	 @ResponseBody
	public Response verify( @RequestParam(value="user_code",required=true) String userCode , @RequestParam(value="password",required=true) String password ,HttpSession session)
	 {
        String moduleName = moduleName();
	    log.debug("userCode=" + userCode +"  password=" +password) ;

		 User u= new User() ;
		 u.setUser_code(userCode) ;
		 u.setPassword(FunHelper.encodeMD5(password));
		try {
			if(userService.existUser(u))
			{
				 List<User> ls = userService.selectUser(u) ;
				 if(ls.size()>0){
					 
					 User user = ls.get(0) ;
					 if(user.getBus_state() != 1){
						pushSysLog(user.getId(), moduleName,"用户登录", userCode, "失败","该用户状态异常");
						 return Response.error("该用户状态异常，不允许登录",null,"-1") ; 
					 }
					 
					/* if(user.getLogin_state() == 1){
						 return Response.error("该用户已经登录",null,"-3") ; 
					 }*/
					
					 session.setAttribute(StaticFinal.SESSION_KEY_USER, user);
			
					 pushSysLog(user.getId(), moduleName,"用户登录", userCode, "成功");
					 return Response.success("success") ; 
				 }else{
					 pushSysLog(null, moduleName,"用户登录", userCode, "登录失败","密码错误");
					 return Response.error("密码错误！",null,"-2") ; 
				 }
				
			}else{
				 pushSysLog(null, moduleName,"用户登录", userCode, "登录失败","用户不存在");
				 return Response.error("用户不存在") ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return null;
	}
	 
	 
	
	 
	 @RequestMapping(value="/queryUsers.do" ,method=RequestMethod.POST)
	 @ResponseBody
	public JSONObject queryUsers(HttpSession session,@RequestParam(value="uerName",required=false) String uerName, @RequestParam(value="entityId",required=false) Integer entityId,@RequestParam(value="isUse",required=false) String isUse){
		
		 if(uerName == null){
			 uerName = "" ;
		 }
		
		 log.debug("uerName=" + uerName +"  entityId=" +entityId + "  isUse=" + isUse) ;
 
		
		 Integer i_isuse = (isUse == null || "".equals(isUse)) ? null: new Integer(isUse) ;
		 
		 User u = new User() ;
		 u.setUser_name(uerName) ;
		
		 u.setBus_state(1) ;
		 
		 User su = (User)session.getAttribute(StaticFinal.SESSION_KEY_USER) ;
		 

		 
		 List<User> users = null;
		try {
			users = userService.queryUsers(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 JSONObject response  = new JSONObject() ;
		 response.put("total", users.size()) ; 
		 JSONArray rows = new JSONArray() ; //"rows":

		 for(int i=0 ;i< users.size() ; i++){
			 User user = users.get(i) ;
			 JSONObject row =  new JSONObject() ;
//			 row.put("userId", user.getId()) ;
//			 row.put("userName", user.getUsername()) ;
//			 row.put("userCode", user.getUser_code()) ;
//			 row.put("mobile", user.getMobile()) ;
//			 row.put("email", user.getEmail()) ; 
//			 row.put("isUse", user.getIs_use()) ;
//			 row.put("createDate", user.getCreate_date()) ;
//			 row.put("creator", user.getCreate_user_name()) ;
//			 row.put("staff_id", user.getStaff_id()) ;
//			 row.put("staff_name", user.getStaff_name()) ;
			 
			 rows.add(row) ;
			 	 
		 }
		  response.put("rows", rows) ;
		  return response ;
	}
	 

	 @RequestMapping(value="/addUser.do" ,method = RequestMethod.POST)
	 @ResponseBody
	 private Response addUserInfo(@ModelAttribute("user") User user ,HttpSession session)
	 {
		
		User suser = getUser(session); ;
		
	    user.setPassword(FunHelper.encodeMD5(user.getPassword()));
	    
	   int rows =0;
		try {
			rows = userService.insertUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   String moduleName = moduleName();
		if(rows >=1){
			pushUserLog(suser.getId(), moduleName, "新增用户","用户id="+user.getId(), "增加成功") ;
			return Response.success("增加用户："+user.getUser_name()) ;
		}else{
			pushUserLog(suser.getId(), moduleName, "新增用户", "用户id"+user.getId(), "增加失败","插入表失败") ;
			return Response.error("增加用户："+user.getUser_name()+"失败") ;
		}
		
	 }
	 
	 @RequestMapping(value="/updateUser.do" ,method = RequestMethod.POST)
	 @ResponseBody
	 private Response updateUserInfo(@ModelAttribute("user") User user ,HttpSession session)
	 {
		
		User suser = getUser(session); ;
	

	    int rows = 0;
		try {
			rows = userService.updateUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    String moduleName = moduleName();
		if(rows >=1){
			pushUserLog(suser.getId(), moduleName, "更新用户","用户id="+user.getId(), "更新成功") ;
			return Response.success("更新用户:用户id"+user.getId()) ;
		}else{
			pushUserLog(suser.getId(), moduleName, "更新用户", "用户id"+user.getId(), "更新失败","写数据库失败") ;
			return Response.error("更新用户失败，id："+user.getId()) ;
		}
		
	 }
	 
	 
	 @RequestMapping(value="/deleteUser.do" ,method = RequestMethod.POST)
	 @ResponseBody
	 private Response deleteUser(@ModelAttribute("user") User user ,HttpSession session)
	 {
		
		User suser = getUser(session); 
		//user.setLast_update_user_id(suser.getId()) ;
		//user.setIs_use(2);
		int rows = 0;
		try {
			rows = userService.updateUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 String moduleName = moduleName();
		if(rows >=1){
			pushUserLog(suser.getId(), moduleName, "禁用用户", "用户id="+user.getId(), "禁用成功") ;
			return Response.success("删除用户："+user.getId() +" "+user.getUser_code()) ;
		}
		else{
			pushUserLog(suser.getId(), moduleName, "禁用用户","用户id="+user.getId(), "禁用失败","写数据库失败") ;
			return Response.error("禁用户失败,id:"+user.getId()) ;
		}
		
	 }
	 
	 @RequestMapping(value="/kickUser.do" ,method = RequestMethod.POST)
	 @ResponseBody
	 private Response kickOutUser(String userCode){
		
		 //--WebSocketManager.sendMsgTo(userCode, Notify.NotifyJson("KICKOUT", "强迫用户下线", null).toString()) ;
		 
		 User user = new User();
		 user.setUser_code(userCode) ;
		 user.setLogin_state(2) ;
		 try {
			userService.updateUser(user) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Response.success("已通知下线") ;
	 }
	 
	 
	 @RequestMapping("/loadGroups.do")
	 @ResponseBody
	 private Response loadGroups(UserGroup group){
		try {
			List<Group> list= userService.queryGroups(group) ;
			
			return Response.success("获取分组成功" ,JSONArray.fromObject(list)) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		} 
	 }
	 
	 @RequestMapping("/loadAppInfos.do")
	 @ResponseBody
	 private Response loadAppInfos(AppInfo appInfo){
		try {
			
			List<AppInfo> list = userService.selectAppInfos(appInfo) ;
			JSONArray ret = JSONArray.fromObject(list) ;
			return Response.success("获取小程序列表成功" ,ret) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		} 
	 }
	 

	 
	 
	 
}
