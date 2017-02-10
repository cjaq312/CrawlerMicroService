package com.jagan.SearchApiService.models;

public class Category {
	private String primaryCategory = "";
	private String secondaryCategory = "";
	private String tertiaryCategory = "";

	public Category() {
		setPrimaryCategory("");
		setSecondaryCategory("");
		setTertiaryCategory("");
	}

	public Category(String primary) {
		this.primaryCategory = primary;
	}

	public Category(String primary, String secondary) {
		this.primaryCategory = primary;
		this.secondaryCategory = secondary;
	}

	public Category(String primary, String secondary, String tertiary) {
		this.primaryCategory = primary;
		this.secondaryCategory = secondary;
		this.tertiaryCategory = tertiary;
	}

	public String getPrimaryCategory() {
		return primaryCategory;
	}

	public void setPrimaryCategory(String primary) {
		this.primaryCategory = primary;
	}

	public String getSecondaryCategory() {
		return secondaryCategory;
	}

	public void setSecondaryCategory(String secondary) {
		this.secondaryCategory = secondary;
	}

	public String getTertiaryCategory() {
		return tertiaryCategory;
	}

	public void setTertiaryCategory(String tertiary) {
		this.tertiaryCategory = tertiary;
	}
	
	public void reset(){
		setPrimaryCategory("");
		setSecondaryCategory("");
		setTertiaryCategory("");
	}
}
