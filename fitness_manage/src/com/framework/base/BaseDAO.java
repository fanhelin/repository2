package com.framework.base;



@SuppressWarnings("unchecked")
public abstract class BaseDAO<T>{
	
	protected abstract Class<T> getModelClass();
}
