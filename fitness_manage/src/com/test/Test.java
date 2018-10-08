package com.test;


import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.sf.json.JSONObject;


import com.framework.common.Notify;
import com.util.FileHelper;
import com.util.UUIDUntil;
import com.util.UUIDUntil.CaseEnum;
import com.util.json.JsonUtils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FileHelper.getInstance().writeFile("{'total':28,'rows':[{'productid':'FI-SW-01','productname':'Koi','unitcost':10.00,'status':'P','listprice':36.50,'attr1':'Large','itemid':'EST-1'},{'productid':'K9-DL-01','productname':'Dalmation','unitcost':12.00,'status':'P','listprice':18.50,'attr1':'Spotted Adult Female','itemid':'EST-10'},{'productid':'RP-SN-01','productname':'Rattlesnake','unitcost':12.00,'status':'P','listprice':38.50,'attr1':'Venomless','itemid':'EST-11'},{'productid':'RP-SN-01''productname':'Rattlesnake','unitcost':12.00,'status':'P','listprice':26.50,'attr1':'Rattleless','itemid':'EST-12'},{'selected':true,'productid':'RP-LI-02','productname':'Iguana','unitcost':12.00,'status':'P','listprice':35.50,'attr1':'Green Adult','itemid':'EST-13'},{'productid':'FL-DSH-01','productname':'Manx','unitcost':12.00,'status':'P','listprice':158.50,'attr1':'Tailless','itemid':'EST-14'},{'productid':'FL-DSH-01','productname':'Manx','unitcost':12.00,'status':'P','listprice':83.50,'attr1':'With tail','itemid':'EST-15'},{'productid':'FL-DLH-02','productname':'Persian','unitcost':12.00,'status':'P','listprice':23.50,'attr1':'Adult Female','itemid':'EST-16'},{'productid':'FL-DLH-02','productname':'Persian','unitcost':12.00,'status':'P','listprice':89.50,'attr1':'Adult Male','itemid':'EST-17'},{'productid':'AV-CB-01','productname':'Amazon Parrot','unitcost':92.00,'status':'P','listprice':63.50,'attr1':'Adult Male','itemid':'EST-18'}]}", "d:\\log\\test.log", true) ;
		if(true){
			return ;
		}
		
		testVidoTime() ;
		 File source = new File("d:\\ee.mp4");
		 long l= getVidoTime(source) ;
		 System.out.println("l="+l) ;
		 
		 if(true == true){
			 return ;
		 }
       
		 System.out.println( UUIDUntil.genUUID(37, "RCV-",CaseEnum.UPPER)) ;
		
		int index = 0 ;
		Set<String> set= new HashSet<String>() ;
		while(index ++< 100000000){
			String uuid = UUID.randomUUID().toString() ; //85a27696-ed55-48d9-a1e8-82fbbb69e3a3
			String uuidArr[] = uuid.split("-") ;
			String uuid_2 = uuidArr[0]+uuidArr[1] ;
			if(set.contains(uuid_2)){
				System.out.println("same uuid:"+uuid_2) ;
			   return;
			}
			set.add(uuid_2) ;
		    System.out.println(index +": randomUUID:" + UUID.randomUUID().toString())  ;
			try {
				Thread.sleep(1) ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		JSONObject object= new JSONObject() ;
	       object.put("bb", "bbbbbb") ;
	       object.put("aa", "bbbbbb") ;
	       object.put("ee", "bbbbbb") ;
	       object.put("ec", "ecececec") ;
	       object.put("cc", "cccccccc") ;
	       object.put("ce", "cccccccc") ;
	       object.put("ca", "cacacacaca") ;
	       
	       System.out.println("before:"+ object.toString() );
	       
	      JSONObject newObj = JsonUtils.orderJsonKeys(object) ;
	      System.out.println("after:"+ newObj.toString() );

	      String urlStr = JsonUtils.json2UrlStr(newObj);
	      System.out.println("urlStr:"+ urlStr );
		  if(true){
				return;
			}
			
		
		
		
		String pathString = "F:/WX_SP/apache-tomcat-8.0.38/webapps/wx_manage" ;
		pathString = pathString.replaceFirst("wx_manage", "") ;
		System.out.println(pathString);
		
		// TODO Auto-generated method stub
		
//		List<User> list = new ArrayList<User>() ;
//	
//	
//		
//		System.out.println(JSONObject.fromObject(null).toString());
//       
//		JSONObject response = new JSONObject() ;
//		response.put("tatal", 0) ;
//		response.put("rows", JSONArray.fromObject("[]")) ;
//		
//		System.out.println("response:"+response) ;
//		
//		
//		System.out.println(FunHelper.encodeMD5("123456"));
//		
//		Test test = new Test() ;
//		
//	    System.out.println("methodName:" +test.getMethodName()) ;
//	    
	    
	    System.out.println(Notify.NotifyJson("kedjij", "messagedkjjdkfkdf", null).toString()) ;
	    
	    Integer var1 = 33;
	    Integer var2 = 33 ;
	    
	    System.out.println("var1 == var2:" + (var1 == var2));	
	    System.out.println("var1 equals var2:" + (var1.equals(var2) ));
	    
	    Class class1 =  String[].class ;
		   System.out.println("{String[].class} =" +class1.toString()) ;
		   
		   
	    String str ="TAKE_ADDR:接货地址,TAKE_MILEAGE:67,SETTLE_CODE:J101,TAKE_PRICE:88,REMARK:^|^" ;
	    
	    String[] strArrStrings = str.split("\\^\\|\\^",-1) ;
	    System.out.println(strArrStrings.toString()) ;
	    
	    
	    JSONObject retJsonObject =  JSONObject.fromObject("{total:0,rows:[]}") ;
	    System.out.println(retJsonObject) ;
	    
	    StringBuffer var11 = new StringBuffer() ;
	    StringBuffer var33 = new StringBuffer();
	    new Test().setInnerValue(var11, var33) ;
	 
	    System.out.println(var11 + "  "+var33) ;
	    retJsonObject.put("total", 44) ;
	    
	    System.out.println(retJsonObject) ;
	    
	    
	}
	
	

		  
		   protected String getMethodName() {  
				  
				  String clazzName = new Object() {  
				        public String getClassName() {  
				            String clazzName = this.getClass().getName();  
				            return clazzName.substring(0, clazzName.lastIndexOf('$'));  
				        }  
				    }.getClassName(); 
				    
			      StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();  
			      StackTraceElement e = stacktrace[2];  
			      String methodName = e.getMethodName();  
			      return clazzName+"[" + methodName + "]";  
			      
			      
			  }  
		   
		  // ApplicationContext ac = new FileSystemXmlApplicationContext("applicationContext.xml"); 
		   //ac.getBean("beanId"); 
		   
		 void setInnerValue(StringBuffer var1 ,StringBuffer var2){
			 var1.append("dddddddddddd") ;
			 var2.append("1111111111111111" );
		 }
		 
		 public static long getVidoTime(File file){
			 
		        Encoder encoder = new Encoder();
		        try {
		       
		        	 MultimediaInfo m = encoder.getInfo(file);
		             long ls = m.getDuration();
		          
		             //视频帧宽高
		             System.out.println(m.getVideo().getSize().getHeight());
		             System.out.println(m.getVideo().getSize().getWidth());
		             return ls /(1000*60) ;
		        } catch (Exception e) {
		            e.printStackTrace();
		            return -1;
		        }
		 }
		 
		 public static void testVidoTime(){
			 String pathString = "d:\\ee.mp4";
			 //pathString = "E:\\测试视频\\R41.avi"
			 File source = new File(pathString);
		        Encoder encoder = new Encoder();
		        try {
		             MultimediaInfo m = encoder.getInfo(source);
		             long ls = m.getDuration();
		         System.out.println("ls:"+ls) ;
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		 }
}
