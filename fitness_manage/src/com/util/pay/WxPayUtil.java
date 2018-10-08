package com.util.pay;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;



import com.framework.common.Config;
import com.framework.common.StaticFinal;
import com.thoughtworks.xstream.XStream;
import com.util.FileHelper;
import com.util.UUIDUntil;
import com.util.pay.common.HttpRequest;
import com.util.pay.common.Signature;
import com.util.pay.common.StreamUtil;
import com.util.pay.model.OrderInfo;
import com.util.pay.model.OrderReturnInfo;
import com.util.pay.model.SignInfo;
import com.wx.entity.pay.NotifyInfo;
import com.wx.entity.sys.AppInfo;

/**
 * 微信支付工具
 * @author fhr
 *
 */
public class WxPayUtil {
	
	/*
    private static String key = "pingdingshanxiangshunjiaxiaomy07";

	//小程序ID	
	private static String appID = "wxa56ffafd16adc497";
	//商户号
	private static String mch_id = "1493428482";
	//
	private static String secret = "44006bdfab40ffc94301e1b534d7ea4a";
	*/
	
	public Logger log = Logger.getLogger(this.getClass());
    public String getOpenid(String appId,String secret ,String js_code){
    	
    	String params = "appid="+appId+"&secret="+secret+"&js_code="+js_code+"&grant_type=authorization_code";
    	String url= Config.getConfig(StaticFinal.WX_URL_CODE2SESSION, "https://api.weixin.qq.com/sns/jscode2session?");
    	HttpGet httpGet = new HttpGet(url +params);
        
		//设置请求器的配置
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse res;
		try {
			 res = httpClient.execute(httpGet);
             HttpEntity entity = res.getEntity();
              String result = EntityUtils.toString(entity, "UTF-8");
              return result ;
        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  null ;
		}
    }
    
    

   public OrderReturnInfo unifedOrder(OrderInfo orderInfo,String key){
	   try {

			//OrderInfo order = new OrderInfo();
			//orderInfo.setAppid(appID);
			//orderInfo.setMch_id(mch_id);
			//orderInfo.setSpbill_create_ip("123.57.218.54");
		    //orderInfo.setBody("dfdfdf");
			//orderInfo.setOpenid(openid);
		    //orderInfo.setTotal_fee(1);
		    
		    if(orderInfo.getNonce_str() == null){
		    	//orderInfo.setNonce_str(UUIDUntil.genUUID(32,""));
		    	orderInfo.setNonce_str(UUIDUntil.randomString(32));
		    }
			
		    if(orderInfo.getOut_trade_no() == null){
		    	//orderInfo.setOut_trade_no(UUIDUntil.genUUID(32,""));	
		    	orderInfo.setOut_trade_no(UUIDUntil.randomString(32));	
		    }
			
			
            if(orderInfo.getNotify_url() == null){
            	orderInfo.setNotify_url(Config.getConfig(StaticFinal.WX_URL_NOTIFY));
            }
			
			orderInfo.setTrade_type("JSAPI");
	
			orderInfo.setSign_type("MD5");
			//生成签名
			String sign = Signature.getSign(orderInfo,key);
			orderInfo.setSign(sign);
			
			
			String result = HttpRequest.sendPost(Config.getConfig(StaticFinal.WX_URL_UNIFIEDORDER,"https://api.mch.weixin.qq.com/pay/unifiedorder"), orderInfo);
			System.out.println(result);
			log.info("统一订单:"+result);
			XStream xStream = new XStream();
			xStream.alias("xml", OrderReturnInfo.class); 

			OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
			//JSONObject json = new JSONObject();
			//json.put("prepay_id", returnInfo.getPrepay_id());
			
			return returnInfo ;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("-------", e);
			return  null ;
		} 
   }
   

