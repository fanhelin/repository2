package com.wx.service.score;

import java.util.List;
import java.util.Map;

import com.util.PageBean;
import com.wx.entity.score.Score;

public interface ScoreService {
	public PageBean<Score> findScoreList(Map<String,Object> map)throws Exception;
	
	public void updateScoreOrderState(Score score)throws Exception;
	
	public void addExchangeOrder(Score score) throws Exception ;
	
	public List<Score> loadHisExs(Score score) throws Exception ;
}
