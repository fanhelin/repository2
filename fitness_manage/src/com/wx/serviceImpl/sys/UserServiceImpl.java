package com.wx.serviceImpl.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.entity.sys.AppInfo;
import com.wx.entity.sys.Group;
import com.wx.entity.sys.User;
import com.wx.entity.sys.UserGroup;
import com.wx.mapper.sys.UserMapper;
import com.wx.service.sys.UserService;
import com.util.FunHelper;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired

    private UserMapper userMapper;

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		try {
			return userMapper.insertUser(user) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0 ;
		}
	}

	@Override
	public List<User> selectUser(User user) {
		// TODO Auto-generated method stub
		try {
			return userMapper.selectUser(user) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	@Override
	public boolean existUser(User user) {
		// TODO Auto-generated method stub
		 List<User> userList;
		try {
			userList = userMapper.selectUserByUser(user);

	        if(userList.size()>0)
	            return true;
	        else
	            return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false ;
		}
	}

	@Override
	public List<User> queryUsers(User user) {
		// TODO Auto-generated method stub
		try {
			return userMapper.queryUsers(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		try {
			return userMapper.updateUser(user) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	
	}

	@Override
	public List<Group> queryGroups(UserGroup userGroup) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.queryGroups(userGroup);
	}

	@Override
	public List<AppInfo> selectAppInfos(AppInfo appInfo )throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectAppInfos(appInfo);
	}

}
