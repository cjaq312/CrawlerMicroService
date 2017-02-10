package com.jagan.AnalyzerService.models;

import com.jagan.AnalyzerService.models.Category;

import scala.Serializable;

public class Product {

	private String pid;
	private String name;
	private String longDescription;
	private String smallDescription;
	private float price;
	private String skuId;
	private String url;
	private String color;
	private String size;
	private String retailerName;
	private String retailerId;
	private String manufacturer;
	private String largeImage;
	private String smallImage;
	private Category categories;

	public Product() {
		setPid("");
		setName("");
		setLongDescription("");
		setSmallDescription("");
		setPrice(0);
		setSkuId("");
		setUrl("");
		setColor("");
		setSize("");
		setRetailerId("");
		setRetailerName("");
		setManufacturer("");
		setLargeImage("");
		setSmallImage("");
		setCategories(new Category());
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String id) {
		this.pid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getSmallDescription() {
		return smallDescription;
	}

	public void setSmallDescription(String smallDescription) {
		this.smallDescription = smallDescription;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Category getCategories() {
		return categories;
	}

	public void setCategories(Category categories) {
		this.categories = categories;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public void reset(){
		setPid("");
		setName("");
		setLongDescription("");
		setSmallDescription("");
		setPrice(0);
		setSkuId("");
		setUrl("");
		setColor("");
		setSize("");
		setRetailerId("");
		setRetailerName("");
		setManufacturer("");
		setLargeImage("");
		setSmallImage("");
		categories.reset();
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\"").append(getPid()).append("\",\"").append(getName()).append("\",\"")
				.append(getLongDescription()).append("\",\"").append(getSmallDescription()).append("\",\"")
				.append(getPrice()).append("\",\"").append(getSkuId()).append("\",\"").append(getUrl()).append("\",\"")
				.append(getColor()).append("\",\"").append(getSize()).append("\",\"")
				.append(getCategories().getPrimaryCategory()).append("\",\"")
				.append(getCategories().getSecondaryCategory()).append("\",\"")
				.append(getCategories().getTertiaryCategory()).append("\",\"").append(getManufacturer()).append("\",\"")
				.append(getRetailerName()).append("\",\"").append(getRetailerId()).append("\",\"")
				.append(getLargeImage()).append("\",\"").append(getSmallImage()).append("\"");

		return buffer.toString();

	}

}
