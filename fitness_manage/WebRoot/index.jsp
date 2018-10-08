<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录</title>
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>


</head>

<body style="margin:0!important;">
<img src="img/login.jpg" style="width:100%;height:100%"> 
<div id="loginWin"  class="easyui-window" title="登 录" style="width:380px;height:210px;padding:5px;"
	
   minimizable="false" maximizable="false" resizable="false" collapsible="false" closable="false">
  
    <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="loginForm" method="post">
            <div style="padding:15px 0;">
                <label for="uerName">帐  号:</label>
                <input type="text" name="user_code" style="width:260px;"></input>
            </div>
            <div style="padding:5px 0;">
                <label for="password">密 码:</label>
                <input type="password" name="password" style="width:260px;"></input>
            </div>
             <div style="padding:5px 0;text-align: center;color: red;" id="showMsg"></div>
        </form>
            </div>
            <div region="south" border="false" style="text-align:right;padding:5px 0;">
                <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="login()">登 录</a>
                <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="cleardata()">重置</a>
            </div>
    </div>
   
</div>
 </img>
</body>
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
if(window.top!=window.self){
	window.top.location=window.self.location;
}

document.onkeydown = function(e){
    var event = e || window.event;  
    var code = event.keyCode || event.which || event.charCode;
    if (code == 13) {
        login();
    }
}

$(function(){
    $("input[name='login']").focus();
   
});

function cleardata(){
    $('#loginForm').form('clear');
	 $("#showMsg").html("");
}
function login(){

     //alert($("#loginForm").serialize()) ;
     if($("input[name='login']").val()=="" || $("input[name='password']").val()==""){
         $("#showMsg").html("用户名或密码为空，请输入");
         $("input[name='login']").focus();
    }else{
            //ajax异步提交  
           $.ajax({            
                  type:"POST",   //post提交方式默认是get
                  url:"<%=path%>/user/verify.do", 
                  data:$("#loginForm").serialize(),   //序列化               
                  error:function(request) {      // 设置表单提交出错                 
                      $("#showMsg").html(request);  //登录错误提示信息
                  },
                  success:function(data) {
                        
                      if(data.success)
                       {
                       		document.location = "<%=path%>/doLogin.do";
                       }
                       else
                       	{
                       		if(data.retCode == "-3"){
                       	
                       			document.location = "<%=path%>/doLogin.do";
                       			
                       		}else{
                       			$("#showMsg").html(data.message);
                       		}
                       		
                       		
                       	} 
                  }            
            });       
        } 
}


</script>
</html>