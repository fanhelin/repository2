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
import com.framework.exception.RunTimeException;



import com.util.Base64Uitil;
import com.util.img.ICompress;
import com.util.img.ImageHelper;
import com.wx.entity.pic.PicSet;
import com.wx.entity.pic.PicSetList;


import com.wx.mapper.pic.PicSetMapper;
import com.wx.service.pic.PicSetService;

@Service
public class PicSetServiceImp extends BaseService implements
PicSetService {
	
	@Autowired
	PicSetMapper picSetMapper ;
	

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
			
           if(height> icbJson.getInt("height")){
				return icbJson.getDouble("height") / height ;
			}
		
			
			return 1;
		}
		
	};

	@Override
	public List<PicSet> selectPicSets(PicSet picSet) throws Exception {
		// TODO Auto-generated method stub
		return picSetMapper.selectPicSets(picSet);
	}


	@Override
	public List<PicSetList> selectPicSetList(PicSetList picSetList)
			throws Exception {
		// TODO Auto-generated method stub
		return picSetMapper.selectPicSetList(picSetList);
	}

	
	@Override
	public boolean addPicSet(PicSet picSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			int ret = picSetMapper.addPicSet(picSet) ;
			if(ret <1){
				throw new Exception("增加图片集失败") ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RunTimeException(e.getMessage()) ;
		}
		
		return true;
	}


	@Override
	public boolean modifyPicSet(PicSet picSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			int ret = picSetMapper.modifyPicSet(picSet) ;
			if(ret <1){
				throw new Exception("修改图片集失败") ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RunTimeException(e.getMessage()) ;
		}
		
		return true;
	}


	@Override
	public boolean deletePicSet(PicSet picSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			 PicSetList picSetList = new PicSetList();
			 picSetList.setSet_code(picSet.getSet_code());
			int ret = picSetMapper.getMaxPicSetListId(picSetList) ;
			if(ret>1){
				throw new Exception("该图片集下还有未删除的图片") ;
			}
			 ret = picSetMapper.deletePicSet(picSet) ;
			if(ret <1){
				throw new Exception("删除图片集失败") ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RunTimeException(e.getMessage()) ;
		}
		
		return true;
	}


	@Override
	public int getMaxPicSetListId(PicSetList picSetList) throws Exception {
		// TODO Auto-generated method stub
		
		
		Integer id= picSetMapper.getMaxPicSetListId(picSetList) ;
		id = id == null? 1 :id ;
		return id ;
	}


	@Override
	public boolean addPicSetList(PicSetList picSetList) throws Exception {
		// TODO Auto-generated method stub
		try{
		  int sum = picSetMapper.addPicSetList(picSetList) ;
  	       if(sum>0) {
	   	    	boolean ret = Base64Uitil.getFileBase64().generateImage(picSetList.getData(), picSetList.getPath()) ;
	   	   	    if(ret){
	   	   	     if( Config.getConfig(StaticFinal.IMG_COMPRESS_FLAG, "off").equalsIgnoreCase("on")){
				   	   ret =  ImageHelper.scaleImage(picSetList.getPath(), picSetList.getPath(), 0.5, picSetList.getExe_name(), iCompress) ;
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
	public boolean deletePicSetList(PicSetList picSetList, String picPath)throws Exception {
		// TODO Auto-generated method stub
		try {
			int ret = picSetMapper.deletePicSetList(picSetList) ;
			if(ret>0){
				File file = new File(picPath) ;
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
	public boolean modifyPicSetList(PicSetList picSetList, String oldPath)
			throws Exception {
		// TODO Auto-generated method stub
		try {

			int sum = picSetMapper.modifyPicSetList(picSetList) ;
			if(sum<=0){
				throw new Exception("更新数据库失败") ;
			}
			if(oldPath != null){
				File oldfile = new File(oldPath) ;
				boolean ret =oldfile.delete() ;
				if(!ret){
					//throw new Exception("删除旧图片失败") ;
				}
				
				ret = Base64Uitil.getFileBase64().generateImage(picSetList.getData(), picSetList.getPath()) ;
				if(!ret){
					throw new Exception("保存新图片失败") ;
				}
				
			  if( Config.getConfig(StaticFinal.IMG_COMPRESS_FLAG, "off").equalsIgnoreCase("on")){
				    ret =  ImageHelper.scaleImage( picSetList.getPath(),  picSetList.getPath(), 0.5, picSetList.getExe_name(), iCompress) ;
					 if(!ret){
						 throw new Exception("压缩新图片失败") ;
					 }
			  }
				
			}
			return true ;
		
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}
	
	

}
