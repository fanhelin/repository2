package com.wx.mapper.practice;

import java.util.List;


import java.util.Map;

import com.wx.entity.practice.Practice;




public interface PracticeMapper {
	
  List<Practice> selectPractice(Map<?,?> param) throws Exception ;
  
  void addPractice(Practice practice)throws Exception;
  

}
