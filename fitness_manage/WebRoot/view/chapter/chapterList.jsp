<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String upload_url=com.framework.common.Config.getConfig(com.framework.common.StaticFinal.UPLOAD_FOLD_URL)+"/";
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
<script type="text/javascript" src= "chapterList.js"></script>
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
		 <table id="chapterListDatagrid"></table>
   </div>
   
   	<div id="tb_zcmx" style="padding:7px 5px">
		<div>
		  <!--   <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-search" style="margin: 0px 0px;" id="searchBtn">查询</a> -->
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" style="margin: 0px 0px;" id="addBtn">新增章节</a>	
		    <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" style="margin: 0px 0px;" id="editBtn">修改章节</a>
            <a  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-remove" style="margin: 0px 0px;" id="removeBtn">删除</a>	
            
            



		</div>
	</div>
	
	<div id="dlg_edit" class="easyui-dialog" title="修改章节信息" style="width:580px;height:500px;padding:5px;"
			data-options="
				iconCls: 'icon-edit',
				buttons: '#edit_dlg-buttons',
				closed: 'true',
				modal:true
			">
	  <!--  <form id="editForm" method="post" enctype="multipart/form-data">
		   
		      <table cellpadding="4">
              
	    		  <tr> 
		    		  <td  align="right">章节编号</td> <td><input class="easyui-textbox" type="text" id="edit_code" name="code" data-options="readonly:true" style="width:150px;"></input></td>
		    		  <td align="right">章节名称</td> <td><input class="easyui-textbox" type="text" id="edit_name" name="name" data-options="readonly:false" style="width:150px;"></input></td> 
		    	  </tr>
		    	  
	    		  <tr> 
	    		     <td align="right"> 章节描述 </td> <td colspan ="3"><input class="easyui-textbox" type="text" id="edit_describe" name="describe" data-options="readonly:false" style="width:380px;height:38px"></input></td> 
	    	      </tr>
	    	      
	    	     <tr> 
		    		  <td  align="right">章节封面</td> <td><input class="easyui-textbox" type="text" id="edit_code" name="img_name" data-options="readonly:true" style="width:150px;"></input></td>
		    		  <td align="right"></td> <td></td> 
	    		 </tr>
	    		 
	    		  <tr> 
		    		  <td  align="right">视频路径</td> <td colspan ="3">
		    		  <input type="file" id="vidofile" required />
		    		  <input class="easyui-filebox" data-options="buttonText:'选择文件',accept:'application/vnd.ms-excel' " style="width:340px;"/>
		    		  <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:uploadVido()">上传</a>
		    		  </td>
		    		  <td align="right"></td> <td></td> 
	    		 </tr>
	    	     
	    	</table>
	       </form>  -->
	       
	       <form method="post" id="editForm" action="" enctype="multipart/form-data">  
	       <table cellpadding="4" border = "0px" width="100%">
	               <tr> 
		    		  <td  align="right">编号</td> <td><input class="easyui-textbox" type="text" id="edit_code" name="code" data-options="readonly:true" style="width:150px;"></input></td>
		    		  <td align="right">名称</td> <td><input class="easyui-textbox" type="text" id="edit_name" name="name" data-options="readonly:false" style="width:150px;"></input></td> 
		    	  </tr>
		    	  
	    		  <tr> 
	    		     <td align="right"> 描述 </td> <td colspan ="3"><input class="easyui-textbox" type="text" id="edit_describe" name="describe" data-options="readonly:false,multiline:true" style="width:380px;height:60px"></input></td> 
	    	      </tr>
	    	      
	    	       <tr> 
	    		     <td align="right"> 视频名称 </td> <td colspan ="3">  <input class="easyui-textbox" type="text" id="edit_vido_name" name="vido_name" data-options="readonly:true" style="width:380px;"></input><a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" onclick="javascript:clickUploadVidoButton()">上传</a> </td> 
	    	      </tr>
	    	      
	       <tr style="display:none">
	          <td > 新视频:</td> 
	          <td colspan ="3"> 
	               <input class="easyui-textbox" type="text" id="edit_vido_name_old" name="vido_name_old" >
	               <input type="file" name="file" id="id_fileSelect" onchange="uploadVido()" />
	          </td>
	       </tr>
	       <tr> 
	    	  <td align="right">章节图片</td> <td colspan ="3"><input class="easyui-textbox" type="text" id="edit_img_name" name="image_name" data-options="readonly:true" style="width:380px;"></input><a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" onclick="javascript:clickUploadImgButton()">上传</a> </td> 
	       </tr>
	       <tr style="display:none">
	          <td > 新章节图片:</td> 
	          <td colspan ="3"> 
	               <input class="easyui-textbox" type="text" id="edit_img_name_old" name="img_name_old" >
	               <input type="file" name="img_file" id="id_img_fileSelect" onchange="uploadImg()"/>
	          </td>
	       </tr>
	       </table>
	       <img src="" id="Img1" width="350px" height="230px" style="clear: both;display:block;margin:auto;"/>
        </form> 
	</div>
	<div id="edit_dlg-buttons">

		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-edit" onclick="javascript:updateChapter()">确定</a>
	</div>
	
	
	
	<div id="dlg_add" class="easyui-dialog" title="增加章节信息" style="width:540px;height:300px;padding:5px;"
			data-options="
				iconCls: 'icon-add',
				buttons: '#add_dlg-buttons',
				closed: 'true',
				modal:true
			">
		   <form id="addForm" method="post">
		   
		      <table cellpadding="4">
              
	    		  <tr> 
		    		  <td  align="right">章节编号</td> <td><input class="easyui-textbox" type="text" id="add_code" name="code" data-options="readonly:true" style="width:150px;" value="待生成***"></input></td>
		    		  <td align="right">章节名称</td> <td><input class="easyui-textbox" type="text" id="add_name" name="name" data-options="readonly:false" style="width:150px;"></input></td> 
		    	  </tr>
	    		 
	    		  <tr> 
	    		     <td align="right"> 课程描述 </td> <td colspan ="3"><input class="easyui-textbox" type="text" id="add_describe" name="describe" data-options="readonly:false,multiline:true" style="width:380px;height:60px"></input></td> 
	    	 
	    	      </tr>
	    	     
	    	</table>
	       </form>
	</div>
	<div id="add_dlg-buttons">

		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#dlg_add').dialog('close')">关闭</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="javascript:addChapter()">确定</a>
	</div>
</body>
</html>