package com.jagan.SearchApiService.persistence;

import java.util.List;

import com.jagan.SearchApiService.models.Product;

public interface PersistDAO {

	public Product getProduct(String id);

	public List<Product> getAllProducts();

	public Product deleteProduct(String id);

	public Product putProduct(Product product);

	public Product postProduct(Product product);

}
