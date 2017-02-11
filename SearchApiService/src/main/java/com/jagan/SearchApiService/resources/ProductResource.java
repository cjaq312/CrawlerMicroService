package com.jagan.SearchApiService.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.jagan.SearchApiService.beanparams.ProductQueryBean;
import com.jagan.SearchApiService.models.Product;
import com.jagan.SearchApiService.persistence.InternalMemory;
import com.jagan.SearchApiService.utils.QueryGenerator;

@Path("/products")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
	public InternalMemory db;

	@GET
	@Path("/{productId}")
	public Product getProduct(@PathParam("productId") String productId) {
		return InternalMemory.productMap.get(productId);
	}

	@DELETE
	@Path("/{productId}")
	public Product deleteProduct(@PathParam("productId") String productId) {
		return InternalMemory.productMap.remove(productId);
	}

	@PUT
	@Path("/{productId}")
	public Product putProduct(Product product, @PathParam("productId") String productId) {
		product.setPid(productId);
		InternalMemory.productMap.put(productId, product);
		return InternalMemory.productMap.get(product.getPid());
	}

	@POST
	public Product postProduct(Product product) {
		InternalMemory.productMap.put(product.getPid(), product);
		return InternalMemory.productMap.get(product.getPid());
	}

	@GET
	public List<Product> getAllProducts(@BeanParam ProductQueryBean bean) {
		List<Product> list = new ArrayList<Product>();
		for (Entry<String, Product> i : db.productMap.entrySet())
			list.add(i.getValue());

		String conditionString = "";
		float a = 0;
		Map<String, String> conditions = new HashMap<String, String>();
		if (bean != null || !bean.getPid().isEmpty())
			conditions.put("id", bean.getPid());
		if (bean != null || !bean.getpName().isEmpty())
			conditions.put("name", bean.getpName());
		if (bean != null || !bean.getRetailer().isEmpty())
			conditions.put("retailerName", bean.getRetailer());
		if (bean != null || !bean.getManufacturer().isEmpty())
			conditions.put("manufacturer", bean.getManufacturer());
		if (bean != null || !bean.getSkuId().isEmpty())
			conditions.put("skuid", bean.getSkuId());
		if (bean != null || !bean.getCategory().isEmpty()) {
			conditions.put("primarycategory", bean.getCategory());
			conditions.put("secondarycategory", bean.getCategory());
			conditions.put("tertiarycategory", bean.getCategory());
		}
		if (bean != null || !bean.getStartPrice().isEmpty()) {
			try {
				a = Float.parseFloat(bean.getStartPrice());
				conditions.put("startprice", bean.getStartPrice());
			} catch (Exception ex) {
				// do nothing
			}
		}
		if (bean != null || !bean.getEndPrice().isEmpty()) {
			try {
				a = Float.parseFloat(bean.getEndPrice());
				conditions.put("endprice", bean.getEndPrice());
			} catch (Exception ex) {
				// do nothing
			}
		}
		if (conditions.size() > 0)
			conditionString = QueryGenerator.generateConditions(conditions);

		// db query and return the result below

		return list;
	}

}
