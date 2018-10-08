
/**
 *combogrid 模糊查询组件。
 *支持字段配置，中文和拼音模糊查询
 *
 * @author fhr 
 * @returns {CombogridSearchHelper}
 * 2017-10-23
 */

/**
 * @param params{
	  @bindID string: 绑定的组件id
	  @keyID string: 关键字段
	  @fs array: 需要模糊查询的域['field1','field2','field3',......]
	  @st string: 模糊配置的方式['f':全称匹配|'s'：简写匹配|'a'：s和f 匹配]
	  @url string : 外部拼音服务url ，默认绑定：g_path+"/pyServer/getPY.do",
	  @onlySelect bool :是否只能通过选择录入，不选择时，值将被清空 默认true
 * }
 * @returns {CombogridSearchHelper}
 */
function CombogridSearchHelper(params){
	
	this.bindID = params.bindID ;
	this.bindObj = null ;
	this.keyID =  params.keyID ;
	this.data = {total:0,rows:[]} ;
	this.st =  params.st ;//s:拼音简称,f：拼音全称 ,a：简称和全称
	this.fields =  params.fs ;
	this.URL = (function() { 
					var ph = window.document.location.href;   
				    var pn = window.document.location.pathname;
				    var idx = ph.indexOf(pn);
				    var hp = ph.substring(0, idx);
				    var sbn = pn.substring(0, pn.substr(1).indexOf('/') + 1);
				    return hp + sbn;
	            })()+"/pyServer/getPY.do" ;
	params.url && (this.URL =  params.url) ;
	
	
	if(!this.fields || ! this.fields instanceof Array || this.fields.length <1 ){
		throw new Error("[CombogridSearchHelper] [construction] fields is Invalid") ;	
	}

	this.initFlg = false ;
	this.selectFlg = false ;
	this.onlySelect = true ;
	if(params.onlySelect != undefined )
	 {
		this.onlySelect =  params.onlySelect == false ? false : true ;
	 }
	
	this._init();
}

CombogridSearchHelper.prototype._init = function(){;
	var me = this ;
	me.bindObj = $("#"+me.bindID) ;
	var clsN = this.bindObj.attr("class");
	if(clsN.indexOf("combogrid") == -1){
		throw new Error("this componet cant't suport:"+clsN) ;	
	}
	
	/*if(clsN.idexOf("combobox" != -1)){
		me.bindObj.("")
	}*/
    /*
		var str = "me.bindObj."+bindType+"({});" ;
	    str += "me.mainFun = me.bindObj."+bindType ;
	    eval(str) ;
	    function bindFun(ctx,fn){
	         return function(){
	            return fn.apply(ctx,arguments);
	     };           
	    }
    me.mainFun = bindFun(me.bindObj , me.mainFun) ;
	 */
	
	//var options = me.bindObj.combogrid('options') ;
	me.bindObj.combogrid({
    	onLoadSuccess: function(data){
    		console.log("onloadSuccess") ;
    		me.onLoadSuccess(data);
    		if(!me.initFlg){	
    			var data = me.bindObj.combogrid("grid").datagrid("getData") ;
    			me.onInitData(data) ;
    			if(data.total > 0){
    				me._setData(data) ;
    				me.initFlg = true ;
    			}
    		
    		  }
    			
    	},
    	
    	onChange:function(newValue, oldValue){
    		console.log("onChange" ,newValue ,oldValue ) ;
    		
    		me.onChange(newValue, oldValue);
    		me._onInnerChange(newValue, oldValue);
    	},
    	
    	onShowPanel:function(){
    		
    		//console.log("onShowPanel" ) ;
    	},
    	
    	onHidePanel:function(){
    		//console.log("onHidePanel" ) ;
    		if(!me.selectFlg){
    			if(me.onlySelect){ 
    			   $(this).combogrid('setValue', ''); 
    			}
    			
    		}
    		
    		me.onHidePanel(me.selectFlg) ;
    		me.selectFlg = false ;
    	},
    	
    	onSelect:function(rowIndex, rowData){
    		//console.log("onselect") ;
    		
    		me.onSelect(rowIndex,rowData);
    		me.selectFlg = true ;
    	},
    	
    	onClickRow : function(index,row){
    	  me.onClickRow(index,row);
    	}

    }) ;

	
};

