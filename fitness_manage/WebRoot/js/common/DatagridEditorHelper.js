/**
 * datagrid editor 辅助工具类.
 * html和js创建方式
 * @param params{
 *        @raw: datagrid 原始配置，请参考easyui api
 *        @addtion{ //附加配置
 *           @editMode :触发编辑方式，取值为datagrid 行事件名（onClickRow，onDblClickRow）和 单元格事件名（onClickCell，onDblClickCell）
 *           @onBeforeEndEdit：function：结束编辑前事件，与easyui的onbeforeEdit类似，但是该事件能阻止结束编辑（当放回fasle时)
 *           @onBeforeBeginEdit:function(index,row,cell):开始编辑前事件
 *           @onAfterBeginEdit:function(index,row,cell):开始编辑后事件，异步
 *           @endSelf:是否能结束自己。true|false ;
 *        }
 * }
 * 
 * @returns {DatagridEditorHelper}
 * @author fhr 
 * @since 2018-1-4
 */
function DatagridEditorHelper(params){
  	this.currentIndex = undefined ;
  	this.currentRow = null ;
  	this.currentCell = null ;
  	
  	this.preIndex = undefined ;
  	this._dg = null ;
  	this._raw = params.raw ;
  	this._addition = params.addition ;
  	
  	var me = this ;
  	var _init = function(){
  		
  		if(!me._addition){
			throw new Error("params.addition is invalid");
			return false;
		} 

  		if(me._addition.editMode && "[onClickRow|onDblClickRow|onClickCell|onDblClickCell]".indexOf(me._addition.editMode) == -1){
  			throw new Error("addition.editMode must one of: [onClickRow|onDblClickRow|onClickCell|onDblClickCell]");
			return false;
  		}
  		
  		if(!me._raw || me._raw==""){
  			me._raw = $('#'+me._addition.id).datagrid("options") ;
  		}
  	
  		
  		var evFun = "" ;
  		var t_f = null ;
  		if(me._addition && me._addition.editMode && me._addition.editMode != null){
  			eval('( t_f = me._raw.'+me._addition.editMode+' )')
  			evFun += " me._raw."+me._addition.editMode+"=function(r,d,v){" ;
  			evFun += " if(me._EE(true)){ " ;
  			evFun += " me._BE(r,d,v) \;" ;
  			evFun += " if(typeof t_f ==='function')" ;
  			evFun += "  {t_f(r,d,v) \;} \; " ;
  	        evFun += "}}" ;
  			eval("("+ evFun +")") ;
  		}
  		
  		if(me._addition && me._addition.endSelf===true ){
  			var t_ae = me._raw.onAfterEdit ; 
  			me._raw.onAfterEdit = function(rowIndex, rowData, changes){
  				t_ae(rowIndex, rowData, changes) ;
  				if(me.preIndex == me.currentIndex){
  					setTimeout(function(){me.editOver() ;},100) ;
  				}
  			
  			};
  		}
  		me._dg  = $('#'+me._addition.id).datagrid(me._raw) ;
  	};
  	
  	_init();
  	
  	
} ;

/**
 * 内部方法
 * @param r 行号
 * @param d 行数据
 * @param v 单元数据
 */
DatagridEditorHelper.prototype._BE = function(r,d,v){
	var me = this ;

    function _BFBE(){
    	if(me._addition && me._addition.onBeforeBeginEdit && typeof me._addition.onBeforeBeginEdit == "function"){
    		return me._addition.onBeforeBeginEdit(r ,d ,v) ;
    	}
    	return true ;
    }

    if(_BFBE()){
    	me.currentIndex = r ;
    	me.currentRow = d ;
    	me.currentCell = v ;
    	
    	this._dg && this._dg.datagrid("beginEdit",this.currentIndex) ;
		if(me._addition && me._addition.onAfterBeginEdit && typeof me._addition.onAfterBeginEdit == "function"){
    		setTimeout(function(){
    			me._addition.onAfterBeginEdit(me.currentIndex ,me.currentRow ,me.currentCell) ;
    		} ,10) ;
    	}
    }
};

/**
 * 结束编辑
 * 内部方法
 * @needValidate 是否需要校验
 * @returns {Boolean}
 */
