package com.wx.controller.score;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Response;
import com.util.PageBean;
import com.wx.entity.score.Score;
import com.wx.service.score.ScoreService;

@Controller
@RequestMapping("/score")
public class ScoreController extends BaseController{
	@Autowired
	ScoreService ScoreService ;
	
	@ResponseBody
	@RequestMapping("/findScoreList.do")
	public String findScoreList(@RequestParam Map<String,Object> score){
		
		try {
			PageBean<Score> page = ScoreService.findScoreList(score) ;
			JSONObject ret = new JSONObject() ;
			ret.put("total", page.getTotal()) ;
			ret.put("rows", JSONArray.fromObject(page.getRows())) ;
			return ret.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return retEmptyJsonObj().toString() ;
		}
	}
	
	@ResponseBody
	@RequestMapping("/updateScoreOrderState.do")
	public Response updateScoreOrderState(Score score){
		try {
			this.ScoreService.updateScoreOrderState(score);
			return Response.success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
	@ResponseBody
	@RequestMapping("/exchangeScore.do")
	public Response exchangeScore(Score score){
		try {
			this.ScoreService.addExchangeOrder(score);
			return Response.success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
	@ResponseBody
	@RequestMapping("/loadHisExs.do")
	public Response loadHisExs(Score score){
		try {
			List<Score>list = ScoreService.loadHisExs(score);
			return Response.success("加载历史兑换成功",list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
}
