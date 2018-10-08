/**
 * 工具方法js类
 *  @author fhr
 *  @since 2016-9-28
 */
if(window.top==window.self){
	if(window.self.location.href.indexOf("index.jsp")==-1&&window.self.location.href.indexOf("doLogin.do")==-1)
		window.top.location="/fitness_manage/index.jsp";
};
$.ajaxSetup({
    complete:function(XMLHttpRequest,status){
      var res = XMLHttpRequest.responseText;
      if(res.indexOf("<html>")>0){
    	  if(res.indexOf("<title>欢迎登录</title>")>0){
    		  if(window.top!=window.self){
    				window.top.location="/fitness_manage/index.jsp"; 
    			}
    	  }
      }
    }
});

function ToolKits(){
	
}

ToolKits.getMainFrame = function(){
	return window.parent.MainFrame.prototype.instance() ;
};

ToolKits.getUserInfo = function(){
	return ToolKits.getMainFrame().getUserInfo();
};

ToolKits.centerShow = function(options , pop){
	if(options == null || pop == null){
		//return  ;
	}
	var jqo =  $("#datagrid_roles");
	alert(JSON.stringify(jqo) );
	var easyo =  $("#datagrid_roles");
	console.log(easyo.datagrid("options"));
	
} ;

ToolKits.getTreeNodeParents = function(treeId,node,parentArr){
	
	if(node == null || node.target == null){
		return ;
	}
	
	var parentNode = $(treeId).tree('getParent',node.target);
	if(parentNode != null){
		parentArr.push(parentNode) ;
		ToolKits.getTreeNodeParents(treeId,parentNode,parentArr) ;
	}else{
		return ;
	}
	
};


ToolKits.setTreeCheckState = function(tree,node,isCheck)
{
	if(tree == null || node == null){
		return ;
	}
	
	var state = isCheck ? "check" :"uncheck" ;
	tree.tree(state, node.target);  
	if(tree.tree("isLeaf",node.target)){
		return ;
	}else{
		var arr = tree.tree("getChildren",node.target) ;
		for(var i=0;i< arr.length ;i++){
			ToolKits.setTreeCheckState(tree,arr[i],isCheck) ;
		}
	}

} ;

ToolKits.setTreeCheck = function(treeId ,isCheck){
	  var tree = $(treeId);
	  var rootArr = tree.tree("getRoots") ;
	  
	  if(tree == null ||  rootArr == null)
		  return ;

	   if(tree.tree("options").cascadeCheck){
		   
		   for ( var i = 0; i < rootArr.length; i++) {  
	            var node = tree.tree('find', rootArr[i].id);//查找节点  
	            var state = isCheck ?"check":"uncheck" ;
	            tree.tree(state, node.target);//将得到的节点选中  
	        }  
		   
		}else{
			for(var i =0 ; i<rootArr.length ;i++){
				ToolKits.setTreeCheckState(tree,rootArr[i],isCheck) ;
			}
		}
	  
};

ToolKits.getNowFormatDate = function(seperator1) {
    var date = new Date();
    // var seperator1 = "-";
    //var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
          //  + " " + date.getHours() + seperator2 + date.getMinutes()
         //   + seperator2 + date.getSeconds();
    return currentdate;
};

ToolKits.getNowFormatDayTime = function() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var seperator3 = " ";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate =  month + seperator1 + strDate
            + seperator3 + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
};


ToolKits.shortDayTime = function() {
    var date = new Date();
    var seperator1 = "/";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
   
    var currentdate =  month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes();
            
    return currentdate;
};

ToolKits.popTip = function(title ,msg ,timeout ,showType){
	title = title ?  title :"提示" ;
	msg = msg ? msg : "" ;
	timeout = timeout ? timeout :3000;
	showType = showType ? showType:'slide' ;
	
	$.messager.show({
		width: 200,
		height:110, 
		title:title,
		msg: msg,
		timeout: timeout ,
		showType: showType
	});
} ;

ToolKits.writeLog = function(log){
	ToolKits.getMainFrame().writeLog(log) ;
} ;

