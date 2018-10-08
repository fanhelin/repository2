package com.wx.mapper.sys;

import java.util.List;


import com.wx.entity.sys.AppInfo;
import com.wx.entity.sys.Group;
import com.wx.entity.sys.User;
import com.wx.entity.sys.UserGroup;

public interface UserMapper {
	  public List<User> selectUser(User user)throws Exception;
	  public int insertUser(User user) throws Exception;
	  public List<User> selectUserByUser(User user)throws Exception;
	  public List<User> queryUsers(User user)throws Exception;
	  public int updateUser(User user) throws Exception ;
	  
	  public List<Group> queryGroups(UserGroup group) throws Exception ;
	  
	  public List<AppInfo> selectAppInfos(AppInfo appInfo) throws Exception ;

}
