package com.util;

import java.util.Random;
import java.util.UUID;



public class UUIDUntil {
  public static String genUUID(final int totaLen,String pre){
	    String uuid = UUID.randomUUID().toString() ; //85a27696-ed55-48d9-a1e8-82fbbb69e3a3
	    uuid = uuid.replaceAll("-","") ;
	    if(pre != null){
	    	uuid = pre + uuid ;
	    }
	    if(uuid.length()<totaLen){
	    	return uuid ;
	    }
	    return uuid.substring(0, totaLen) ;	  
	    
  }
  
  
  public static String genUUID(final int totaLen,String pre, CaseEnum ce){
	    String uuid = UUID.randomUUID().toString() ; //85a27696-ed55-48d9-a1e8-82fbbb69e3a3
	    uuid = uuid.replaceAll("-","") ;
	    if(pre != null){
	    	uuid = pre + uuid ;
	    }
	    if(uuid.length()<totaLen){
	    	if(ce == CaseEnum.LOWER){
		    	uuid = uuid.toLowerCase() ;  
	 	    }else{
	 	    	uuid = uuid.toUpperCase() ;  
	 	    }
	    	return uuid ;
	    }
	   
	    uuid = uuid.substring(0, totaLen) ;
	    if(ce == CaseEnum.LOWER){
	    	uuid = uuid.toLowerCase() ;  
 	    }else{
 	    	uuid = uuid.toUpperCase() ;  
 	    }
	    return uuid ;
	    
}
  public static String randomString(int length) {
      String base = "abcdefghijklmnopqrstuvwxyz0123456789";
      Random random = new Random();
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < length; i++) {
          int number = random.nextInt(base.length());
          sb.append(base.charAt(number));
      }
      return sb.toString();
  }
  
  public static void main(String[] args) {
	 System.out.println( UUIDUntil.genUUID(16, "RCV-")) ;
	 System.out.println( UUIDUntil.genUUID(37, "RCV-",CaseEnum.UPPER)) ;
   }
  

public static enum CaseEnum {  
	   LOWER , UPPER
	} 
} 