ToolKits.writeMsg = function(msg){
	ToolKits.getMainFrame().writeMsg(msg) ;
} ;




ToolKits.addTab = function(tabInfo){
	ToolKits.getMainFrame().addTab(tabInfo) ;
} ;

ToolKits.closeTab = function(tabName){
	ToolKits.getMainFrame().closeTab(tabName) ;  
};

/**
 * 搜索城市
 */
ToolKits.searchCitys = function(type,text){
	return ToolKits.getMainFrame().areaManage.searchCitys(type,text) ;
} ;


ToolKits.searchDistricts = function(type,pId ,text){
	return ToolKits.getMainFrame().areaManage.searchDistricts(type,pId ,text) ;
} ;



ToolKits.getRequestParams = function () { 
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
	} 
	return theRequest; 
}  ; 


ToolKits.trim=function(str){
	if(typeof str == "string")
		str = str.replace(/(^\s*)|(\s*$)/g, "");
	
    return str;
 };

ToolKits.ltrim=function(str){
	if(typeof str == "string")
		str = str.replace(/(^\s*)/g,"");
    return str;
 };

ToolKits.rtrim=function(str){
	if(typeof str == "string")
		str = str.replace(/(\s*$)/g,"");
	
    return str;
 };
 
 ToolKits.isPhone=function(str ,len){
	 str = ToolKits.trim(str);
	 if(str.length >=len){
		 return true ;
	 }else{
		 return false ;
	 }
	 
	};
	
 ToolKits.isEmpty=function(str){
	  str = ToolKits.trim(str);
	  return str.length == 0 ;
 };
 
 /**
  * @todo 合并datagrid单元格
  * @param datagrid 
  * @param data 
  * @param fieldName
  */
 ToolKits.MarginDataGrid = function(datagrid ,data,fieldName){
	 
	 if(!datagrid ){
			throw new Error("datagrid is null") ;	
	 }
	 if(data == null){
		data = datagrid.datagrid("getData") ; 
	 }

	   var marginArr = [] ;
	   var currentV = null ;
	   var beginIndex = -1 ;
	   var offSet = 0 ;
	   data = data.rows ;
	   for(var i=0 ;i< data.length ;i++)
	   {
		   var row = data[i] ;
		   if(currentV == row[fieldName])
		   {
			   offSet ++ ; 
		   } else {
			   if(beginIndex != -1)
			   {
				 var margin = {index:beginIndex ,rowspan:offSet} ;  
				 marginArr.push(margin) ;
				 
				 beginIndex = -1 ;
				 offSet = 0 ;  
				 currentV = null ;
			   }
			   
			   currentV = row[fieldName] ;
			   beginIndex = i ;
			   offSet =1 ;
		  }
		   
		   if(i >= data.length -1){ //最后一条记录
			   var margin = {index:beginIndex ,rowspan:offSet} ;  
				marginArr.push(margin) ;
				 
				beginIndex = -1 ;
				offSet = 0 ;  
				currentV = null ;
		   }
		   
	   }
	   
	   for(var i=0; i<marginArr.length; i++)
		   datagrid.datagrid('mergeCells',{
				index:marginArr[i].index,
				field:fieldName,
				rowspan:marginArr[i].rowspan
			});
	   
 };
 
 /**
  * 从 datagrid 的数据 data：{total:0,rows:[]} 中删除数据，
  *  @param datagrid 删除的目标datagrid
  *  @param index ,删除行索引
  */
 ToolKits.delDGRowFromData = function(datagrid ,index){
	 var data = datagrid.datagrid("getData") ;
	 if(data.total ==0 )
		 return ;
	 
	 //JSON.stringify(data) ;
	 data.rows.splice(index,1);
	 data.total = data.total -1 ;
	 datagrid.datagrid("loadData",data) ;
 };
 
 
 
 /**
  * 
  * 客户端清空datagrid
  */
 ToolKits.clearDG = function(datagrid){
	if(!datagrid ){
		throw new Error("datagrid is null") ;	
	}
		datagrid.datagrid("loadData",{total:0,rows:[]}) ;
	
 };
 
 
 /**
  * @todo  检测指定条件记录是否在 datagrid中
  * @param datagrid 被检测列表
  * @param fields {array} :[{field:'',value:''}]
  * @returns boolean 在存在记录TRUE，否则false
  */
 ToolKits.isInDGFD = function(datagrid, fields){
	 if(!datagrid ){
		 throw new Error("datagrid is null") ;	
		}
	 
	 if(!fields){
		throw new Error("fields is null") ;
	 }
	 
	 var data = datagrid.datagrid("getData") ;
	 if(data.total<=0){
		 return false ;
	 }
	 data = data.rows ;
	 
	 function isInFun(row,fields){
		for(var i =0 ;i<fields.length; i++){
			if(row[fields[i].field] != fields[i].value){
				return false ;
			}
		} 
		return true ;
	 }
	 
	 for(var i=0 ;i<data.length ;i++){
		if(isInFun(data[i],fields) ){
			return true ;
		}
	 }
	 
	 return false ;
 };
 

 /**
  * @todo  检测指定条件记录是否在 datagrid中
  * @param datagrid 被检测列表
  * @param fields {array} :[{field:'',value:''}]
  * @returns boolean 在存在记录TRUE，否则false
  */
 ToolKits.isInDG = function(datagrid, fields){
	 if(!datagrid ){
		 throw new Error("datagrid is null") ;	
		}
	 
	 if(!fields){
		throw new Error("fields is null") ;
	 }
	 
	 var data = datagrid.datagrid("getRows") ;
	 if(data.length<=0){
		 return false ;
	 }


	 
	 function isInFun(row,fields){
		for(var i =0 ;i<fields.length; i++){
			if(!row[fields[i].field] || row[fields[i].field] != fields[i].value){
				return false ;
			}
		} 
		return true ;
	 }
	 
	 for(var i=0 ;i<data.length ;i++){
		if(isInFun(data[i],fields) ){
			return true ;
		}
	 }
	 
	 return false ;
 };
 
 
 /**
  * 从内存数组中插入数据到 datagrid
  * @param datagrid
  * @param row
  * @param af appendField
  */
 ToolKits.insertToDGFD = function(datagrid ,row,af){
	 if(!datagrid ){
		 throw new Error("datagrid is null") ;	
		}
	 var data = datagrid.datagrid("getData") ;
	  
	 
	 for(var i=0 ;i< data.total ;i++){
		 if(data.rows[i][af] == row[af]){
			 data.rows.splice(i,0,row) ;
			 data.total ++ ;
			 datagrid.datagrid("loadData",data) ;
			 return i;
		 }
	 }
	 
	 data.rows.splice(data.total,0,row) ;
	 data.total ++ ;
	 datagrid.datagrid("loadData",data) ;
	 return data.total -1 ;
 };
 
 ToolKits.insertToDG = function(datagrid ,row,af){
	 if(!datagrid ){
		 throw new Error("datagrid is null") ;	
	 }
	 
	 var rows = datagrid.datagrid("getRows") ;
	 for(var i=0 ;i< rows.length ;i++){
		 if( rows[i][af] == row[af]){
			 datagrid.datagrid("insertRow" ,{index:i,row:row}) ;
			 return i;
		 }
	 }

	 datagrid.datagrid("appendRow",row) ;
	 var data = datagrid.datagrid("getData") ;
	 return data.total-1  ;
 };
 
 
 
 /**
  * @todo :批量设置datagrid 记录值
  * @param datagrid:
  * @param checkFlg 复选标志，a:全部，c:勾选的，u：没勾选的
  * @param kvArr:设置字段键值数组 [{f:'field',v:'value'}]
  * @parma exclude:function(row) ,排除在外的条件函数，返回true排除，否则不排除false
  */
 ToolKits.setDGBatchV = function(datagrid,checkFlg,kvArr,exclude){
	 if(!datagrid ){
		 throw new Error("datagrid is null") ;	
	 }
	 
	 if(!checkFlg ){
		 throw new Error("checkFlg is null") ;	
	 }
	 
	 if(!kvArr ){
		 throw new Error("kvArr is null") ;	
	 }
	 
	 if("c" == checkFlg){
		 function updateRow(row ,kvArr){
			 var index =  datagrid.datagrid("getRowIndex",row) ;
			 var count = 0 ;
			datagrid.datagrid("beginEdit",index ) ;
			 for(var i =0 ;i<kvArr.length ;i++){
				 if(row[kvArr[i].f] !== undefined){
				
					 var ed = datagrid.datagrid('getEditor', {index:index,field:kvArr[i].f});
					 if(ed && ed.target){
						 $(ed.target).textbox('setValue',  kvArr[i].v);
					 }else{
						row[kvArr[i].f] = kvArr[i].v ; 
					 }
					 count ++ ;
				
				 }
			 }
			 
			 datagrid.datagrid("endEdit",index ) ;
			if(count >0){
			
			
			 datagrid.datagrid("refreshRow",index ) ;
			 //datagrid.datagrid("uncheckRow",index ) ;
			 
			}
		 }
		 
		 var rows = datagrid.datagrid("getChecked") ; 
		 for(var i=0 ;i<rows.length ;i++){
			 if(exclude && typeof exclude === "function")
			 {
				 if(!exclude(rows[i]))
				  {updateRow(rows[i],kvArr) ;}
			 }else{
				 updateRow(rows[i],kvArr) ;
			 }
		 }
		 datagrid.datagrid("uncheckAll");
		 
	 }
  
 };
 
 ToolKits.getNum = function(text){
		var value = text.replace(/[^0-9]/ig,""); 
		value = parseInt(value,10) ;
		return value ;
 };
 
 /**
  * @todo 批量设置元素的可见性
  * @param idArr id数组
  * @param display 是否
  */
 ToolKits.setDisplay = function(idArr,display){
	 for(var i =0 ;i<idArr.length ;i++){
		 if(document.getElementById(idArr[i]))
		 document.getElementById(idArr[i]).style.display = display; 
	 }
	
 } ;
 
 /**
  * @todo 格式化数据
  * @param x, 输入数据
  * @param preNum 精度
  */
 ToolKits.formatData = function(x,preNum){
	// var f = parseFloat(x);    
     if (isNaN(x)) {    
    	 throw new Error("x is NaN") ;	    
     }    
    // var f = Math.round(x*100)/100;    
    // var s = f.toString();   
	 var s = x.toString(); 
     var rs = s.indexOf('.');    
     if (rs < 0) {    
         rs = s.length;    
         s += '.';    
     }    
     while (s.length <= rs + preNum) {    
         s += '0';    
     }    
     return s;    
 } ;
 
 
