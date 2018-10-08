<%@page import="com.util.FunHelper"%>
<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@page import="com.framework.common.StaticFinal" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String wsURL = basePath.replace("http:", "ws:") + StaticFinal.WS_SERVER_URL;
String userInfo = FunHelper.getUserInfo(session) ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8">
	<title>测试demo </title>
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/easyui/jquery.cookie.js"></script>
	
    <script type="text/javascript" src="view/mainFrame.js"></script>
    <script type="text/javascript" src="<%=path %>/js/DataGridPack.js"></script>
    <script type="text/javascript" src="<%=path %>/js/ToolKits.js"></script>
    <script type="text/javascript" src="<%=path %>/js/swfobject.js"></script>
    <script type="text/javascript" src="<%=path %>/js/web_socket.js"></script>
    <script type="text/javascript" src="<%=path %>/js/MessageHandler.js"></script>
    <script type="text/javascript" src="<%=path %>/js/AreaManage.js"></script>
    <script type="text/javascript" src="<%=path %>/layer-v3.1.1/layer/layer.js"></script>
	<style>
	.icon-filter-one{background:url('icon/icon_one.png') no-repeat center center;}
	.icon-filter-two{background:url('icon/icon_two.png') no-repeat center center;}
	.icon-filter-three{background:url('icon/icon_three.png') no-repeat center center;}
	.icon-filter-four{background:url('icon/icon_four.png') no-repeat center center;}
	.icon-filter-five{background:url('icon/icon_five.png') no-repeat center center;}
	.icon-filter-six{background:url('icon/icon_six.png') no-repeat center center;}
	.icon-filter-seven{background:url('icon/icon_seven.png') no-repeat center center;}
	.icon-filter-eight{background:url('icon/icon_eight.png') no-repeat center center;}
	.icon-filter-nine{background:url('icon/icon_nine.png') no-repeat center center;}
	.icon-filter-ten{background:url('icon/icon_ten.png') no-repeat center center;}
	.icon-filter-eleven{background:url('icon/icon_eleven.png') no-repeat center center;}
	.icon-filter-twelve{background:url('icon/icon_twelve.png') no-repeat center center;}
	.icon-filter-thirteen{background:url('icon/icon_thirteen.png') no-repeat center center;}
	.icon-filter-img{background:url('icon/icon_img.png') no-repeat center center;}
	</style>
	<script type="text/javascript">
	    var g_path = "<%=path %>" ; 
	    var g_wsURL = "<%=wsURL%>" ;
	    var g_userInfo = <%=userInfo%> ;
	    
	    console.log('g_userInfo',g_userInfo);
	    $.cookie('login_token', '1131231312313212312');
	</script>
<style type="text/css">
	body{font-family:"Microsoft YaHei";}
	#frameMsgTabs{position: fixed;bottom: 0px;}
    #frameMsgTabs.datagrid-header { position: absolute;visibility: hidden}
	#bussMenus_id>li{
		border-bottom:1px solid #ddd;
	}
	#bussMenus_id>li .tree-node{
		height:40px;
		color:#333;
	}
	#bussMenus_id>li .tree-node>span{
		font-size:14px;
		position:relative;
		top:10px;
	}
	#bussMenus_id>li .tree-node>span.tree-title{
		margin-left:8px;
	}

	#frameLogo{font-size:14px;color:#fff;line-height:50px;}
	.easyuiDIV div{float:right}	
	.panel-title{height:25px!important;line-height:25px!important;}
	.easyui-linkbutton{background:none!important;border:none!important;color:#fff!important;font-size:14px!important;}
 </style>
</head>

<body class="easyui-layout">
	<div class="easyuiDIV" data-options="region:'north',title:'',collapsible:false" style="height:52px;background:#95B8E7">
		<div class="logo" data-options="split:false" style="float:left;margin-left:42px;line-height:48px;font-size: 24px;color: #fff;font-weight: bold;">某 某 公 司</div>		
		<div class="photo" style="width:30px;height:30px;float:right;margin-right:100px;text-align:center;line-height:30px;margin-top:9px;"><img src="icon/yonghuaa.png" id="Img" style="cursor:pointer;"></div>
		<div><a style="left:80px;top:12px;position:relative;float:right;" plain="false" href="<%=path %>/index.jsp" class="easyui-linkbutton" data-options="">退出</a></div>
		<div id="frameLogo" data-options="region:'north',split:false">
			<!-- <span>公      司：</span><label id="userEntityId"  value="" style="color: blue"></label>&nbsp;&nbsp;&nbsp;&nbsp;
			<span>机      构：</span><label id="userOrg" value="" style="color: blue"></label>&nbsp;&nbsp;&nbsp;&nbsp;
			<span>姓      名：</span><label id="userName"  value="" style="color: blue"></label> -->
	    </div>
	</div>
	
	<div data-options="region:'west',split:true,title:' '" style="width:200px;padding:2px;">
	     <div  class="easyui-layout" data-options="fit:true">
			 <div id="frameMenu" data-options="region:'center',split:true" style="width:100%;padding:2px;"> 
			    <div class="easyui-accordion" data-options="fit:true,border:false">
					<div style="padding:2px;height:30px;line-height:30px;background: #E0ECFF;">
						<ul id="bussMenus_id" class="easyui-tree" data-options="url:'',method:'POST',animate:true">
						</ul>
					</div>
	
			   </div>

			 </div>

			<!--操作记录 消息部分-->

		 </div>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'保留'" style="width:180px;padding:2px;">east region
		<div id="frameMsg" data-options="region:'south',split:true" style="width:100%;height:30%;padding:2px;">
			<div id="frameMsgTabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="操作记录" style="padding:2px">
				<table id="systemLogDatagrid" class="easyui-datagrid" data-options="fit:true,fitColumns:false,singleSelect:true,showHeader:true">
				<thead>
				<tr>
				<th data-options="field:'dateTime',width:75"></th>
				<th data-options="field:'content',width:250"></th>
				</tr>
				</thead>
				<tbody>

				</tbody>
				</table>
				</div>

				<div title="消息"  style="padding:2px">
				<table id="systemMsgDatagrid" class="easyui-datagrid" data-options="fit:true,fitColumns:false,singleSelect:true,showHeader:true">
				<thead>
				<tr>
				<th data-options="field:'dateTime',width:75"></th>
				<th data-options="field:'content',width:250"></th>

				</tr>
				</thead>
				<tbody>

				</tbody>
				</table>
				</div>
		</div>
		</div>

	</div>

	<!--
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	-->
  
	<div data-options="region:'center'" style="padding:2px">
	    <div id="frameCenterTabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true,onContextMenu:restFunction">
		 <%--    <div title="首页" data-options="href:'<%=path %>/view/welcome/welcome.html',closable:false" style="padding:2px"></div> --%>
            
        </div>
	</div>
	 <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabupdate">刷新</div>
        <div class="menu-sep"></div>
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseall">全部关闭</div>
        <div id="mm-tabcloseother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-exit">退出</div>
    </div>
</body>

</html>