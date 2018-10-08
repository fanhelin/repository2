/**
 * 公司-人员信息窗口类
 * /queryCompanyManFWB.do
 * @author fhr 
 * @returns {CompanyUserInfoDialog}
 * 2017-4-17
 */
$.parser.auto = false ;
function CompanyUserInfoDialog()
{

	this.preFix = "COMP_USER_" ;
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

CompanyUserInfoDialog.prototype._inner_ok = function(){
	
	var row =  $('#'+this.DATAGRID_ID).treegrid("getSelected"); 
	if(this.onConfirm(row)){
		this.showDialog(false) ;
	}
};

CompanyUserInfoDialog.prototype._inner_dgrowdbclick = function(rowData){
	
	if(CompanyUserInfoDialog.prototype.instance().onConfirm(rowData)){
		CompanyUserInfoDialog.prototype.instance().showDialog(false) ;
	}
};

CompanyUserInfoDialog.prototype.setHtml = function(){
	
	this.htm = "<div id='" + this.DLG_ID+ "' class='easyui-dialog' title='" + this.title + "' style='width:" + this.width + "px;height:" + this.height + "px;padding:5px;'" ;
	this.htm +=" data-options=\" iconCls: 'icon-edit', buttons: '#" + this.DLG_BUTTONS_ID + "',closed: 'true', modal:true\"> " ;	

	this.htm += "<div id='"+ this.INNER_SEARCH_TB +"' style='padding:5px;height:auto'>" ;
	this.htm += "公司姓名：<input class='easyui-searchbox' data-options=\"prompt:'搜索公司信息',searcher:CompanyUserInfoDialog.prototype.instance().innerSearch\" id='"+this.DLG_SEARCHBOX_ID+"' style='width:40%'>" ;
	this.htm += "</div>" ;

	this.htm += "<table id='" + this.DATAGRID_ID + "' class='easyui-treegrid' " ;
	this.htm += " data-options=\"rownumbers:true, singleSelect:true ,idField:'id',treeField:'company_name', url:'', fit:true,onDblClickRow:CompanyUserInfoDialog.prototype.instance()._inner_dgrowdbclick,toolbar:'#"+this.INNER_SEARCH_TB+"'\">";
	this.htm +="<thead>" ;
	this.htm +="<tr>" ;
	this.htm +="	<th data-options=\"field:'checkbox',checkbox:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'company_code',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'company_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'identity_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'client_man_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'man_code',hidden:true\"></th>" ;

	this.htm +="	<th data-options=\"field:'company_name',width:180\">公司名</th>" ;
	this.htm +="	<th data-options=\"field:'man_name',width:80\">联系人名</th>" ;
	
	this.htm +="	<th data-options=\"field:'man_sex',width:80,formatter:function(value){if(value==1)return '男'; if(value==2)return '女';}\">性别</th>" ;
	this.htm +="	<th data-options=\"field:'man_phone',width:100\">联系人座机</th>" ;
	this.htm +="	<th data-options=\"field:'man_mobile',width:80\">联系人手机</th>" ;

	
	this.htm +="</tr>" ;
	this.htm +="</thead>" ;
	this.htm +="</table>" ;
	this.htm +="</div>" ;
	
	this.htm +="<div id='" + this.DLG_BUTTONS_ID + "'>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-save'  onclick='javascript:CompanyUserInfoDialog.prototype.instance()._inner_ok()'>确 定</a>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-cancel' onclick='javascript:CompanyUserInfoDialog.prototype.instance().showDialog(false)'>取 消</a>" ;
    this.htm +="</div>" ;
	
};


CompanyUserInfoDialog.prototype.setURL = function(url){
	this.URL = url ;
};

CompanyUserInfoDialog.prototype.setTrgSearchBox = function(sb_id , onSelectRecord){
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
			   var searchData = {company_name:value} ;
			 	  $.ajax({            
		               type:"POST",   //post提交方式默认是get
		               url: me.URL, 
		               data: searchData ,   //序列化               
		               error:function(request) {      // 设置表单提交出错               
		                 
		               },
		               success:function(data) {

						 	if(data.total == 1 && data.rows[0].company_name == value){
						 		onSelectRecord(data.rows[0]) ;
							}else{
								onSelectRecord(null) ;
								CompanyUserInfoDialog.prototype.instance().showDialog(true,null,searchData);
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
CompanyUserInfoDialog.prototype.innerSearch = function(value){
	 var searchData = {company_name:value}  ;
	CompanyUserInfoDialog.prototype.instance().queryData(searchData) ;
};

CompanyUserInfoDialog.prototype.appendHtml = function(){
	this.setHtml() ;
	$('body').append(this.htm) ;
	
};


CompanyUserInfoDialog.prototype.init = function(title,width,height){
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
 * @param searchData {company_name:'',is_use:1}
 */
CompanyUserInfoDialog.prototype.showDialog = function(flag,showData,searchData){
	var obj = $('#'+this.DLG_ID) ;
	if(flag)
	{
		if(showData != null ){
			this.showData(showData);
			if(searchData != null){
				//var searchData = {company_name,waybill_state:1}  ;
				this.setSearchText(searchData.company_name) ;
			}
		}else if(searchData != null){
			this.queryData(searchData) ;
			this.setSearchText(searchData.company_name) ;
		}
		
		obj.dialog('open') ;
	}
	else{
     obj.dialog('close') ;
	}
};

CompanyUserInfoDialog.prototype.instance = function(){
	
	if(CompanyUserInfoDialog.prototype.me == null){
		CompanyUserInfoDialog.prototype.me = new CompanyUserInfoDialog();
	}
	return CompanyUserInfoDialog.prototype.me ;
	
};

/**
 * datagrid 搜索数据
 * @param value
 */
CompanyUserInfoDialog.prototype.queryData = function(value){
	
	 var dataGrid =  $('#'+ this.DATAGRID_ID) ;
     var options =  dataGrid.treegrid('options') ;
     options.queryParams = value;  
	 options.url = this.URL ; // 
	 dataGrid.treegrid("reload") ;
};

/**
 * 显示传入数据
 * @param showData json{total：0，rows:[]}
 */
CompanyUserInfoDialog.prototype.showData = function(showData){
	$('#'+ this.DATAGRID_ID).treegrid("loadData",showData); 
};

/**
 * 搜索文本
 * @param sTxt
 */
CompanyUserInfoDialog.prototype.setSearchText = function(sTxt){
	$('#'+ this.DLG_SEARCHBOX_ID).searchbox("setValue",sTxt) ;
};


/**
 * 选择确认后回调函数
 * @param row 选择的行
 * @returns {Boolean}是否要关闭选择窗口，true：关闭窗口，false不关闭
 */
CompanyUserInfoDialog.prototype.onConfirm = function(row){
	alert("请根据业务重新写该事件") ;
	return true ;
};





