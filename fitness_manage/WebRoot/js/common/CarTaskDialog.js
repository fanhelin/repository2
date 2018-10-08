/**
 * 车辆信息窗口类
 * @author fhr 
 * @returns {CarTaskDialog}
 *url1:/carTask/selectCarTaskDialog.do
 *url2:/carUnload/selectUnloadCarTask.do 卸车单选择任务单
 * 2017-5-28
 */
$.parser.auto = false ;
function CarTaskDialog()
{

	this.preFix = "CAR_TASK_" ;
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
	this.type =0 ;//搜索类型 默认0 为任务单号，1为车牌
}

CarTaskDialog.prototype._inner_ok = function(){
	
	var row =  $('#'+this.DATAGRID_ID).datagrid("getSelected"); 
	if(this.onConfirm(row)){
		this.showDialog(false) ;
	}
};

CarTaskDialog.prototype._inner_dgrowdbclick = function(rowIndex, rowData){
	
	if(CarTaskDialog.prototype.instance().onConfirm(rowData)){
		CarTaskDialog.prototype.instance().showDialog(false) ;
	}
};


CarTaskDialog.prototype.setHtml = function(){
	
	this.htm = "<div id='" + this.DLG_ID+ "' class='easyui-dialog' title='" + this.title + "' style='width:" + this.width + "px;height:" + this.height + "px;padding:5px;'" ;
	this.htm +=" data-options=\" iconCls: 'icon-edit', buttons: '#" + this.DLG_BUTTONS_ID + "',closed: 'true', modal:true\"> " ;	

	this.htm += "<div id='"+this.INNER_SEARCH_TB+"' style='padding:5px;height:auto'>" ;
	if(this.type == 0){
		this.htm += "任务单号：<input class='easyui-searchbox' data-options=\"prompt:'搜索车辆任务单',searcher:CarTaskDialog.prototype.instance().innerSearch\" id='"+this.DLG_SEARCHBOX_ID+"' style='width:25%'>" ;
	}else if( this.type == 1){
		this.htm += "车牌号：<input class='easyui-searchbox' data-options=\"prompt:'搜索车辆任务单',searcher:CarTaskDialog.prototype.instance().innerSearch\" id='"+this.DLG_SEARCHBOX_ID+"' style='width:25%'>" ;
	}
	this.htm += "</div>" ;

	this.htm += "<table id='" + this.DATAGRID_ID + "' class='easyui-datagrid' " ;
	this.htm += " data-options=\"rownumbers:true, singleSelect:true , url:'' , fit:true,onDblClickRow:CarTaskDialog.prototype.instance()._inner_dgrowdbclick,toolbar:'#"+this.INNER_SEARCH_TB+"'\">";
	this.htm +="<thead>" ;
	this.htm +="<tr>" ;
	this.htm +="	<th data-options=\"field:'checkbox',checkbox:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'entity_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'organization_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'parent_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'staff_id',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'car_state',hidden:true\"></th>" ;
	this.htm +="	<th data-options=\"field:'plate_no'\">车牌号码</th>" ;
	this.htm +="	<th data-options=\"field:'task_code'\">任务单号</th>" ;
	this.htm +="	<th data-options=\"field:'ent_org'\">任务单所属单位</th>" ;
	this.htm +="	<th data-options=\"field:'car_state_name'\">车辆状态</th>" ;
	
	this.htm +="	<th data-options=\"field:'max_weight'\">车辆高度</th>" ;
	this.htm +="	<th data-options=\"field:'first_station_name'\">始发站点</th>" ;
	this.htm +="	<th data-options=\"field:'final_station_name'\">终点站</th>" ;
	
	this.htm +="	<th data-options=\"field:'staff_name'\">任务负责人</th>" ;
	this.htm +="	<th data-options=\"field:'staff_mobile'\">负责人电话</th>" ;

	this.htm +="</tr>" ;
	this.htm +="</thead>" ;
	this.htm +="</table>" ;
	this.htm +="</div>" ;
	
	this.htm +="<div id='" + this.DLG_BUTTONS_ID + "'>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-save'  onclick='javascript:CarTaskDialog.prototype.instance()._inner_ok()'>确 定</a>" ;
	this.htm +="<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-cancel' onclick='javascript:CarTaskDialog.prototype.instance().showDialog(false)'>取 消</a>" ;
    this.htm +="</div>" ;
	
};


CarTaskDialog.prototype.setURL = function(url){
	this.URL = url ;
};

