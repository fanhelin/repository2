package com.framework.common;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;






/**
 * 
 * @todo sql tool class ，use to create sql. example ,insert,batchinsert，update.....
 *
 * @author fhr
 * @dateTime 2016 2016-12-9 下午4:36:30
 */
public class SqlHelper {
  
	public static String createInsert(String tableName ,Hashtable<String,Object> fields){
		
        
		StringBuffer fieldStr = new StringBuffer() ;
		StringBuffer valueStr = new StringBuffer() ;
		
	    for (String key : fields.keySet()) {
	    	fieldStr.append(key).append(",") ;
		}
	    if(fieldStr.length()>1){
	    	fieldStr.deleteCharAt(fieldStr.length() -1) ;
	    }
	    
	    for (Object value : fields.values()) {
	    	if(value instanceof String){
	    		value = roundWithQt(value.toString()) ;
	    	}
	    	valueStr.append(value).append(",") ;
		}
	    
	    if(valueStr.length()>1){
	    	valueStr.deleteCharAt(valueStr.length() - 1) ;
	    }
	    String sqlString = "insert into "+tableName +"("  + fieldStr.toString() +") values(" + valueStr.toString() +")" ;
	    
	    
		//System.out.println(sqlString) ;
		
		return sqlString ;
		
	}
	
	/**
	 * 创建更新sql语句
	 * @param tableName 表名
	 * @param updateFields 更新域
	 * @param conditionFields 更新条件
	 * @return 返回sql
	 */
	public static String createUpdateSql(String tableName , Hashtable <String, Object> updateFields , LinkedHashMap <String, Object> conditionFields){
		if(tableName == null || "".equals(tableName) || updateFields.size() == 0 ){
			return "" ;
		}
		
		StringBuffer updateBS = new StringBuffer("update ");
		updateBS.append(tableName).append(" set ") ;
		
		for (String key : updateFields.keySet()) 
		 {
			 updateBS.append(key).append("=");
			 Object value = updateFields.get(key) ;
			 if(value instanceof String){
		    	 value = roundWithQt(value.toString()) ;
		     }
			 updateBS.append(value).append(",") ;
		 }
		
		if(updateBS.length()>1){
			updateBS.deleteCharAt(updateBS.length() - 1) ;
	    }
		
		if(conditionFields.size() >0){
			updateBS.append(" where ") ;
			
			for (String key : conditionFields.keySet()) {
				updateBS.append(key) ;
				
				Object value = conditionFields.get(key) ;
				updateBS.append(value);
			}
		}
		
		return updateBS.toString() ;
	}
	
	/**
	 * 创建更新sql
	 * @param tableName 表名
	 * @param fields 更新字段
	 * @param condition 条件
	 * @return sql语句
	 */
	public static String createUpdateSql(String tableName,Hashtable<String,Object> fields,String condition) {
		StringBuffer fieldSb = new StringBuffer() ;
		 for (String key : fields.keySet()) {
			  Object value = fields.get(key) ;
			  
				if(value instanceof String){
		    		value = roundWithQt(value.toString()) ;
		    	}
			   fieldSb.append(key+"="+value+",") ;
			  
			}
		 
		 if(fieldSb.length()>1){
			 fieldSb.deleteCharAt(fieldSb.length() -1) ;
		  }
		 
		 StringBuffer updateStr= new StringBuffer() ;
		 updateStr.append("update ").append(tableName).append(" set ").append(fieldSb) ;
		 if(!"".equals(condition)){
			 updateStr.append(" where ").append(condition);
		 }
		
		return updateStr.toString();
		
	}
	
	
	/**
	 * 创建批量插入sql
	 * @param tableName 表名
	 * @param list 插入字段列表 链表。
	 * @return sql
	 */
	public static String createBatchInsert(String tableName , List<Hashtable <String, Object>> list){
		if(list.size() <1){
			return "" ;
		}
		StringBuffer insertSb = new StringBuffer();
		StringBuffer selectSb = new StringBuffer();
		insertSb.append("insert into ").append(tableName).append("(") ;
		
		for (int i = 0; i < list.size(); i++) {
			Hashtable<String, Object> tableItem = list.get(i) ;
			if(i == 0){
				 for (String key : tableItem.keySet()) {
					  insertSb.append(key).append(",") ;
					}
				 
				 if(insertSb.length()>1){
					 insertSb.deleteCharAt(insertSb.length() -1) ;
				    }
				 insertSb.append(")");
				 
				 selectSb.append("(select ") ;
				 for (Object value : tableItem.values()) {
					 if(value instanceof String){
				    		value = roundWithQt(value.toString()) ;
				    	}
					  selectSb.append(value).append(",") ;
					}
				 
				 if(selectSb.length()>1){
					 selectSb.deleteCharAt(selectSb.length() -1) ;
				    }
				 selectSb.append(" from dual)") ;
				 
			}else{
				 selectSb.append("union (select ") ;
				 for (Object value : tableItem.values()) {
					 if(value instanceof String){
				    		value = roundWithQt(value.toString()) ;
				    	}
					  selectSb.append(value).append(",") ;
					}
				 
				 if(selectSb.length()>1){
					 selectSb.deleteCharAt(selectSb.length() -1) ;
				    }
				 selectSb.append(" from dual)") ;
			}
			
		}
		
		return insertSb.append(selectSb).toString() ;
	}
	
	public static String roundWith(String input, String roundStr){
	   return roundStr + input +roundStr ;
	}
	

	public static String roundWithQt(String input){
		String roundStr = "'" ;
	   return roundStr + input +roundStr ;
	}
	

	
	public static void main(String[] arg){
		Hashtable<String, Object> params0 = new Hashtable<String, Object>();
		
		Hashtable<String, Object> params = new Hashtable<String, Object>();
		params.put("id",2) ;
		params.put("f1", "v1") ;
		params.put("f2","v2") ;
		params.put("f3", "v3") ;
		params.put("f4", "v4") ;
		
		//params0.putAll(params) ;
		//params.put("f5", "v5") ;
		
		params0.put("id", 3) ;
		params0.put("f1", "v10") ;
		params0.put("f2","v20") ;
		params0.put("f3", "v30") ;
		params0.put("f4", new MultValueType("funName()", null, null)) ;
		
		
		System.out.println(params0.toString()) ;
		System.out.println("sql ==" +createInsert("t_test" ,params )) ;
		List<Hashtable<String, Object>> list = new ArrayList<Hashtable<String,Object>>() ;
		list.add(params0);
		list.add(params) ;
		
		System.out.println("batchsql ==" +createBatchInsert("t_test" ,list )) ;
		
		System.out.println("updateStr ==" +createUpdateSql("t_test", params0, "id=3"));

		
		LinkedHashMap<String, Object> condition = new LinkedHashMap<String, Object>();
		
		condition.put("id", new MultValueType("1", ">", " and ")) ;
		condition.put("f3", new MultValueType("'oldf3'", "=",  "" )) ;
		
		
		System.out.println("new updateStr== "+ createUpdateSql("t_test", params0, condition)) ;
		
		
	}
	
	
}

