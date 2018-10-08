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
<title>欢迎登录**物流平台</title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
	
	
	
</head>
<script type="text/javascript">
 	var g_path = "<%=path%>" ;
 	var doOnLoad = function(){
 	   
 	};
</script>

<body onload="doOnLoad()">
  <h2>Row Editing in DataGrid</h2>
	<p>Click the row to start editing.</p>
	<div style="margin:20px 0;"></div>
	
	<table id="dg" class="easyui-datagrid" title="Row Editing in DataGrid" style="width:700px;height:auto"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: 'datagrid_data1.json',
				method: 'get',
				onClickRow: onClickRow
			">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80">Item ID</th>
				<th data-options="field:'productid',width:100,
						formatter:function(value,row){
							return row.productname;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'productid',
								textField:'productname',
								method:'get',
								url:'http://www.jeasyui.net/demo/products.json',
								required:true
							}
						}">Product</th>
				<th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>
				<th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Unit Cost</th>
				<th data-options="field:'attr1',width:250,editor:'textbox'">Attribute</th>
				<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>
			</tr>
		</thead>
	</table>
 
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
	</div>
	
	<script type="text/javascript">
	
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
				var productname = $(ed.target).combobox('getText');
				$('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
		}
		function accept(){
			if (endEditing()){
				$('#dg').datagrid('acceptChanges');
			}
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		function getChanges(){
			var rows = $('#dg').datagrid('getChanges',"updated");
			alert(rows.length+' rows are changed!');
			
			 rows = $('#dg').datagrid('getChanges',"inserted");
			 alert(rows.length+' rows are inserted!');
			 
			 rows = $('#dg').datagrid('getChanges',"deleted");
			 alert(rows.length+' rows are deleted!');
			 
			 
			 
		}
	</script>
	
<div class="easyui-tabs" style="width:100%;height:400px;">
		<div title="About" style="padding:10px">
			<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
			<ul>
				<li>easyui is a collection of user-interface plugin based on jQuery.</li>
				<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
				<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
				<li>complete framework for HTML5 web page.</li>
				<li>easyui save your time and scales while developing your products.</li>
				<li>easyui is very easy but powerful.</li>
			</ul>
		</div>
		
		<div title="My Documents" style="padding:10px">
		<table width="1235px" border = 0 >
	    <tr><td width="32%" id="id_cargoDetails">
			<table id="id_dg_cargo_details" class="easyui-datagrid" title="" style="width:100%;height:364px;margin:0px 0px 0px 0px;"
					data-options="rownumbers:true,singleSelect:true,url:'',method:'POST'">
				<thead>
				     <tr>
				    	 <th align="left" colspan=7 data-options="field:'220'">【在库货物明细】</th>
				    </tr>
				    
					<tr>
						<th data-options="field:'my_b_wb_c',width:90">运单号</th>
						<th data-options="field:'arrival_place_name',width:120">到货地</th>
						<th data-options="field:'cargo_name',width:120">货物名称</th>
						<th data-options="field:'quantity',width:70">件 数</th>
						<th data-options="field:'volume',width:70">体 积</th>
						<th data-options="field:'weight',width:70">重 量</th>
						<th data-options="field:'remark',width:120">备  注</th>
						<th data-options="field:'id',hidden:true">id</th>
						<th data-options="field:'waybill_code',hidden:true">waybill_code</th>
						<th data-options="field:'arrival_place_id',hidden:true">arrival_place_id</th>
						<th data-options="field:'cargo_type_code',hidden:true">cargo_type_code </th>
						<th data-options="field:'package_code',hidden:true">package_code</th>
						<th data-options="field:'length',hidden:true">length</th>
						<th data-options="field:'width',hidden:true">width</th>
						<th data-options="field:'high',hidden:true">high</th>
						<th data-options="field:'subitem_id',hidden:true">subitem_id</th>
						
					</tr>
				</thead>
			</table>
	    </td> 
	  
	    <td width="68%"  id="id_loadCargos">
	
			  	<table id="id_dg_load_cargos" class="easyui-datagrid" title="" style="height:364px;margin:0px 0px 0px 0px;"
					data-options="rownumbers:true,url:'',width:'auto',method:'POST',toolbar:'#tb_zcmx2',checkOnSelect:true,selectOnCheck:true">
				  <thead frozen="true">
				  		<th data-options="field:'checkbox',checkbox:true"></th>
					    <th data-options="field:'my_b_wb_c',width:80">运单号</th>
						<th data-options="field:'arrival_place_name',width:120">到货地</th>
						<th data-options="field:'cargo_name',width:120">货物名称</th>
						<th data-options="field:'unload_organization_id',width:100">卸车站点</th>
					    <th data-options="field:'is_delivery',width:60,formatter:function(val){if(1==val)return '是' ;else return '否';}">是否送货</th>
					    <th data-options="field:'settle_name',width:70">结算方式</th>
					    <th data-options="field:'cost_split',width:80,editor:{type:'numberbox',options:{precision:2}}">运费分摊额</th>
					    <th data-options="field:'b_inquiry_amount',width:80,editor:{type:'numberbox',options:{precision:2}}">对方报价</th>
					    <th data-options="field:'inquiry_amount',width:80,editor:{type:'numberbox',options:{precision:2}}">我方报价</th>
					    <th data-options="field:'amount',width:80,editor:{type:'numberbox',options:{precision:2}}">确认报价</th>
				  </thead>
				<thead>
					<tr>
					    <th data-options="field:'quantity',width:40">件 数</th>
						<th data-options="field:'volume',width:40">体 积</th>
						<th data-options="field:'weight',width:40">重 量</th>
					    <th data-options="field:'exec_bill_code',width:100">执行单单号</th>
					    <th data-options="field:'remark',width:100">货物备注</th>
					    <th data-options="field:'waybill_code',hidden:true">waybill_code</th>
					    <th data-options="field:'arrival_place_id',hidden:true">arrival_place_id</th>
					    <th data-options="field:'cargo_type_code',hidden:true">cargo_type_code </th>
						<th data-options="field:'package_code',hidden:true">package_code</th>
						<th data-options="field:'length',hidden:true">length</th>
						<th data-options="field:'width',hidden:true">width</th>
						<th data-options="field:'high',hidden:true">high</th>
						<th data-options="field:'settle_code',hidden:true">settle_code</th>
						<th data-options="field:'settle_type',hidden:true">settle_code</th>
						<th data-options="field:'id',hidden:true">id</th>
						<th data-options="field:'subitem_id',hidden:true">subitem_id</th>
						<th data-options="field:'waybill_cargo_id',hidden:true">waybill_cargo_id</th>
					</tr>
				</thead>
			</table>
			<div id="tb_zcmx" style="padding:0px;height:auto">
			    <a title="隐藏货物明细" id="id_but_sh_detail" class="easyui-linkbutton"  plain="false" style="margin: 0px 10px;" onclick ="hideShowDetail();">&nbsp;<<&nbsp;</a>
			  	<label >【已装车货运明细】 &nbsp;&nbsp;&nbsp;&nbsp;结算方式</label>
			  	<input class="easyui-combobox" 
					name="language"
					data-options="
							valueField:'settle_code',
  							textField:'settle_name',
  							method:'post',
  							panelHeight:'auto',
  							width:100,
  							url:'<%=path %>/common/querySettlementsFCB.do',
  							queryParams:{settle_direction:'支',settleTypeIn:'4,1,2',needBlank:1},
  							
  							editable:false">
			 <a class="easyui-linkbutton" id="id_bt_scftsj" plain="false" style="display:none;margin: 0px 10px;" onclick =";">生成分摊数据</a>
			 <a class="easyui-linkbutton" id="id_bt_bcftsj" plain="false" style="display:none;margin: 0px 10px;" onclick =";">保存报价</a>
			 <a class="easyui-linkbutton" id="id_bt_load_sczxd" plain="false" style="display:none;margin: 0px 10px;" onclick =";">生成执行单</a>
			</div>
	  </td>
	    
	  </tr>
	</table>
		</div>
		
		<div title="Help" data-options="iconCls:'icon-help',closable:true" style="padding:10px">
			This is the help content.
		</div>
		
	</div>	
	
<div id="tt0" class="easyui-tabs" style="width:100%;height:400px;" data-options="">
     <div title="货物明细单" style="padding:0px;">
	  <table width="100%" height="100%" border = 1 >
	    <tr>
	    <td width="32%" id="id_cargoDetails">
		       弟弟顶顶顶顶顶顶顶顶顶顶点点滴滴多多多多多多多
	    </td> 
	  
	    <td width="68%"  id="id_loadCargos">
	
			  	<table id="id_dg_load_cargos" class="easyui-datagrid" title="" style="height:364px;margin:0px 0px 0px 0px;"
					data-options="rownumbers:true,url:'',width:'auto',method:'POST',toolbar:'#tb_zcmx',checkOnSelect:true,selectOnCheck:true">
				  <thead frozen="true">
				  		<th data-options="field:'checkbox',checkbox:true"></th>
					    <th data-options="field:'my_b_wb_c',width:80">运单号</th>
						<th data-options="field:'arrival_place_name',width:120">到货地</th>
						<th data-options="field:'cargo_name',width:120">货物名称</th>
						<th data-options="field:'unload_organization_id',width:100">卸车站点</th>
					    <th data-options="field:'is_delivery',width:60,formatter:function(val){if(1==val)return '是' ;else return '否';}">是否送货</th>
					    <th data-options="field:'settle_name',width:70">结算方式</th>
					    <th data-options="field:'cost_split',width:80,editor:{type:'numberbox',options:{precision:2}}">运费分摊额</th>
					    <th data-options="field:'b_inquiry_amount',width:80,editor:{type:'numberbox',options:{precision:2}}">对方报价</th>
					    <th data-options="field:'inquiry_amount',width:80,editor:{type:'numberbox',options:{precision:2}}">我方报价</th>
					    <th data-options="field:'amount',width:80,editor:{type:'numberbox',options:{precision:2}}">确认报价</th>
				  </thead>
				<thead>
					<tr>
					    <th data-options="field:'quantity',width:40">件 数</th>
						<th data-options="field:'volume',width:40">体 积</th>
						<th data-options="field:'weight',width:40">重 量</th>
					    <th data-options="field:'exec_bill_code',width:100">执行单单号</th>
					    <th data-options="field:'remark',width:100">货物备注</th>
					    <th data-options="field:'waybill_code',hidden:true">waybill_code</th>
					    <th data-options="field:'arrival_place_id',hidden:true">arrival_place_id</th>
					    <th data-options="field:'cargo_type_code',hidden:true">cargo_type_code </th>
						<th data-options="field:'package_code',hidden:true">package_code</th>
						<th data-options="field:'length',hidden:true">length</th>
						<th data-options="field:'width',hidden:true">width</th>
						<th data-options="field:'high',hidden:true">high</th>
						<th data-options="field:'settle_code',hidden:true">settle_code</th>
						<th data-options="field:'settle_type',hidden:true">settle_code</th>
						<th data-options="field:'id',hidden:true">id</th>
						<th data-options="field:'subitem_id',hidden:true">subitem_id</th>
						<th data-options="field:'waybill_cargo_id',hidden:true">waybill_cargo_id</th>
					</tr>
				</thead>
			</table>
			<div id="tb_zcmx2" style="padding:0px;height:auto">
			    <a title="隐藏货物明细" id="id_but_sh_detail" class="easyui-linkbutton"  plain="false" style="margin: 0px 10px;" onclick =";">&nbsp;<<&nbsp;</a>
			  	<label >【已装车货运明细】 &nbsp;&nbsp;&nbsp;&nbsp;结算方式</label>
			  	<input class="easyui-combobox" 
					name="language"
					data-options="
							valueField:'settle_code',
  							textField:'settle_name',
  							method:'post',
  							panelHeight:'auto',
  							width:100,
  							url:'',
  							queryParams:{settle_direction:'支',settleTypeIn:'4,1,2',needBlank:1},
  						
  							editable:false">
			 <a class="easyui-linkbutton" id="id_bt_scftsj" plain="false" style="display:none;margin: 0px 10px;" onclick =";">生成分摊数据</a>
			 <a class="easyui-linkbutton" id="id_bt_bcftsj" plain="false" style="display:none;margin: 0px 10px;" onclick =";">保存报价</a>
			 <a class="easyui-linkbutton" id="id_bt_load_sczxd" plain="false" style="display:none;margin: 0px 10px;" onclick =";">生成执行单</a>
			</div>
	  </td>
	    
	  </tr>
	</table>
     	
  </div>
     
     <div title="下游站点结算" style="padding:0px;">
     	<table width="1250px" border=0>
    	<tr>
    		<td width="100%"> 
    				<table id="" class="easyui-datagrid" title="" style="width:100%;height:360px;margin:0px 0px 0px 0px;"
					data-options="rownumbers:true,url:'',method:'POST',onClickCell: null,toolbar:'#tb_xy_js'">
					<thead>
						<tr>
						    <th data-options="field:'checkbox',checkbox:true"></th>
							<th data-options="field:'3',width:120">运单号</th>
							<th data-options="field:'5',width:175">支出项目(本站点支付下游站点)</th>
							
							<th data-options="field:'6',width:70">对方报价</th>
							<th data-options="field:'66',width:70">我方报价</th>
							<th data-options="field:'7',width:70">确认金额</th>
							<th data-options="field:'8',width:100">结算方式</th>
							<th data-options="field:'9',width:130">执行单号</th>
							<th data-options="field:'id',hidden:true">id</th>
							<th data-options="field:'idd',hidden:true">idd</th>
						</tr>
					</thead>
					</table>
					<div id="tb_xy_js" style="padding:0px;height:auto">
						
					  	<label >【下游站点费用结算单】
					  	 &nbsp;&nbsp;&nbsp;&nbsp;</label>
					  	 <a class="easyui-linkbutton" id="id_bt_xyjs_scjsd" plain="false" style="display:none;margin: 0px 10px;" onclick =";">生成结算单</a>
					  	 
					  	<label>结算方式</label><input class="easyui-combobox" 
							name="language"
							data-options="
									valueField:'settle_code',
		  							textField:'settle_name',
		  							method:'post',
		  							panelHeight:'auto',
		  							width:100,
		  							url:'',
		  							queryParams:{settle_direction:'支',settleTypeIn:'4,1,2'},
		  							
		  							editable:false
							">
					  	<a class="easyui-linkbutton" id="id_bt_xyjs_qrbj" plain="false" style="display:none;margin: 0px 10px;" onclick =";">确认报价</a>
					  	<a class="easyui-linkbutton" id="id_bt_xyjs_bc" plain="false" style="display:none;margin: 0px 10px;" onclick =";">保 存</a>
					  	<a class="easyui-linkbutton" id="id_bt_xyjs_sczxd" plain="false" style="display:none;margin: 0px 10px;" onclick =";">生成执行单</a>
					  	
					</div>
    		</td>
    	
    	</tr>
    	</table>

     </div>
     
     <div title="下游站点垫付" style="padding:0px;">
     	<table width="1250px" border=0>
    	<tr>
    		<td width="100%"> 
    				<table id="" class="easyui-datagrid" title="" style="width:100%;height:360px;margin:0px 0px 0px 0px;"
					  data-options="rownumbers:true,url:'',method:'POST',onClickCell: null,toolbar:'#tb_xy_df'">
					<thead>
						<tr>
							<th data-options="field:'checkbox',checkbox:true"></th>
							<th data-options="field:'3',width:120">运单号</th>
							<th data-options="field:'4',width:160">垫付款项(本站点收下游站点)</th>
						
							<th data-options="field:'5',width:70">垫付金额</th>
							<th data-options="field:'7',width:90">已收垫付金额</th>
							<th data-options="field:'43',width:100">结算方式</th>
							<th data-options="field:'9',width:130">执行单号</th>
							<th data-options="field:'id',hidden:true">id</th>
						</tr>
					</thead>
					</table>
					
					<div id="tb_xy_df" style="padding:0px;height:auto">
					  	<label >【下游站点垫付单】 &nbsp;&nbsp;&nbsp;&nbsp;</label>
					  	<a class="easyui-linkbutton" id="id_bt_xydf_scdfd" plain="false" style="display:none;margin: 0px 10px;" onclick =";">生成垫付单</a>
					  	<label>结算方式</label>
					  	<input class="easyui-combobox" 
							name="language"
							data-options="
									valueField:'settle_code',
		  							textField:'settle_name',
		  							method:'post',
		  							panelHeight:'auto',
		  							width:100,
		  							url:'',
		  							queryParams:{settle_direction:'支',settleTypeIn:'4,1,2'},
		  						
		  							editable:false
							">
					  	<a class="easyui-linkbutton" id="id_bt_xydf_qrbj" plain="false" style="display:none;margin: 0px 10px;" onclick =";">确认报价</a>
					  	<a class="easyui-linkbutton" id="id_bt_xydf_bc" plain="false" style="display:none;margin: 0px 10px;" onclick =";">保 存</a>
					  	<a class="easyui-linkbutton" id="id_bt_xydf_sczxd" plain="false" style="display:none;margin: 0px 10px;" onclick =";">生成执行单</a>
					</div>
    		</td>
    	</tr>
    	</table>

     </div>
 
  </div>	
	

</body>

<script type="text/javascript">

</script>
</html>