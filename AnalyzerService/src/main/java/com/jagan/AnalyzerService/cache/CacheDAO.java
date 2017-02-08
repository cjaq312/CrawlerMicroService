package com.jagan.AnalyzerService.cache;

import java.util.List;

import com.jagan.AnalyzerService.models.Product;

public interface CacheDAO {
	void insertProduct(Product p);
	
	void insertProducts(List<Product> products);

	void deleteProduct(String id);
	
	void deleteProducts(List<String>products);

	Product getProduct(String id);

	List<Product> getProducts();
	
	List<Product> executeQuery(String query);
}
