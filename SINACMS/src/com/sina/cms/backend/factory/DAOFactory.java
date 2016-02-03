package com.sina.cms.backend.factory;

import java.lang.reflect.Method;

import com.sina.cms.utils.StringUtils;

public class DAOFactory {
	
	/*
	 * 参数为articleDao之类的
	 */
	public static Object getDaoByVarName(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		return SpringContext.getContext().getBean(name);
	}
	
	/*
	 * 为参数为setArticleDao之类的方法设上dao对象
	 */
	public static void fillDaoBySetMethod(Method method, Object instance) {
		if (method == null || instance == null) {
			return ;
		}
		if (method.getName().startsWith("set")) {
			String methodName = method.getName().substring(3);
			StringBuilder sb = new StringBuilder(methodName);
			sb.replace(0, 1, (sb.charAt(0) + "").toLowerCase());
			Object daoBean = DAOFactory.getDaoByVarName(sb.toString());
			try {
				method.invoke(instance, daoBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
