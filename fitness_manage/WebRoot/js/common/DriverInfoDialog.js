/**
 * 司机信息窗口类
 * @author fhr 
 * @returns {DriverInfoDialog}
 * 2017-4-17
 */
$.parser.auto = false ;
function DriverInfoDialog()
{

	this.preFix = "DRIVER_INFO_" ;
	this.htm = "" ;
	this.triggerEle = null ;
	
	this.DLG_ID = this.preFix + "ID_DIALOG" ;
	this.DATAGRID_ID = this.preFix + "ID_DATAGRID" ;
	this.DLG_BUTTONS_ID =  this.preFix + "ID_DLG_BUTTONS" ;
	this.DLG_SEARCHBOX_ID =  this.preFix + "SEARCH_TXT";
	this.URL = "" ;
	this.TRG_SEARCHBOX = null ;
	this.INNER_SEARCH_TB =  this.preFix + "inner_search_tb" ;
	
	this.title = "" ; 
	this.width = 0 ;
	this.height = 0 ;	
}

DriverInfoDialog.prototype._inner_ok = function(){
	
	var row =  $('#'+this.DATAGRID_ID).datagrid("getSelected"); 
	if(this.onConfirm(row)){
		this.showDialog(false) ;
	}
};

DriverInfoDialog.prototype._inner_dgrowdbclick = function(rowIndex, rowData){
	
	if(DriverInfoDialog.prototype.instance().onConfirm(rowData)){
		DriverInfoDialog.prototype.instance().showDialog(false) ;
	}
};

DriverInfoDialog.prototype.setHtml = function(){
	
	this.htm = "<div id='" + this.DLG_ID+ "' class='easyui-dialog' title='" + this.title + "' style='width:" + this.width + "px;height:" + this.height + "px;padding:5px;'" ;
	this.htm +=" data-options=\" iconCls: 'icon-edit', buttons: '#" + this.DLG_BUTTONS_ID + "',closed: 'true', modal:true\"> " ;	

	this.htm += "<div id='"+ this.INNER_SEARCH_TB +"' style='padding:5px;height:auto'>" ;
	this.htm += "司机姓名：<input class='easyui-searchbox' data-options=\"prompt:'搜索司机信息',searcher:DriverInfoDialog.prototype.instance().innerSearch\" id='"+this.DLG_SEARCHBOX_ID+"' style='width:40%'>" ;
	this.htm += "</div>" ;

	this.htm += "<table id='" + this.DATAGRID_ID + "' class='easyui-datagrid' " ;
	this.htm += " data-options=\"rownumbers:true, singleSelect:true , url:'', fit:true,onDblClickRow:DriverInfoDialog.prototype.instance()._inner_dgrowdbclick,toolbar:'#"+this.INNER_SEARCH_TB+"'\">";
	this.htm +="<thead>" ;
	this.htm +="<tr>" ;
	this.htm +="	<th data-options=\"field:'checkbox',checkbox:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'driver_code',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'driverman',width:80\">司机姓名</th>" ;
	this.htm +="	<th data-options=\"field:'sex',width:80\">性别</th>" ;
	this.htm +="	<th data-options=\"field:'id_card',width:100\">身份证号</th>" ;
	this.htm +="	<th data-options=\"field:'manphone',width:80\">司机电话</th>" ;
	
	this.htm +="</tr>" ;
	this.htm +="</thead>" ;
	this.htm +="</table>" ;
	this.htm +="</div>" ;
	
	this.htm +="<div id='" + this.DLG_BUTTONS_ID + "'>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-save'  onclick='javascript:DriverInfoDialog.prototype.instance()._inner_ok()'>确 定</a>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-cancel' onclick='javascript:DriverInfoDialog.prototype.instance().showDialog(false)'>取 消</a>" ;
    this.htm +="</div>" ;
	
};


DriverInfoDialog.prototype.setURL = function(url){
	this.URL = url ;
};

DriverInfoDialog.prototype.setTrgSearchBox = function(sb_id , onSelectRecord){
	if(sb_id == null || !$("#"+sb_id) ){
		throw new Error("init trg search box fail") ;
		return ;
	}
	
	if(onSelectRecord == null ||(  typeof onSelectRecord != "function")){
		throw new Error("onSelectRecord is not a function");
			return ;
	}
	
	
	var me = this ;
	this.TRG_SEARCHBOX = $("#"+sb_id) ;
	this.TRG_SEARCHBOX.searchbox({
		searcher:function(value){
			   var searchData = {driverman:value} ;
			 	  $.ajax({            
		               type:"POST",   //post提交方式默认是get
		               url: me.URL, 
		               data: searchData ,   //序列化               
		               error:function(request) {      // 设置表单提交出错               
		                 
		               },
		               success:function(data) {

						 	if(data.total == 1 && data.rows[0].driverman == value){
						 		onSelectRecord(data.rows[0]) ;
							}else{
								onSelectRecord(null) ;
								DriverInfoDialog.prototype.instance().showDialog(true,null,searchData);
							} 
		               }            
		         }); 
			   
			
		 }
	}) ;
	
	
	me.onConfirm = function(row){
		return onSelectRecord(row) ;
	} ;
	
};


/**
 * 搜索事件函数
 * @param value 模糊搜索的文本，此处为运单号
 */
DriverInfoDialog.prototype.innerSearch = function(value){
	 var searchData = {driverman:value}  ;
	DriverInfoDialog.prototype.instance().queryData(searchData) ;
};

DriverInfoDialog.prototype.appendHtml = function(){
	this.setHtml() ;
	$('body').append(this.htm) ;
	
};


DriverInfoDialog.prototype.init = function(title,width,height){
	this.title = title ; 
	this.width = width ;
	this.height = height ;
	
	this.appendHtml();
	//$.parser.parse();
};



/**
 * 显示或者隐藏窗口
 * @param flag ：true显示，false ：隐藏
 * @param showData json：{total:0,rows[]}
 * @param searchData {driverman:'',is_use:1}
 */
DriverInfoDialog.prototype.showDialog = function(flag,showData,searchData){
	var obj = $('#'+this.DLG_ID) ;
	if(flag)
	{
		if(showData != null ){
			this.showData(showData);
			if(searchData != null){
				//var searchData = {driverman,waybill_state:1}  ;
				this.setSearchText(searchData.driverman) ;
			}
		}else if(searchData != null){
			this.queryData(searchData) ;
			this.setSearchText(searchData.driverman) ;
		}
		
		obj.dialog('open') ;
	}
	else{
     obj.dialog('close') ;
	}
};

DriverInfoDialog.prototype.instance = function(){
	
	if(DriverInfoDialog.prototype.me == null){
		DriverInfoDialog.prototype.me = new DriverInfoDialog();
	}
	return DriverInfoDialog.prototype.me ;
	
};

/**
 * datagrid 搜索数据
 * @param value
 */
DriverInfoDialog.prototype.queryData = function(value){
	
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
DriverInfoDialog.prototype.showData = function(showData){
	$('#'+ this.DATAGRID_ID).datagrid("loadData",showData); 
};

/**
 * 搜索文本
 * @param sTxt
 */
DriverInfoDialog.prototype.setSearchText = function(sTxt){
	$('#'+ this.DLG_SEARCHBOX_ID).searchbox("setValue",sTxt) ;
};


/**
 * 选择确认后回调函数
 * @param row 选择的行
 * @returns {Boolean}是否要关闭选择窗口，true：关闭窗口，false不关闭
 */
DriverInfoDialog.prototype.onConfirm = function(row){
	alert("请根据业务重新写该事件") ;
	return true ;
};





