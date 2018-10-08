package com.wx.service.applet;

import java.util.Map;

import com.wx.entity.sys.AppInfo;

import net.sf.json.JSONObject;

public interface AppService {
   
   
   JSONObject getAppJson(AppInfo appInfo) throws Exception ;
   
   AppInfo getAppInfo(AppInfo appInfo) throws Exception ; 
   
   JSONObject getUserInfo(Map<?,?> map) throws Exception ;
}
