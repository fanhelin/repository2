package com.wx.controller.pic;

import java.io.File;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.wx.entity.pic.ImageInfo;

import com.wx.service.pic.PicManageService;

@Controller
@RequestMapping("/pic")
public class PicManageController extends BaseController {
	@Autowired
	PicManageService picManageService ;
	

	
	@RequestMapping("/loadPics.do")
	@ResponseBody
	private JSONObject loadPics(){
		
		ImageInfo ii = new ImageInfo() ;
		ii.setApp_info_code(this.user.getApp_info_code()) ;
       try {
		List<ImageInfo> list = picManageService.selectPictures(ii) ;
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
	
	
	
	@RequestMapping("/addPic.do")
	@ResponseBody
	private Response addPic( HttpServletRequest request ,ImageInfo imageInfo){
		
	 
  	   String app_info_code = this.user.getApp_info_code();
  	   if(app_info_code==null || app_info_code.isEmpty()){
  		   return Response.error("请指定一个小程") ;
  	   }
  	   
  	   String appType = this.user.getApp_type();

  	   imageInfo.setApp_info_code(app_info_code) ;
  
		
		String imag_name = imageInfo.getImg_name() ;
		imag_name = FunHelper.trim(imag_name,new String[]{" ","　"," ","（","）","\\(","\\)","[","]","［","］","【","】","?","*","？","×"}) ;
		String picData =  imageInfo.getData() ;
		if(imag_name == null || imag_name.isEmpty() || picData == null || picData.isEmpty() ){
			return Response.error("获取上传数据失败") ;
		}
		
		
        String exe_name = imageInfo.getExe_name() ;
        if(exe_name == null || exe_name.isEmpty()){
        	return Response.error("获取扩展名失败") ;
        }
        
		try {
		   //data = URLDecoder.decode(data, "UTF-8");
	
    	  
    	 
    	   //Integer id = picManageService.getMaxImageId() ;
    	  
    	   //imageInfo.setImg_code(id+"_"+imag_name) ;
    	   imageInfo.setImg_code(imag_name) ;
    	   imageInfo.setImg_name(imageInfo.getImg_code()+"."+exe_name) ;
    	   imageInfo.setExe_name(exe_name) ;
    	   
    	   String basePath = FunHelper.getClassPath(this) ;
    	   basePath = basePath.split("/WEB-INF/")[0] ;
    	   basePath += "/"+Config.getConfig(StaticFinal.WX_UPLOAD_FOLD, "uploadFiles") +"/"+appType+"/"+"appId" ;
    	   File dir = new File(basePath);
    	   if(!dir.exists()){
    		   dir.mkdirs() ;
    		   dir.setWritable(true,false) ;
    		   dir.setReadable(true,false) ;
    		   dir.setExecutable(true,false) ;
    		   
    	   }
    	   
    	   String fileUrl = basePath +"/"+imageInfo.getImg_name() ;
    	   File file = new File(fileUrl) ;
    	   if(file.isFile() && file.exists()){
    			return Response.error("存在同名文件") ;
    	   }
    	   
    	    imageInfo.setPath(fileUrl) ;
    	    imageInfo.setData(picData) ;
   	        picManageService.addPicture(imageInfo ) ;
   	        return Response.success("增加图片成功");

	} catch (Exception e) {
		// TODO Auto-generated catch block
		 e.printStackTrace();
		return Response.error(e.getMessage()) ;
	}
	
  }
	
		@RequestMapping("/deletePic.do")
		@ResponseBody
		private Response deletePic( HttpServletRequest request ,ImageInfo imageInfo){

			try {
			   //data = URLDecoder.decode(data, "UTF-8");
		
	    	   String appType = this.user.getApp_type();
	    	   String appId = "appid";//this.user.getApp_id() ;
	    	   imageInfo.setApp_info_code(this.user.getApp_info_code()) ;

	    	   String basePath = FunHelper.getClassPath(this) ;
	    	   basePath = basePath.split("/WEB-INF/")[0] ;
	    	   basePath += "/"+Config.getConfig(StaticFinal.WX_UPLOAD_FOLD, "uploadFiles") +"/"+appType+"/"+appId ;

	   	        picManageService.deletePicture(imageInfo, basePath+"/"+imageInfo.getImg_name());
	   	        
	   	       return Response.success("删除成功");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
		
	  }
		
		@RequestMapping("/modifyPic.do")
		@ResponseBody
		private Response modifyPic( ImageInfo imageInfo){
			try {
				String imag_name = imageInfo.getImg_name() ;
				imag_name = imag_name.trim() ;
				String picData =  imageInfo.getData() ;
				if(imag_name == null || imag_name.isEmpty() || picData == null || picData.isEmpty() ){
					return Response.error("获取上传数据失败") ;
				}
				
				
		        String exe_name = imageInfo.getExe_name() ;
		        if(exe_name == null || exe_name.isEmpty()){
		        	return Response.error("获取扩展名失败") ;
		        }
		        
		        
		        String basePath = FunHelper.getClassPath(this) ;
	            basePath = basePath.split("/WEB-INF/")[0] ;
	           
	           // File file = new File(imageInfo.getPath());
	            String appType = this.user.getApp_type();
	     	    String appId ="" ;//this.user.getApp_id() ;
	     	    imageInfo.setApp_info_code(this.user.getApp_info_code()) ;
	     	    String proName = Config.getConfig(StaticFinal.PROJECT_NAME) ;
	     	    String oldPath = basePath.replace("/"+proName, "") ;
	     	    oldPath += imageInfo.getPath() ;
	     	    
	     	   
	     	    
	     	   basePath += "/"+Config.getConfig(StaticFinal.WX_UPLOAD_FOLD, "uploadFiles") +"/"+appType+"/"+appId ;
	    	   File dir = new File(basePath);
	    	   
	    	   if(!dir.exists()){
	    		   dir.mkdirs() ;
	    		   dir.setWritable(true,false) ;
	    		   dir.setReadable(true,false) ;
	    		   dir.setExecutable(true,false) ;
	    		   
	    	   }
	    	   
	    	   imageInfo.setImg_name(imageInfo.getImg_name()+"."+imageInfo.getExe_name()) ;
	    	   String newPath = basePath +"/"+imageInfo.getImg_name() ;
	    	 //  File file = new File(newPath) ;
			 //	    	   if(file.isFile() && file.exists()){
			 //	    			return Response.error("存在同名文件") ;
			 //	   }
				            
	    	    picManageService.modifyPicture(imageInfo, oldPath, newPath) ;
	         
	            return Response.success("修改图片成功");
		        
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
		
	  }	
	
   
}
