package com.sina.cms.backend.factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Properties;


public class PropertiesDaoFactory implements BeanFactory {
	
	private Map<String, Object> beans = new HashMap<String, Object>();
	private static PropertiesDaoFactory instance = new PropertiesDaoFactory();
	
	private PropertiesDaoFactory() {
		
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dao.properties"));
			Set set = properties.entrySet();
			for (Iterator iteratorx = set.iterator(); iteratorx.hasNext();) {
				Map.Entry entry = (Map.Entry) iteratorx.next();
				String key = (String) entry.getKey();
				String value = (String)entry.getValue();
				System.out.println("key:" + key + ", value:" + value);
				Object obj = Class.forName(value).newInstance();
				beans.put(key, obj);
				System.out.println(key +" " + value + " " + obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static PropertiesDaoFactory getInstance() {
		return instance;
	}

	@Override
	public Object getBean(String name) {
		return beans.get(name);
	}

}
