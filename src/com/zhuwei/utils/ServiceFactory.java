package com.zhuwei.utils;

import java.awt.print.PrinterAbortException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import com.zhuwei.domain.Privilege;
import com.zhuwei.domain.User;
import com.zhuwei.exception.PrivilegeException;
import com.zhuwei.service.BusinessService;


public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();
	
	private ServiceFactory(){}
	
	public static ServiceFactory getServiceFactory(){
		return instance;
	}
	
	
	//BusinessServiceProxy
	@SuppressWarnings("unchecked")
	public <T> T CreatedService(String className, Class<T> clazz, final User user){
		
		try {
			final T t = (T) Class.forName(className).newInstance();
			
			return (T) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), t.getClass().getInterfaces(), new InvocationHandler() {
				
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					
					//1.判断用户调用的是什么方法
					String methodName = method.getName();
					
					//2.反射出真实对象相应的方法
					Method m = t.getClass().getMethod(methodName, method.getParameterTypes());
					
					//3.看真实对象相应方法是否有权限注解
					Permission p = m.getAnnotation(Permission.class);
					
					//4.如果没有，代表该方法不需要权限， 直接放行
					if(p == null){
						return method.invoke(t, args);
					}
					
					//5.如果有，代表该方法需要权限，检查用户是否有权限
					String name = p.value();
					Privilege privilege = new Privilege();
					privilege.setName(name);
					
					//6.检查用户是否有权限,如果有，放行
					if(user == null){
						throw new PrivilegeException("Sorry, Login first");
					}
					
					BusinessService service = (BusinessService) t;
					List<Privilege> list = service.getUserPrivileges(user.getId());
					
					if(list.contains(privilege)){
						return method.invoke(t, args);
					}
					
					//7.如果没有，抛运行是异常给用户以友好的界面
					throw new PrivilegeException("Sorry, you have no privilege to operate");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
}