DatagridEditorHelper.prototype._EE = function(needValidate){
   if(this.currentIndex == undefined){
	  return true ;  
   }
    var me = this ;
    var _beforeEndEdit = function(){
		if(me._addition && me._addition.onBeforeEndEdit && typeof me._addition.onBeforeEndEdit == "function" ){
			var rows = this._dg.datagrid("getRows") ;
			return me._addition.onBeforeEndEdit(me.currentIndex,me.currentRow,me.currentCell,rows);
			
		}
		return true ;
	}
    
   /*  var _afterEndEdit =function(){
    	if(me._addition && me._addition.afterEndEdit && typeof me._addition.afterEndEdit == "function" ){
    		if(!me._addition.afterEndEdit(me.currentIndex,me.currentRow,me.currentCell))
    			return false;
    	}
    	return true ;
    }*/

     if(_beforeEndEdit()){
    	  if (needValidate ){
    		  if(this._dg.datagrid('validateRow', this.currentIndex)){
	    		  me._dg.datagrid("endEdit",this.currentIndex) ;
	    		  //_afterEndEdit(); 
	    		  this.preIndex = this.currentIndex ;
	    		  this.currentIndex = undefined ;
	    		  this.currentRow = null ;
	    		  this.currentCell = null ;
	    		  return true ;
    		  }else{
    			  return false ;
    		  }
    	  }else{
    		      me._dg.datagrid("endEdit",this.currentIndex) ;
	    		  //_afterEndEdit(); 
	    		  this.preIndex = this.currentIndex ;
	    		  this.currentIndex = undefined ;
	    		  this.currentRow = null ;
	    		  this.currentCell = null ;
    	  }
     }else{
    	 return false ;
     }

};

/**
 * 插入行接口
 * @param row 行数据{index：number，row{}}
 * @param bc（row，rows） return boolean：删除前回调 ，false取消删除 
 * @returns {Boolean}
 */
DatagridEditorHelper.prototype.insertRow = function(row,editNow,bc){
	try{
		if(!this._dg){
			throw new Error("_dg is invalid");
			return false;
		} 
		if(this._EE(true)){
			
			if(bc && typeof bc ==="function"){
				var rows = this._dg.datagrid("getRows") ;
				if(!bc(row,rows)){
					return false ;
				}
			}
			
			 this._dg.datagrid('insertRow',row);
			if(editNow){
				this._dg.datagrid("selectRow",row.index);
				this._BE(row.index ,row.row,null) ;
			}
			
			return true ;
		}{
			return false ;
		}
	}catch(e){
		return false ;
	}
};

/**
 * 在末尾增加一行
 * @param row 行数据
 * @editNow 马上编辑：true|false
 * @param bc（row:附件数据，rows：已有全部数据） return boolean：删除前回调 ，false取消删除 
 * @returns {Boolean}
 */
DatagridEditorHelper.prototype.appendRow = function(row,editNow,bc){
	try{
		if(!this._dg){
			throw new Error("_dg is invalid");
			return false;
		} 
		if(this._EE(true)){
			if(bc && typeof bc ==="function"){
				var rows = this._dg.datagrid("getRows") ;
				if(!bc(row,rows)){
					return false ;
				}
			}
			this._dg.datagrid('appendRow',row);
			if(editNow){
				var rows = this._dg.datagrid("getRows") ;
				var index = rows.length -1 ;
				me._BE(index ,rows[index],null) ;
			}
			return true ;
		}{
			return false ;
		}
	}catch(e){
		return false ;
	}
};

/**
 * 删除行接口
 * @param index 索引
 * @parame endEdit{needValidate：true|false 是否校验}
 * @param bc（row，rows） return boolean：删除前回调 ，false取消删除 
 * @returns {Boolean}
 */
