package com.framework.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.framework.common.Config;
import com.framework.common.StaticFinal;

/**
 * 
 * @todo websocket helper calss .
 * use to download flash security policy file.
 *
 * @author fhr
 * @dateTime 2016 2016-11-17 下午4:31:43
 */

public class WebSocketHelper {
	ExecutorService executor = null;
	boolean flag = true ;
	public void loadFalshSecurityPolicy(){
		Executors.newSingleThreadScheduledExecutor().equals(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ServerSocket server = null ;
				try {
					 server = new ServerSocket(Integer.parseInt(Config.getConfig(StaticFinal.WS_SP_LISTEN_PORT,"843")));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				byte buffer[] = new byte[2048];
				String msg = "<?xml version=\"1.0\"?>" 
							+ "<cross-domain-policy>"
							+ "<site-control permitted-cross-domain-policies=\"all\"/>"
							+ "<allow-access-from domain=\"*\" to-ports=\""+Config.getConfig(StaticFinal.WS_ACCESS_TO_PORT)+"\"/>"
							+ "</cross-domain-policy>";
				while (flag) {
					InputStream in = null;
					OutputStream out = null;
					Socket socket = null;
					try {
						socket = server.accept();
						in = socket.getInputStream();
						out = socket.getOutputStream();
						int i = in.read(buffer);
						out.write(msg.getBytes());
						out.flush();
						Thread.sleep(30);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (in != null)
								in.close();
							if (out != null)
								out.close();
							if (socket != null)
								socket.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}}) ;
	}
	
	public void stop(){
		this.flag = false ;
		
		try {
			Thread.sleep(1000) ;
			if(this.executor != null){
				this.executor.shutdown() ;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}catch(Exception e){
			
		}
		
		
	}
	 

}
