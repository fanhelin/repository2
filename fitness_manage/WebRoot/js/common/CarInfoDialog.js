/**
 * 车辆信息窗口类
 * @author fhr 
 * @returns {CarInfoDialog}
 *:/base/bus/selectCarInfoForDialog.do
 * 2017-4-17
 */
$.parser.auto = false ;
function CarInfoDialog()
{

	this.preFix = "CAR_INFO_" ;
	this.htm = "" ;
	this.triggerEle = null ;
	
	this.DLG_ID = this.preFix + "ID_DIALOG" ;
	this.DATAGRID_ID = this.preFix + "ID_DATAGRID" ;
	this.DLG_BUTTONS_ID =  this.preFix + "ID_DLG_BUTTONS" ;
	this.DLG_SEARCHBOX_ID =  this.preFix + "SEARCH_TXT";
	this.INNER_SEARCH_TB =  this.preFix + "inner_search_tb" ;
	this.URL = "" ;
	this.TRG_SEARCHBOX = null ;
	
	this.title = "" ; 
	this.width = 0 ;
	this.height = 0 ;	
}

CarInfoDialog.prototype._inner_ok = function(){
	
	var row =  $('#'+this.DATAGRID_ID).datagrid("getSelected"); 
	if(this.onConfirm(row)){
		this.showDialog(false) ;
	}
};

CarInfoDialog.prototype._inner_dgrowdbclick = function(rowIndex, rowData){
	
	if(CarInfoDialog.prototype.instance().onConfirm(rowData)){
		CarInfoDialog.prototype.instance().showDialog(false) ;
	}
};


CarInfoDialog.prototype.setHtml = function(){
	
	this.htm = "<div id='" + this.DLG_ID+ "' class='easyui-dialog' title='" + this.title + "' style='width:" + this.width + "px;height:" + this.height + "px;padding:5px;'" ;
	this.htm +=" data-options=\" iconCls: 'icon-edit', buttons: '#" + this.DLG_BUTTONS_ID + "',closed: 'true', modal:true\"> " ;	

	this.htm += "<div id='"+this.INNER_SEARCH_TB+"' style='padding:5px;height:auto'>" ;
	this.htm += "车牌号：<input class='easyui-searchbox' data-options=\"prompt:'搜索车辆信息',searcher:CarInfoDialog.prototype.instance().innerSearch\" id='"+this.DLG_SEARCHBOX_ID+"' style='width:30%'>" ;
	this.htm += "</div>" ;

	this.htm += "<table id='" + this.DATAGRID_ID + "' class='easyui-datagrid' " ;
	this.htm += " data-options=\"rownumbers:true, singleSelect:true , url:'' , fit:true,onDblClickRow:CarInfoDialog.prototype.instance()._inner_dgrowdbclick,toolbar:'#"+this.INNER_SEARCH_TB+"'\">";
	this.htm +="<thead>" ;
	this.htm +="<tr>" ;
	this.htm +="	<th data-options=\"field:'checkbox',checkbox:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'car_code',hidden:true\"></th>" ;

	this.htm +="	<th data-options=\"field:'plate_no'\">车牌号</th>" ;
	this.htm +="	<th data-options=\"field:'vin'\">车辆识别号码</th>" ;
	this.htm +="	<th data-options=\"field:'engine_no'\">发动机号</th>" ;
	this.htm +="	<th data-options=\"field:'archive_num'\">车辆档案号</th>" ;
	this.htm +="	<th data-options=\"field:'total_mass'\">车辆吨位</th>" ;
	this.htm +="	<th data-options=\"field:'load_weight'\">牵引吨位</th>" ;
	this.htm +="</tr>" ;
	this.htm +="</thead>" ;
	this.htm +="</table>" ;
	this.htm +="</div>" ;
	
	this.htm +="<div id='" + this.DLG_BUTTONS_ID + "'>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-save'  onclick='javascript:CarInfoDialog.prototype.instance()._inner_ok()'>确 定</a>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-cancel' onclick='javascript:CarInfoDialog.prototype.instance().showDialog(false)'>取 消</a>" ;
    this.htm +="</div>" ;
	
};


CarInfoDialog.prototype.setURL = function(url){
	this.URL = url ;
};

