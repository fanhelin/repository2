/**
 * datagrid 表头工具
 *  @author fhr
 * @param moduleId 模块id
 * @param menuId 菜单id
 * @param dgId 绑定datagrid id
 * @param menuW 菜单宽度 ，
 * @param type 菜单模式：1:全模式，2：部分模式
 * @returns {DatagridColumTool}
 */
//$.parser.auto = false ;
function DatagridColumTool(moduleId,menuId , dgId ,menuW,type){
	
	if(moduleId == null){
		 throw new Error("moduleId is null") ;	
	}
	
	if(dgId == null){
		 throw new Error("dgId is null") ;	
	}
	
	this.moduleId = moduleId ;
	this.menuId = "MENU_ID" ;
	menuId && (this.menuId = menuId) ;
	this.menuW =  '120px' ;
	menuW && (this.menuW = menuW) ;
	
	this.dg = $("#"+dgId) ;
	var clsN = this.dg.attr("class");
	if( !( clsN.indexOf("easyui-datagrid")!= -1 || clsN.indexOf("easyui-combogrid") != -1 || clsN.indexOf("datagrid-f") != -1 ) ){
		  throw new Error("this componet can't suppert :" + clsN ) ;	
	}
	if(clsN.indexOf("easyui-combogrid") != -1){
		this.dg = this.dg.combogrid("grid") ;
	}
	
	this.cfd = "" ;
	this.CONFINE  =1 ; //隐藏边界
	
	this.hideItems = null ;
	this._countDGHideItem();
    if(1 == type){
    	this.fullMode() ;
    }else if(2 == type){
    	this.subMode() ;
    }else{
    	this.fullMode() ;//暂时用全模式，备扩展
    }
}

DatagridColumTool.prototype.subMode = function(){
	var me = this ;
	this.mu = $('<div/>').appendTo('body');
	
    function menuEvent(){
    	me.mu && me.mu.menu({onClick:function(item){
			if (item.name == 'hideItem'){
				
				var fields = me._getDGFields();
				if(fields.length - me.hideItems.length <= me.CONFINE){
					return ;
				}
				
				me.dg.datagrid('hideColumn', me.cfd );
			    var col = me.dg.datagrid('getColumnOption', me.cfd) ;
    		    me.mu.menu('appendItem', {
					text:  "显示-"+col.title,
					name: me.cfd,
					iconCls: 'icon-empty'
				}); 
				
				me._setHItem('add', me.cfd ) ;
			} else {
				me.dg.datagrid('showColumn', item.name);
				var cm = me.mu.menu('findItem', item.text);
				me.mu.menu("removeItem" ,cm.target) ;
				me._setHItem('remove',item.name) ;
			}
		}
    	
    	}) ;	
    }
    

    function menuInit(){
    	var tmp = localStorage.getItem(me.moduleId+"_"+me.menuId);
	    if(null == tmp || '' == tmp){
	    	me.hideItems = new Array() ;
	    }else{
	    	me.hideItems = eval(tmp) ;
	    }
	    
	    me.mu.menu('appendItem', {
			text: "隐藏本列",
			name: "hideItem",
			iconCls: 'icon-save'
		});
	    
		var fields = me._getDGFields();
		fields.forEach(function (e, se,all) {  
			 var col = me.dg.datagrid('getColumnOption', e) ;
	    	 if(me._isHideField(e) || col.hidden) {
	    	  
    		    me.mu.menu('appendItem', {
					text: '显示-' + col.title,
					name: e,
					iconCls: 'icon-empty'
				});
    		    
    		    col.hidden || me.dg.datagrid('hideColumn', e);
	    	 }
	    });	
    }

    menuEvent() ;
    menuInit() ;
    me._dgEvent() ;
};



