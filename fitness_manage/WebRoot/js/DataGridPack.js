/**
 * datagrid 包装类
 * @param target datagrid
 * @param maxLen 每页最大记录数
 * @param bufferSize 缓冲大小
 * @returns {DataGridPack}
 */
function DataGridPack(target , maxLen ,bufferSize ){
	this.datagrid = target ;
	this.maxLen = maxLen ;
	this.bufferSize = bufferSize ;
	
	this.init() ;
} ;

DataGridPack.prototype.init = function(){
	
	if(typeof this.datagrid == 'string'){
	  this.datagrid = $(this.datagrid) ;
	  
		
	}else if(typeof this.datagrid == 'object'){
		
	}else{
		return false ;
	}
	return true ;
	
};


DataGridPack.prototype.addRecord = function(row){
	this.datagrid.datagrid("insertRow" ,{index:0,row:row}) ;
    
	var rows = this.datagrid.datagrid("getRows") ;
	var size = rows.length ;
	if(size > this.maxLen +this.bufferSize){
	
		for(var i = size-1 ; i > size - this.bufferSize -1 ; i--){
			this.datagrid.datagrid("deleteRow" ,i) ;
		}
	}
};





