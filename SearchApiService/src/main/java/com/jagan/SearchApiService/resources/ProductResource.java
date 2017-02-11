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

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.SearchApiService.beanparams.ProductQueryBean;
import com.jagan.SearchApiService.config.Configuration;
import com.jagan.SearchApiService.exceptions.DataNotFoundException;
import com.jagan.SearchApiService.models.Product;
import com.jagan.SearchApiService.persistence.InternalMemory;
import com.jagan.SearchApiService.persistence.PersistDAO;
import com.jagan.SearchApiService.persistence.PersistDAOImpl;
import com.jagan.SearchApiService.utils.QueryGenerator;

@Path("/products")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	PersistDAO db = (PersistDAO) context.getBean("cacheDAO");


	@GET
	@Path("/{productId}")
	public Product getProduct(@PathParam("productId") String productId) {
		Product result =db.getProduct(productId);
		if(result==null)
			throw new DataNotFoundException("Not available in database");
		return result;
	}

	@DELETE
	@Path("/{productId}")
	public Product deleteProduct(@PathParam("productId") String productId) {
		Product result =db.deleteProduct(productId);
		if(result==null)
			throw new DataNotFoundException("Not available in database");
		return result;
	}

	@PUT
	@Path("/{productId}")
	public Product putProduct(Product product, @PathParam("productId") String productId) {
		product.setPid(productId);
		Product result = db.insertProduct(product);
		if(result==null)
			throw new DataNotFoundException("Invalid data");
		return result;
	}

//	@POST
//	public Product postProduct(Product product) {
//		InternalMemory.productMap.put(product.getPid(), product);
//		return InternalMemory.productMap.get(product.getPid());
//	}

	@GET
	public List<Product> getAllProducts(@BeanParam ProductQueryBean bean) {

		String conditionString = "";
		float a = 0;
		Map<String, String> conditions = new HashMap<String, String>();
		if (bean.getPid() != null && !bean.getPid().isEmpty())
			conditions.put("id", bean.getPid());
		if (bean.getpName() != null && !bean.getpName().isEmpty())
			conditions.put("name", bean.getpName());
		if (bean.getRetailer() != null && !bean.getRetailer().isEmpty())
			conditions.put("retailerName", bean.getRetailer());
		if (bean.getManufacturer() != null && !bean.getManufacturer().isEmpty())
			conditions.put("manufacturer", bean.getManufacturer());
		if (bean.getSkuId() != null && !bean.getSkuId().isEmpty())
			conditions.put("skuid", bean.getSkuId());
		if (bean.getCategory() != null && !bean.getCategory().isEmpty()) {
			conditions.put("primarycategory", bean.getCategory());
			conditions.put("secondarycategory", bean.getCategory());
			conditions.put("tertiarycategory", bean.getCategory());
		}
		if (bean.getStartPrice() != null && !bean.getStartPrice().isEmpty()) {
			try {
				a = Float.parseFloat(bean.getStartPrice());
				conditions.put("startprice", bean.getStartPrice());
			} catch (Exception ex) {
				// do nothing
			}
		}
		if (bean.getEndPrice() != null && !bean.getEndPrice().isEmpty()) {
			try {
				a = Float.parseFloat(bean.getEndPrice());
				conditions.put("endprice", bean.getEndPrice());
			} catch (Exception ex) {
				// do nothing
			}
		}

		conditionString = QueryGenerator.generateConditions(conditions, "Product");
		List<Product> result =db.executeQuery(conditionString);
		if(result==null)
			throw new DataNotFoundException("Not available in database");
		else if(result.size()==0)
			throw new DataNotFoundException("Not available in database");
		return result;
	}

}
