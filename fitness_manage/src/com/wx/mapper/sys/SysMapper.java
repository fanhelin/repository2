package com.wx.mapper.sys;

import java.util.HashMap;
import java.util.List;

import com.wx.entity.sys.Menu;
import com.wx.entity.sys.Role;
import com.wx.entity.sys.RoleMenu;


public interface SysMapper {
  public List<Menu> queryMenusTree(HashMap<String, Object> params) throws Exception ;
  
  public int insertRole(Role role)throws Exception ;
  
  public List<Role> queryRoles(Role role) throws Exception ;
  
  public int updateRole(Role role) throws Exception ;
  
  public int insertRoleMenu(RoleMenu menuRole) throws Exception ;
  
  public boolean updateRoleMenu(RoleMenu menuRole) throws Exception ;
  
  public List<RoleMenu> queryRoleMenus(RoleMenu menuRole) throws Exception ;
  
  public int deleteRoleMenus(RoleMenu menuRole)throws Exception ;
}
