package com.util.json;


import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * ClassName:JsonUtils <br/>
 * Function: Json 操作工具类. <br/>
 * Date:     2014-9-26 上午9:55:29 <br/>
 * @author   z144746
 * @since    JDK 1.7
 */
public class JsonUtils {
	
	 /**
	 * jsonStr2List:(Json 字符串转List). <br/>
	 * @author z144746
	 * @param str
	 * @param clazz
	 * @since JDK 1.7
	 */
	@SuppressWarnings("unchecked")
	public static List<?> jsonStr2List(String str,Class<?> clazz){
		 	if(StringUtils.isEmpty(str)){
		 		return null;
		 	}
		 	List<?> objList = null;
		 	try{
		 		 JSONUtils.getMorpherRegistry().registerMorpher(
		                new DateMorpherEx(new String[] { "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm",
		                        "yyyy-MM-dd", "yyyy-MM-dd't'HH:mm:ss" }, (Date)null),true);
		 		
		 		 JSONArray jarr=JSONArray.fromObject(str);
		 		objList = (List<Object>)JSONArray.toCollection(jarr,clazz);
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
	        return objList;
	}
	/**
	 * jsonStr2List:(Json 字符串转实体). <br/>
	 * @author z144746
	 * @param str
	 * @param clazz
	 * @since JDK 1.7
	 */
	public static Object jsonStr2Entity(String str,Class<?> clazz){
		 	if(StringUtils.isEmpty(str)){
		 		return null;
		 	}
		 	Object obj = null;
		 	try{
		 		JSONUtils.getMorpherRegistry().registerMorpher(
		                new DateMorpherEx(new String[] { "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm",
		                        "yyyy-MM-dd", "yyyy-MM-dd't'HH:mm:ss" }, (Date)null),true);
		 		obj = JSONObject.toBean( JSONObject.fromObject(str), clazz);
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
	        return obj;
	}
	
	// 将String转换成JSON
		public static String string2json(String key, String value) {
			JSONObject object = new JSONObject();
			object.put(key, value);
			return object.toString();
		}

		// 将JSON转换成数组,其中valueClz为数组中存放的对象的Class
		public static Object json2Array(String json, Class valueClz) {
			JSONArray jsonArray = JSONArray.fromObject(json);
			return JSONArray.toArray(jsonArray, valueClz);
		}

		// 将Collection转换成JSON
		public static String collection2json(Object object) {
			JSONArray jsonArray = JSONArray.fromObject(object);
			return jsonArray.toString();
		}

		// 将数组转换成JSON
		public static String array2json(Object object) {
			JSONArray jsonArray = JSONArray.fromObject(object);
			return jsonArray.toString();
		}

		// 将Map转换成JSON
		public static String map2json(Object object) {
			JSONObject jsonObject = JSONObject.fromObject(object);
			return jsonObject.toString();
		}

		// 将JSON转换成Map,其中valueClz为Map中value的Class,keyArray为Map的key
		public static Map json2Map(Object[] keyArray, String json, Class valueClz) {
			JSONObject jsonObject = JSONObject.fromObject(json);
			Map classMap = new HashMap();

			for (int i = 0; i < keyArray.length; i++) {
				classMap.put(keyArray[i], valueClz);
			}

			return (Map) JSONObject.toBean(jsonObject, Map.class, classMap);
		}

		// 将POJO转换成JSON
		public static String bean2json(Object object) {
			JSONObject jsonObject = JSONObject.fromObject(object);
			return jsonObject.toString();
		}



		// 将JSON转换成String
		public static String json2String(String json, String key) {
			JSONObject jsonObject = JSONObject.fromObject(json);
			return jsonObject.get(key).toString();
	    }
		
		/**
		 * 对json key 排序
		 * @param jsonObject
		 * @return jsonobject
		 * @author  fhr 2017/9/2
		 */
		public static JSONObject orderJsonKeys(JSONObject jsonObject){
			Set set = jsonObject.keySet() ;
			Object [] keys =  set.toArray() ;
			 Arrays.sort(keys) ;
			 JSONObject newJson = new JSONObject();
			 for(Object key : keys){
				 newJson.put(key, jsonObject.get(key)) ;
		       }
			 
			 return newJson ;
		}
		
		public static  String json2UrlStr(JSONObject obj)
		{
			Set set = obj.keySet();
	        String urlStr = "" ;
			for(Object key :  set ){
				urlStr += "&"+key+"=" + obj.getString((String)key);
			}
			return urlStr ;
		}
			
}

