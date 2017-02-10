package com.jagan.SearchApiService.tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.SearchApiService.models.Category;
import com.jagan.SearchApiService.models.Product;
import com.jagan.SearchApiService.persistence.PersistDAO;

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
