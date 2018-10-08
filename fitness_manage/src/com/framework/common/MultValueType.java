package com.framework.common;
/**
 * 
 * @todo 复合数据类型 
 *
 * @author fhr
 * @dateTime 2016 2016-12-19 下午4:03:09
 */
public class MultValueType {
	public MultValueType(Object value, String pre,String suf){
		this.value = value ;
		this.prefixion = pre ;
		this.suffix = suf ;
	}
  	public Object value ;
  	public String prefixion ;
  	public String suffix ;
	@Override
	public String toString() {
		String tmpString="" ;
		if(prefixion != null){
			tmpString = prefixion + this.value ;
		}else{
			tmpString = this.value.toString() ;
		}
		
		if(suffix != null){
			tmpString += suffix  ;
		}
		
		return tmpString ;
				
	}
  	
}
