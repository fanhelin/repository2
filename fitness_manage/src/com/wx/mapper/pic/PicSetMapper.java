package com.wx.mapper.pic;

import java.util.List;


import com.wx.entity.pic.PicSet;
import com.wx.entity.pic.PicSetList;



public interface PicSetMapper {
  List<PicSet> selectPicSets(PicSet picSet) throws Exception ;
  
  List<PicSetList>selectPicSetList(PicSetList picSetList) throws Exception ; 
  
  int addPicSet(PicSet PicSet) throws Exception ;
  
  int modifyPicSet(PicSet picSet) throws Exception ;


  int deletePicSet(PicSet picSet ) throws Exception ;
  
  Integer getMaxPicSetListId(PicSetList picSetList) throws Exception ;
  
  int addPicSetList(PicSetList picSetList) throws Exception ;
  
  int deletePicSetList(PicSetList picSetList) throws Exception ;
  
  int modifyPicSetList(PicSetList picSetList) throws Exception ;
  
  
  
}
