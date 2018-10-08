package com.wx.controller.register;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.framework.base.BaseController;
import com.framework.common.Config;
import com.framework.common.Response;
import com.framework.common.StaticFinal;
import com.util.FunHelper;
import com.util.NetworkUtil;
import com.util.pay.WxPayUtil;

import com.util.pay.model.OrderInfo;
import com.util.pay.model.OrderReturnInfo;
import com.wx.entity.pay.NotifyInfo;
import com.wx.entity.register.Payment;
import com.wx.entity.register.Student;
import com.wx.entity.sys.AppInfo;
import com.wx.redis.JRedisClient;
import com.wx.service.register.RegisterService;

@Controller
@RequestMapping("/reg")
public class RegisterController extends BaseController {
	@Autowired
	private RegisterService  registerService = null;

	@Autowired
	private JRedisClient redis ;
	  static Integer WX_NOTIFY_EXPIRE = 3600;
	static{
	   WX_NOTIFY_EXPIRE = Integer.parseInt(Config.getConfig(StaticFinal.WX_NOTIFY_EXPIRE, "wx_notify_expire")) ;
	}
	@RequestMapping(value = "/upLoadFile.do", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadDiagFile(@RequestParam("file") MultipartFile file,  String uploadType,String preName ,Student student ,HttpServletRequest request) throws IOException {
		 File targetFile = null ;
		try {
				if(!FunHelper.isValidate(student.getApp_info_code(), 6) || !FunHelper.isValidate(student.getApp_type(),1) || !FunHelper.isValidate(student.getCode(), 20)){
					return Response.error("附加参数无效", "app_info_code: "+student.getApp_info_code() +" app_type: "+ student.getApp_type() +" openid: "+ student.getCode());
				}
		      
			 
			
		        String path = request.getSession().getServletContext().getRealPath(Config.getConfig("upload_files","upload_files"));
		        path += File.separator + student.getApp_type() + File.separator + student.getApp_info_code() + File.separator +student.getCode() ;
		        String fileName = file.getOriginalFilename(); //UUIDUntil.genUUID(30, "pic_"); 
	
		   
		        targetFile = new File(path, fileName);
		        if(!targetFile.exists()){
		            targetFile.mkdirs();
		        }

	        //保存
	     
	            file.transferTo(targetFile);
	        
	        
	        if(uploadType.equals("sfz")){
	        	student.setIdcard_img(fileName) ;
	        }else if(uploadType.equals("tjb")){
	        	student.setHealth_img(fileName) ;
	        }else if(uploadType.equals("zmz")){
	        	student.setFace_img(fileName) ;
	        }else if(uploadType.equals("xcxy")){
	        	student.setProtocol_img(fileName) ;
	        }
	       
	        String prePathStr = FunHelper.isValidate(preName, 1)? path + File.separator +preName  : "";
			boolean ret = registerService.updateRegInfo(student , prePathStr) ;
			if(!ret ){
				targetFile.delete();
				return Response.error("更新数据库失败") ;
			}
				
			JSONObject picInfObject = new JSONObject() ;
			 picInfObject.put("path", path) ;
			 picInfObject.put("fileName", fileName) ;

			 return Response.success("上传成功", picInfObject);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(targetFile != null){
					targetFile.delete();
				}
				return Response.error(e.getMessage()) ;
			}
	        
	       
	}
	
	@RequestMapping("/saveRegInfo.do")
	@ResponseBody
	public Response saveRegInfo(Student student){
		try {
			/*if(!FunHelper.isValidate(student.getCode(), 20)){
				student.setCode(UUIDUntil.genUUID(30, "st_")) ;
			}*/
			
			if(!FunHelper.isValidate(student.getApp_info_code(), 6) || !FunHelper.isValidate(student.getCode(), 20)){
				return Response.error("附加参数无效", "app_info_code: "+student.getApp_info_code()  +" openid: "+ student.getCode());
			}
			
			Student stuInfo = registerService.selectStudentInfo(student) ;
			if(stuInfo!=null && stuInfo.getState() == 2){
				return Response.error("已审核，不能改") ;
			}
			
			student.setState(0); //默认0 为保存状态 1，初审核，2审核通过
			boolean ret = registerService.saveStudentInfo(student) ;
			
			if(ret){
				return Response.success("保存报名数据成功");
			}else{
				return Response.error("保存报名数据失败") ;
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	}
	
	@RequestMapping("/selectRegInfo.do")
	@ResponseBody
	public Response selectRegInfo(Student student){
		
		if(!FunHelper.isValidate(student.getApp_info_code(), 2) || !FunHelper.isValidate(student.getCode(),20)){
			return Response.error("查询条件无效" ) ;
		}
		
		try {
			Student ret = registerService.selectStudentInfo(student) ;
			
			if(ret == null){
				return Response.error("查询失败") ;
			}else{
				return Response.success("查询成功", ret) ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
		
	}
	
	
	@RequestMapping("/classifyStudents.do")
	@ResponseBody
	private Response classifyStudents(Student student ){
		
		try {
			JSONObject retJsonObject = registerService.selectClassifyStudents(student) ;
			
			return Response.success("查询学员分类成功", retJsonObject) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage());
		}
		
		
	}
	
	@RequestMapping("/updateStudent.do")
	@ResponseBody
	private Response updateStudent(Student student ){
		
		try {
			boolean ret = registerService.updateStudent(student) ;
			if(ret){
				return Response.success("更新用户信息成功" ) ;
			}
			
			return Response.error("更新用户信息失败") ;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage());
		}
	}
	

	@RequestMapping("/loadHisPay.do")
	@ResponseBody
	private Response loadHisPay(Payment payment){
		try {
			List<Payment> pays = registerService.selectPayments(payment) ;
			return Response.success("查询缴费记录成功", pays) ;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.success("查询缴费记录成功", JSONArray.fromObject("[]")) ;
		}

	}
	
	
	
	@RequestMapping("/addPayment.do")
	@ResponseBody
	private Response addPayment(Payment payment){
		try {
		   	registerService.addPayment(payment) ;
		   	return Response.success("付款成功") ;
		} catch (Exception e) {
			// TODO: handle exception
		 	return Response.success(e.getMessage()) ;
		}
	}
	
	

	@RequestMapping("/unifiedOrder.do")
	@ResponseBody
	private Response unifiedOrder(AppInfo appInfo, String openid,double totalFee,String body,HttpServletRequest request){
		try {
			WxPayUtil wxp = new WxPayUtil() ;
			OrderInfo orderInfo = new OrderInfo() ;
			orderInfo.setAppid(appInfo.getApp_id()) ;
			orderInfo.setOpenid(openid) ;
			
			orderInfo.setBody(body) ;
			
			orderInfo.setMch_id(appInfo.getMch_id()) ;
			orderInfo.setNonce_str(null);
			orderInfo.setNotify_url(null) ;
	
			orderInfo.setOut_trade_no(null);
			String spbill_create_ip = NetworkUtil.getIpAddress(request) ;
			orderInfo.setSpbill_create_ip(spbill_create_ip) ;
			
			Double temp = totalFee * 100 ;
			orderInfo.setTotal_fee(temp.intValue() ) ;
			//orderInfo.setTrade_type(trade_type)
			
			OrderReturnInfo retObject = wxp.unifedOrder(orderInfo, appInfo.getMch_key()) ;
			if(retObject == null){
				return Response.error("下单失败") ;
			}else{
				if(retObject.getReturn_code().equalsIgnoreCase("FAIL")) {
					return Response.error("下单失败") ;
				}
				return Response.success("下单成功", retObject) ;
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
			
			String key = redis.get(result.getNonce_str() + result.getOut_trade_no() ) ;
			if(key == null || "".equals(key)){
				boolean ret = registerService.addPaymentNotify(result) ;
				if(ret){
					redis.expire(key, WX_NOTIFY_EXPIRE) ; //成功就保存对应的key
					response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		
	}
	
	

}