CombogridSearchHelper.prototype._onInnerChange = function(newValue, oldValue){
	var me = this  ;
	if(me.data == null || me.data.total <=0){
		return ;
	}
	
	if(newValue == ""){
		me.bindObj.combogrid("grid").datagrid("loadData" ,me.data);
		//me.mainFun("showPanel" );
		return ;
	}
	
	var fitData = [] ;
	var spStr = "&*&" ;
	
	
	if(/^[\u4e00-\u9fa5]/.test(newValue)){
          me.data.rows.forEach(function(r,sr,ar){
        	  var rv = "" ;
        	  
    	  me.fields.forEach(function(f,sf,af){
    		   rv += spStr + r[f] ; 
    	  });
    	  
    	 var reg = new RegExp(newValue, 'i');
    	  if(rv.match(reg)){
    		  fitData.push(r) ;
    	  }
    	  
     }) ;
	}else{
		 me.data.rows.forEach(function(r,sr,ar){
       	  var rv = "" ;
       	  if("a" == me.st ){
		   	  me.fields.forEach(function(f,sf,af){
		   		   rv += spStr + r[f+"_s"] + spStr + r[f+"_f"]; 
		   	  });
	   	  
		   	  var reg = new RegExp(newValue, 'i');
		   	  if(rv.match(reg)){
		   		  fitData.push(r) ;
		   	   } 
	   	  
       	  }else{
       		 var sux= "_"+me.st ;
       		 me.fields.forEach(function(f,sf,af){
		   		   rv += spStr + r[f+sux] ; 
		   	  });
       		  var reg = new RegExp(newValue, 'i');
		   	  if(rv.match(reg)){
		   		  fitData.push(r) ;
		   	  } 
       	  }
   	  }) ;
		 
	}
	
	console.log("load:",{ total:fitData.length ,row: fitData}) ;
	me.bindObj.combogrid("grid").datagrid("loadData" ,{ total:fitData.length ,rows: fitData} );
};


CombogridSearchHelper.prototype._setData = function(data){
	this.data = data ;
	var me = this ;
	
	if(me.data == null || ! me.data.rows instanceof Array ||  me.data.rows.length<1){
		 throw new Error("[CombogridSearchHelper] [_setData ] data is Invalid") ;	
	}
	
	/*
	 * {  type:'s|f|a',
	 *    fields:['f1','f2','f3'] , 
	 *    keyID: '',
	      rows:[{ #keyid:'',val1:'value1',val2:'value2',val3:'value3',.....]},{} .... ] 
	   } ;
	*/
	
	var searchData = {} ;
	searchData.type = me.st ;
	searchData.fields = me.fields ;
	searchData.keyID = me.keyID ;
	searchData.rows = [] ;
	
	//获取域字段 内容
	function getFVs(r){
		if(!r || !r[me.keyID]){
			return null ;		
		}
		
		var row = {} ;
		row[me.keyID] = r[me.keyID] ; 
		me.fields.forEach(function(f,sf,af){
			f && (row[f] = r[f]) ;
		}) ;
		
		return row ;
	}
	
	me.data.rows.forEach(function(r,sr,ar){
		var fv = getFVs(r);
		 fv && searchData.rows.push(fv) ;
	});
	
	$.ajax({            
          type:"POST",   //post提交方式默认是get
          url: me.URL, 
          data: {datas:JSON.stringify(searchData) } ,   //序列化               
          error:function(request) {      // 设置表单提交出错               
            
          },
          success:function(rsp) {
        	  console.log("rsp:",rsp) ;
        	  var spd = rsp.data ;
        	  if(spd== null || spd.length == 0){
        		  return ;
        	  }
        	  
        	  function catPYData(spd,id){
        		  if(!id){
        			  return null ;
        		  }
        		  
        		  for(var i = 0 ;i<spd.length ;i++){
        			   if(id == spd[i][me.keyID]){
        				   return spd[i] ;
        			   }
        		  }
        		  return null ;
        	  }
        	  
        	  me.data.rows.forEach(function(e,i,a){
        		  var fd = catPYData(spd , e[me.keyID]) ;
        		  if(fd != null){
        			  for(var nf in fd) {
        				  if(nf == me.keyID){ continue ;}
        				  
        				   e[nf] = fd[nf];
        			  }
        		   }
        	  }) ;
          }            
    }); 
	  
};

CombogridSearchHelper.prototype.onLoadSuccess = function(data){};
CombogridSearchHelper.prototype.onInitData = function(data){} ;
CombogridSearchHelper.prototype.onSelect = function(rowIndex,rowData){} ;
CombogridSearchHelper.prototype.onChange = function(newValue, oldValue){} ;
CombogridSearchHelper.prototype.onHidePanel = function(selectFlg){} ;
CombogridSearchHelper.prototype.onClickRow = function(index,row){};


