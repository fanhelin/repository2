package com.wx.mapper.common;

import java.util.HashMap;


import java.util.List;

import com.wx.entity.common.CarClass;

import com.wx.entity.common.CargoType;
import com.wx.entity.common.Industry;
import com.wx.entity.common.PackageUnit;
import com.wx.entity.common.QueryEntity;
import com.wx.entity.common.TruckType;
import com.wx.entity.common.VehicleType;
import com.wx.entity.common.UseCharacter;

public interface CommonMapper 
{
  public int querySequence(HashMap tableName) throws Exception;
  public List<TruckType> queryTruck_Type(TruckType truckType);
  public List<VehicleType> queryVehicleType(VehicleType vehicleType);
  public List<CarClass> queryCarClass(CarClass carClass) throws Exception ;
  public List<UseCharacter>queryUseCharacter(UseCharacter seCharacter) throws Exception;
  
  public List<Industry>queryIndustrys()throws Exception ;
  public List<PackageUnit>queryPackageUnits()throws Exception ;
  public List<CargoType>queryCargoTypes()throws Exception ;

  public int queryCarArriveState(QueryEntity queryEntity)throws Exception ;
  public int queryCarDepart(QueryEntity queryEntity)throws Exception ;
  
}
