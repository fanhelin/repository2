/**
 * 关联公司窗口类
 * url:("/organization/selectRealtionOrgs.do")
 * @author fhr 
 * @returns {RelationStationDialog}
 * 2017-4-17
 */
$.parser.auto = false ;
function RelationStationDialog()
{

	this.preFix = "REL_STAT" ;
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

RelationStationDialog.prototype._inner_ok = function(){
	
	var row =  $('#'+this.DATAGRID_ID).treegrid("getSelected"); 
	if(this.onConfirm(row)){
		this.showDialog(false) ;
	}
};

RelationStationDialog.prototype._inner_dgrowdbclick = function(rowData){
	
	if(RelationStationDialog.prototype.instance().onConfirm(rowData)){
		RelationStationDialog.prototype.instance().showDialog(false) ;
	}
};

RelationStationDialog.prototype.setHtml = function(){
	
	this.htm = "<div id='" + this.DLG_ID+ "' class='easyui-dialog' title='" + this.title + "' style='width:" + this.width + "px;height:" + this.height + "px;padding:5px;'" ;
	this.htm +=" data-options=\" iconCls: 'icon-edit', buttons: '#" + this.DLG_BUTTONS_ID + "',closed: 'true', modal:true\"> " ;	

	//this.htm += "<div id='"+ this.INNER_SEARCH_TB +"' style='padding:5px;height:auto'>" ;
	//this.htm += "公司姓名：<input class='easyui-searchbox' data-options=\"prompt:'搜索公司信息',searcher:RelationStationDialog.prototype.instance().innerSearch\" id='"+this.DLG_SEARCHBOX_ID+"' style='width:40%'>" ;
	//this.htm += "</div>" ;

	this.htm += "<table id='" + this.DATAGRID_ID + "' class='easyui-treegrid' " ;
	this.htm += " data-options=\"rownumbers:true, singleSelect:true ,idField:'id',treeField:'relation_type', url:'', fit:true,onDblClickRow:RelationStationDialog.prototype.instance()._inner_dgrowdbclick,toolbar:'#"+this.INNER_SEARCH_TB+"'\">";
	this.htm +="<thead>" ;
	this.htm +="<tr>" ;
	this.htm +="	<th data-options=\"field:'id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'relation_station_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'staff_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'relation_type',width:150,formatter:function(v){if(v == 1){ return '公司站点';}else if(v == 2){ return '加盟站点';} else if(v ==3){ return '代理站点';} else {return '';} }\">站点类型</th>" ;
	this.htm +="	<th data-options=\"field:'organization_name',width:120\">站点名称</th>" ;
	this.htm +="	<th data-options=\"field:'staff_name',width:80\">联系人名</th>" ;
	this.htm +="	<th data-options=\"field:'phone',width:100\">联系人电话</th>" ;
	this.htm +="	<th data-options=\"field:'address',width:120\">站点地址</th>" ;
	
	this.htm +="</tr>" ;
	this.htm +="</thead>" ;
	this.htm +="</table>" ;
	this.htm +="</div>" ;
	
	this.htm +="<div id='" + this.DLG_BUTTONS_ID + "'>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-save'  onclick='javascript:RelationStationDialog.prototype.instance()._inner_ok()'>确 定</a>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-cancel' onclick='javascript:RelationStationDialog.prototype.instance().showDialog(false)'>取 消</a>" ;
    this.htm +="</div>" ;
	
};


RelationStationDialog.prototype.setURL = function(url){
	this.URL = url ;
};

RelationStationDialog.prototype.setSelectHandle = function(onSelectRecord){
	
	
	if(onSelectRecord == null ||(  typeof onSelectRecord != "function")){
		throw new Error("onSelectRecord is not a function");
			return ;
	}

	var me = this ;
	me.onConfirm = function(row){
		return onSelectRecord(row) ;
	} ;
	
};


/**
 * 搜索事件函数
 * @param value 模糊搜索的文本，此处为运单号
 */
RelationStationDialog.prototype.innerSearch = function(value){
	 var searchData = {company_name:value}  ;
	RelationStationDialog.prototype.instance().queryData(searchData) ;
};

RelationStationDialog.prototype.appendHtml = function(){
	this.setHtml() ;
	$('body').append(this.htm) ;
	
};


RelationStationDialog.prototype.init = function(title,width,height){
	this.title = title ; 
	this.width = width ;
	this.height = height ;
	
	this.appendHtml();
	//$.parser.parse();
};



/**
 * 显示或者隐藏窗口
 * @param flag ：true显示，false ：隐藏
 * @param reload 是否重新加载: true 是 ,false 否

 */
RelationStationDialog.prototype.showDialog = function(flag,reload)
{
	var obj = $('#'+this.DLG_ID) ;
	var me = this ;
	if(flag)
	{
		if(reload)
		{
			me.queryData() ;
		}
		obj.dialog('open') ;
		
	}else{
		obj.dialog('close') ;
	}
};

RelationStationDialog.prototype.instance = function(){
	
	if(RelationStationDialog.prototype.me == null){
		RelationStationDialog.prototype.me = new RelationStationDialog();
	}
	return RelationStationDialog.prototype.me ;
	
};

/**
 * datagrid 搜索数据
 * @param value
 */
RelationStationDialog.prototype.queryData = function(){
	
	 var dataGrid =  $('#'+ this.DATAGRID_ID) ;
     var options =  dataGrid.treegrid('options') ;
     //options.queryParams = value;  
	 options.url = this.URL ; // 
	 dataGrid.treegrid("reload") ;
};

/**
 * 显示传入数据
 * @param showData json{total：0，rows:[]}
 */
RelationStationDialog.prototype.showData = function(showData){
	$('#'+ this.DATAGRID_ID).treegrid("loadData",showData); 
};

/**
 * 搜索文本
 * @param sTxt
 */
RelationStationDialog.prototype.setSearchText = function(sTxt){
//	$('#'+ this.DLG_SEARCHBOX_ID).searchbox("setValue",sTxt) ;
};


/**
 * 选择确认后回调函数
 * @param row 选择的行
 * @returns {Boolean}是否要关闭选择窗口，true：关闭窗口，false不关闭
 */
RelationStationDialog.prototype.onConfirm = function(row){
	alert("请根据业务重新写该事件") ;
	return true ;
};