/**
 * @to exclude repetition
 * @param source 比较的源数组
 * @param target 排除重复的目标数组
 * @param keys [] 排除重复的键
 */
 ToolKits.excludeRepet = function(source,target,keys){
	 if(!source || source.length <=0 ){
		 return  ;
	 }
	 
	 function isSame(source ,tar ,keys){
		 if(!source || source.length <=0){
			 return false ;
		 }
		 for(var i=0 ;i<source.length ;i++){
			  for(var j=0 ;j<keys.length ;j++){
				  if(!source[i][keys[j]] || !tar[keys[j]]){  return false ; }
				  
				  if(source[i][keys[j]] != tar[keys[j]]){ return false ;}
			  }
		 }
		 
		 return true ;
	 }
	 
	 for(var i=0 ;i<target.length ;){
		if( isSame(source ,target[i] ,keys)){
			//arr.splice(1,1) 
			target.splice(i,1);
		}else{
			i++ ;
		}
	 }
 };
	 
 /**
  * @todo 结束datagrid编辑
  * @param datagrid 
  * @param index 索引
  */
 ToolKits.endDatagridEdit = function(dg,index){
	 if(!dg ){
		 throw new Error("datagrid is null") ;	
	 }
	 
	if(index){
		if(dg.datagrid('validateRow', index))
		{
			dg.datagrid('endEdit', index);
			return true;
		} else {
			return false;
		}
	} else{
		var rows = dg.datagrid("getRows") ;
		var ret = true ;
		for(var i=0 ;i<rows.length ;i++){
			
			if(dg.datagrid('validateRow', i))
			{
				dg.datagrid('endEdit', i);
			} else {
				dg.datagrid('endEdit', i);
				ret = false ;
			}
			
		}
		
		return ret ;
	}
		
 };
 
