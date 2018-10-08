package com.util.sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;


public class SignUtil {
	
	
	public static String getSign(Map<String,Object> map ,String key){  
        ArrayList<String> list = new ArrayList<String>();  
        for(Map.Entry<String,Object> entry:map.entrySet()){  
            if(entry.getValue()!=""){  
                list.add(entry.getKey() + "=" + entry.getValue() + "&");  
            }  
        }
        
        int size = list.size();  
        String [] arrayToSort = list.toArray(new String[size]);  
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);  
        StringBuilder sb = new StringBuilder();  
        for(int i = 0; i < size; i ++) {  
            sb.append(arrayToSort[i]);  
        }  
        String result = sb.toString();  
        result += "key=" + key;  
    
        result = MD5Utils.MD5Encode(result,"utf-8").toUpperCase();  
      
        return result;  
    }  
	

	public static String getSign(JSONObject obj ,String key,String nouse){  
		
		Set set = obj.keySet();
		StringBuilder sb = new StringBuilder();   ;
		for(Object k :  set ){
			if(obj.getString((String)k)==null || obj.getString((String)k).isEmpty())
				continue ;
			sb.append(k+"=").append(obj.getString((String)k) ).append("&");
		}
		String urlStr = sb.toString() + "key="+key;
		
		urlStr = MD5Utils.MD5Encode(urlStr,"utf-8").toUpperCase();  
        //Util.log("Sign Result:" + result);  
        return urlStr;  
    }  

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}
}
