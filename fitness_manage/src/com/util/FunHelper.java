package com.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpSession;


import net.sf.json.JSONObject;

import com.framework.common.Config;
import com.framework.common.StaticFinal;
import com.wx.entity.sys.User;

public class FunHelper {
   
	public final static String encodeMD5(String s){
		 char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
	        try {
	            byte[] btInput = s.getBytes();
	            // 获得MD5摘要算法的 MessageDigest 对象
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            // 使用指定的字节更新摘要
	            mdInst.update(btInput);
	            // 获得密文
	            byte[] md = mdInst.digest();
	            // 把密文转换成十六进制的字符串形式
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	}
	
	//2016-10-5 20:30 
	public static String getCurrentDate(String spYMD )
	{
		Calendar rightNow = Calendar.getInstance();
		int month = rightNow.get(Calendar.MONTH)+1;
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		String time = rightNow.get(Calendar.YEAR) +spYMD;
	
		if(month<10) 
			time +="0"+month;
		else 
			time +=month+"";
		
		time += spYMD ;
		
		if(day<10) 
			time +="0"+day;
		else 
			time +=day+"";
		
		return time;
	}	
	
	public static String getCurrentTime(String spYMD , String spDT ,String spHMS)
	{
		Calendar rightNow = Calendar.getInstance();
		String time = rightNow.get(Calendar.YEAR)+spYMD+(rightNow.get(Calendar.MONTH)+1)+spYMD+rightNow.get(Calendar.DAY_OF_MONTH)+
		
		spDT+rightNow.get(Calendar.HOUR_OF_DAY)
		+spHMS+rightNow.get(Calendar.MINUTE)
		+spHMS+rightNow.get(Calendar.SECOND);

		return time;
	}
	
	public static String getCurrentTime(String spYMD , String spDT ,String spHMS,boolean needMillSecond)
	{
		Calendar rightNow = Calendar.getInstance();
		String time = rightNow.get(Calendar.YEAR)+spYMD+(rightNow.get(Calendar.MONTH)+1)+spYMD+rightNow.get(Calendar.DAY_OF_MONTH)+
		
		spDT+rightNow.get(Calendar.HOUR_OF_DAY)
		+spHMS+rightNow.get(Calendar.MINUTE)
		+spHMS+rightNow.get(Calendar.SECOND);
		if(needMillSecond)
		{
			time += "."+rightNow.get(Calendar.MILLISECOND);
		}
		
		return time;
	}
	
   public static String getLastDayOfMonth(int year, int month) {     
         Calendar cal = Calendar.getInstance();     
         cal.set(Calendar.YEAR, year);     
         cal.set(Calendar.MONTH, month);     
         cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));  
        return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());  
     }   
   
    public static String getFirstDayOfMonth(int year, int month) {     
         Calendar cal = Calendar.getInstance();     
         cal.set(Calendar.YEAR, year);     
         cal.set(Calendar.MONTH, month);  
         cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));  
        return   new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());  
     } 
    
    
	public static String createWaybillCode(String seqid){
		String preCode = Config.getConfig(StaticFinal.WAYBILL_CODE_PRECODE, "6") ;
		String zoreStr = "00000000000000000000000000000000000000" ;
		seqid= zoreStr + seqid ;
		seqid = preCode + seqid.substring(seqid.length() - 9) ;
		
		return seqid ;
	}
	
	public static String createCode(String prefix , String seqid ,int totalLen){
		String zoreStr = "00000000000000000000000000000000000000" ;
		seqid = zoreStr + seqid ;
	
		String ret = prefix + seqid.substring(seqid.length() - totalLen) ;
		return ret ;
	}
	
	public static String getUserName(HttpSession session){
		if(session == null){
			return "" ;
		}
		try{
		User user = (User)session.getAttribute(StaticFinal.SESSION_KEY_USER);
		return user.getUser_name() ;
		}catch (Exception e) {
			// TODO: handle exception
			return "" ;
		}
	}
	
	public static String getUserInfo(HttpSession session){
		String jsonStr = "{userId:'' , userName:'' , mainOrgId:0 , ownOrgIds:[] , staffId:'' , staffName:''}" ;
		
		if(session == null){
			return jsonStr ;
		}
		
		try{
			User user = (User)session.getAttribute(StaticFinal.SESSION_KEY_USER);
			JSONObject jsonObject = new JSONObject() ;
			
			jsonObject.put("id", user.getId()) ;
			jsonObject.put("user_code", user.getUser_code()) ;
		    jsonObject.put("user_name", user.getUser_name()) ;
		    jsonObject.put("app_info_code", user.getApp_info_code()) ;
		 
		    
		    jsonObject.put("right_mask", user.getRight_mask()) ;
		
		    jsonObject.put("app_type", user.getApp_type()) ;
		    jsonObject.put("is_admin", user.getIs_admin()) ;
	
			//user.setOwnOrgIdStr(user.getOwnOrgIds());
			//jsonObject.put("ownOrgIds", user.getOwnOrgIds()) ;
			//jsonObject.put("staffId", user.getStaff_id()) ;
			//jsonObject.put("staffName", user.getStaff_name()) ;
			//jsonObject.put("staffMobile", user.getMobile()) ;
			//jsonObject.put("entity_id", user.getEntity_id());
			//jsonObject.put("mainOrgName", user.getMainOrgName());
			//jsonObject.put("entity_name", user.getEntity_name());
			
			return jsonObject.toString() ;
			}catch (Exception e) {
				// TODO: handle exception
				return jsonStr ;
			}

	}
	
	public static Integer getNowYear(){
		Calendar rightNow = Calendar.getInstance();
		return rightNow.get(Calendar.YEAR) ;
	}
	
	//2017-03-27 18:50:30
	public static Integer getYear(String dateStr){
		
		if(dateStr != null ||"".equals(dateStr)){
			String[] dateFiled = dateStr.split(" ") ;
			
			try {
				String[] ymd = dateFiled[0].split("-") ;
				return Integer.parseInt(ymd[0]) ;
				
			} catch (Exception e) {
				// TODO: handle exception
				return getNowYear();
			}
			
		}else{
			
			return getNowYear();
			
		}
		
	}
	
   public static Integer getNowMonth()
   {
		Calendar rightNow = Calendar.getInstance();
		int month = rightNow.get(Calendar.MONTH)+1;
		
		return month ;
  }
   
   public static String getYMDDateStr(String strDate) {
	   if(strDate != null && strDate.contains(" ")){
			String [] strArr = strDate.split(" ") ;
			return strArr[0] ;
		}else{
			return strDate ;
		}
	}

	public static Integer getMonth(String dateStr){
		if(dateStr != null ||"".equals(dateStr)){
			String[] dateFiled = dateStr.split(" ") ;
			
			try {
				String[] ymd = dateFiled[0].split("-") ;
				return Integer.parseInt(ymd[1]) ;
				
			} catch (Exception e) {
				// TODO: handle exception
				return getNowMonth();
			}
			
		}else{
			
			return getNowMonth();
			
		}
		
	}
	
	public static String precisionFloat(float data,final int  preNum ){
		//BigDecimal b = new BigDecimal(data);  
		//return b.setScale(preNum,  BigDecimal.ROUND_HALF_UP).floatValue();  
		String zeros= "000000000000000000" ;
		zeros = "."+zeros.substring(0, preNum) ;
		
		DecimalFormat decimalFormat = new DecimalFormat(zeros);//构造方法的字符格式这里如果小数不足preNum位,会以0补足.
		String p = decimalFormat.format(data);//format 返回的是字符串
		if(p.startsWith(".")){
			p = "0"+p ;
		}
		return p ;
		
	}
	
	public static String getClassPath(Object o)
	{
		String path = o.getClass().getClassLoader().getResource("/").getPath();
		return path ;
	}
	
	public static String trim(String str){
		if(str != null){
			str = str.trim() ;
			str = str.replaceAll(" ", "") ;
			str = str.replaceAll(" ", "") ;
			str = str.replaceAll("　", "") ;
		}
		return str ;
	}
	
	public static String trim(String str,String []tirmArr){
		if(str != null){
			for(int i=0 ;i< tirmArr.length ;i++)
				try {
					str = str.replaceAll(tirmArr[i], "") ;
				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
		
		}
		return str ;
	}
	
	public static String createWXSession(String pre)
	{
		String uuid = UUID.randomUUID().toString() ;
		String uuidArr[] = uuid.split("-") ;
		String uuid_2 = uuidArr[0]+uuidArr[1] ;
		if(pre != null && !pre.isEmpty()){
			return pre+"_"+uuid_2 ;
		}
		return uuid_2 ;
	}
	
	public static boolean isValidate(String inStr ,int required){
		if(inStr == null || inStr.isEmpty()){
			return false ;
		}else{
			inStr = inStr.trim() ;
			if(inStr.length() >= required){
				return true ;
			}
			return false ;
		}
			
	}
	
	public static void main(String[] args)
	{
		System.out.println("getYMDDateStr(2016-07-12 20:12:30)="+getYMDDateStr("2016-07-12 20:12:30")) ;
		
		System.out.println("getYear(2016-07-12 20:12:30)="+getYear("2016-07-12 20:12:30")) ;

		System.out.println("getYear(2016-07-12)="+getYear("2016-07-12")) ;
		
		System.out.println("getMonth(2016-07-12 20:12:30)="+getMonth("2016-07-12 20:12:30")) ;

		System.out.println("getMonth(2016-07-12)="+getMonth("2016-07-12")) ;
		
		String [] strArr = new String[3];
		String str ="" ;
		System.out.println(strArr.toString()) ;
		System.out.println(str.toString()) ;
		
		System.out.println(FunHelper.precisionFloat(10.0f, 3)) ;
		System.out.println(FunHelper.precisionFloat(3.0f, 3)) ;
		System.out.println(FunHelper.precisionFloat(0.0f, 3)) ;
	
		if(true )
			return; 
		System.out.println(""+FunHelper.encodeMD5("123456")) ;
		System.out.println("getCurrentTime :"+FunHelper.getCurrentTime("-"," ",":")) ;
		System.out.println("getCurrentDate :"+FunHelper.getCurrentDate("-")) ;
		
		for(int j=0;j<10;j++){
			for(int i=0 ;i<12 ;i++){
				System.out.println("getFirstDayOfMonth------getLastDayOfMonth :"+getFirstDayOfMonth(2016+j,i)+"---------------------"+getLastDayOfMonth(2016+j ,i)) ;
			}
			
			System.out.println("#########################################################") ;
		}
		
		System.out.println("fffffff:"+FunHelper.createWaybillCode ("9999999")) ;
		
		System.out.println(Calendar.getInstance().get(Calendar.YEAR)) ;  

		try {
			System.out.println("fffffff:"+FunHelper.createCode("ysk", "3", 5)) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(290 * 5) ;
		
	}
	
}
