package com.zhuwei.utils;



public class Daofactory {
	private static final Daofactory factory = new Daofactory();
	
	private Daofactory(){}
	
	public static Daofactory getInstance(){
		return factory;
	}
	
	public <T> T createDao(String className, Class<T> clazz){
		try {
			T t = (T) Class.forName(className).newInstance();
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	
	
	
	
}
