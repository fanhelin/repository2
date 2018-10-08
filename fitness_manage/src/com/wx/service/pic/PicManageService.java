package com.wx.service.pic;

import java.util.List;

import com.wx.entity.pic.ImageInfo;

public interface PicManageService {
	List<ImageInfo> selectPictures(ImageInfo imageInfo) throws Exception ;
	
    boolean addPicture(ImageInfo imageInfo) throws Exception ;
    
    Integer getMaxImageId() throws Exception ;
    
    boolean deletePicture(ImageInfo imageInfo ,String path) throws Exception ;
    
    boolean modifyPicture(ImageInfo info, String oldPath, String newPath) throws Exception ;
}
