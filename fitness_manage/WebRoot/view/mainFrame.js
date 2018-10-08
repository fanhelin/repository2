var titleIndex=0;
var colseForbiddenTab = "首页";
function restFunction(e, title,index){
	    titleIndex=index;
	    if (3 == e.which) {
           e.preventDefault();
		   $('#mm').menu('show', {
               left: e.pageX,
               top: e.pageY
           });
		   
		   $('#mm').data("currtab",title);
	    }
	 }; 
function MainFrame(){
	 this.systemLog = null ;
	 this.systemMsg = null ;
	 this.msgHandler = null ;
	 this.areaManage = null ; 
	 this.userInfo = {userId:'', userName:'' , mainOrgId:0 , ownOrgIds:[] } ;
	 this.init() ;
	
 } ;
 
 MainFrame.prototype.init = function(){
	 this.systemLog = new DataGridPack("#systemLogDatagrid",20,10);
	 this.systemMsg =  new DataGridPack("#systemMsgDatagrid",20,10);
	 //this.msgHandler = new MessageHandler(g_wsURL) ;
	 // this.areaManage = new AreaManage() ;
	 
 };
 
 
 MainFrame.prototype.instance = function(){
	 if(MainFrame.prototype.me == null){
		 MainFrame.prototype.me = new MainFrame() ;
		
	 }
	 return MainFrame.prototype.me ;
 };

      
	$(
	   function(){
		 var mainFrame = MainFrame.prototype.instance() ;
		 mainFrame.loadMenus();
		 mainFrame.setTabAction();
		 mainFrame.setMenuAction();
		 mainFrame.setUserInfo(g_userInfo) ;

		 $('#userEntityId').text(g_userInfo.entity_name+"("+g_userInfo.entity_id+")");
		 $('#userName').text(g_userInfo.userName+"("+g_userInfo.userId+")");
		 $('#userOrg').text(g_userInfo.mainOrgName+"("+g_userInfo.mainOrgId+")");
		 
	     tabCloseEven();
	     
	     var s ={text:'首页' ,fName:'uuu' ,url: "/view/welcome/welcome.html",closable:false};
	 
	     mainFrame.addTab(s);
		// console.log(a+b);
	     
	     
	     $('#frameCenterTabs').tabs({onSelect:function(title,index){
			   var tr = $('#bussMenus_id');
			   var rts =  tr.tree("getRoots") ;
			   for(var i = 0 ;i< rts.length ; i++){
				   if(!rts[i])
					   continue ;
				   
				   var chs = tr.tree('getChildren',rts[i]) ;
				   if(!chs){
					   continue ;
				   }
				   
				   for(var n=0 ; n<chs.length ; n++ ){
					   if(chs[n] && chs[n].text == title){
						   tr.tree('select', chs[n].target);
						   tr.tree('scrollTo', chs[n].target);
						   return ;
					   }
					
				   }
				   
				   
			   }   
		  }}) ;
	  }
	   
	
	   
	   
	);
        
      
		MainFrame.prototype.setTabAction = function(){
			var tabs = $('#frameCenterTabs').tabs().tabs('tabs');
		};

		MainFrame.prototype.setMenuAction = function(){
		   
           var tree = $('#bussMenus_id');
           var me = this ;
		   tree.tree({onClick:function(node){
			           if(tree.tree("isLeaf",node.target)){
						   me.addTab(node) ;
					   }else{
						   tree.tree(node.state === 'closed' ? 'expand' : 'collapse', node.target);
					   }
		   }});

		};
		
		
		MainFrame.prototype.loadOrganizations = function(){
			$.ajax(
             { //请求登录处理页
                 url: g_path+"/organization/loadOrgnization.do", //登录处理页
                 type: 'POST',
                 dataType: "json",
                 //传送请求数据
                  data: { id: row.ID },
                  success: function (ret) { //登录成功后返回的数据
                        //根据返回值进行状态显示
                        if (ret. success) {//注意是True,不是true
                            dataGrid.datagrid("deleteRow" ,index);
                        }
                        else {
                            //$("#divError").show().html("用户名或密码错误！" + strValue);
                        }
                    }
                });
		} ;
		
		MainFrame.prototype.loadMenus = function(){	
			var menuData = [{
					"id":12,
					 url:"/view/client/clientList.jsp",
					 fName:"frame_clientList",
					 closable:true,
					 text:"小程序用户"
				},{
					"id":13,
					 url:"/view/score/scoreList.jsp",
					 fName:"frame_scoreList",
					 closable:true,
					"text":"用户积分兑换"
				},{
					"id":14,
					 url:"/view/course/courseList.jsp",
					 fName:"frame_courseList",
					 closable:true,
					"text":"课程管理"
				},{
					"id":15,
					url:"/view/commodity/commodityList.jsp",
					fName:"frame_commodityList",
					closable:true,
					"text":"兑换商品管理"
				},{
					"id":16,
					 url:"/view/payment/paymentList.jsp",
					 fName:"frame_paymentList",
					 closable:true,
					"text":"视频支付订单"
				}
			];
			
			 $("#bussMenus_id").tree({
              	data:menuData
              });
			
		};
		
		
		MainFrame.prototype.writeLog = function( log){
		
		  if(this.systemLog){
			  var row = {dateTime:ToolKits.shortDayTime(),content:log} ;
			  this.systemLog.addRecord(row) ;
		  }
		  
		};
		
		MainFrame.prototype.writeMsg = function( msg){
			 if(this.systemMsg){
				 var row = {dateTime:ToolKits.shortDayTime(),content:msg} ;
				 this.systemMsg.addRecord(row) ; 
			 }
			  
		};
		
		MainFrame.prototype.sMTS = function(msg){
			if(this.msgHandler){
				this.msgHandler.sendMsg(msg) ;
			}
		};
		
	/**
	 * add tab to frameCenterTab control
	 * @param tabInfo ={text:'' ,fName:'' ,url:''}
	 */
	   MainFrame.prototype.addTab = function(tabInfo){
		   
		   if(tabInfo == null || !tabInfo.fName || !tabInfo.url || !tabInfo.text){
			   return ;
		   }
		  
		   
		    if($('#frameCenterTabs').tabs("exists" ,tabInfo.text)){
				   $('#frameCenterTabs').tabs("select" ,tabInfo.text);
				}
				else{
                  var content = '<iframe name="'+tabInfo.fName+'" scrolling="auto" frameborder="0"  src="'+g_path+tabInfo.url+'" style="width:100%;height:100%;"></iframe>';
               
                  $('#frameCenterTabs').tabs('add',{
						title: tabInfo.text,
						content: content,
						closable: tabInfo.closable
					});

				}
	   };
	   
	   MainFrame.prototype.closeTab = function(tabName){
		   if(tabName){
			   $('#frameCenterTabs').tabs("close" ,tabName) ;
		   }else{
			  var tab = $('#frameCenterTabs').tabs('getSelected') ;
			  if(tab ){
				  var index = $('#frameCenterTabs').tabs('getTabIndex',tab);
				  $('#frameCenterTabs').tabs("close" ,index) ; 
			  }
			
		   }
	   };
	   
	   MainFrame.prototype.setUserInfo = function(userInfo){
		 this.userInfo = userInfo ;
	   };
	   
	   MainFrame.prototype.getUserInfo = function(){
			 return this.userInfo ;
	   };
	   
	   

       //绑定右键菜单事件
    function tabCloseEven() {
       // 刷新
       $('#mm-tabupdate').click(function () {
           var currtab_title = $('#mm').data("currtab");
           $('#frameCenterTabs').tabs('refresh', currtab_title);
       });
       //关闭当前
       $('#mm-tabclose').click(function(){
           var currtab_title = $('#mm').data("currtab");
           if (currtab_title != colseForbiddenTab) {
               $('#frameCenterTabs').tabs('close',currtab_title);
           }
       });
       //全部关闭
       $('#mm-tabcloseall').click(function(){
           $('.tabs-inner span').each(function(i,n){
               var t = $(n).text();
               if (t != colseForbiddenTab) {
                   $('#frameCenterTabs').tabs('close', t);
               }
           });
       });
       //关闭除当前之外的TAB
       $('#mm-tabcloseother').click(function(){
           var currtab_title = $('#mm').data("currtab");
           $('.tabs-inner span').each(function(i,n){
               var t = $(n).text();
               if(t!=currtab_title && t != colseForbiddenTab)
                   $('#frameCenterTabs').tabs('close',t);
           });
       });
       
     //关闭当前右侧的TAB
       $('#mm-tabcloseright').click(function(){
           var nextall = $('.tabs-selected').nextAll();
           if(nextall.length==0){
               return false;
           }
           nextall.each(function(i,n){
               var t=$('a:eq(0) span',$(n)).text();
               if (t != colseForbiddenTab) {
                   $('#frameCenterTabs').tabs('close', t);
               }
           });
           return false;
       });
       //关闭当前左侧的TAB
       $('#mm-tabcloseleft').click(function(){
           var prevall = $('.tabs-selected').prevAll();
           if(prevall.length==0){
             
               return false;
           }
           prevall.each(function(i,n){
               var t=$('a:eq(0) span',$(n)).text();
               if (t != colseForbiddenTab) {
                   $('#frameCenterTabs').tabs('close', t);
               }
           });
           return false;
       });

       //退出
       $("#mm-exit").click(function(){
           $('#mm').menu('hide');
       });

   };
   
   MainFrame.prototype.showImage = function(obj){
	   
	   console.log("height:" , window.screen.availHeight , "width:" ,window.screen.availWidth ) ;
		
		var img = new Image();  
	    img.src = $(obj).attr("src");    
	     var imgHtml = "<img src='" + $(obj).attr("src") + "' />";  
	    //捕获页  
	    layer.open({  
	        type: 1,  
	        shade: false,  
	        title: false, //不显示标题  
	        area: [(img.width> window.screen.availWidth? (window.screen.availWidth -90):img.width) +'px', 
	               (img.height>window.screen.availHeight? (window.screen.availHeight-90):img.height)+'px'],  
	        content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
	        cancel: function () {  
	            //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });  
	        }  
	    });
		
		
	}
   
		