package com.wx.service.sys;

import java.util.List;

import com.wx.entity.sys.Menu;
import com.wx.entity.sys.Role;
import com.wx.entity.sys.RoleMenu;
import com.wx.entity.sys.User;



public interface SysService {
    public List<Menu> queryMenusTree(String entityID)throws Exception ;
    
    public int insertRole(Role role)throws Exception ;
    
    public List<Role> queryRoles(Role role) throws Exception ;
    
    public int updteRole(Role role)throws Exception  ;
    
    public int insertRoleMenu(RoleMenu menuRole)throws Exception  ;
    
    public boolean updateRoleMenu(User user,String roleId ,List<String> menuList) throws Exception ;
    
    public List<RoleMenu> queryRoleMenus(RoleMenu menuRole) throws Exception ;
}
