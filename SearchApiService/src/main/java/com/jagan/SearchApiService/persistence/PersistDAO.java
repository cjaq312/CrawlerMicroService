package com.jagan.SearchApiService.persistence;

import java.util.List;

import com.jagan.SearchApiService.models.Product;

public interface PersistDAO {
	Product insertProduct(Product p);
	
	Product deleteProduct(String id);

	Product getProduct(String id);

	List<Product> getProducts();
	
	List<Product> executeQuery(String query);
}
