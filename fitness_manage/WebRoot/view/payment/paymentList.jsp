<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/common.css">
<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path %>/js/common/DatagridColumTool.js"></script>
<script type="text/javascript" src= "<%=path %>/js/ToolKits.js"></script>
<script type="text/javascript" src= "paymentList.js"></script>
<script type="text/javascript">
 	var g_path = "<%=path%>" ;
</script>
<style>
	#tb_zcmx  label{margin:0 10px;}
</style>
</head>
<body class="easyui-layout">
   <div data-options="region:'center'" style="padding:5px 3px 3px 3px;border:none;"  title="">  
		 <table id="paymentlistdg"  title="视频支付订单信息"></table>
   </div>
	<div id="tb_zcmx" style="padding:5px;height:auto">
		<div>
			开始时间<input class="easyui-datetimebox" id="startTimeId" style="width:120px">
			<span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
			截止时间<input class="easyui-datetimebox" id="endTimeId" style="width:120px">
			<span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
		          手机号<input class="easyui-textbox" id="mobileId" style="width:120px">
		    <span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="searchBtn">查询</a>
		</div>
	</div>
</body>
</html>