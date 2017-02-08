package com.jagan.DatabaseService.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.DatabaseService.models.Product;
import com.jagan.DatabaseService.persistence.PersistDAO;

public class DBTest {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		PersistDAO cacheDAO = (PersistDAO) context.getBean("cacheDAO");

		Product product = new Product();

		product.setPid("jagan");
		cacheDAO.insertProduct(product);

		context.close();

	}
}
