<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String upload_url=com.framework.common.Config.getConfig(com.framework.common.StaticFinal.UPLOAD_FOLD_URL) + "/client";
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
<script type="text/javascript" src= "clientList.js"></script>

<script type="text/javascript">
 	var g_path = "<%=path%>" ;
 	var upload_url="<%=upload_url%>";
</script>
<style>
	#tb_zcmx  label{margin:0 10px;}
</style>
</head>
<body class="easyui-layout">
   <div data-options="region:'center'" style="padding:5px 3px 3px 3px;border:none;"  title="">  
		 <table id="clientListDatagrid"  title="注册客户信息"></table>
   </div>
   
   	<div id="tb_zcmx" style="padding:7px 5px">
		<div>
		          手机号<input class="easyui-textbox" id="mobileId" style="width:120px">
		    <span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
		         用户名<input class="easyui-textbox" id="userNameId" style="width:120px">
		    <span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" style="margin: 0px 0px;" id="searchBtn">查询</a>
		    <span class="datagrid-btn-separator" style="vertical-align: middle;display:inline-block;float:none"></span>
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" style="margin: 0px 0px;" id="editBtn">查看详情</a>
		    <!-- 
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" style="margin: 0px 0px;" id="addBtn">新增</a>		    
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-redo" style="margin: 0px 0px;" id="auditBtn">审核</a>			
            <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-remove" style="margin: 0px 0px;" id="removeBtn">删除</a>	
           -->
		</div>
	</div>
	
	<div id="dlg_edit" class="easyui-dialog" title="查看客户信息" style="width:540px;height:540px;padding:5px;"
			data-options="
				iconCls: 'icon-blank',
				buttons: '#edit_dlg-buttons',
				closed: 'true'
			">
		   <form id="editForm" method="post">
		   
		      <table cellpadding="4">
              
	    		  <tr> 
	    		  <td  align="right">openid</td> <td><input class="easyui-textbox" type="text" id="openid" name="openid" data-options="readonly:true" style="width:150px;"></input></td>
	    		  <td align="right">姓名</td> <td><input class="easyui-textbox" type="text" id="name" name="name" data-options="readonly:true" style="width:150px;"></input></td> 
	    		  </tr>
	    		  <tr> 
		    		  <td align="right">手机号</td> <td><input class="easyui-textbox" type="text" id="mobile" name="mobile" data-options="readonly:true" style="width:150px;"></input></td> 
		    		  <td align="right">总积分</td> <td><input class="easyui-textbox" type="text" id="score" name="score" data-options="readonly:true" style="width:150px;"></input></td> 
	    		  </tr>
	    		  <tr> 
	    		    <td align="right">签到中天数</td> <td><input class="easyui-textbox" type="text" id="sign_days" name="sign_days" data-options="readonly:true" style="width:150px;"></input></td> 
	    	       <td align="right">连续签到天数</td> <td><input class="easyui-textbox" type="text" id="con_sign_days" name="con_sign_days" data-options="readonly:true" style="width:150px;"></input></td>
	    	      </tr>
	    	       <tr> <td align="right">地 址</td> <td  colspan ="3"><input class="easyui-textbox" id="address" name="address" data-options="multiline:true,readonly:true" style="width:390px;height:38px"></input> </td>  </tr>
	    	        <tr> 
	    		    	<td align="right">最后签到图</td><td colspan="3"><img src="" id="sign_image" width="350px" height="230px" style="clear: both;display:block;margin:auto;" onclick=" ToolKits.showImage(this)"/></td>
	    	
	    	     </tr> 
	    	
	    	</table>
	       </form>
	</div>
	<div id="edit_dlg-buttons">

		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a>
	</div>
</body>
</html>