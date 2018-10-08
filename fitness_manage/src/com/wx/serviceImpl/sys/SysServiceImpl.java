package com.wx.serviceImpl.sys;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.wx.entity.sys.Menu;
import com.wx.entity.sys.Role;
import com.wx.entity.sys.RoleMenu;
import com.wx.entity.sys.User;
import com.wx.mapper.sys.SysMapper;
import com.wx.service.sys.SysService;
import com.util.FunHelper;

@Service
public class SysServiceImpl extends BaseService implements SysService {

	@Autowired
	private SysMapper sysMapper ;

	@Override
	public List<Menu> queryMenusTree(String entityID) throws Exception{
		// TODO Auto-generated method stub
		
	
			HashMap<String, Object> params = new HashMap() ;
			params.put("ENTITY_ID", new Integer(entityID)) ;
			List<Menu> list  = sysMapper.queryMenusTree(params) ;
			return list ;
		
	}

	@Override
	public int insertRole(Role role)  throws Exception{
		// TODO Auto-generated method stub
		int ret =  sysMapper.insertRole(role);
		return ret ;
		
	}

	@Override
	public List<Role> queryRoles(Role role) throws Exception {
		// TODO Auto-generated method stub
		return  sysMapper.queryRoles(role) ;
	}

	@Override
	public int updteRole(Role role)  throws Exception{
		// TODO Auto-generated method stub
		 String dateTime = FunHelper.getCurrentDate("-"); 
		 role.setLAST_UPDATE_DATE(dateTime) ;
		return sysMapper.updateRole(role) ;
		
	}

	@Override
	public int insertRoleMenu(RoleMenu menuRole)  throws Exception{
		// TODO Auto-generated method stub
		 String dateTime = FunHelper.getCurrentDate("-"); 
		 menuRole.setCREATE_DATE(dateTime) ;
		return sysMapper.insertRoleMenu(menuRole ) ;
	}

	@Override
	public boolean updateRoleMenu(User user,String roleId ,List<String> menuList)  throws Exception{
		// TODO Auto-generated method stub
		 String dateTime = FunHelper.getCurrentTime("-", " ", ":"); 
		
			
			RoleMenu roleMenu = new RoleMenu() ;
			roleMenu.setROLE_ID(new Integer(roleId)) ;
		

			sysMapper.deleteRoleMenus(roleMenu) ;
			
			roleMenu.setCREATE_USER_ID(user.getId()) ;
			roleMenu.setCREATE_DATE(dateTime);
			roleMenu.setLAST_UPDATE_USER_ID(user.getId()) ;
			roleMenu.setLAST_UPDATE_DATE(dateTime) ;
			
			for(int i = 0 ;i< menuList.size() ;i++){
				String menuid = menuList.get(i) ;
				roleMenu.setMENU_ID(new Integer(menuid)) ;
				
				sysMapper.insertRoleMenu(roleMenu) ;
				
			  }
			
			return true ;
		
	}

	@Override
	public List<RoleMenu> queryRoleMenus(RoleMenu menuRole)  throws Exception{
		// TODO Auto-generated method stub
		return sysMapper.queryRoleMenus(menuRole) ;
		
	}
	
	
	private boolean isExist(String menuId ,List<RoleMenu> RMlist )  throws Exception{
		for(int i= 0 ;i< RMlist.size() ;i++){
			RoleMenu rMenu = RMlist.get(i) ;
			String rmidStr = rMenu.getMENU_ID().toString() ;
			if(menuId.equals(rmidStr)){
				return true ;
			}
		}
		return false ;
	}

}
