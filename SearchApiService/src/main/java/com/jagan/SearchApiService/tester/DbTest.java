package com.jagan.SearchApiService.tester;

import com.jagan.SearchApiService.models.Product;
import com.jagan.SearchApiService.persistence.InternalMemory;

public class DbTest {
	public static void main(String[] args) {
		System.out.println(InternalMemory.productMap);

		InternalMemory.productMap.put("345", new Product());
		System.out.println(InternalMemory.productMap);
	}
}
