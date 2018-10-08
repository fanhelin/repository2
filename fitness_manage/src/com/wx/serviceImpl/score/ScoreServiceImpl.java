package com.wx.serviceImpl.score;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.util.PageBean;
import com.util.UUIDUntil;
import com.util.UUIDUntil.CaseEnum;
import com.wx.entity.client.Client;
import com.wx.entity.commodity.CommodityInfo;
import com.wx.entity.score.Score;
import com.wx.mapper.client.ClientMapper;
import com.wx.mapper.commodity.CommodityInfoMapper;
import com.wx.mapper.score.ScoreMapper;
import com.wx.service.score.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService{
	
	 @Autowired
	 ScoreMapper mapper ;
	 
	 @Autowired
	 ClientMapper clientMapper ;
	 
	 @Autowired
	 CommodityInfoMapper commodityInfoMapper ;


	@Override
	public PageBean<Score> findScoreList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		PageBean<Score> pageBean=new PageBean<Score>(Integer.parseInt(map.get("rows").toString()),Integer.parseInt(map.get("page").toString()));
		pageBean.setTotal(this.mapper.findScoreTotal(map));
		map.put("currentLine", (pageBean.getPageNumber()-1)*pageBean.getPageSize());
		pageBean.setRows(this.mapper.findScoreList(map));
		return pageBean;
	}

	@Override
	public void updateScoreOrderState(Score score) throws Exception {
		// TODO Auto-generated method stub
		this.mapper.updateScoreState(score);
	}

	@Override
	public void addExchangeOrder(Score score) throws Exception {
		// TODO Auto-generated method stub
		try {
			score.setCode(UUIDUntil.genUUID(30, "ex_", CaseEnum.LOWER )) ;
			int ret = mapper.addExchangeOrder(score);
			if(ret<1){
				throw new Exception("插入兑换记录错误，兑换失败");
			}
			Client client = new Client();
			client.setOpenid(score.getOpenid());
			client.setApp_info_code(score.getApp_info_code()) ;
			client.setScore(score.getLeftScore()) ;
			ret = clientMapper.updateClient(client) ;
			
			if(ret<1){
				throw new Exception("修改积分错误，兑换失败");
			}
			
			CommodityInfo commodityInfo = new CommodityInfo() ;
			commodityInfo.setApp_info_code(score.getApp_info_code()) ;
			commodityInfo.setCode(score.getCom_code()) ;
			commodityInfo.setReduceSum(1) ; //库存减少一件
			ret = commodityInfoMapper.updateCommodityInfo(commodityInfo) ;
			if(ret<1){
				throw new Exception("修改商品库存错误，兑换失败");
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        throw new RuntimeException(e.getMessage()) ;
		}
		
	}

	@Override
	public List<Score> loadHisExs(Score score) throws Exception {
		// TODO Auto-generated method stub
		return mapper.loadHisExs(score);
	}
	
	

}
