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
					
					//1.�ж��û����õ���ʲô����
					String methodName = method.getName();
					
					//2.�������ʵ������Ӧ�ķ���
					Method m = t.getClass().getMethod(methodName, method.getParameterTypes());
					
					//3.����ʵ������Ӧ�����Ƿ���Ȩ��ע��
					Permission p = m.getAnnotation(Permission.class);
					
					//4.���û�У�����÷�������ҪȨ�ޣ� ֱ�ӷ���
					if(p == null){
						return method.invoke(t, args);
					}
					
					//5.����У�����÷�����ҪȨ�ޣ�����û��Ƿ���Ȩ��
					String name = p.value();
					Privilege privilege = new Privilege();
					privilege.setName(name);
					
					//6.����û��Ƿ���Ȩ��,����У�����
					if(user == null){
						throw new PrivilegeException("Sorry, Login first");
					}
					
					BusinessService service = (BusinessService) t;
					List<Privilege> list = service.getUserPrivileges(user.getId());
					
					if(list.contains(privilege)){
						return method.invoke(t, args);
					}
					
					//7.���û�У����������쳣���û����ѺõĽ���
					throw new PrivilegeException("Sorry, you have no privilege to operate");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
}
