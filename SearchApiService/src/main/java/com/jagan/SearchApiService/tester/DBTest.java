package com.jagan.SearchApiService.tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.SearchApiService.models.Product;
import com.jagan.SearchApiService.persistence.PersistDAO;

public class DBTest {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		PersistDAO cacheDAO = (PersistDAO) context.getBean("cacheDAO");

		System.out.println(cacheDAO.getProduct("test"));
		context.close();

	}

}
