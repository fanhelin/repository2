package com.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

import net.sf.json.JSONObject;

public class VidoUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	 public static JSONObject getVidoTime(File file){
		 
	        Encoder encoder = new Encoder();
	        try {
	       
	        	 MultimediaInfo m = encoder.getInfo(file);
	             long ls = m.getDuration();
	          
	             //视频帧宽高
	             System.out.println(m.getVideo().getSize().getHeight());
	             System.out.println(m.getVideo().getSize().getWidth());
	             
	             JSONObject retJsonObject = new JSONObject();
	             retJsonObject.put("seconds", (ls /1000)) ;
	             retJsonObject.put("height", m.getVideo().getSize().getHeight()) ;
	             retJsonObject.put("width", m.getVideo().getSize().getWidth()) ;
	             return retJsonObject ;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return JSONObject.fromObject("{'seconds':0,'height':0,'width':0}");
	        }
	 }

}
