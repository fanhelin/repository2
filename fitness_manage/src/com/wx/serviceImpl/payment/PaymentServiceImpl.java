package com.wx.serviceImpl.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.util.FileHelper;
import com.util.PageBean;
import com.util.UUIDUntil;
import com.util.UUIDUntil.CaseEnum;
import com.wx.entity.course.CourseOrder;
import com.wx.entity.pay.NotifyInfo;
import com.wx.entity.payment.WxPayment;
import com.wx.mapper.course.CourseMapper;
import com.wx.mapper.payment.PaymentMapper;
import com.wx.redis.JRedisClient;
import com.wx.service.payment.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
   
	
	@Autowired
	private PaymentMapper paymentMapper ;
	
	@Autowired
	private CourseMapper courseMapper ;

	@Override
	public PageBean<WxPayment> findPaymentList(Map<String,Object> map) throws Exception {
		// TODO Auto-generated method stub
		PageBean<WxPayment> pageBean=new PageBean<WxPayment>(Integer.parseInt(map.get("rows").toString()),Integer.parseInt(map.get("page").toString()));
		pageBean.setTotal(this.paymentMapper.findPaymentTotal(map));
		map.put("currentLine", (pageBean.getPageNumber()-1)*pageBean.getPageSize());
		pageBean.setRows(this.paymentMapper.findPaymentList(map));
		return pageBean;
	}


	@Override
	public boolean addPayment(NotifyInfo notifyInfo,JRedisClient redis,int WX_NOTIFY_EXPIRE) throws Exception {
		// TODO Auto-generated method stub
    try{
		 int ret = paymentMapper.addPayment(notifyInfo) ;
		 if(ret != 1){
			 throw new Exception("写入订单记录失败") ;
		 }
		 String attchkey = notifyInfo.getAttach() ;  //attch 中保留了 订单明细 保存于redis中内容 对应的key
		 /*    attch:{appInfo:{} , goods:[{course_code:'code1',course_name:'name1',price: 300},{course_code:'code1',course_name:'name1',price: 300}]} 
		  * */
		String attch =  redis.get(attchkey) ;
		 
		FileHelper.getInstance().writeFile("++++++++++++++++++++++++", "payment/redis.log", false) ; //用于测试
		FileHelper.getInstance().writeFile("attchkey="+ attchkey, "payment/redis.log", true) ;
		FileHelper.getInstance().writeFile("------------------------", "payment/redis.log", true) ;
	    FileHelper.getInstance().writeFile("attch="+attch, "payment/redis.log", true) ;
	    FileHelper.getInstance().writeFile("------------------------", "payment/redis.log", true) ;
		 
		 
		 if(attch != null && !"".equals(attch)){
			 try{
			 JSONObject attchJson = JSONObject.fromObject(attch) ;
			 if(attchJson != null){
				 List<CourseOrder> list = new ArrayList<CourseOrder>() ;
				 JSONObject appInfo = attchJson.getJSONObject("appInfo") ;
				 JSONArray goodsArray = attchJson.getJSONArray("goods") ;
				 if(appInfo != null && goodsArray.size()>0){
					
					 for(int i=0 ;i< goodsArray.size() ;i++){
						 JSONObject item = goodsArray.getJSONObject(i) ;
						 CourseOrder courseOrder = new CourseOrder(); 
						 courseOrder.setApp_info_code(appInfo.getString("app_info_code")) ;
						 courseOrder.setCode(UUIDUntil.genUUID(26, "co_", CaseEnum.LOWER)) ;
						 courseOrder.setCourse_code(item.getString("course_code")) ;
						 courseOrder.setCourse_name(item.getString("course_name")) ;
						 courseOrder.setNonce_str(notifyInfo.getNonce_str()) ;
						 courseOrder.setOpenid(notifyInfo.getOpenid()) ;
						 courseOrder.setPrice(item.getDouble("price")) ;
						 list.add(courseOrder) ;
					 }
					 if(list.size()>0){
						 ret = courseMapper.addCourseOrder(list) ; 
					 }
				 }
				
			 }
			 
		
			 }catch(Exception e){
				 
				 e.printStackTrace() ;
			 }
			
		 }
		 
		 String key = notifyInfo.getNonce_str() + notifyInfo.getOut_trade_no() ;
		 if(key == null || "".equals(key)){
		     redis.expire(key, WX_NOTIFY_EXPIRE) ; //成功就保存对应的key //将通知里的主键写入redis，防止重复通知。
		 }
		 return true ;
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace() ;
	    	return false ;
		}
	}

}
