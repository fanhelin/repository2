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

<script type="text/javascript" src="<%=path %>/js/common/DatagridColumTool.js"></script>

<script type="text/javascript" src= "<%=path %>/js/ToolKits.js"></script>
<script type="text/javascript" src= "scoreList.js"></script>
<script type="text/javascript">
 	var g_path = "<%=path%>" ;
</script>
<style>
	#tb_zcmx  label{margin:0 10px;}
</style>
</head>
<body class="easyui-layout">
   <div data-options="region:'center'" style="padding:5px 3px 3px 3px;border:none;"  title="">  
		 <table id="scoreListDatagrid"  title="用户积分兑换记录"></table>
   </div>
   
   	<div id="tb_zcmx" style="padding:7px 5px">
		<div>
		          开始时间<input class="easyui-datetimebox" id="startTimeId" style="width:120px">
			<span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
			截止时间<input class="easyui-datetimebox" id="endTimeId" style="width:120px">
			<span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
		          手机号<input class="easyui-textbox" id="mobileId" style="width:120px">
		    <span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
		         发货地址<input class="easyui-textbox" id="addressId" style="width:200px">
		    <span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
		         状态<select class="easyui-combobox" id="stateId"><option value="">请选择</option><option value="1">已处理</option><option value="0">未处理</option></select>
		    <span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" style="margin: 0px 0px;" id="searchBtn">查询</a>
		    <span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" style="margin: 0px 0px;" id="doBtn">处理</a>
		</div>
	</div>
</body>
</html>