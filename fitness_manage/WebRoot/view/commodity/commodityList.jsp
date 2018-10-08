<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String upload_url=com.framework.common.Config.getConfig(com.framework.common.StaticFinal.UPLOAD_FOLD_URL) + "/commodity";
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
<script type="text/javascript" src= "commodityList.js"></script>
<script type="text/javascript">
 	var g_path ="<%=path%>";
 	var upload_url="<%=upload_url%>";
</script>
<style>
	#tb_zcmx  label{margin:0 10px;}
</style>


</head>
<body class="easyui-layout">
   <div data-options="region:'center'" style="padding:5px 3px 3px 3px;border:none;"  title="">  
		 <table id="commodityListDatagrid"></table>
   </div>
   
   	<div id="tb_zcmx" style="padding:7px 5px">
		<div>
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" style="margin: 0px 0px;" id="searchBtn">查询</a>
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" style="margin: 0px 0px;" id="addBtn">新增商品</a>	
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" style="margin: 0px 0px;" id="editBtn">修改商品</a>
            <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-remove" style="margin: 0px 0px;" id="removeBtn">删除商品</a>	


		</div>
	</div>
	
	<div id="dlg_edit" class="easyui-dialog" title="修改商品信息" style="width:710px;height:480px;padding:5px;"
			data-options="
				iconCls: 'icon-edit',
				buttons: '#edit_dlg-buttons',
				closed: 'true',
				modal:true
			">
		   <form id="editForm" method="post" enctype="multipart/form-data" accept="image/jpeg,image/jpg,image/png">
		   
		      <table cellpadding="6">
              
	    		  <tr> 
	    		  	<td  align="right">商品编号</td> <td><input class="easyui-textbox" type="text" id="edit_code" name="code" data-options="readonly:true" style="width:150px;"></input></td>
	    		  	<td align="right">商品名称</td> <td><input class="easyui-textbox" type="text" id="edit_name" name="name" data-options="readonly:true" style="width:150px;"></input></td>
	    		  	<td  align="right">需要积分</td> <td><input class="easyui-textbox" type="text" id="edit_score" name="score" data-options="readonly:false" style="width:150px;"></input></td> 
	    		  </tr>
	    		  <tr> 
	    		  	<td align="right">价格</td> <td><input class="easyui-textbox" type="text" id="edit_price" name="price" data-options="readonly:false" style="width:150px;"></input></td>
	    		  	<td align="right">实际价格</td> <td><input class="easyui-textbox" type="text" id="edit_r_price" name="r_price" data-options="readonly:false" style="width:150px;"></input></td> 
	    		  	<td align="right">库存数量</td> <td><input class="easyui-textbox" type="text" id="edit_sum" name="sum" data-options="readonly:false" style="width:150px;"></input></td> 
	    		  </tr>
	    		  <tr> 
	    		  	<td align="right">单位</td> <td><input class="easyui-textbox" type="text" id="edit_unit" name="unit" data-options="readonly:false" style="width:150px;"></input></td>
	    		  	<td align="right" >商品标题</td> <td colspan="3"><input class="easyui-textbox" type="text" id="edit_title" name="title" data-options="readonly:false" style="width:380px;height:38px"></input></td> 
	    		  </tr>
	    		  <tr> 
	    		     <td align="right">状态</td> <td><input type="radio" name="status" value="1">上架</input> <input type="radio" name="status" value="0">下架</input></td> 
		    		 <td align="right">商品描述</td> <td colspan ="3"><input class="easyui-textbox" type="text" id="edit_desc" name="des" data-options="readonly:false" style="width:380px;height:38px"></input></td> 
	    		  </tr>
	    		 <tr> 
	    		  	<td align="right">商品主图</td><td colspan="2"><input  type="file" id="edit_main_img" name="main_img_file" style="width:150px;" onchange="preImg(this.id,'eImg1')"></input></td>
	    		  	<td align="right">商品附图</td><td colspan="2"><input  type="file" id="edit_fu_img" name="fu_img_file" style="width:150px;" onchange="preImg(this.id,'eImg2')"></input></td>
	    	     </tr> 
	    	</table>
	    	<img src="" id="eImg1" width="302px" height="170px" style="float:left"/>
	    	<img src="" id="eImg2" width="302px" height="170px" style="float:right"/>
	    	<input type="hidden" id="main_img_h" name="main_img" ></input>
	    	<input type="hidden" id="fu_img_h" name="fu_img"></input>
	       </form>
	</div>
	<div id="edit_dlg-buttons">

		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" onclick="javascript:updatecommodity()">确定</a>
	</div>
	
	
	
	<div id="dlg_add" class="easyui-dialog" title="增加商品信息" style="width:710px;height:480px;padding:5px;"
			data-options="
				iconCls: 'icon-add',
				buttons: '#add_dlg-buttons',
				closed: 'true',
				modal:true
			">
		   <form id="addForm" method="post" enctype="multipart/form-data" accept="image/jpeg,image/jpg,image/png">
		   
		      <table cellpadding="6">
              
	    		  <tr> 
	    		  	<td  align="right">商品编号</td> <td><input class="easyui-textbox" type="text" id="add_code" name="code" data-options="readonly:true" style="width:150px;" value="待生成***"></input></td>
	    		  	<td align="right">商品名称</td> <td><input class="easyui-textbox" type="text" id="add_name" name="name" data-options="readonly:false" style="width:150px;"></input></td>
	    		  	<td  align="right">需要积分</td> <td><input class="easyui-textbox" type="text" id="add_sorce" name="sorce" data-options="readonly:false" style="width:150px;"></input></td> 
	    		  </tr>
	    		  <tr> 
	    		  	<td align="right">价格</td> <td><input class="easyui-textbox" type="text" id="add_price" name="price" data-options="readonly:false" style="width:150px;"></input></td>
	    		  	<td align="right">实际价格</td> <td><input class="easyui-textbox" type="text" id="add_r_price" name="r_price" data-options="readonly:false" style="width:150px;"></input></td> 
	    		  	<td align="right">库存数量</td> <td><input class="easyui-textbox" type="text" id="add_sum" name="sum" data-options="readonly:false" style="width:150px;"></input></td> 
	    		  </tr>
	    		  <tr> 
	    		  	<td align="right">单位</td> <td><input class="easyui-textbox" type="text" id="add_unit" name="unit" data-options="readonly:false" style="width:150px;"></input></td>
	    		  	<td align="right" >商品标题</td> <td colspan="3"><input class="easyui-textbox" type="text" id="add_title" name="title" data-options="readonly:false" style="width:380px;height:38px"></input></td> 
	    		  </tr>
	    		  <tr> 
	    		     <td align="right">状态</td> <td><input type="radio" name="add_status" value="1">上架</input> <input type="radio" name="add_status" value="0">下架</input></td> 
		    		 <td align="right">商品描述</td> <td colspan ="3"><input class="easyui-textbox" type="text" id="add_desc" name="desc" data-options="readonly:false" style="width:380px;height:38px"></input></td> 
	    		  </tr>
	    		  <tr> 
	    		  	<td align="right">商品主图</td><td colspan="2"><input  type="file" id="add_main_img" name="main_img" style="width:150px;" onchange="preImg(this.id,'Img1')"></input></td>
	    		  	<td align="right">商品附图</td><td colspan="2"><input  type="file" id="add_fu_img" name="fu_img" style="width:150px;" onchange="preImg(this.id,'Img2')"></input></td>
	    	      </tr> 
	    	</table>
	    	<img src="" id="Img1" width="302px" height="170px" style="float:left"/>
	    	<img src="" id="Img2" width="302px" height="170px" style="float:right"/>
	    	
	       </form>
	</div>
	<div id="add_dlg-buttons">

		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#dlg_add').dialog('close')">关闭</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="javascript:addcommodity()">确定</a>
	</div>
</body>
</html>