    public JSONObject reSign(AppInfo appInfo,String repay_id) {
	    try {
			
			SignInfo signInfo = new SignInfo();
			signInfo.setAppId( appInfo.getApp_id());
			long time = System.currentTimeMillis()/1000;
			signInfo.setTimeStamp(String.valueOf(time));
			signInfo.setNonceStr(UUIDUntil.genUUID(32, ""));
			signInfo.setRepay_id("prepay_id="+repay_id);
			signInfo.setSignType("MD5");
			//生成签名
			String sign = Signature.getSign(signInfo,appInfo.getMch_key());
			
			JSONObject json = new JSONObject();
			json.put("timeStamp", signInfo.getTimeStamp());
			json.put("nonceStr", signInfo.getNonceStr());
			json.put("package", signInfo.getRepay_id());
			json.put("signType", signInfo.getSignType());
			json.put("paySign", sign);
			log.info("-------再签名:"+json.toString());
			return json ;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
    
    
     public NotifyInfo payResult(HttpServletRequest request){
	
		String reqParams = "";
		try {
			reqParams = StreamUtil.read(request.getInputStream());
			FileHelper.getInstance().writeFile(new Date().toString() +":"+reqParams,"payLogs/payResult.log",true) ;
			/*reqParams ="<xml><appid><![CDATA[wxa56ffafd16adc497]]></appid>";
			reqParams += "<bank_type><![CDATA[CCB_DEBIT]]></bank_type>";
			reqParams += "<cash_fee><![CDATA[1]]></cash_fee>";
			reqParams += "<fee_type><![CDATA[CNY]]></fee_type>";
			reqParams += "<is_subscribe><![CDATA[N]]></is_subscribe>";
			reqParams += "<mch_id><![CDATA[1493428482]]></mch_id>";
			reqParams += "<nonce_str><![CDATA[bo9w9n2i5prqmif9aab8py9ivtdqwl0d]]></nonce_str>";
			reqParams += "<openid><![CDATA[oGI4T0bQecdf0yE_wcGdf5raeHP4]]></openid>";
			reqParams += "<out_trade_no><![CDATA[knybio2cy0m0a26suqy3ui2t6s4sqnc2]]></out_trade_no>";
			reqParams += "<result_code><![CDATA[SUCCESS]]></result_code>";
			reqParams += "<return_code><![CDATA[SUCCESS]]></return_code>";
			reqParams += "<sign><![CDATA[472D7872B4E5FE2E96389430FC2D4327]]></sign>";
			reqParams += "<time_end><![CDATA[20180126145530]]></time_end>";
			reqParams += "<total_fee>1</total_fee>";
			reqParams += "<trade_type><![CDATA[JSAPI]]></trade_type>";
			reqParams += "<transaction_id><![CDATA[4200000078201801261133874787]]></transaction_id>";
			reqParams += "</xml>";*/
			//log.info("-------支付结果:"+reqParams);
		    // System.out.println("reqParams:" +reqParams) ;
			XStream xStream = new XStream();
			xStream.alias("xml", NotifyInfo.class); 
			NotifyInfo returnInfo = (NotifyInfo)xStream.fromXML(reqParams);
			returnInfo.setTotal_fee(returnInfo.getTotal_fee() ) ;
	        
			return returnInfo ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		
		
	}
     
     
     
     public NotifyInfo payResult_test(String reqParams){
	
	  //String reqParams = "";
		try {
			//reqParams = StreamUtil.read(request.getInputStream());
			/*reqParams ="<xml><appid><![CDATA[wxa56ffafd16adc497]]></appid>";
			reqParams += "<bank_type><![CDATA[CCB_DEBIT]]></bank_type>";
			reqParams += "<cash_fee><![CDATA[1]]></cash_fee>";
			reqParams += "<fee_type><![CDATA[CNY]]></fee_type>";
			reqParams += "<is_subscribe><![CDATA[N]]></is_subscribe>";
			reqParams += "<mch_id><![CDATA[1493428482]]></mch_id>";
			reqParams += "<nonce_str><![CDATA[bo9w9n2i5prqmif9aab8py9ivtdqwl0d]]></nonce_str>";
			reqParams += "<openid><![CDATA[oGI4T0bQecdf0yE_wcGdf5raeHP4]]></openid>";
			reqParams += "<out_trade_no><![CDATA[knybio2cy0m0a26suqy3ui2t6s4sqnc2]]></out_trade_no>";
			reqParams += "<result_code><![CDATA[SUCCESS]]></result_code>";
			reqParams += "<return_code><![CDATA[SUCCESS]]></return_code>";
			reqParams += "<sign><![CDATA[472D7872B4E5FE2E96389430FC2D4327]]></sign>";
			reqParams += "<time_end><![CDATA[20180126145530]]></time_end>";
			reqParams += "<total_fee>1</total_fee>";
			reqParams += "<trade_type><![CDATA[JSAPI]]></trade_type>";
			reqParams += "<transaction_id><![CDATA[4200000078201801261133874787]]></transaction_id>";
			reqParams += "</xml>";*/
			//log.info("-------支付结果:"+reqParams);
		    // System.out.println("reqParams:" +reqParams) ;
			XStream xStream = new XStream();
			xStream.alias("xml", NotifyInfo.class); 
			NotifyInfo returnInfo = (NotifyInfo)xStream.fromXML(reqParams);
			returnInfo.setTotal_fee(returnInfo.getTotal_fee() ) ;
	        
			return returnInfo ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		
		
	}


    
}