/**
 * @todo datagrid是否全勾选
 * @param datagrid
 * @param isPart
 * @param fieldArr
 * @return true：是，false ：否
 */
ToolKits.isCheckAll=function(dg,isPart,fieldArr){
	if(!dg ){
		 throw new Error("datagrid is null") ;	
	 }
	
	if(isPart){
		if(!fieldArr){
			 throw new Error("fieldArr is null") ;	
		}
	}
	
	var cs = dg.datagrid("getChecked") ;
	var rs = dg.datagrid("getRows") ;
	if(cs.length <= 0){
		return false ;
	}

	if(!isPart){
		return cs.length == rs.length ;	
	}else{
		
		function isEque(row,tg,fs){
			for(var n=0 ; n< fs.length ;n++){
				if(row[fs[n]] == undefined || row[fs[n]] != tg[fs[n]] ){
					return false ;
				}
			}
			return true ;
		}
		
		function isSame(r,ts){
			for(var m =0 ;m<ts.length ;m++){
				if(r === ts[m]){ //排除已勾选项
					return true ;
				}
			}
			return false ;
		}
		
		for(var i=0 ;i< rs.length ;i++){
		       if(isSame(rs[i],cs)){
		    	   continue ;
		       }
			
			if(isEque(rs[i],cs[0],fieldArr)){
				return false;
			}
		}
		return true ;
	}
};

