package com.jagan.SearchApiService.beanparams;

import javax.ws.rs.QueryParam;

public class ProductQueryBean {
	
	private @QueryParam("pid") String pid;
	private @QueryParam("productname") String pName;
	private @QueryParam("category") String category;
	private @QueryParam("startprice") String startPrice;
	private @QueryParam("endprice") String endPrice;
	private @QueryParam("retailer") String retailer;
	private @QueryParam("manufacturer") String manufacturer;
	private @QueryParam("skuid") String skuId;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}
	public String getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	
	

}
