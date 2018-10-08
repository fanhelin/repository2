package com.framework.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

import org.apache.log4j.Logger;

import com.framework.buss.UserBuss;
import com.framework.common.Notify;
import com.framework.common.StaticFinal;
import com.wx.entity.sys.User;
/**
 * 
 * @todo websocket event process.
 *
 * @author fhr
 * @dateTime 2016 2016-11-22 上午10:13:08
 */
public class WSEventDefault implements IWSEvent {
	Logger log = Logger.getLogger(this.getClass());
	@Override
	public void onOpen(WebSocketServer serverEndpoint,
			Session session, EndpointConfig config) {
		
		WebSocketManager.addWebScoket(serverEndpoint.getHttpSession(), serverEndpoint);     //纳入管理中  
		
		
		User user = (User)serverEndpoint.getHttpSession().getAttribute(StaticFinal.SESSION_KEY_USER) ;
		String msg = user.getUser_code() +":" +user.getUser_name() +" 上线" ;
		msg = Notify.NotifyJsonStr("MESSAGE", "客户端消息", msg) ;
		UserBuss ub = new UserBuss() ;
		user.setLogin_state(1) ;
		ub.setUserStatus(user) ;
		
		
		WebSocketManager.broadcastMsg(serverEndpoint.getHttpSession() ,msg,new IBroadCastFilter() {
			
			@Override
			public boolean isFit(HttpSession session, WebSocketServer wss) {
				// TODO Auto-generated method stub
				
				User currentUser = (User)session.getAttribute(StaticFinal.SESSION_KEY_USER) ;
				User orgUser =(User)wss.getHttpSession().getAttribute(StaticFinal.SESSION_KEY_USER) ;
				/*if(currentUser.getGroupCode().equals(orgUser.getGroupCode())){
					return true ;
				}*/
				
				return false;
			}
		});
		
		 String message  = "有新连接加入！当前在线人数为" + WebSocketManager.getOnlineCount();
		 System.out.println(message);
		 log.info(message)  ;
		 
	}

	@Override
	public void onClose(WebSocketServer serverEndpoint) {
		// TODO Auto-generated method stub
		
		HttpSession currentSession = serverEndpoint.getHttpSession() ;
		
		WebSocketManager.removeWebSocket(currentSession);  //从Map中删除  
		
		User user = (User)currentSession.getAttribute(StaticFinal.SESSION_KEY_USER) ;
		
		UserBuss ub = new UserBuss() ;
	    user.setLogin_state(2) ;
		ub.setUserStatus(user) ;
		
		String msg = user.getUser_code() +":" +user.getUser_name() +" 下线" ;
		msg = Notify.NotifyJsonStr("MESSAGE", "客户端消息", msg) ;
		WebSocketManager.broadcastMsg(serverEndpoint.getHttpSession() ,msg,new IBroadCastFilter() {
			
			@Override
			public boolean isFit(HttpSession session, WebSocketServer wss) {
				// TODO Auto-generated method stub
				
				User currenUser = (User)session.getAttribute(StaticFinal.SESSION_KEY_USER) ;
				User orgUser =(User)wss.getHttpSession().getAttribute(StaticFinal.SESSION_KEY_USER) ;
			/*	if(currenUser.getGroupCode().equals(orgUser.getGroupCode()) ){
					return true ;
				}*/
				
				return false;
			}
		});
		 currentSession.invalidate(); //让当前会话失效
		 String message  = "有一连接关闭！当前在线人数为" + WebSocketManager.getOnlineCount();
		 System.out.println(message);
		 log.info(message)  ;
	}

	@Override
	public void onMessage(WebSocketServer serverEndpoint,String message, Session session) {
		// TODO Auto-generated method stub
		
		      
		        WebSocketManager.broadcastMsg(serverEndpoint.getHttpSession() ,message,new IBroadCastFilter() {
					
					@Override
					public boolean isFit(HttpSession session, WebSocketServer wss) {
						// TODO Auto-generated method stub
						User currenUser = (User)session.getAttribute(StaticFinal.SESSION_KEY_USER) ;
						User orgUser =(User)wss.getHttpSession().getAttribute(StaticFinal.SESSION_KEY_USER) ;
						/*if(currenUser.getGroupCode() == orgUser.getGroupCode()){
							return true ;
						}*/
						
						return false;
					}
				});   
	}

	@Override
	public void onError(WebSocketServer serverEndpoint,Session session, Throwable error) {
		// TODO Auto-generated method stub
		 log.info("有一连接关闭！当前在线人数为" + WebSocketManager.getOnlineCount());  
	}
}
