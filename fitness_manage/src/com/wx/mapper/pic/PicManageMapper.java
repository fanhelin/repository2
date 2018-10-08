package com.wx.mapper.pic;

import java.util.List;

import com.wx.entity.pic.ImageInfo;



public interface PicManageMapper {
  List<ImageInfo> selectPictures(ImageInfo imageInfo) throws Exception ;
  
  int addPicture(ImageInfo imageInfo) throws Exception ;
  
  Integer selectMaxImageId() throws Exception ;
  
  int deletePicture(ImageInfo imageInfo) throws Exception ;
  
  int updatePicture(ImageInfo imageInfo) throws Exception ;
  
}
