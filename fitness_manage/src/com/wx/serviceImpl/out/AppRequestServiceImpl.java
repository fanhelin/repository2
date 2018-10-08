package com.wx.serviceImpl.out;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.framework.base.BaseService;

import com.wx.entity.commodity.CommodityImg;
import com.wx.entity.commodity.CommodityInfo;
import com.wx.entity.commodity.CommodityType;
import com.wx.entity.out.OutPicInfo;
import com.wx.entity.out.OutPicSetDetail;
import com.wx.entity.out.OutTextInfo;
import com.wx.entity.pic.ImageInfo;
import com.wx.entity.pic.PicSet;
import com.wx.entity.rcv.RcvInfo;
import com.wx.entity.register.LicenseType;
import com.wx.entity.register.Station;
import com.wx.entity.sys.AppInfo;

import com.wx.mapper.out.AppRequestMapper;
import com.wx.mapper.rcv.RcvInfoMapper;
import com.wx.mapper.register.RegisterMapper;
import com.wx.service.out.AppRequestService;

@Service
public class AppRequestServiceImpl extends BaseService implements
		AppRequestService {
	
	@Autowired
	AppRequestMapper appRequestMapper ;
	
	@Autowired
	RcvInfoMapper rcvInfoMapper ;
	
	@Autowired
	RegisterMapper registerMapper ;

	@Override
	public JSONObject loadPicTexts(String app_info_code) throws Exception {
		// TODO Auto-generated method stub
		/*ImageInfo imageInfo = new ImageInfo() ;
		imageInfo.setApp_info_code(app_info_code) ;
		List<OutPicInfo> iiList =  appRequestMapper.selectPictures(imageInfo);
		
		TextInfo textInfo = new TextInfo();
		textInfo.setApp_info_code(app_info_code) ;
		List<OutTextInfo> tiList = appRequestMapper.selectTexts(textInfo) ;
		
		JSONObject retObject = new JSONObject() ;
		retObject.put("images", JSONArray.fromObject(iiList)) ;
		
		retObject.put("texts", JSONArray.fromObject(tiList)) ;
		
		PicSet picSet = new PicSet() ;
		picSet.setApp_info_code(app_info_code) ;
		List<OutPicSetDetail> picSetLists = appRequestMapper.selectPicSetLists(picSet) ; 
		
		JSONObject picSetsJson = new JSONObject() ;
		
		for(OutPicSetDetail psl : picSetLists){
			if(picSetsJson.containsKey(psl.getSet_code())){
				picSetsJson.getJSONArray(psl.getSet_code()).add(psl);
			}else{
				JSONArray arry= new JSONArray() ;
				arry.add(psl);
				picSetsJson.put(psl.getSet_code(), arry) ;
			}
		}
		
		retObject.put("imageSets",picSetsJson) ;
		AppInfo appInfo = new AppInfo() ;
		appInfo.setApp_info_code(app_info_code);
		appInfo =  appRequestMapper.selectAppInfo(appInfo);
		retObject.put("appInfo", appInfo) ;*/
		return null ;
	}

	
	@Override
	public JSONObject loadAppInfo(String app_info_code) throws Exception {
		// TODO Auto-generated method stub
		
		/*JSONObject retObject = new JSONObject() ;
		
		AppInfo appInfo = new AppInfo() ;
		appInfo.setApp_info_code(app_info_code);
		appInfo =  appRequestMapper.selectAppInfo(appInfo);
		retObject.put("appInfo", appInfo) ;
		
		ImageInfo imageInfo = new ImageInfo() ;
		imageInfo.setApp_info_code(app_info_code) ;
		List<OutPicInfo> iiList =  appRequestMapper.selectPictures(imageInfo);
		
		TextInfo textInfo = new TextInfo();
		textInfo.setApp_info_code(app_info_code) ;
		List<OutTextInfo> tiList = appRequestMapper.selectTexts(textInfo) ;
		
		
		retObject.put("images", JSONArray.fromObject(iiList)) ;
		
		retObject.put("texts", JSONArray.fromObject(tiList)) ;
		
		PicSet picSet = new PicSet() ;
		picSet.setApp_info_code(app_info_code) ;
		List<OutPicSetDetail> picSetLists = appRequestMapper.selectPicSetLists(picSet) ; 
		
		JSONObject picSetsJson = new JSONObject() ;
		
		for(OutPicSetDetail psl : picSetLists)
		{
			if(picSetsJson.containsKey(psl.getSet_code())){
				picSetsJson.getJSONArray(psl.getSet_code()).add(psl);
			}else{
				JSONArray arry= new JSONArray() ;
				arry.add(psl);
				picSetsJson.put(psl.getSet_code(), arry) ;
			}
		}
		
		retObject.put("imageSets",picSetsJson) ;
		
		if(appInfo.getIs_olt().equals(1)){
			CommodityType commodityType = new CommodityType();
			commodityType.setApp_info_code(app_info_code) ;
			List<CommodityType> ctList = appRequestMapper.selectCommodityType(commodityType) ;
			JSONArray comdTypes =  JSONArray.fromObject(ctList);
			for(int i=0 ;i< comdTypes.size() ;i++){
				JSONObject object=(JSONObject) comdTypes.get(i) ;
				object.remove("app_info_code") ;
			}
			retObject.put("comdTypes" , comdTypes) ;
			
		}
		return retObject ;*/
		return null ;

	}

	@Override
	public List<CommodityInfo> getCommodityList(CommodityInfo commodifyInfo)
			throws Exception {
		// TODO Auto-generated method stub
		
		return appRequestMapper.selectCommodityInfo(commodifyInfo);
	}


	@Override
	public List<CommodityImg> selectCommodityImgs(CommodityImg commodityImg)
			throws Exception {
		    // TODO Auto-generated method stub
		return appRequestMapper.selectCommodityImgs(commodityImg);
	}


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


	@Override
	public JSONObject getAppInfo(AppInfo appInfo) throws Exception {
		// TODO Auto-generated method stub

		JSONObject retObject = new JSONObject() ;
		AppInfo retInfo = appRequestMapper.selectAppInfo(appInfo);
		retObject.put("appInfo", retInfo) ;
		
		LicenseType licenseType = new LicenseType() ;
		licenseType.setApp_info_code(appInfo.getApp_info_code()) ;
		List<LicenseType> lsts = registerMapper.selectLicenseTypes(licenseType) ;
		retObject.put("licenseType", JSONArray.fromObject(lsts)) ;
		
		Station station = new Station() ;
		station.setApp_info_code(appInfo.getApp_info_code()) ;
		List<Station> stas = registerMapper.selectStations(station) ;
		retObject.put("station", JSONArray.fromObject(stas)) ;
		
		
		
		return retObject ;
		
		
		
	}

}
