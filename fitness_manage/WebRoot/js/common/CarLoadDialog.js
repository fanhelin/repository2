/**
 * 装车清单信息窗口类
 * @author fhr 
 * @returns {CarLoadDialog}
 * url:/carLoadBillH/selectCarLoadDialog.do //装车清单
 * 2018-1-2
 */

/**
 *params: {
	id:'',
	url:'/carLoadBillH/selectCarLoadDialog.do ',
	title:'窗口标题',
	type: 0,1 
	
}
 */
function CarLoadDialog(params)
{

	this.preFix = "CAR_LOAD_" ;
	this.htm = "" ;
	this.triggerEle = null ;
	
	this.DLG_ID = this.preFix + "ID_DIALOG" +params.id;
	this.DATAGRID_ID = this.preFix + "ID_DATAGRID"+params.id;
	this.DLG_BUTTONS_ID =  this.preFix + "ID_DLG_BUTTONS"+params.id; 
	this.DLG_SEARCHBOX_ID =  this.preFix + "SEARCH_TXT"+params.id;
	this.INNER_SEARCH_TB =  this.preFix + "inner_search_tb" +params.id; 
	this.DLG_BUT_ADD = this.preFix +"dlg_but_add" +params.id ;
	this.DLG_BUT_CANCEL = this.preFix +"dlg_but_cancel" +params.id ;
	this.URL = params.url || (function() { 
		var ph = window.document.location.href;   
	    var pn = window.document.location.pathname;
	    var idx = ph.indexOf(pn);
	    var hp = ph.substring(0, idx);
	    var sbn = pn.substring(0, pn.substr(1).indexOf('/') + 1);
	    return hp + sbn;
    })()+'/carLoadBillH/selectCarLoadDialog.do ' ;
	this.TRG_SEARCHBOX = null ;
	
	this.title = params.title || '装车单'; 
	this.width = 850;//params.width || 350;
	this.height = 450 ;//params.height || 300;
	this.type = 0 ;//params.type ;//搜索类型
	
	this.init(params);
}

CarLoadDialog.prototype._inner_ok = function(){
	
	var row =  $('#'+this.DATAGRID_ID).datagrid("getSelected"); 
	if(this.onConfirm(row)){
		this.showDialog(false) ;
	}
};

CarLoadDialog.prototype._inner_dgrowdbclick = function(rowIndex, rowData){
	
	if(this.onConfirm(rowData)){
		this.showDialog(false) ;
	}
};


CarLoadDialog.prototype.setHtml = function(){
	
	this.htm = "<div id='" + this.DLG_ID+ "' class='easyui-dialog' title='" + this.title + "' style='width:" + this.width + "px;height:" + this.height + "px;padding:5px;'" ;
	this.htm +=" data-options=\" iconCls: 'icon-edit', buttons: '#" + this.DLG_BUTTONS_ID + "',closed: 'true', modal:true\"> " ;	

	this.htm += "<div id='"+this.INNER_SEARCH_TB+"' style='padding:5px;height:auto;'>" ;
	if(this.type == 0){
		this.htm += "装车清单号：<input class='easyui-searchbox' data-options=\"prompt:'搜索装车清单'\" id='"+this.DLG_SEARCHBOX_ID+"' style='width:25%'>" ;
	}else if( this.type == 1){
		
	}
	this.htm += "</div>" ;

	this.htm += "<table id='" + this.DATAGRID_ID + "' class='easyui-datagrid' " ;
	this.htm += " data-options=\"rownumbers:true, singleSelect:true , url:'', fit:true,toolbar:'#"+this.INNER_SEARCH_TB+"'\">";
	this.htm +="<thead>" ;
	this.htm +="<tr>" ;
	this.htm +="	<th data-options=\"field:'checkbox',checkbox:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'entity_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'organization_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'task_ent',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'task_org',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'task_staff_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'load_staff_id',hidden:true\"></th>" ;
	
	this.htm +="	<th data-options=\"field:'plate_no'\">车牌号码</th>" ;
	this.htm +="	<th data-options=\"field:'bill_code'\">装车单号</th>" ;
	this.htm +="	<th data-options=\"field:'task_code'\">任务单号</th>" ;
	this.htm +="	<th data-options=\"field:'bill_state',formatter:function(v){v = v == 1?'保存':'审核'; return v ;}\">车辆状态</th>" ;
	this.htm +="	<th data-options=\"field:'task_staff_name'\">任务单人员姓名</th>" ;
	this.htm +="	<th data-options=\"field:'task_staff_mobile'\">任务单人员电话</th>" ;
	this.htm +="	<th data-options=\"field:'load_staff_name'\">装车人员姓名</th>" ;
	this.htm +="	<th data-options=\"field:'load_staff_mobile'\">装车人员电话</th>" ;

	this.htm +="</tr>" ;
	this.htm +="</thead>" ;
	this.htm +="</table>" ;
	this.htm +="</div>" ;
	
	this.htm +="<div id='" + this.DLG_BUTTONS_ID + "'>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-save' id='"+ this.DLG_BUT_ADD +"' >确 定</a>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-cancel' id='"+this.DLG_BUT_CANCEL+"'>取 消</a>" ;
    this.htm +="</div>" ;
	
};



