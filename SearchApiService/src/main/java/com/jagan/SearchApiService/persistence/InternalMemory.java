package com.jagan.SearchApiService.persistence;

import java.util.HashMap;

import com.jagan.SearchApiService.models.Product;

public class InternalMemory {

	public static HashMap<String, Product> productMap = new HashMap<String, Product>();

	static {
		Product p1 = new Product();
		p1.setPid("123");
		p1.setName("p1");
		p1.setColor("red");
		p1.setSize("23");
		Product p2 = new Product();
		p2.setPid("13");
		p2.setName("p2");
		p2.setColor("blue");
		p2.setSize("32");
		productMap.put("123", p1);
		productMap.put("321", p2);
	}

	public Product getProduct(String id) {
		return productMap.get(id);
	}

	public Product putProduct(Product product) {
		productMap.put(product.getPid(), product);
		return productMap.get(product.getPid());
	}

	public Product deleteProduct(Product product) {
		Product result = productMap.get(product.getPid());
		productMap.remove(product.getPid());
		return result;
	}

}