DatagridColumTool.prototype.fullMode = function(){
	
	this.mu = $('<div/>').appendTo('body');
	var me = this ;

    function menuInit(){
    	var tmp = localStorage.getItem(me.moduleId+"_"+me.menuId);
	    if(null == tmp || '' == tmp){
	    	me.hideItems = new Array() ;
	    }else{
	    	me.hideItems = eval(tmp) ;
	    }
	    
		var fields = me._getDGFields();
		fields.forEach(function (e, se,all) {  
	          //alert("参数1="+element+",参数2="+all);
			 var col = me.dg.datagrid('getColumnOption', e) ;
	    	 if(me._isHideField(e) || col.hidden){
	    	    
    		    me.mu.menu('appendItem', {
					text: col.title,
					name: e,
					iconCls: 'icon-empty'
				});  
    		    col.hidden || me.dg.datagrid('hideColumn', e);
	    	 }else{
	    		 
	    		  var col = me.dg.datagrid('getColumnOption', e) ;
	    		  me.mu.menu('appendItem', {
						text: col.title,
						name: e,
						iconCls: 'icon-ok'
					});
	    	 }
	    });     
    }
    
    function menuEvent(){
    	me.mu && me.mu.menu({onClick:function(item){
			if (item.iconCls == 'icon-ok'){
				var fields = me._getDGFields();
				if(fields.length - me.hideItems.length <= me.CONFINE){
					return ;
				}
				
				me.dg.datagrid('hideColumn', item.name);
				me.mu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
				me._setHItem('add',item.name) ;
				
			} else {
				me.dg.datagrid('showColumn', item.name);
				me.mu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
				me._setHItem('remove',item.name) ;
			}
		}
    	
    	}) ;
    }

  menuEvent() ;
  menuInit() ;
  me._dgEvent() ;
};

DatagridColumTool.prototype._dgEvent = function(){
	var me = this ;
	 me.dg && me.dg.datagrid({
			onHeaderContextMenu:function(e, field){
				  if (3 == e.which) {
			            e.preventDefault();
			            me.mu.menu('show', {
			                left: e.pageX,//在鼠标点击处显示菜单
			                top: e.pageY
			            });
			        }
			        me.cfd = field;
			}
		});
};

DatagridColumTool.prototype._setHItem = function (flg,fn){
	var hit = new Set(this.hideItems) ;
	if('remove' == flg){
		hit.delete(fn) ;
	}else if('add' == flg){
		hit.add(fn) ;
	}
	
	this.hideItems = Array.from(hit);
    localStorage.setItem(this.moduleId+"_"+this.menuId, JSON.stringify(this.hideItems));
};


DatagridColumTool.prototype._isHideField = function(field){
	if(this.hideItems){
	
		for(var i=0; i< this.hideItems.length; i++)
		{
			if(field != null && field == this.hideItems[i])
			{
				 return true ;
			}
		}
	}
	return false ;
};

/**
 * @fit 是否复合条件 回调函数
 */
DatagridColumTool.prototype._getDGFields = function(fit){
	var me = this ;
	if(!this.dg){
		return [] ;
	} 
	if(fit == null || typeof fit != 'function' ){
		fit = function(e){
			 var col = me.dg.datagrid('getColumnOption', e) ;
			 if( !col.title){
				 return false ;
			 }
			 return true ;
		};
	}
	
	var fields = this.dg.datagrid('getColumnFields');
	var fArr = new Array() ;
	fields && fields.forEach(function (e, se,all) {  
        //alert("参数1="+element+",参数2="+all);
		if( fit(e)){
			fArr.push(e);
		} 
	});
	
	return fArr ;
   
};

DatagridColumTool.prototype._countDGHideItem = function(fit){
	
	var me = this ;
	if(!this.dg){
		return  ;
	} 
	if(fit == null || typeof fit != 'function' ){
		fit = function(e){
			 var col = me.dg.datagrid('getColumnOption', e) ;
			 if( !col.title ||  !col.hidden){
				 return false ;
			 }
			 return true ;
		};
	}
	
	var fields = this.dg.datagrid('getColumnFields');

	fields && fields.forEach(function (e, se,all) {  
        //alert("参数1="+element+",参数2="+all);
		if( fit(e) ){
			me.CONFINE ++ ;
		} 
	});
	


};




