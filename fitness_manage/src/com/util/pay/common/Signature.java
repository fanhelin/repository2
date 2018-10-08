package com.util.pay.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * 
 * @author HP
 *
 */
public class Signature {
	private static final Logger L = Logger.getLogger(Signature.class);
	/**
     * 签名算法
     * @param o 要参与签名的数据对象
     * @param key 商户秘钥
     * @return 签名
     * @throws IllegalAccessException
     */
    public static String getSign(Object o,String key) throws IllegalAccessException {
        ArrayList<String> list = new ArrayList<String>();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(o) != null && f.get(o) != "") {
            	String name = f.getName();
            	XStreamAlias anno = f.getAnnotation(XStreamAlias.class);
            	if(anno != null)
            		name = anno.value();
                list.add(name + "=" + f.get(o) + "&");
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
        System.out.println("签名数据："+result);
        result = MD5Util.MD5Encode(result,"UTF-8").toUpperCase();
        return result;
    }

    public static String getSign(Map<String,Object> map,String key){
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
        //Util.log("Sign Before MD5:" + result);
        result = MD5Util.MD5Encode(result,"UTF-8").toUpperCase();
        //Util.log("Sign Result:" + result);
        return result;
    }

    

}
