package com.jagan.DatabaseService.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.DatabaseService.models.Category;
import com.jagan.DatabaseService.models.Product;
import com.jagan.DatabaseService.persistence.PersistDAO;

public class DBTest {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		PersistDAO cacheDAO = (PersistDAO) context.getBean("cacheDAO");

		Product product = new Product();
		product.setPid("test2");
		Category cat = new Category();
		product.setCategories(cat);
		
		product.getCategories().setPrimaryCategory("Prime");

//		 cacheDAO.insertProduct(product);
//		for (Product i : cacheDAO.getProducts())
//			System.out.println(i.toString());

		System.out.println(cacheDAO.getProduct("test"));
		context.close();

	}
}
