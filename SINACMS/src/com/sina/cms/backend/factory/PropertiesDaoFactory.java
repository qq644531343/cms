package com.sina.cms.backend.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Properties;


public class PropertiesDaoFactory implements BeanFactory {
	
	private Map<String, Object> beans = new HashMap<String, Object>();
	
	public PropertiesDaoFactory() {
		this("dao.properties");
	}
	
	public PropertiesDaoFactory(String configLocation){
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(configLocation));
			Set set = properties.entrySet();
			for (Iterator iteratorx = set.iterator(); iteratorx.hasNext();) {
				Map.Entry entry = (Map.Entry) iteratorx.next();
				String key = (String) entry.getKey();
				String value = (String)entry.getValue();
				Object obj = Class.forName(value).newInstance();
				beans.put(key, obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public Object getBean(String name) {
		return beans.get(name);
	}

}
