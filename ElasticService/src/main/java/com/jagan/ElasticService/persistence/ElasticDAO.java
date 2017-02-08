package com.jagan.ElasticService.persistence;

import java.util.List;

import com.jagan.ElasticService.models.Product;

public interface ElasticDAO {
	void insertProduct(Product p);
	
	void insertProducts(List<Product> products);

	void deleteProduct(String id);
	
	void deleteProducts(List<String>products);

	Product getProduct(String id);

	List<Product> getProducts();
	
	List<Product> executeQuery(String query);
}
