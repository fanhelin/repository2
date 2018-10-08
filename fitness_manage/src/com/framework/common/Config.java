package com.framework.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * 
 * @todo 系统配置文件获取类
 *
 * @author fhr
 * @dateTime 2016 2016-11-4 下午2:20:44
 */

public class Config {
    private static Config instance = null;
	private static Object locker = new Object();
    private static final Properties prop = new Properties(); 
    
    static{
		try {
			String path = Config.class.getClassLoader().getResource("").toURI().getPath();
			FileInputStream fis = new FileInputStream(new File(path + StaticFinal.CONFIG_FILE_PATH));
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		
    }
    
	private Config() {
	
   }
	
  private static Config Instance(){
	  if(instance == null){
		  synchronized (locker){
			instance = new Config();
		  }
	  }
	  
	  return instance ;
  }
  
  
  public static String getConfig(String key,String defaultValue){
	  
	String value =  Instance().prop.getProperty(key);
	if(value == null){
		return defaultValue ;
	}
	return value ;
  }
  
  
  public static String getConfig(String key){
	  
	String value =  Instance().prop.getProperty(key);
	if(value == null){
		return "" ;
	}
	return value ;
  }
  
   
}
