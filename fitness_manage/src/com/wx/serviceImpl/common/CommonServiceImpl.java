package com.wx.serviceImpl.common;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;

import com.wx.entity.common.CarClass;

import com.wx.entity.common.CargoType;
import com.wx.entity.common.Industry;
import com.wx.entity.common.PackageUnit;
import com.wx.entity.common.QueryEntity;
import com.wx.entity.common.TruckType;
import com.wx.entity.common.UseCharacter;
import com.wx.entity.common.VehicleType;
import com.wx.service.common.CommonService;
import com.wx.mapper.common.CommonMapper;

@Service("commonService")
public class CommonServiceImpl extends BaseService implements CommonService{
	
	@Autowired
	private CommonMapper commonMapper ;
	
	public int getSequence(String tableName){
		int ret =0 ;
		
		try {
			HashMap params = new HashMap();
			params.put("tableName", tableName) ;
			ret = commonMapper.querySequence(params) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0 ;
		}
		
		return ret ;
	}

	@Override
	public List<TruckType> queryTruck_Type(TruckType truckType) {
		// TODO Auto-generated method stub
		
		return commonMapper.queryTruck_Type(truckType);
	}

	@Override
	public List<VehicleType> queryVehicleType(VehicleType vehicleType) {
		// TODO Auto-generated method stub
		return commonMapper.queryVehicleType(vehicleType);
	}

	@Override
	public List<CarClass> queryCarClass(CarClass carClass) throws Exception {
		// TODO Auto-generated method stub
		return commonMapper.queryCarClass(carClass);
	}

	@Override
	public List<UseCharacter> queryUseCharacter(UseCharacter useCharacter)
			throws Exception {
		// TODO Auto-generated method stub
		return commonMapper.queryUseCharacter(useCharacter);
	}

	

	@Override
	public List<Industry> queryIndustrys() throws Exception {
		// TODO Auto-generated method stub
		return commonMapper.queryIndustrys();
	}

	@Override
	public List<PackageUnit> queryPackageUnits() throws Exception {
		// TODO Auto-generated method stub
		return commonMapper.queryPackageUnits();
	}

	@Override
	public List<CargoType> queryCargoTypes() throws Exception {
		// TODO Auto-generated method stub
		return commonMapper.queryCargoTypes();
	}

	@Override
	public boolean isTaskCarArrive(QueryEntity queryEntity) throws Exception {
		// TODO Auto-generated method stub
		int count = commonMapper.queryCarArriveState(queryEntity) ;
		if(count ==0 ){
			return false ;
		}else{
			return true;
		}
		
	}

	@Override
	public boolean isTaskCarDepart(QueryEntity queryEntity) throws Exception {
		// TODO Auto-generated method stub
		int count = commonMapper.queryCarDepart(queryEntity) ;
		if(count ==0 ){
			return false ;
		}else{
			return true;
		}
	}

	

	
}
