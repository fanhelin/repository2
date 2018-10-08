package com.wx.controller.payment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Config;
import com.framework.common.Response;
import com.framework.common.StaticFinal;
import com.util.FileHelper;
import com.util.NetworkUtil;
import com.util.PageBean;
import com.util.UUIDUntil;
import com.util.UUIDUntil.CaseEnum;
import com.util.pay.WxPayUtil;
import com.util.pay.model.OrderInfo;
import com.util.pay.model.OrderReturnInfo;
import com.wx.entity.pay.NotifyInfo;
import com.wx.entity.payment.WxPayment;
import com.wx.entity.sys.AppInfo;
import com.wx.redis.JRedisClient;
import com.wx.service.payment.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController extends BaseController{

	Integer WX_NOTIFY_EXPIRE = Integer.parseInt(Config.getConfig(StaticFinal.WX_NOTIFY_EXPIRE, "3700")) ;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private JRedisClient redis ;

	@ResponseBody
	@RequestMapping("/findPaymentList.do")
	public JSONObject findPaymentList(@RequestParam Map<String,Object> payment){
		try {
			PageBean<WxPayment> pageBean = paymentService.findPaymentList(payment);
			JSONObject ret = new JSONObject() ;
			ret.put("total", pageBean.getTotal()) ;
			ret.put("rows", JSONArray.fromObject(pageBean.getRows()));
			return ret;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return retEmptyJsonObj() ;
		}
	
	}
    
/*	@ResponseBody
	@RequestMapping("/addPayment.do")
	public Response addPayment(WxPayment payment){
		try {
			this.paymentService.addPayment(payment);
			return Response.success("支付成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}*/
	

	@RequestMapping("/unifiedOrder.do")
	@ResponseBody
	private Response unifiedOrder(AppInfo appInfo,OrderInfo orderInfo,String commodity ,HttpServletRequest request){
		try {
			WxPayUtil wxp = new WxPayUtil() ;
			orderInfo.setAppid(appInfo.getApp_id()) ;
			orderInfo.setNonce_str(null);
			orderInfo.setNotify_url(null) ;
			orderInfo.setOut_trade_no(null);
			String spbill_create_ip = NetworkUtil.getIpAddress(request) ;
			orderInfo.setSpbill_create_ip(spbill_create_ip) ;
			
			String goodsKey = UUIDUntil.genUUID(32, "payNo_", CaseEnum.LOWER) ;
			orderInfo.setAttach(goodsKey) ;
			
			OrderReturnInfo orderReturnInfo = wxp.unifedOrder(orderInfo, appInfo.getMch_key()) ;
			if(orderReturnInfo == null){
				return Response.error("下单失败") ;
			}else{
				if(orderReturnInfo.getReturn_code().equalsIgnoreCase("FAIL")) {
					return Response.error("下单失败") ;
				}
				
				JSONObject retObject = wxp.reSign(appInfo,orderReturnInfo.getPrepay_id() ) ;
				if(retObject == null){
					return Response.error("再签名失败") ;
				}else{
					retObject.put("prepay_id",orderReturnInfo.getPrepay_id()) ;
					retObject.put("redisKey", goodsKey) ;
					this.redis.setex(goodsKey, WX_NOTIFY_EXPIRE , commodity) ; //将商品明细保存到redis，支付成功后写入数据库
					
					FileHelper.getInstance().writeFile("++++++++++++++++++++++++", "payment/payment.log", false) ; //用于测试
					FileHelper.getInstance().writeFile("goodsKey="+goodsKey, "payment/payment.log", true) ;
					FileHelper.getInstance().writeFile("------------------------", "payment/payment.log", true) ;
					FileHelper.getInstance().writeFile("commodity="+commodity, "payment/payment.log", true) ;
					FileHelper.getInstance().writeFile("------------------------", "payment/payment.log", true) ;
					
					return Response.success("下单成功", retObject) ;
				}
		
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return Response.error(e.getMessage()) ;
		}	
	}
	
	@RequestMapping("/reSign.do")
	@ResponseBody
	private Response reSign(AppInfo appInfo,String prepay_id){
	try {
			WxPayUtil wxp = new WxPayUtil() ;
			JSONObject retObject = wxp.reSign(appInfo, prepay_id) ;
			if(retObject == null){
				return Response.error("再签名失败") ;
			}else{
				return Response.success("再签名成功", retObject) ;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return Response.error(e.getMessage()) ;
		}	
	}
	
	@RequestMapping("/payResult.do")
	@ResponseBody
	private void payResult(HttpServletRequest request,HttpServletResponse response){
		try {
			WxPayUtil wxp = new WxPayUtil() ;
			NotifyInfo result = wxp.payResult(request) ;
			
			if(result == null || !result.getReturn_code().equals("SUCCESS")){
				return ;
			}
			String handler = redis.get(result.getNonce_str() + result.getOut_trade_no() ) ;
			if(handler == null || "".equals(handler)){
				boolean ret = paymentService.addPayment(result,redis ,WX_NOTIFY_EXPIRE) ;
				if(ret){
					response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
				}
			}else{ //redis中已存在key，说明是重复通知
				    response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
			}
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
  }
	
	@RequestMapping("/payResult_test.do")
	@ResponseBody
	private void payResult_test(HttpServletRequest request,HttpServletResponse response){
		try {
			WxPayUtil wxp = new WxPayUtil() ;
			
			String attch = request.getParameter("attch") ;
			JSONObject attchJsonObject = JSONObject.fromObject(attch) ;
			JSONObject appInfoJsonObject = attchJsonObject.getJSONObject("appInfo") ;
			
			String reqParams ="<xml><appid><![CDATA["+appInfoJsonObject.getString("appid")+"]]></appid>";
			reqParams += "<bank_type><![CDATA[CCB_DEBIT]]></bank_type>";
			reqParams += "<cash_fee><![CDATA[1]]></cash_fee>";
			reqParams += "<fee_type><![CDATA[CNY]]></fee_type>";
			reqParams += "<is_subscribe><![CDATA[N]]></is_subscribe>";
			reqParams += "<mch_id><![CDATA[1493428482]]></mch_id>";
			reqParams += "<nonce_str><![CDATA[bo9w9n2i5prqmif9aab8py9ivtdqwl0d]]></nonce_str>";
			reqParams += "<openid><![CDATA["+appInfoJsonObject.getString("openid")+"]]></openid>";
			reqParams += "<out_trade_no><![CDATA[knybio2cy0m0a26suqy3ui2t6s4sqnc2]]></out_trade_no>";
			reqParams += "<result_code><![CDATA[SUCCESS]]></result_code>";
			reqParams += "<return_code><![CDATA[SUCCESS]]></return_code>";
			reqParams += "<sign><![CDATA[472D7872B4E5FE2E96389430FC2D4327]]></sign>";
			reqParams += "<time_end><![CDATA[20180126145530]]></time_end>";
			reqParams += "<total_fee>1</total_fee>";
			reqParams += "<trade_type><![CDATA[JSAPI]]></trade_type>";
			reqParams += "<transaction_id><![CDATA[4200000078201801261133874787]]></transaction_id>";
			reqParams += "<attach><![CDATA["+attchJsonObject.toString()+"]]></attach>";
			
			reqParams += "</xml>";
			NotifyInfo result = wxp.payResult_test(reqParams) ;
			
			if(result == null || !result.getReturn_code().equals("SUCCESS")){
				return ;
			}
			
			String handler = redis.get(result.getNonce_str() + result.getOut_trade_no() ) ;
			if(handler == null || "".equals(handler)){
				boolean ret = paymentService.addPayment(result,redis,WX_NOTIFY_EXPIRE) ;
				if(ret){
					response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
				}
			}
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
  }
	
	
}
