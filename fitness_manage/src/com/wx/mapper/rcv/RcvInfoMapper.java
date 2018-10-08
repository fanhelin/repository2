package com.wx.mapper.rcv;

import java.util.List;


import com.wx.entity.rcv.RcvInfo;


public interface RcvInfoMapper {
   List<RcvInfo> selectRcvInfos(RcvInfo rcvInfo) throws Exception ;
  
   int updateRcvInfos(RcvInfo rcvInfo) throws Exception ;
  
   int addRcvInfo(RcvInfo rcvInfo) throws Exception ;
  
   int deleteRcvInfo(RcvInfo rcvInfo) throws Exception ;
   
   
}
