
function Organization(id,code,name ,parentId ,createDateTime,remark){
  this.ID = id ;
  this.code = code ;
  this.name = name ;
  this.parentId = parentId ;
  this.creater = createDateTime;
  this.createDateTime ;
  this.remark ;
  
  this.children = [] ;
  
};

Organization.prototype.addChild = function(org){
	
	if( this.isChild(org)){
		return false;
	}
	
	this.children.push(org) ;
	return true ;

};


Organization.prototype.isChild = function(org){
	
	if(org.parentId != this.ID){
		return false;
	}
	
	for(var i = 0 ;i< this.children.length ;i++){
		
		if(org.ID == this.children[i].ID){
			return true ;
		}
	}
	
	return false ;

};


/**
 * 遍历整个结构
 * @param onEach
 */
Organization.prototype.foreach = function(onEach){
	
	if( typeof onEach != 'function' ){
		return ;
	}
	
	for(var i = 0 ;i< this.children.length ;i++)
	{
		if(this.hashChild()){
			this.foreach(onEach) ;
		}else{
			onEach(this.children[i]) ;
		}
	}
	
};


Organization.prototype.hashChild = function(){
	return this.children.length >0 ;

};