CarLoadDialog.prototype.setURL = function(url){
	this.URL = url ;
};

CarLoadDialog.prototype._setTrgSearchBox = function(sb_id , onSelectCarLoad){
	if(this.type == 0 && (sb_id == null || !$("#"+sb_id)) ){
		throw new Error("init trg searchbox fail") ;
		return ;
	}
	
	if(onSelectCarLoad == null ||(  typeof onSelectCarLoad != "function")){
		throw new Error("onSelectCarLoad is not a function");
			return ;
	}
	
	
	var me = this ;
	this.TRG_SEARCHBOX = $("#"+sb_id) ;
	this.TRG_SEARCHBOX && this.TRG_SEARCHBOX.searchbox({
		searcher:function(value){
			   var searchData ={} ;
			   if(me.type == 0)
			   {
				   searchData.bill_code = value ;
			   } else if(me.type == 1){
				  
			   }
			 	  $.ajax({            
		               type:"POST",   //post提交方式默认是get
		               url: me.URL, 
		               data: searchData ,   //序列化               
		               error:function(request) {      // 设置表单提交出错               
		                 
		               },
		               success:function(data) {
                            if(me.type == 0){
							 	if(data.total == 1 && data.rows[0].bill_code == value){
							 		onSelectCarLoad(data.rows[0]) ;
								}else{
									onSelectCarLoad(null) ;
									me.showDialog(true,null,searchData);
								} 
                            }else if(me.type == 1){
                            	
                            }
		               }            
		         }); 
			   
			
		 }
	}) ;
	
	
	me.onConfirm = function(row){
		return onSelectCarLoad(row) ;
	} ;
	
};


/**
 * 搜索事件函数
 * @param value 模糊搜索的文本，此处为运单号
 */
CarLoadDialog.prototype.innerSearch = function(value){
	 var searchData = {}  ;
	 if(this.type == 0){
		 searchData.bill_code = value ;
	 }else if(this.type == 1){
		 searchData.plate_no = value ;
	 }
	 this.queryData(searchData) ;
};


CarLoadDialog.prototype.appendHtml = function(){
	this.setHtml() ;
	$('body').append(this.htm) ;
	
};


CarLoadDialog.prototype.init = function(params){

	this.appendHtml();
	
	var me = this ;
	$("#"+this.DLG_SEARCHBOX_ID).searchbox({
	    searcher:function(value,name){
	         me.innerSearch(value) ;
	    }
	});
	
	
	$("#"+this.DATAGRID_ID).datagrid({
		onDblClickRow : function(rowIndex, rowData){ me._inner_dgrowdbclick(rowIndex, rowData)}
	});



	$("#"+me.DLG_BUT_ADD).bind('click', function(){ me._inner_ok();});
	$("#"+me.DLG_BUT_CANCEL).bind('click',function(){me.showDialog(false) ;});
	if(this.type == 0 ){
	   me._setTrgSearchBox(params.bindId,params.onSelectCarLoad) ;
	}
	setTimeout(function(){
		//$.parser.parse();
	} ,1) ;
	
};



/**
 * 显示或者隐藏窗口
 * @param flag ：true显示，false ：隐藏
 * @param showData json：{total:0,rows[]}
 * @param searchData {plate_no:'',is_use:1}
 */
CarLoadDialog.prototype.showDialog = function(flag,showData,searchData){
	var obj = $('#'+this.DLG_ID) ;
	if(flag)
	{
		if(showData != null ){
			this.showData(showData);
			if(searchData != null){
				//var searchData = {bill_code}  ;
				if(this.type == 0)
				 this.setSearchText(searchData.bill_code) ;
				else if(this.type == 1){
				
				}
			}
		}else if(searchData != null){
			this.queryData(searchData) ;
			if(this.type==0){
				this.setSearchText(searchData.bill_code) ;
			}else if(this.type == 1){
				
			}
		}
		
		obj.dialog('open') ;
	}
	else{
     obj.dialog('close') ;
	}
};


/**
 * datagrid 搜索数据
 * @param value
 */
CarLoadDialog.prototype.queryData = function(value){
	
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
CarLoadDialog.prototype.showData = function(showData){
	$('#'+ this.DATAGRID_ID).datagrid("loadData",showData); 
};

/**
 * 搜索文本
 * @param sTxt
 */
CarLoadDialog.prototype.setSearchText = function(sTxt){
	$('#'+ this.DLG_SEARCHBOX_ID).searchbox("setValue",sTxt) ;
};


/**
 * 选择确认后回调函数
 * @param row 选择的行
 * @returns {Boolean}是否要关闭选择窗口，true：关闭窗口，false不关闭
 */
CarLoadDialog.prototype.onConfirm = function(row){
	throw new Error("请根据业务重新写该事件") ;
	return true ;
};