CarTaskDialog.prototype.setTrgSearchBox = function(sb_id , onSelectCarTask){
	if(sb_id == null || !$("#"+sb_id) ){
		throw new Error("init trg searchbox fail") ;
		return ;
	}
	
	if(onSelectCarTask == null ||(  typeof onSelectCarTask != "function")){
		throw new Error("onSelectCarTask is not a function");
			return ;
	}
	
	
	var me = this ;
	this.TRG_SEARCHBOX = $("#"+sb_id) ;
	this.TRG_SEARCHBOX.searchbox({
		searcher:function(value){
			   var searchData ={} ;
			   if(me.type == 0)
			   {
				   searchData.task_code = value ;
			   } else if(me.type == 1){
				   searchData.plate_no = value ;
			   }
			 	  $.ajax({            
		               type:"POST",   //post提交方式默认是get
		               url: me.URL, 
		               data: searchData ,   //序列化               
		               error:function(request) {      // 设置表单提交出错               
		                 
		               },
		               success:function(data) {
                            if(me.type == 0){
							 	if(data.total == 1 && data.rows[0].task_code == value){
							 		onSelectCarTask(data.rows[0]) ;
								}else{
									onSelectCarTask(null) ;
									CarTaskDialog.prototype.instance().showDialog(true,null,searchData);
								} 
                            }else if(me.type == 1){
                            	if(data.total == 1 && data.rows[0].plate_no == value){
							 		onSelectCarTask(data.rows[0]) ;
								}else{
									onSelectCarTask(null) ;
									CarTaskDialog.prototype.instance().showDialog(true,null,searchData);
								}
                            }
		               }            
		         }); 
			   
			
		 }
	}) ;
	
	
	me.onConfirm = function(row){
		return onSelectCarTask(row) ;
	} ;
	
};


/**
 * 搜索事件函数
 * @param value 模糊搜索的文本，此处为运单号
 */
CarTaskDialog.prototype.innerSearch = function(value){
	 var searchData = {}  ;
	 if(CarTaskDialog.prototype.instance().type == 0){
		 searchData.task_code = value ;
	 }else if(CarTaskDialog.prototype.instance().type == 1){
		 searchData.plate_no = value ;
	 }
	CarTaskDialog.prototype.instance().queryData(searchData) ;
};


CarTaskDialog.prototype.appendHtml = function(){
	this.setHtml() ;
	$('body').append(this.htm) ;
	
};


CarTaskDialog.prototype.init = function(title,width,height,type){
	this.title = title ; 
	this.width = 850;//width ;
	this.height = 450 ;//height ;
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
 * @param searchData {plate_no:'',is_use:1}
 */
CarTaskDialog.prototype.showDialog = function(flag,showData,searchData){
	var obj = $('#'+this.DLG_ID) ;
	if(flag)
	{
		if(showData != null ){
			this.showData(showData);
			if(searchData != null){
				//var searchData = {task_code}  ;
				if(this.type == 0)
				 this.setSearchText(searchData.task_code) ;
				else if(this.type == 1){
				 this.setSearchText(searchData.plate_no) ;
				}
			}
		}else if(searchData != null){
			this.queryData(searchData) ;
			if(this.type==0){
				this.setSearchText(searchData.task_code) ;
			}else if(this.type == 1){
				this.setSearchText(searchData.plate_no) ;
			}
		}
		
		obj.dialog('open') ;
	}
	else{
     obj.dialog('close') ;
	}
};

CarTaskDialog.prototype.instance = function(){
	
	if(CarTaskDialog.prototype.me == null){
		CarTaskDialog.prototype.me = new CarTaskDialog();
	}
	return CarTaskDialog.prototype.me ;
	
};

/**
 * datagrid 搜索数据
 * @param value
 */
CarTaskDialog.prototype.queryData = function(value){
	
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
CarTaskDialog.prototype.showData = function(showData){
	$('#'+ this.DATAGRID_ID).datagrid("loadData",showData); 
};

/**
 * 搜索文本
 * @param sTxt
 */
CarTaskDialog.prototype.setSearchText = function(sTxt){
	$('#'+ this.DLG_SEARCHBOX_ID).searchbox("setValue",sTxt) ;
};


/**
 * 选择确认后回调函数
 * @param row 选择的行
 * @returns {Boolean}是否要关闭选择窗口，true：关闭窗口，false不关闭
 */
CarTaskDialog.prototype.onConfirm = function(row){
	throw new Error("请根据业务重新写该事件") ;
	return true ;
};

