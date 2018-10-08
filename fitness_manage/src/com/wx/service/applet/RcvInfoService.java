package com.wx.service.applet;

import java.util.List;

import com.wx.entity.rcv.RcvInfo;

public interface RcvInfoService {

    List<RcvInfo> selectRcvInfos(RcvInfo rcvInfo) throws Exception ;
    
    boolean setDefaultRcv(RcvInfo rcvInfo) throws Exception;
    
    boolean addRcvInfo(RcvInfo rcvInfo) throws Exception ;
    
    boolean deleteRcvInfo(RcvInfo rcvInfo) throws Exception ;
    
    boolean modifyRcvInfo(RcvInfo rcvInfo) throws Exception ;
}
