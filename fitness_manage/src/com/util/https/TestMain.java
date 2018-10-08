package com.util.https;

import java.util.HashMap;
import java.util.Map;

public class TestMain {
	 private String url = "https://xxx.xxx.xxx/";  
	  private String charset = "utf-8";  
	  private HttpClientUtil httpClientUtil = null;  
	     
	  public TestMain(){  
	    httpClientUtil = new HttpClientUtil();  
	  }  
	     
	  public void test(){  
	    String httpOrgCreateTest = url + "xxx/xxx/delivery";  
	    Map<String,String> createMap = new HashMap<String,String>();  
	    createMap.put("delivery_code","1D1QZ222Z22SM21A");  
	    createMap.put("timestamp","1479198840000");  
	    createMap.put("sign","F2109C333F3EADE929F932E89703FA0F683D43EB");  
	    String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);  
	    System.out.println("result:" + httpOrgCreateTestRtn);  
	  }  
	     
	  public static void main(String[] args){  
	    TestMain main = new TestMain();  
	    main.test();  
	  }  
}