/**
 * @todo 获取datagrid 复选状态对应 ，和指定域的数据。
 * @param datagrid
 * @param flag : 1 为checked，0 为 unchecked，2，all
 * @fields []字段数组
 * return 数组
 */
ToolKits.getCheckData = function(dg, flag, fields){
	if(!dg ){
		 throw new Error("datagrid is null") ;	
	 }
	
	var rows = [] ;
	if(flag == 1){
		rows = dg.datagrid("getChecked") ;
	}else if(flag == 0){
		rows = [] ;
	}else if(flag == 2){
		rows = dg.datagrid("getRows") ;
	}
	var rets = [] ;
	for(var i=0 ;i< rows.length ;i++){
		var d = (function GD(row,fs){
					var d={} ;
					for(var j=0 ;j<fs.length ;j++){ 
						d[fs[j]] = row[fs[j]] ; 
					}
					return d ;
		        })(rows[i],fields) ;
		rets.push(d) ;
	}
	return rets ;
		
};

/**
 * @todo 批量设置datagrid 指定记录 记录中的值
 * @param dg datagrid 被设置列表
 * @param datas 设置的内容
 * @tarKeys [] 匹配记录的关键字段数组
 */
ToolKits.setDGRowData = function(dg, datas,tarKeys){

	if(!dg ){
		 throw new Error("datagrid is null") ;	
	 }
	
	if( datas == null || datas.length == 0){
		return ;
	}
	
	function isSame(row,data ,keys){
		for(var i=0 ;i<keys.length ;i++){
			if(row[keys[i]] != data[keys[i]]){
				return false ;
			}
		}
		return true ;
	}
	
	function getD(row,datas ,keys){
		var data = null ;
		for(var n=0 ;n<datas.length ;n++){
			if(isSame(row,datas[n] ,keys)){
				data = datas[n] ;
				datas.splice(n,1) ;//移除已匹配的，提高下轮匹配速度
				return data ;
			}
		}
	}
	
	var crows = dg.datagrid("getChecked") ;
	for(var i=0 ;i<crows.length ;i++){
		var data = getD(crows[i] ,datas,tarKeys) ;
		if(data){
			for(var m in data){
				if(crows[i][m]==undefined  ){
					continue ;
				}
				crows[i][m] = data[m] ;
			}
			
			var index = dg.datagrid("getRowIndex",crows[i]);
			//dg.datagrid("updateRow",{index:index ,row:crows[i]}) ;
		    dg.datagrid("refreshRow",index ) ;
		}
	
	}

};

