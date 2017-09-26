package cn.itcast.utils;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.itcast.service.UserService;

public class FactoryDemo 
{
	
	
	public static Object getBean(String id) throws Exception
	{
		// dom4j
		SAXReader reader = new SAXReader();
		// 读取文件
		Document dm = reader.read(FactoryDemo.class.getClassLoader().getResourceAsStream("beans.xml"));
		Element root = dm.getRootElement();
		List<Element> elements = root.elements();
		Object bean=null;
		for(Element el:elements)
		{
			if(el.attributeValue("id").equals(id))
			{
				Class clazz = Class.forName(el.attributeValue("class"));
				bean = clazz.newInstance();
			}
		}
		
		return bean;
	}
	
	
	
	
	
	
	
	
	
	
	
}
