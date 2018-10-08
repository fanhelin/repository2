package com.framework.common;
/**
 * 静态常量类
 * @author fhr
 *
 */

public class StaticFinal {
	//user对象 保存key
   public static final String SESSION_KEY_USER = "SESSION_KEY_USER" ;
   
   //请求路径
  // public static final String REQUEST_PATH_LOGIN = "/wx_manage/user/verify.do" ;
  // public static final String REQUEST_PATH_KICKUSER = "/wx_manage/user/kickUser.do" ;
   
  // public static final String  APPRE_QUEST_PICTEXT = "/wx_manage/appReq/loadPicTexts.do" ;
  // public static final String  APPRE_EXCEPT="/out/" ;
  
   //日志线程配置
   //配置文件路径
   public static final String CONFIG_FILE_PATH = "configs/config.properties" ;
   public static final String CONFIG_LOG_FLAG = "log_Flag" ;
   public static final String CONFIG_LOG_DEEPSLEEP = "log_DeepSleep" ;
   public static final String CONFIG_LOG_SLEEP = "log_Sleep" ;
   public static final String CONFIG_LOG_THREADS = "log_Threads" ;
   public static final String CONFIG_LOG_BATCHSIZE = "log_batchSize" ;
   //日志线程配置
   public static final String CONFIG_DISPATCHER = "dispatcherServlet" ;
   
   //异步任务线程
   public static final String CONFIG_ASYNTASK_SLEEP = "asynTask_Sleep" ;
   public static final String CONFIG_ASYNTASK_THREADS = "asynTask_Threads" ;
   //异步任务线程
   
   //websocket config
   public static final String USER_PROPERTIES_SESSION = "user_properties_session" ;
   public static final String WS_SP_LISTEN_PORT = "ws_sp_listen_port" ;
   public static final String WS_ACCESS_TO_PORT = "ws_access_to_port" ;
   public static final String WS_SERVER_URL = "ws/websocket.sr" ;
   
   //业务配置
   public static final String BUSS_PERIOD_SPAN = "buss_period_span" ;
   public static final String BUSS_BATCHSIZE = "buss_batchSize" ;
   public static final String WAYBILL_CODE_PRECODE = "waybill_code_precode";
   //业务配置
   
   //redis buss
   public static final String REDIS_PU_AREA_CITYS = "redis_pu_area_citys" ;
   public static final String REDIS_PU_AREA_DISTRICTS = "redis_pu_area_districts" ;
   public static final String REDIS_PU_PACKAGEUNITS = "redis_pu_packageunits" ;
   public static final String REDIS_PU_CARGOTYPE = "redis_pu_cargotype" ;
   //redis buss
   
   public static final String SESSION_UNLOAD_STATION = "session_unload_station" ;
   
   public static final String WX_UPLOAD_FOLD = "upload_files" ;
   public static final String PROJECT_NAME = "project_name" ;
   public static final String WX_URL_CODE2SESSION="wx_url_code2session" ;
   public static final String WX_URL_UNIFIEDORDER="wx_url_unifiedorder" ;
   public static final String WX_URL_NOTIFY="wx_url_notify" ;
   public static final String WX_SECRETY_KEY= "WX_SECRETY_KEY" ;
   public static final String WX_NOTIFY_EXPIRE= "wx_notify_expire" ;
   public static final String DISPARK_URLS="dispark_urls_" ;
   public static final String DISPARK_URL_NUM="dispark_url_num" ;
   public static final String IMG_COMPRESS_FLAG="img_compress_flag"; //图片是否压缩标志
   public static final String IMG_COMPRESS_BOUND="img_compress_bound";

   public static final String UPLOAD_FOLD="upload_fold" ;
   
   public static final String UPLOAD_FOLD_URL="upload_fold_url";
   
}
