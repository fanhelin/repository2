package com.framework.interceptor;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.framework.common.Config;
import com.framework.common.StaticFinal;
import com.wx.entity.sys.User;
/**
 * 全局拦拦截器
 * @author fhr
 */

public class AllInterceptor implements HandlerInterceptor {
	public  Logger logger = Logger.getLogger(this.getClass()); 
	
	public static Set<String> DISPARK_URLS = new HashSet<String>() ;
	static{
		 int num = Integer.parseInt(Config.getConfig(StaticFinal.DISPARK_URL_NUM)) ;
		 
		 for(int n=0 ;n<num ;n++){
			 String wx_dispark_str = Config.getConfig(StaticFinal.DISPARK_URLS+n) ;
			 if(wx_dispark_str==null || "".equals(wx_dispark_str))
				 continue ;
			  System.out.println("wx_dispark_str :"+wx_dispark_str) ;
			 String [] strArr= wx_dispark_str.split(",");
			 for (int i =0 ;i< strArr.length ;i++) {
				 strArr[i] =  "/" + Config.getConfig( StaticFinal.PROJECT_NAME) +"/"+ strArr[i];
				 DISPARK_URLS.add( strArr[i]) ;
			 }
		 }
		
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String url = arg0.getRequestURI() ;
		if(isExclude(url)) 
		{
			return true ;
		}
		User user=(User) arg0.getSession().getAttribute(StaticFinal.SESSION_KEY_USER);
		 //if( arg0.getSession().getAttribute(StaticFinal.CONFIG_DISPATCHER) == null){
		 //	  arg0.getSession().invalidate();
		 // }
        if(user==null){
        	logger.error("user not login");
        	arg1.sendRedirect("index.jsp");
            return false;
        }
        
        logger.info("UserInfo="+user);
        return true;
		
	}
	
	
	public static boolean isExclude(String url){
		if(DISPARK_URLS.contains(url)){
			  return true ;
			}else{
				return false ;
			}
	}


}


