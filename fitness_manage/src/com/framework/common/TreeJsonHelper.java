package com.framework.common;


import com.wx.entity.sys.Menu;
import com.wx.entity.sys.Organization;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * easyui 控件数据帮助类。
 * @author fhr
 *
 */
public class TreeJsonHelper {
	
	/**
	 * 加入机构节点
	 * @param node 
	 * @param org
	 */
	public static boolean AddOrganizationNode(JSONObject node ,Organization org)
	{
		try{
			if(node == null || node.containsKey("empty")){
				return  false;
			}
			
			JSONArray children = null ;
			if(!hasChidren(node)){
				children = new JSONArray() ;
				node.put("children", children) ;
				children = node.getJSONArray("children") ;
			}else{
				children = node.getJSONArray("children") ;
			}
			
		   String nodeId =getId(node).toString() ;
	       String p_parentId = getParentId(node) ;
	       String nodeParentPath = p_parentId.equals("")? nodeId: p_parentId+"."+nodeId;
	       String myParentId = org.getParent_id() ;
	     
	       
			if(myParentId.equals(nodeParentPath)){
	            
				JSONObject child  = new JSONObject() ;
				child.put("id", org.getId()) ;
				child.put("text", org.getOrganization_name()) ;
				child.put("state", "opened") ;
				
				 JSONObject attr_c  = new JSONObject() ;
				 attr_c.put("parentId", org.getParent_id()) ;
			
				 attr_c.put("code", org.getOrganization_code()) ;
				 attr_c.put("type", org.getOrganization_type()) ;
				 child.put("attr", attr_c) ;
				 children.add(child) ;
				 return true;
			}

			if(myParentId.indexOf(nodeParentPath) == -1){
				return false;
			}
			
			for(int index = 0 ; index< children.size() ;index ++){
				 JSONObject cd = children.getJSONObject(index) ;
				 if( AddOrganizationNode(cd ,org) ){
					 return true ;
				 }
			}
			
			return false ;
		}catch (Exception e) {
			// TODO: handle exception
			return false ;
		}
	}
	
	/**
	 * 增加菜单节点
	 * @param node
	 * @param menu
	 */
	public static void AddMenuNode(JSONObject node ,Menu menu)
	{
		if(node == null){
			return  ;
		}
		
		JSONArray children = null ;
		if(!hasChidren(node)){
			children = new JSONArray() ;
			node.put("children", children) ;
			children = node.getJSONArray("children") ;
		}else{
			children = node.getJSONArray("children") ;
		}
		
	   String nodeCode = getCode(node).toString() ;
       String p_parentId = getParentId(node) ;
       String nodeParentPath = p_parentId.equals("")? nodeCode: p_parentId+"."+nodeCode;
       
       String myParentId = menu.getPARENT_ID() ;
     
       
		if(myParentId.equals(nodeParentPath)){
            
			JSONObject child  = new JSONObject() ;
			child.put("id", menu.getID()) ;
			child.put("text", menu.getMENU_NAME_CN()) ;
			child.put("url", menu.getFUNCA_PATH()) ;
			String state = menu.getSTATE() == null? "closed": menu.getSTATE();
			child.put("state", state) ;
			child.put("fName", menu.getIFRAM_NAME()) ;
			child.put("code", menu.getMENU_CODE()) ;
			 JSONObject attr_c  = new JSONObject() ;
			 attr_c.put("parentId", menu.getPARENT_ID()) ;
			 attr_c.put("entityId", menu.getENTITY_ID()) ;
			
			
			 
			 child.put("attr", attr_c) ;
			 children.add(child) ;
			 return ;
		}
		
		
		if(myParentId.indexOf(nodeParentPath) == -1){
			return ;
		}
		
		for(int index = 0 ; index< children.size() ;index ++){
			JSONObject cd = (JSONObject) children.get(index) ;
			AddMenuNode(cd ,menu) ;
		}

	}

	
	
	/**
	 * 增加仓库节点
	 * @param node
	 * @param menu
	
	public static void AddStorageNode(JSONObject node ,Storage storage)
	{
		if(node == null){
			return  ;
		}
		
		JSONArray children = null ;
		if(!hasChidren(node)){
			children = new JSONArray() ;
			node.put("children", children) ;
			children = node.getJSONArray("children") ;
		}else{
			children = node.getJSONArray("children") ;
		}
		
	   String nodeId =getId(node).toString() ;
       String p_parentId = getParentId(node) ;
       String nodeParentPath = p_parentId.equals("")? nodeId: p_parentId+"."+nodeId;
       
       String myParentId = storage.getParent_id() ;
     
       
		if(myParentId.equals(nodeParentPath)){
            
			JSONObject child  = new JSONObject() ;
			child.put("id", storage.getId()) ;
			child.put("text", storage.getStorage_name()) ;
		
			child.put("state", "open") ;

			
			 JSONObject attr_c  = new JSONObject() ;
			 attr_c.put("parentId", storage.getParent_id()) ;
			 attr_c.put("entityId", storage.getEntity_id()) ;
			 attr_c.put("code", storage.getStorage_code()) ;
			
			 
			 child.put("attr", attr_c) ;
			 children.add(child) ;
			 return ;
		}
		
		
		if(myParentId.indexOf(nodeParentPath) == -1){
			return ;
		}
		
		for(int index = 0 ; index< children.size() ;index ++){
			JSONObject cd = (JSONObject) children.get(index) ;
			AddStorageNode(cd ,storage) ;
		}

	} */


	
	public static boolean hasChidren(JSONObject node){
		
		if(node.containsKey("children")){
			return true ;
		}
         
		return false ;
	}
	
	
	public static String getParentId(JSONObject node){
		JSONObject attr = (JSONObject)node.get("attr") ;
		Object parentIdObject = attr.get("parentId") ;
		String parentId = parentIdObject == null?"" :parentIdObject.toString() ;
		return parentId ;
	}
	
	
	public static Integer getId(JSONObject node){
		return (Integer)node.get("id");
	
	}
   
	public static Integer getCode(JSONObject node){
		return (Integer)node.get("code");
	
	}
}
