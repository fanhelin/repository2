/**
 * 区域管理
 *  @author fhr
 * @returns {AreaManage}
 */

function AreaManage (){
	this.citys = [] ;
	this.districts = [] ;
	
	this.loadCitys();
	this.loadDistricts();
	
}

AreaManage.prototype.loadCitys = function(){
	var me  = this ;
    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url: g_path+"/area/queryCitys.do", 
        data: {level_type:2} ,   //序列化               
        error:function(request) {      // 设置表单提交出错                 
           
        },
        success:function(data) {
                      
            if(data.success)
             {
            	 me.citys = data.data;
            	 
				 ToolKits.writeMsg("citys:"+data.message);	
             }else{
            	 ToolKits.writeMsg("citys:"+data.message);	
             } 
        }            
  });  
};

/**
 * 
 * @param type 1:name ,2:pinyin
 * @param text
 * @returns {Array}
 */
AreaManage.prototype.searchCitys = function(type , text){
	var retArr = [] ;
	var index = "name";
    if(type == 1){
    	index = "name" ;
    }else if(type == 2){
    	index = "pinyin" ;
    }
    
    retArr.push({'id':'','parent_id':'','name':'全 部'}) ;
	for(var i = 0 ;i< this.citys.length ;i++)
	{
		if(this.citys[i][index].indexOf(text) != -1){
			retArr.push(this.citys[i]) ;
		}
	}
	
	return retArr ;
	
} ;



AreaManage.prototype.loadDistricts = function(){
	var me  = this ;
    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url: g_path+"/area/queryDistricts.do", 
        data: {level_type:3} ,   //序列化               
        error:function(request) {      // 设置表单提交出错                 
           
        },
        success:function(data) {
                      
            if(data.success)
             {
            	 me.districts = data.data;
				 ToolKits.writeMsg("distrits:"+data.message);	
             }else{
            	 ToolKits.writeMsg("distrits:"+data.message);	
             } 
        }            
  });  
};

/**
 * 
 * @param type 1:name,2:pinyin
 * @param pId 
 * @param text
 */
AreaManage.prototype.searchDistricts = function(type,pId ,text){
	var retArr = [] ;
	var index = "name";
    if(type == 1){
    	index = "name" ;
    }else if(type == 2){
    	index = "pinyin" ;
    }
    
    retArr.push({'id':'','parent_id':'','name':'全 部'}) ;
    if(pId == null || pId =="" || pId == undefined){
    	
		for(var i = 0 ;i< this.districts.length ;i++)
		{
			if(this.districts[i][index].indexOf(text) != -1){
				retArr.push(this.districts[i]) ;
			}
		}
	}else{
		for(var i = 0 ;i< this.districts.length ;i++)
		{
			if(this.districts[i]["parent_id"] != pId)
				continue ;
			
			if(this.districts[i][index].indexOf(text) != -1){
				retArr.push(this.districts[i]) ;
			}
		}
	}
   
	return retArr ;
} ;
