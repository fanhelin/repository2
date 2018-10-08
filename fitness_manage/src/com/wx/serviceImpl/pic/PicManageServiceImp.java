package com.wx.serviceImpl.pic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.framework.common.Config;
import com.framework.common.StaticFinal;


import com.util.Base64Uitil;
import com.util.img.ICompress;
import com.util.img.ImageHelper;
import com.wx.entity.pic.ImageInfo;
import com.wx.mapper.pic.PicManageMapper;
import com.wx.service.pic.PicManageService;

@Service
public class PicManageServiceImp extends BaseService implements
		PicManageService {
	
	@Autowired
	PicManageMapper picManageMapper ;
    
	private ICompress iCompress = new ICompress(){

		@Override
		public boolean needCompress(BufferedImage bufferedImage,String exe) {
			// TODO Auto-generated method stub
			if("gif".equals(exe)){
				return false ;
			}
			
			String icbStr=Config.getConfig(StaticFinal.IMG_COMPRESS_BOUND, "{width:300,height:400}");
			JSONObject icbJson = JSONObject.fromObject(icbStr) ;
		
			int width = bufferedImage.getWidth();
		    int height = bufferedImage.getHeight();
		    if(width < icbJson.getInt("width") && height< icbJson.getInt("height")){
		    	return false ;
		    }
			
			return true;
		}

		@Override
		public double getScale(BufferedImage bufferedImage) {
			// TODO Auto-generated method stub
			int width = bufferedImage.getWidth();
		    int height = bufferedImage.getHeight();
			String icbStr=Config.getConfig(StaticFinal.IMG_COMPRESS_BOUND, "{width:300,height:400}");
			
			JSONObject icbJson = JSONObject.fromObject(icbStr) ;
			if(width > icbJson.getInt("width")){
				
				return icbJson.getDouble("width") / width ;
			}
			
           if(height > icbJson.getInt("height")){
				return icbJson.getDouble("height") / height ;
			}
           
           return 1 ;
		
		}
		
	};
	
	@Override
	public List<ImageInfo> selectPictures(ImageInfo imageInfo) throws Exception {
		// TODO Auto-generated method stub
		return picManageMapper.selectPictures(imageInfo);
	}

	@Override
	public boolean addPicture(ImageInfo imageInfo)
			throws Exception {
		 // TODO Auto-generated method stub
	 	try{
	
   	       int sum = picManageMapper.addPicture(imageInfo) ;
   	       if(sum>0) {
	   	    	boolean ret = Base64Uitil.getFileBase64().generateImage(imageInfo.getData(), imageInfo.getPath()) ;
	   	   	    if(ret){
	   	   	       if( Config.getConfig(StaticFinal.IMG_COMPRESS_FLAG, "off").equalsIgnoreCase("on")){
		   	   	       ret =  ImageHelper.scaleImage(imageInfo.getPath(), imageInfo.getPath(), 0.5, imageInfo.getExe_name(), iCompress) ;
		   	   	       if(!ret){
		   	   	    	   	throw new Exception("压缩图片失败");
		   	   	       }
	   	   	       }
	   	   	    }else{
	   	   	     throw new Exception("保存图片失败");
	   	   	    }
   	       }else{
   	    	   throw new Exception("写人数据库失败");
   	       }
   	   
          return true ;
	 	}catch (Exception e) {
			// TODO: handle exception
	 		throw new RuntimeException(e.getMessage()) ;
		}
	}

	@Override
	public Integer getMaxImageId() throws Exception {
		// TODO Auto-generated method stub
		Integer id= picManageMapper.selectMaxImageId() ;
		id = id == null? 1 :id ;
		return id ;
	}

	@Override
	public boolean deletePicture(ImageInfo imageInfo, String path)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			int ret = picManageMapper.deletePicture(imageInfo) ;
			
			if(ret>0){
				File file = new File(path) ;
				if(file.exists() && file.isFile()){
					file.delete() ;
				}
			}else{
				throw new Exception("删除图片失败") ;
			}
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage()) ;
		}
	
	}

	@Override
	public boolean modifyPicture(ImageInfo info, String oldPath, String newPath)
			throws Exception {
		// TODO Auto-generated method stub
		try {

			
			int sum = picManageMapper.updatePicture(info) ;
			if(sum<=0){
				throw new Exception("更新数据库失败") ;
			}
			
			File oldfile = new File(oldPath) ;
			boolean ret =oldfile.delete() ;
			if(!ret){
				//throw new Exception("删除旧图片失败") ;
			}
			
			ret = Base64Uitil.getFileBase64().generateImage(info.getData(), newPath) ;
			if(!ret){
				throw new Exception("保存新图片失败") ;
			}
			 if( Config.getConfig(StaticFinal.IMG_COMPRESS_FLAG, "off").equalsIgnoreCase("on")){
				 ret =  ImageHelper.scaleImage(newPath, newPath, 0.5, info.getExe_name(), iCompress) ;
				 if(!ret){
					 throw new Exception("压缩新图片失败") ;
				 }
			  }
		
			return true ;
		
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}
	
	

}
