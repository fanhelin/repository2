/**
 * 运单搜索选择窗口类
 * @author fhr 
 * @returns {WaybillDialog}
 * url:/wayBillHead/getWaybillForCombbox.do 通用
 * url2:/wayBillHead/selectCarLoadWaybill.do //获取装车清单对应的运单
 */
$.parser.auto = false ;
function WaybillDialog()
{

	this.preFix = "WAY_BILL_" ;
	this.htm = "" ;
	this.triggerEle = null ;
	
	this.DLG_ID = this.preFix + "ID_DIALOG" ;
	this.DATAGRID_ID = this.preFix + "ID_DATAGRID" ;
	this.DLG_BUTTONS_ID =  this.preFix + "ID_DLG_BUTTONS" ;
	this.DLG_SEARCHBOX_ID =  this.preFix + "SEARCH_TXT";
	this.URL = "" ;
	this.TRG_SEARCHBOX = null ;
	
	this.title = "" ; 
	this.width = 500 ;
	this.height = 0 ;
	this.type = 1; // 收发货人类型 ：1：发货人 。2：接货人.3:装车清单对应运单
	this.busData = {} ;
	
}

WaybillDialog.prototype._inner_ok = function(){
	
	var row =  $('#'+this.DATAGRID_ID).datagrid("getSelected"); 
	if(this.onConfirm(row)){
		this.showDialog(false) ;
	}
};

WaybillDialog.prototype._inner_dgrowdbclick = function(rowIndex, rowData){
	
	if(WaybillDialog.prototype.instance().onConfirm(rowData)){
		WaybillDialog.prototype.instance().showDialog(false) ;
	}
};

WaybillDialog.prototype.setHtml = function(){
	
	this.htm = "<div id='" + this.DLG_ID+ "' class='easyui-dialog' title='" + this.title + "' style='width:" + this.width + "px;height:" + this.height + "px;padding:5px;'" ;
	this.htm +=" data-options=\" iconCls: 'icon-edit', buttons: '#" + this.DLG_BUTTONS_ID + "',closed: 'true', modal:true\"> " ;	

	this.htm += "<div id='inner_search_tb' style='padding:5px;height:auto'>" ;
	this.htm += "运单编号：<input class='easyui-searchbox' data-options=\"prompt:'搜索运单',searcher:WaybillDialog.prototype.instance().innerSearch\" id='"+this.DLG_SEARCHBOX_ID+"' style='width:25%'>" ;
	this.htm += "</div>" ;

	this.htm += "<table id='" + this.DATAGRID_ID + "' class='easyui-datagrid' " ;
	this.htm += " data-options=\"rownumbers:true, singleSelect:true , url:'', fit:true,onDblClickRow:WaybillDialog.prototype.instance()._inner_dgrowdbclick,toolbar:'#inner_search_tb'\">";
	this.htm +="<thead>" ;
	this.htm +="<tr>" ;
	this.htm +="	<th data-options=\"field:'checkbox',checkbox:true\"></th>" ;

	this.htm +="	<th data-options=\"field:'bus_wb_code'\">业务单号</th>" ;
	this.htm +="	<th data-options=\"field:'wb_code'\">系统编号</th>" ;
	this.htm +="	<th data-options=\"field:'wb_a_p_n'\">到货地点名</th>" ;
	if(this.type == 1){
		this.htm +="	<th data-options=\"field:'wb_out_cmp_id',hidden:true\"></th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_cmp_code',hidden:true\"></th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_m_id',hidden:true\"></th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_m_code',hidden:true\"></th>" ;
		
		this.htm +="	<th data-options=\"field:'wb_cmp_n'\">发货公司名</th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_m'\">发货人名字</th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_phoe'\">发货人电话</th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_m_identity_id'\">发货人身份证号</th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_mb',width:110\">发货人手机<br></th>" ;
		
	}else if(this.type == 2){
		this.htm +="	<th data-options=\"field:'wb_get_cmp_id',hidden:true\">收货公司ID</th>" ;
		this.htm +="	<th data-options=\"field:'wb_get_m_id',hidden:true\">收货人ID</th>" ;
		this.htm +="	<th data-options=\"field:'control_1',hidden:true\"></th>" ;
		this.htm +="	<th data-options=\"field:'receipt_state',hidden:true\"></th>" ;
		this.htm +="	<th data-options=\"field:'delivery_state',hidden:true\"></th>" ;
		
		this.htm +="	<th data-options=\"field:'wb_cmp_n_g'\">收货公司名</th>" ;
		this.htm +="	<th data-options=\"field:'wb_get_m'\">收货人名字</th>" ;
		this.htm +="	<th data-options=\"field:'wb_get_phoe'\">收货人电话</th>" ;
		this.htm +="	<th data-options=\"field:'wb_get_m_identity_id'\">收货人身份证号</th>" ;
		this.htm +="	<th data-options=\"field:'wb_get_mb',width:110\">收货人手机<br></th>" ;
		
	}else if(this.type == 3){
		this.htm +="	<th data-options=\"field:'wb_out_cmp_id',hidden:true\"></th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_cmp_code',hidden:true\"></th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_m_id',hidden:true\"></th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_m_code',hidden:true\"></th>" ;
		
		this.htm +="	<th data-options=\"field:'wb_cmp_n'\">发货公司名</th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_m'\">发货人名字</th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_phoe'\">发货人电话</th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_m_identity_id'\">发货人身份证号</th>" ;
		this.htm +="	<th data-options=\"field:'wb_out_mb',width:110\">发货人手机<br></th>" ;
	}
	
	this.htm +="</tr>" ;
	this.htm +="</thead>" ;
	this.htm +="</table>" ;
	this.htm +="</div>" ;
	
	this.htm +="<div id='" + this.DLG_BUTTONS_ID + "'>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-save'  onclick='javascript:WaybillDialog.prototype.instance()._inner_ok()'>确 定</a>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-cancel' onclick='javascript:WaybillDialog.prototype.instance().showDialog(false)'>取 消</a>" ;
    this.htm +="</div>" ;
	
};


