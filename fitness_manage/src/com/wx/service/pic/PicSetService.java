package com.wx.service.pic;

import java.util.List;



import com.wx.entity.pic.PicSet;
import com.wx.entity.pic.PicSetList;

public interface PicSetService {
	List<PicSet> selectPicSets(PicSet picSet) throws Exception ;
	
	List<PicSetList>selectPicSetList(PicSetList picSetList) throws Exception ; 
	
    boolean addPicSet(PicSet picSet) throws Exception ;

    boolean modifyPicSet(PicSet picSet) throws Exception ;

    boolean deletePicSet(PicSet picSet ) throws Exception ;
    
    int getMaxPicSetListId(PicSetList picSetList) throws Exception ;
    
    boolean addPicSetList(PicSetList picSetList) throws Exception ;
    
    boolean deletePicSetList(PicSetList picSetList ,String picPath) throws Exception ;
    
    boolean modifyPicSetList(PicSetList picSetList,String oldPath) throws Exception ;
    
}
