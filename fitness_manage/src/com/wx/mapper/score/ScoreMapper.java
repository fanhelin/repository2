package com.wx.mapper.score;

import java.util.List;
import java.util.Map;

import com.wx.entity.score.Score;

public interface ScoreMapper {
	public List<Score> findScoreList(Map<?,?> map) throws Exception;
	
	public int findScoreTotal(Map<?,?> map) throws Exception;
	
	public void updateScoreState(Score score) throws Exception;
	
	public int addExchangeOrder(Score score) throws Exception ;
	
	public List<Score> loadHisExs(Score score) throws Exception ;

}
