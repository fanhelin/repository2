package com.wx.service.common;

import java.util.List;


import com.wx.entity.common.CarClass;

import com.wx.entity.common.CargoType;
import com.wx.entity.common.Industry;
import com.wx.entity.common.PackageUnit;
import com.wx.entity.common.QueryEntity;
import com.wx.entity.common.VehicleType;
import com.wx.entity.common.UseCharacter;
import com.wx.entity.common.TruckType;

public interface CommonService {
   public int getSequence(String tableName) ;
   public List<TruckType> queryTruck_Type(TruckType truckType);
   public List<VehicleType> queryVehicleType(VehicleType vehicleType);
   public List<CarClass> queryCarClass(CarClass carClass) throws Exception ;
   public List<UseCharacter> queryUseCharacter(UseCharacter useCharacter) throws Exception ;

   public List<Industry>queryIndustrys()throws Exception ;
   public List<PackageUnit>queryPackageUnits()throws Exception ;
   public List<CargoType>queryCargoTypes()throws Exception ;

   public boolean isTaskCarArrive(QueryEntity queryEntity) throws Exception ;
   
   public boolean isTaskCarDepart(QueryEntity queryEntity) throws Exception ;
   
   
}
