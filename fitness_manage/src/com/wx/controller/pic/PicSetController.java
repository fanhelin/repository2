package com.wx.controller.pic;

import java.io.File;
import java.util.List;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Config;
import com.framework.common.Response;
import com.framework.common.StaticFinal;
import com.util.FunHelper;
import com.wx.entity.pic.PicSet;
import com.wx.entity.pic.PicSetList;
import com.wx.service.pic.PicSetService;

@Controller
@RequestMapping("/picSet")
public class PicSetController extends BaseController 
{
	@Autowired
	PicSetService picSetService ;
	
	@RequestMapping("/loadPicSets.do")
	@ResponseBody
	private JSONObject loadPicSets(){
		
			PicSet ps = new PicSet() ;
			ps.setApp_info_code(this.user.getApp_info_code()) ;
	       try {
			List<PicSet> list = picSetService.selectPicSets(ps) ;
		    JSONObject rep = new JSONObject();
		    rep.put("total", list.size()) ;
		    rep.put("rows",JSONArray.fromObject(list) ) ;
		    return rep ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return retEmptyJsonObj() ;
		}
		
	}
	

	@RequestMapping("/loadPicSetList.do")
	@ResponseBody
	private JSONObject loadPicSetList(PicSetList picSetList){
       try {
    	picSetList.setApp_info_code(this.user.getApp_info_code());
		List<PicSetList> list = picSetService.selectPicSetList(picSetList) ;
	    JSONObject rep = new JSONObject();
	    rep.put("total", list.size()) ;
	    rep.put("rows",JSONArray.fromObject(list) ) ;
	    return rep ;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return retEmptyJsonObj() ;
	}
	
	}
	
	
	
	@RequestMapping("/addPicSet.do")
	@ResponseBody
	private Response addPicSet(PicSet picSet){
		try {
			picSet.setApp_info_code(user.getApp_info_code()) ;
			picSetService.addPicSet(picSet) ;
			return Response.success("增加图片集成功") ;
			
		} catch (Exception e) {
			// TODO: handle exception
			return Response.error(e.getMessage()) ;
		}
	}
	
	
	@RequestMapping("/modifyPicSet.do")
	@ResponseBody
	private Response modifyPicSet(PicSet picSet){
		try {
			picSet.setApp_info_code(user.getApp_info_code()) ;
			picSetService.modifyPicSet(picSet) ;
			return Response.success("修改图片集成功") ;
			
		} catch (Exception e) {
			// TODO: handle exception
			return Response.error(e.getMessage()) ;
		}
	}
	
	
	@RequestMapping("/deletePicSet.do")
	@ResponseBody
	private Response deletePicSet(PicSet picSet){
		try {
			picSet.setApp_info_code(user.getApp_info_code()) ;
			picSetService.deletePicSet(picSet) ;
			return Response.success("删除图片集成功") ;
			
		} catch (Exception e) {
			// TODO: handle exception
			return Response.error(e.getMessage()) ;
		}
	}
	
	
	
