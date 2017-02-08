package com.jagan.AnalyzerService.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.AnalyzerService.cache.CacheDAO;
import com.jagan.AnalyzerService.models.Product;

public class DBTest {
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		CacheDAO dao = (CacheDAO) context.getBean("cacheDAO");

		Product e = new Product();
		e.setPid("119");
		e.setSkuId("114");
		e.getCategories().setPrimaryCategory("prime");
		dao.insertProduct(e);
//		dao.deleteProduct("116");

	}
}