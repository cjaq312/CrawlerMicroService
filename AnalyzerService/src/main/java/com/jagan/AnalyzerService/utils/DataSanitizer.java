package com.jagan.AnalyzerService.utils;

public class DataSanitizer {

	public DataSanitizer() {
	}

	public static String cleanURL(String url) {
		return url;
	}

	public static String cleanImage(String image) {
		return image;
	}

	public static String cleanField(String field) {
		return field;
	}

	public static float cleanPrice(String price) {
		price = price.replaceAll("\"", "");
		float result = 0;
		try {
			result = Float.parseFloat(price);
		} catch (Exception e) {
			return 0;
		}
		return result;
	}
}