	@RequestMapping("/addPicSetList.do")
	@ResponseBody
	private Response addPicSetList( PicSetList picSetList)
	{
		String exe_name = picSetList.getExe_name() ;
		String picData = picSetList.getData() ;
		String set_code = picSetList.getSet_code() ;
		if( picData == null || picData.isEmpty() ){
			return Response.error("获取上传数据失败") ;
		}
		
		 if(exe_name == null || exe_name.isEmpty()){
	        	return Response.error("获取扩展名失败") ;
	      }
		 
		 if(set_code == null || set_code.isEmpty()){
	        	return Response.error("获取图片集编号失败") ;
	      }
		 
	try { 
  	   String appType = this.user.getApp_type();
 	   String appId ="";// this.user.getApp_id() ;
 
 	   Integer id = picSetService.getMaxPicSetListId(picSetList) ;
 	 
 	   String img_code = set_code +"_" +id ;
 	   picSetList.setImg_code(img_code) ;	 


	   picSetList.setImg_name(picSetList.getImg_code()+"."+exe_name) ;
	   picSetList.setApp_info_code(this.user.getApp_info_code()) ;

	   String basePath = FunHelper.getClassPath(this) ;
	   basePath = basePath.split("/WEB-INF/")[0] ;
	   basePath += "/"+Config.getConfig(StaticFinal.WX_UPLOAD_FOLD, "uploadFiles") +"/"+appType+"/"+appId +"/"+set_code;
	   File dir = new File(basePath);
	   if(!dir.exists()){
		   dir.mkdirs() ;
		   dir.setWritable(true,false) ;
		   dir.setReadable(true,false) ;
		   dir.setExecutable(true,false) ;
		   
	   }
	   
	   String fileUrl = basePath +"/"+picSetList.getImg_name() ;
	   File file = new File(fileUrl) ;
	   if(file.isFile() && file.exists()){
			return Response.error("存在同名文件") ;
	   }
	   
	   picSetList.setPath(fileUrl) ;
	   picSetList.setData(picData) ;
	   picSetService.addPicSetList(picSetList ) ;
       return Response.success("增加图片成功");

	} catch (Exception e) {
		// TODO Auto-generated catch block
		 e.printStackTrace();
		return Response.error(e.getMessage()) ;
	}
	
  }
	
	
	@RequestMapping("/deletePicSetList.do")
	@ResponseBody
	private Response deletePic(PicSetList picSetList){
	try {
    	   String appType = this.user.getApp_type();
    	   String appId ="";// this.user.getApp_id() ;
           String set_code= picSetList.getSet_code() ;
           
           picSetList.setApp_info_code(this.user.getApp_info_code()) ;
           
    	   String basePath = FunHelper.getClassPath(this) ;
    	   basePath = basePath.split("/WEB-INF/")[0] ;
    	   basePath += "/"+Config.getConfig(StaticFinal.WX_UPLOAD_FOLD, "uploadFiles") +"/"+appType+"/"+appId+"/"+set_code ;

    	   picSetService.deletePicSetList(picSetList , basePath+"/"+picSetList.getImg_name());
   	        
   	       return Response.success("删除成功");

	} catch (Exception e) {
		// TODO Auto-generated catch block
		 e.printStackTrace();
		return Response.error(e.getMessage()) ;
	}
	
  }	

	@RequestMapping("/modifyPicList.do")
	@ResponseBody
	private Response modifyPicList( PicSetList picSetList){
		
		
		String set_code = picSetList.getSet_code() ;
	

		if(set_code == null || set_code.isEmpty()){
	         return Response.error("获取图片集编号失败") ;
	     }
	try { 
  	   String appType = this.user.getApp_type();
 	   String appId ="";// this.user.getApp_id() ;
 	  picSetList.setApp_info_code(this.user.getApp_info_code()) ;
 	   //Integer id = picSetService.getMaxPicSetListId(picSetList) ;
 	 
 	  if(picSetList.getData() != null && !picSetList.getData().isEmpty()){ //修改了图片
	 	   String exe_name = picSetList.getExe_name() ;
		   picSetList.setImg_name(picSetList.getImg_code()+"."+exe_name) ;
		   
	  if(exe_name == null || exe_name.isEmpty()){
        	return Response.error("获取扩展名失败") ;
      }
 	 

	   String basePath = FunHelper.getClassPath(this) ;
	   
	   basePath = basePath.split("/WEB-INF/")[0] ;
	   String newPath = basePath + "/"+Config.getConfig(StaticFinal.WX_UPLOAD_FOLD, "uploadFiles") +"/"+appType+"/"+appId +"/"+set_code;
	   File dir = new File(newPath);
	   
	   if(!dir.exists()){
		   dir.mkdirs() ;
		   dir.setWritable(true,false) ;
		   dir.setReadable(true,false) ;
		   dir.setExecutable(true,false) ; 
	   }
	   
	   newPath = newPath + "/" + picSetList.getImg_name() ;
	   //String proName = Config.getConfig(StaticFinal.PROJECT_NAME) ;
	   //String oldPath = basePath.replace("/"+proName, "") ;
	   String oldPath = basePath +"/" +picSetList.getPath() ;
	 
	   picSetList.setPath(newPath) ;
	   picSetService.modifyPicSetList(picSetList, oldPath) ;
	   
 	 }else{
 		  picSetService.modifyPicSetList(picSetList, null) ;
 	 }
       return Response.success("修改图片成功");

	} catch (Exception e) {
		// TODO Auto-generated catch block
		 e.printStackTrace();
		return Response.error(e.getMessage()) ;
	}
	
  }	
	
	
	
}