CarInfoDialog.prototype.setTrgSearchBox = function(sb_id , onSelectCarInfo){
	if(sb_id == null || !$("#"+sb_id) ){
		throw new Error("init trg search box fail") ;
		return ;
	}
	
	if(onSelectCarInfo == null ||(  typeof onSelectCarInfo != "function")){
		throw new Error("onSelectCarInfo is not a function");
			return ;
	}
	
	
	var me = this ;
	this.TRG_SEARCHBOX = $("#"+sb_id) ;
	this.TRG_SEARCHBOX.searchbox({
		searcher:function(value){
			   var searchData = {plate_no:value} ;
			 	  $.ajax({            
		               type:"POST",   //post提交方式默认是get
		               url: me.URL, 
		               data: searchData ,   //序列化               
		               error:function(request) {      // 设置表单提交出错               
		                 
		               },
		               success:function(data) {

						 	if(data.total == 1 && data.rows[0].plate_no == value){
								onSelectCarInfo(data.rows[0]) ;
							}else{
								onSelectCarInfo(null) ;
								CarInfoDialog.prototype.instance().showDialog(true,null,searchData);
							} 
		               }            
		         }); 
			   
			
		 }
	}) ;
	
	
	me.onConfirm = function(row){
		return onSelectCarInfo(row) ;
	} ;
	
};


/**
 * 搜索事件函数
 * @param value 模糊搜索的文本，此处为运单号
 */
CarInfoDialog.prototype.innerSearch = function(value){
	 var searchData = {plate_no:value}  ;
	CarInfoDialog.prototype.instance().queryData(searchData) ;
};

CarInfoDialog.prototype.appendHtml = function(){
	this.setHtml() ;
	$('body').append(this.htm) ;
	
};


CarInfoDialog.prototype.init = function(title,width,height){
	this.title = title ; 
	this.width = 650 ;//width ;
	this.height = 400 ;//height  ;
	
	this.appendHtml();
	//$.parser.parse();
};



/**
 * 显示或者隐藏窗口
 * @param flag ：true显示，false ：隐藏
 * @param showData json：{total:0,rows[]}
 * @param searchData {plate_no:'',is_use:1}
 */
CarInfoDialog.prototype.showDialog = function(flag,showData,searchData){
	var obj = $('#'+this.DLG_ID) ;
	if(flag)
	{
		if(showData != null ){
			this.showData(showData);
			if(searchData != null){
				//var searchData = {plate_no,waybill_state:1}  ;
				this.setSearchText(searchData.plate_no) ;
			}
		}else if(searchData != null){
			this.queryData(searchData) ;
			this.setSearchText(searchData.plate_no) ;
		}
		
		obj.dialog('open') ;
	}
	else{
     obj.dialog('close') ;
	}
};

CarInfoDialog.prototype.instance = function(){
	
	if(CarInfoDialog.prototype.me == null){
		CarInfoDialog.prototype.me = new CarInfoDialog();
	}
	return CarInfoDialog.prototype.me ;
	
};

/**
 * datagrid 搜索数据
 * @param value
 */
CarInfoDialog.prototype.queryData = function(value){
	
	 var dataGrid =  $('#'+ this.DATAGRID_ID) ;
     var options =  dataGrid.datagrid('options') ;
     options.queryParams = value;  
	 options.url = this.URL ; // 
	 dataGrid.datagrid("reload") ;
};

/**
 * 显示传入数据
 * @param showData json{total：0，rows:[]}
 */
CarInfoDialog.prototype.showData = function(showData){
	$('#'+ this.DATAGRID_ID).datagrid("loadData",showData); 
};

/**
 * 搜索文本
 * @param sTxt
 */
CarInfoDialog.prototype.setSearchText = function(sTxt){
	$('#'+ this.DLG_SEARCHBOX_ID).searchbox("setValue",sTxt) ;
};


/**
 * 选择确认后回调函数
 * @param row 选择的行
 * @returns {Boolean}是否要关闭选择窗口，true：关闭窗口，false不关闭
 */
CarInfoDialog.prototype.onConfirm = function(row){
	throw new Error("请根据业务重新写该事件") ;
	return true ;
};