DatagridEditorHelper.prototype.deleteRow = function(index,endEdit,bc,ac){
	try{
		if(!this._dg){
			throw new Error("_dg is invalid");
			return false;
		} 
		if(endEdit){
			if(this._EE(endEidt.needValidate))
			{
				var rows = this._dg.datagrid("getRows") ;
			    if(bc && typeof bc === 'function'){
					
					if(rows){
						if(!bc(rows[index],rows) )
							return false ;	
					} 
				} 
				
				setTimeout(function(){
					this._dg.datagrid('deleteRow',index);
					var arows = this._dg.datagrid("getRows") ;
					ac && typeof ac ==='function' && ac(rows[index],arows) ;
					
				},1) ;
				return true ;
			}{
				return false ;
			}
		}else{
			var rows = this._dg.datagrid("getRows") ;
		    if(bc && typeof bc === 'function'){
				if(rows){
					if(!bc(rows[index],rows) )
						return false ;	
				} 
			} 
		
			setTimeout(function(){
				this._dg.datagrid('deleteRow',index);
				var arows = this._dg.datagrid("getRows") ;
				ac && typeof ac ==='function' && ac(rows[index],arows) ;
			},1) ;
			return true ;
		}
	}catch(e){
		return false ;
	}
};

DatagridEditorHelper.prototype.updateRow = function(updateInfo){
	if(updateInfo.index == undefined){
		updateInfo.index = this.currentIndex ;
	}
	this._dg.datagrid("updateRow",updateInfo);
};

/**
 * 调用datagrid 原始方法的接口
 * @param funName 方法名
 * @param arg 方法对应的参数
 * @param bc(arg：,rows:全部记录) 调用方法前回调
 * @param ac(arg：,rows:全部记录) 调用方法后回调
 * @returns 函数返回结果
 */
DatagridEditorHelper.prototype.rawMethod = function(funName,arg,bc,ac){
	try{
	
		if(!this._dg){
			throw new Error("_dg is invalid");
			return false;
		} 
		var rows = this._dg.datagrid("getRows") ;
		if(bc && typeof bc==="function"){
			if(!bc(arg,rows)){
				return false ;
			}
		}
		var ret = this._dg.datagrid(funName ,arg) ;
		rows = this._dg.datagrid("getRows") ;
		ac && typeof ac ==="function" && ac(arg,rows)
		return ret ;
		
	}catch(e){
		return false ;
	}
}

/**
 * 结束编辑
 * @param cb(@ret:true|false,rows:全部记录) 回调
 */
DatagridEditorHelper.prototype.editOver = function(cb){
	if(!this._dg){
		throw new Error("_dg is invalid");
	} 
	
	var ret = this._EE(true) ;
	var rows = this._dg.datagrid("getRows") ;
	if(cb && typeof cb ==='function' && cb(ret,rows)) ;
};

/**
 * 批量删除
 * @param filer 删除回调函数
 */
DatagridEditorHelper.prototype.batchDelete = function(fit){
	if(!this._dg){
		throw new Error("_dg is invalid");
	} 
	
	throw new Error("还未实现");
	
};


/**
 * 
 * @param fit
 */
DatagridEditorHelper.prototype.forEachEditor = function(fit){
	if(!this._dg){
		throw new Error("_dg is invalid");
	} 
	
	throw new Error("还未实现");
	
};

/**
 * 
 * @param endEdit
 * @param cb
 * @returns {inserted:,deleted:,updated:}
 */
DatagridEditorHelper.prototype.getChanges = function(endEdit,cb){
	if(!this._dg){
		throw new Error("_dg is invalid");
	} 
	
	function cbFun(inserted,deleted,updated){
		if(cb && typeof cb =="function" )
		{
			setTimeout(function(){cb(inserted,deleted,updated) ;},5);
		}
	}
	
	var ret = {} ;//inserted、deleted、updated
	if(endEdit){
		if(this._EE(endEdit.needValidate)){
			ret.inserted = this._dg.datagrid('getChanges','inserted');
			ret.deleted = this._dg.datagrid('getChanges','deleted');
			ret.updated = this._dg.datagrid('getChanges','updated');
			cbFun(ret.inserted,ret.deleted,ret.updated) ;
			return ret ;
		}else{
			cbFun(null,null,null) ;
			return null ;
		}
	}else{

		ret.inserted = this._dg.datagrid('getChanges','inserted');
		ret.deleted = this._dg.datagrid('getChanges','deleted');
		ret.updated = this._dg.datagrid('getChanges','updated');
		
		cbFun(ret.inserted,ret.deleted,ret.updated) ;
		return ret ;
	}
	

};

DatagridEditorHelper.prototype.getCurrentInfo = function(){
	var me = this ;
	return {
			currentIndex:me.currentIndex ,
			currentRow:me.currentRow ,
			currentCell:me.currentCell 
		} ;
}