WaybillDialog.prototype.setURL = function(url){
	this.URL = url ;
};

/**
 * 搜索事件函数
 * @param value 模糊搜索的文本，此处为运单号
 */
WaybillDialog.prototype.innerSearch = function(value){
	var me = WaybillDialog.prototype.instance() ;
	var searchData = {waybill_code:value , waybill_state:1 , searchType:me.type}  ;
	if(me.type == 3){
		searchData.bill_code = me.busData.bill_code ;
	}
	me.queryData(searchData) ;
};

WaybillDialog.prototype.appendHtml = function(){
	this.setHtml() ;
	$('body').append(this.htm) ;
	
};


WaybillDialog.prototype.init = function(title,width,height,type,busData){
	this.title = title ; 
	this.width = 850 ;//width ;
	this.height = 450 ;//height ;
	this.busData = busData ;
	if(type){
		this.type = type ;
	}
	
	this.appendHtml();
	//$.parser.parse();
};



/**
 * 显示或者隐藏窗口
 * @param flag ：true显示，false ：隐藏
 * @param showData json：{total:0,rows[]}
 * @param searchData {waybill_code:value,waybill_state:1}
 */
WaybillDialog.prototype.showDialog = function(flag,showData,searchData){
	var obj = $('#'+this.DLG_ID) ;
	if(flag)
	{
		if(showData != null ){
			this.showData(showData);
			if(searchData != null){
				//var searchData = {waybill_code:value,waybill_state:1}  ;
				this.setSearchText(searchData.waybill_code) ;
			}
		}else if(searchData != null) {
			this.queryData(searchData) ;
			this.setSearchText(searchData.waybill_code) ;
		}
		
		obj.dialog('open') ;
	}
	else{
     obj.dialog('close') ;
	}
};

WaybillDialog.prototype.instance = function(){
	
	if(WaybillDialog.prototype.me == null){
		WaybillDialog.prototype.me = new WaybillDialog();
	}
	return WaybillDialog.prototype.me ;
	
};

/**
 * datagrid 搜索数据
 * @param value
 */
WaybillDialog.prototype.queryData = function(value){
	
	 var dataGrid =  $('#'+ this.DATAGRID_ID) ;
	 
	 var options =  dataGrid.datagrid('options') ;
	 
     options.queryParams = value;  
     
	 options.url = this.URL ; // g_path+"/wayBillHead/getWaybillForCombbox.do" ;
	 
	 dataGrid.datagrid("reload") ;
	 
};

/**
 * 显示传入数据
 * @param showData json{total：0，rows:[]}
 */
WaybillDialog.prototype.showData = function(showData){
	$('#'+ this.DATAGRID_ID).datagrid("loadData",showData); 
};

/**
 * 搜索文本
 * @param sTxt
 */
WaybillDialog.prototype.setSearchText = function(sTxt){
	$('#'+ this.DLG_SEARCHBOX_ID).searchbox("setValue",sTxt) ;
};


/**
 * 选择确认后回调函数
 * @param row 选择的行
 * @returns {Boolean}是否要关闭选择窗口，true：关闭窗口，false不关闭
 */
WaybillDialog.prototype.onConfirm = function(row){
	alert("请根据业务重新写该事件") ;
	return true ;
};


WaybillDialog.prototype.setTrgSearchBox = function(sb_id , onSelectRecord){
	/*if(sb_id == null || !$("#"+sb_id) ){
		throw new Error("init trg search box fail") ;
		return ;
	}*/
	
	if(onSelectRecord == null ||(  typeof onSelectRecord != "function")){
		throw new Error("onSelectRecord is not a function");
			return ;
	}

	var me = this ;
	if(sb_id){
		this.TRG_SEARCHBOX = $("#"+sb_id) ;
		this.TRG_SEARCHBOX.searchbox({
			searcher:function(value){
				   var searchData = {waybill_code:value, searchType:me.type} ;
				 	  $.ajax({            
			               type:"POST",   //post提交方式默认是get
			               url: me.URL, 
			               data: searchData ,   //序列化               
			               error:function(request) {      // 设置表单提交出错               
			                 
			               },
			               success:function(data) {
							 	if(data.total == 1 && data.rows[0].bus_wb_code == value){
							 		onSelectRecord(data.rows[0]) ;
								}else{
									onSelectRecord(null) ;
									me.showDialog(true,null,searchData);
								} 
			               }            
			         }); 
				   
				
			 }
		}) ;
	};
	
	me.onConfirm = function(row){
		return onSelectRecord(row) ;
	} ;
	
};






