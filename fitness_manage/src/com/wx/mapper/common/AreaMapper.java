package com.wx.mapper.common;

import java.util.List;

import com.wx.entity.common.Area;

/**
 * 
 * @todo 日志接口
 *
 * @author fhr
 * @dateTime 2016 2016-11-29 下午2:12:27
 */
public interface AreaMapper {
   List<Area> selectAreas(Area area) throws Exception ;

}
