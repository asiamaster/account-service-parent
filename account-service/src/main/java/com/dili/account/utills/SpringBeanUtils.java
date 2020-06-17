package com.dili.account.utills;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

/**
 * @describe Spring ApplicationContext 获取spring容器中的实例bean
 * @version 1.0.0
 */
public class SpringBeanUtils implements ApplicationContextAware {
	/**
	 * Spring 应用上下文环境
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法,设置上下文环境
	 *
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtils.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象
	 *
	 * @return Object bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String beanName) throws BeansException {
		if (!SpringBeanUtils.containsBean(beanName)) {
			return null;
		} else {
			return applicationContext.getBean(beanName);
		}
	}

	/**
	 * 获取指定类的第一个bean
	 *
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getFirstBeanOfType(Class beanClass) {
		String[] beanNames = applicationContext.getBeanNamesForType(beanClass);
		if (beanNames.length == 0) {
			return null;
		}

		return getBean(beanNames[0]);
	}

	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 *
	 * @param name
	 *            bean注册名
	 * @param requiredType
	 *            返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getBean(String name, Class requiredType)
			throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义,则返回true
	 *
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到,将会抛出一个异常（NoSuchBeanDefinitionException）
	 *
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	@SuppressWarnings("rawtypes")
	public static Class getType(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名,则返回这些别名
	 *
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}


	public static <T> T getBean(Class<T> tClass){

		return applicationContext.getBean(tClass);
	}


	/**
	 * @Author miaoguoxin
	 * @Description 获取配置信息
	 * @Date  2019/7/23
	 * @Param [key]
	 * @return java.lang.String
	 **/
	public static String getProperty(String key){

		return applicationContext.getEnvironment().getProperty(key);
	}

	/**
	* @Description: 发布事件
	* @Param:
	* @return:
	* @Author: miaoguoxin
	* @Date: 2019/10/30
	*/
	public static void publish(ApplicationEvent event){
		applicationContext.publishEvent(event);
	}
	
	/**
	 * 获取制定接口实现类
	 */
	public static <T> List<T> getInterfaceBeansOfType(Class<T> class1){
		return (List<T>) applicationContext.getBeansOfType(class1).values();
	}
}
