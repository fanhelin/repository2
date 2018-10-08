/**
 * message handler js class .
 * the packaging of websocket and  system's bussiness message hanldes. 
 * @author fhr
 * @dateTime 2016 2016-11-17 下午3:30
 */

function MessageHandler(wsURL){
	this.webSocket = null ;
	this.wsURL = wsURL ;
	this.init() ;
};

MessageHandler.prototype.init = function(){
	var me = this ;
	
	this.webSocket = new WebSocket(this.wsURL);
	
     // Set event handlers.
	this.webSocket.onopen = function() {
         me.onopen() ;
     };

     this.webSocket.onmessage = function(e) {
       // e.data contains received string.
    	me.onmessage(e.data) ;
     };

     this.webSocket.onclose = function() {
        me.onclose();
     };

     this.webSocket.onerror = function() {
        me.onerror();
     };
};

MessageHandler.prototype.onmessage = function(data){
	try{
		console.info("onmessage recv:" + data ) ;
		
		var jsondata = eval("("+data+")") ;
	   
		switch(jsondata.retCode){
		
		case 'KICKOUT' :
			ToolKits.popTip("提示","您的账号在其他地方登陆，您将被强迫下线！"); 
			this.webSocket.close();  
			$.messager.confirm('Warning',"您的账号在其他地方登陆，您被强迫下线！是否离开页面？",function(r){
				if(r){
					window.location.href = g_path +"/index.jsp" ;
				}else{
					window.location.href = g_path +"/index.jsp" ;
				}
			});
			
		
		
			break ;
			
		case 'MESSAGE':
			ToolKits.popTip("提示",jsondata.data);
		    ToolKits.writeMsg(jsondata.data);
			break ;
			
		default:
			
			break ;
		}
		
		
	}catch(e){
		
	}

};

MessageHandler.prototype.onopen = function(){
	
};

MessageHandler.prototype.onclose = function(){
	
};

MessageHandler.prototype.onerror = function(){
	var me = this ;
	function reConnect(){
		me.init() ;
	};
   setTimeout(reConnect,3000); //异常需要重连
};

MessageHandler.prototype.sendMsg = function(msg){
	try{
		if(this.webSocket){
			this.webSocket.send(msg) ;
		}
	}catch(e){
		
	}
};