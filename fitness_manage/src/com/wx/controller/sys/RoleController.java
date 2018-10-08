package com.wx.controller.sys;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Response;
import com.framework.common.StaticFinal;
import com.wx.entity.sys.Organization;
import com.wx.entity.sys.Role;
import com.wx.entity.sys.RoleMenu;
import com.wx.entity.sys.User;
import com.wx.service.sys.SysService;
import com.util.FunHelper;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Controller
@RequestMapping("/sys")
public class RoleController extends BaseController {
	@Autowired
	private SysService sysService ;
	
	 @RequestMapping(value="/addRoleInfo.do" ,method=RequestMethod.POST)
	 @ResponseBody
	public Response addRoleInfo(HttpServletRequest request ,HttpSession session) {

		String roleName = request.getParameter("roleName");
		String roleCode = request.getParameter("roleCode");
		String remark = request.getParameter("remark");
		User user = (User) session.getAttribute(StaticFinal.SESSION_KEY_USER);

		Role role = new Role() ;
		role.setCREATE_USER_ID(user.getId());
		role.setCREATE_DATE(FunHelper.getCurrentTime("-", " ", ":"));
		role.setLAST_UPDATE_USER_ID(user.getId()) ;
		role.setLAST_UPDATE_DATE(FunHelper.getCurrentTime("-", " ", ":"));
		
		//--role.setENTITY_ID(user.getEntity_id());
		role.setIS_USE(1);
		role.setROLE_CODE(roleCode);
		role.setROLE_NAME(roleName);
		role.setREMARK(remark) ;

		int num =0 ;
		try {
			num = sysService.insertRole(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(num >= 0){
			return Response.success("增加角色："+roleName) ;
		}else{
			return Response.error("增加角色信息失败");
		}
	}
	 
	 

	 @RequestMapping(value="/queryRoles.do" ,method=RequestMethod.POST)
	 @ResponseBody
	public JSONObject queryRoles(String roleName   ,HttpSession session){
	try {
		 
		
		roleName = roleName == null?"":roleName;
	  
		User user = (User) session.getAttribute(StaticFinal.SESSION_KEY_USER);
		//--Integer entityId = user.getEntity_id() ;
		Role role = new Role() ;
		//--role.setENTITY_ID(entityId);
		role.setROLE_NAME(roleName);
		role.setIS_USE(1) ;
		
		
		List<Role> list = sysService.queryRoles(role);
		JSONObject response  = new JSONObject() ;
		response.put("total", list.size()) ; 
		JSONArray rows = new JSONArray() ; //"rows":
		for (int i = 0; i < list.size(); i++) {
			JSONObject row = new JSONObject();
			Role ro = list.get(i);
			row.put("id", ro.getID()) ;
			row.put("roleCode", ro.getROLE_CODE()) ;
			row.put("roleName", ro.getROLE_NAME()) ;
			row.put("entityId", ro.getENTITY_ID()) ;
			row.put("createDate", ro.getCREATE_DATE()) ;
			row.put("createUserId", ro.getCREATE_USER_NAME()) ;
			row.put("lastUpdateDate", ro.getLAST_UPDATE_DATE()) ;
			row.put("lastUpdateUserId", ro.getLAST_UPDATE_USER_NAME()) ;
			row.put("remark", ro.getREMARK()) ;
			rows.add(row) ;
			
		}
		
		response.put("rows", rows) ;
		return response ;
		
	} catch (Exception e) {
		// TODO: handle exception
		log.error(e.getMessage()) ;
		
	
		return null ;
	}	
  
	}


	 @RequestMapping(value="/updateRole.do" ,method = RequestMethod.POST)
	 @ResponseBody
	 private Response updateRole(@ModelAttribute("role") Role role ,HttpSession session)
	 {
		
		User user = getUser(session); ;
		role.setLAST_UPDATE_USER_ID(user.getId()) ;
		int rows =0 ;
		try {
			rows = sysService.updteRole(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(rows >=1)
			return Response.success("更新角色："+role.getROLE_NAME()) ;
		else
			return Response.error("更新失败") ;
		
	 }
	 
	 
	 @RequestMapping(value="/deleteRole.do" ,method = RequestMethod.POST)
	 @ResponseBody
	 private Response deleteRole(@ModelAttribute("role") Role role ,HttpSession session)
	 {
		
		User user = getUser(session); 
		role.setLAST_UPDATE_USER_ID(user.getId()) ;
		role.setIS_USE(2);
		int rows = 0;
		try {
			rows = sysService.updteRole(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(rows >=1)
			return Response.success("删除角色："+role.getROLE_NAME()) ;
		else
			return Response.error("禁用角色失败") ;
		
	 }
	 
	 @RequestMapping(value="/queryRoleMenus.do" ,method = RequestMethod.POST)
	 @ResponseBody
	 private Response getRoleMenues(@ModelAttribute("RoleMenu") RoleMenu roleMenu ,HttpSession session) {
		 User user = getUser(session); 
		 if(user == null){
			 return Response.error("获取用户信息失败") ;
		 }
		 
		//--roleMenu.setENTITY_ID(user.getEntity_id()) ;
		List<RoleMenu> list = null;
		try {
			list = sysService.queryRoleMenus(roleMenu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray jarr = new JSONArray() ;
		for (int i = 0; list !=null && i < list.size(); i++) {
			RoleMenu rMenu = list.get(i) ;
			JSONObject rJson = new JSONObject() ;
			rJson.put("id", rMenu.getID()) ;
			rJson.put("roleId", rMenu.getROLE_ID()) ;
			rJson.put("menuId", rMenu.getMENU_ID()) ;
			rJson.put("entityId", rMenu.getENTITY_ID()) ;
			jarr.add(rJson) ;
			
		}
		return Response.success("成功", "("+jarr.toString()+")") ;  
	 }
	  
	 @RequestMapping(value="/setRoleMenus.do" ,method = RequestMethod.POST)
	 @ResponseBody
	 private Response setRoleMenus(HttpServletRequest request ,HttpSession session) {
		 User user = getUser(session); 
		 if(user == null){
			 return Response.error("获取用户信息失败") ;
		 }
		 
		 
		 String roleId = request.getParameter("roleId") ;
		 
		
		 if(roleId == null || "".equals(roleId)){
			 return Response.error("请选择一个角色") ;
		 }
		 
		 String menuId = request.getParameter("menuId") ;
		 menuId = menuId.replace("[", "") ;
		 menuId = menuId.replace("]", "") ;
		 String []menuArr = menuId.split(",") ;
		 List<String> menuList = Arrays.asList(menuArr) ;
		 
		 
		boolean ret = false;
		try {
			ret = sysService.updateRoleMenu(user, roleId, menuList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ret){
			return Response.success("角色："+roleId +" 设置菜单："+menuId) ;
		}else{
			return Response.error("设置角色菜单失败") ;
		}
		
		 
	}
	 
}
