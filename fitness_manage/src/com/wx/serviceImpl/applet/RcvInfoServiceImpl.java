package com.wx.serviceImpl.applet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.wx.entity.rcv.RcvInfo;
import com.wx.mapper.rcv.RcvInfoMapper;
import com.wx.service.applet.RcvInfoService;

@Service
public class RcvInfoServiceImpl extends BaseService implements RcvInfoService {

	
	@Autowired
	RcvInfoMapper rcvInfoMapper ;

	@Override
	public List<RcvInfo> selectRcvInfos(RcvInfo rcvInfo) throws Exception {
		// TODO Auto-generated method stub
		return rcvInfoMapper.selectRcvInfos(rcvInfo);
	}


	@Override
	public boolean setDefaultRcv(RcvInfo rcvInfo) throws Exception {
		// TODO Auto-generated method stub
		try{
			String rcvCode = rcvInfo.getRcv_code();
			rcvInfo.setRcv_code(null) ;
			rcvInfo.setIsDefault(0) ;
			int ret = rcvInfoMapper.updateRcvInfos(rcvInfo) ;
			if(ret >0){
				rcvInfo.setRcv_code(rcvCode) ;
				rcvInfo.setIsDefault(1) ;
				ret = rcvInfoMapper.updateRcvInfos(rcvInfo) ;
				if(ret == 1){
					return true ;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		    throw new RuntimeException(e.getMessage()) ;
		}
		
		return false;
	}


	@Override
	public boolean addRcvInfo(RcvInfo rcvInfo) throws Exception {
		// TODO Auto-generated method stub
		try{
			int ret = rcvInfoMapper.addRcvInfo(rcvInfo) ;
			return ret==1;
			
		}catch (Exception e) {
			// TODO: handle exception
			  throw new RuntimeException(e.getMessage()) ;
		}	
	}

	@Override
	public boolean deleteRcvInfo(RcvInfo rcvInfo) throws Exception {
		// TODO Auto-generated method stub
		try{
			int ret = rcvInfoMapper.deleteRcvInfo(rcvInfo) ;
			return ret == 1;
			
		}catch (Exception e) {
			// TODO: handle exception
			  throw new RuntimeException(e.getMessage()) ;
		}
	}


	@Override
	public boolean modifyRcvInfo(RcvInfo rcvInfo) throws Exception {
		// TODO Auto-generated method stub
		try{
			int ret = rcvInfoMapper.updateRcvInfos(rcvInfo) ;
			return ret > 0;
			
		}catch (Exception e) {
			// TODO: handle exception
			  throw new RuntimeException(e.getMessage()) ;
		}
	}

}