/**
 * @todo 统计datagrid中 指定列的和
 * @param dg ，datagrid
 * @param fields []
 * @return {field1:3，field2:4,......}
  */
ToolKits.statisticsDG = function(dg,fields){

	if(!dg ){
		 throw new Error("datagrid is null") ;	
	 }
	
	if( fields == null || fields.length == 0){
		return null;
	}
	var total={} ;
	for(var n= 0 ;n<fields.length ;n++){
		total[fields[n]] = 0.0 ;
	}	
	
	function sum(t,r,fs){
		for(var n=0 ;n< fs.length ;n++){
			if(r[fs[n]]!=undefined){
				t[fs[n]] += parseFloat(r[fs[n]]) ;
			}
		}
	}
	
	var rows = dg.datagrid("getRows") ;
	for(var i=0 ;i< rows.length ;i++ ){
		sum(total,rows[i],fields) ;
	}
	
	return total ;
};

ToolKits.setDialogDisplay = function(dlg,flg,title){
	if(!dlg){
		throw new Error("dialog is null") ;
	}
	if(flg ==false){
		dlg.dialog('close');
	}else{
		setTimeout(function(){
			if(title){
				dlg.dialog({title:title});
			}
			dlg.dialog('open');
		},1);
	}

};

ToolKits.serializeDG=function(dg){
	if(!dg ){
		 throw new Error("datagrid is null") ;	
	 }
	
	 var rows = dg.datagrid("getRows") ;
	 return JSON.stringify(rows) ;
	
};
/**
 * form(id)
 */
ToolKits.form2Json=function(id) {
	 
    var arr = $("#" + id).serializeArray();
    var jsonStr = "";

    jsonStr += '{';
    for (var i = 0; i < arr.length; i++) {
        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",';
    }
    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
    jsonStr += '}';

    var json = JSON.parse(jsonStr);
    return json;
};

//格式化时间函数
ToolKits.dateFormat = function(date){
	return new Date(date).Format("yyyy-MM-dd hh:mm:ss");
};

Date.prototype.Format = function (fmt) { //
	var o = {
	"M+": this.getMonth() + 1, //月份
	"d+": this.getDate(), //日
	"h+": this.getHours(), //小时
	"m+": this.getMinutes(), //分
	"s+": this.getSeconds(), //秒
	"q+": Math.floor((this.getMonth() + 3) / 3), //季度
	"S": this.getMilliseconds() //毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};


ToolKits.JqmTableRefresh = function(id){
	   $("#"+id).trigger("create");
	   $("#"+id).table("refresh") ;
};

/**
 * 解决easyui 手机web 打开 dialog存在的bug
 * @author fhr
 * @param id :dialog id
 * @param wdRatio ,宽度比例（相对手机屏幕像素） 
 */
ToolKits.openDialogForM = function(id,wdRatio){
	$('#'+id).dialog('open');  
	$('#'+id).panel("resize",{width: $(window).width() * wdRatio}) ;
	$('#'+id).window("center") ;
	$('#'+id).panel("doLayout");
	
};


//解析时间函数
function parseDate(date){
	var t = Date.parse(date);
	if (!isNaN(t)){
		return new Date(t);
	} else {
		return new Date();
	}
}

//遮罩效果
function EasyUILoad(msg) {  
    $("<div class=\"datagrid-mask\" style=\"z-index:9999\"></div>").css({ display: "block", width: "100%", height: "auto !important" }).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\" style=\"z-index:9999\"></div>").html(msg).appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
}  

function dispalyEasyUILoad() {  
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();  
}

//本地显示图片
function getFileUrl(sourceId) {  
    var url;  
    if (navigator.userAgent.indexOf("MSIE")>=1) { // IE  
        url = document.getElementById(sourceId).value;  
    } else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox  
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));  
    } else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome  
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));  
    }  
    return url;  
}

function preImg(sourceId, targetId) {   
    var url = getFileUrl(sourceId);   
    var imgPre = document.getElementById(targetId);   
    imgPre.src = url;   
}

 ToolKits.showImage = function(obj){
	
	 return ToolKits.getMainFrame().showImage(obj);
};


 