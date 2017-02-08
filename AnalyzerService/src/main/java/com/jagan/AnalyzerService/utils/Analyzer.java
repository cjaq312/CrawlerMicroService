package com.jagan.AnalyzerService.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.storm.tuple.Tuple;

public class Analyzer {
	List<String> fieldsList;
	private static HashMap<String, Integer> map = new HashMap<String, Integer>();
	private static Collection<Integer> skipList = new ArrayList<Integer>();
	private static Collection<Integer> excludeList = new ArrayList<Integer>();

	static {
		map.put("price", 4);
		map.put("url", 6);
		map.put("largeimage", 15);
		map.put("smallimage", 16);
		skipList = map.values();
	}

	public Analyzer() {

	}

	public static boolean validateTuple(Tuple tuple) {
		for (int i = 0; i < tuple.size(); i++) {
			if (skipList.contains(i) || excludeList.contains(i))
				continue;
			if (!validateField(tuple.getString(i)))
				return false;
		}

		if (validateImageUrl(tuple.getString(map.get("largeimage"))) && validateURL(tuple.getString(map.get("url")))
				&& validateImageUrl(tuple.getString(map.get("smallimage")))
				&& validatePrice(tuple.getFloat(map.get("price"))))
			return true;
		return false;
	}

	public static boolean validateField(String field) {
		if (isFieldEmpty(field))
			return false;
		return true;
	}

	public static boolean isFieldEmpty(String field) {
		return field.replaceAll("\"", "").length() == 0;
	}

	public static boolean validateURL(String url) {
		if (isFieldEmpty(url))
			return false;
		return true;
	}

	public static boolean validatePrice(float price) {
		return price > 0.0;
	}

	public static boolean validateImageUrl(String image) {
		if (isFieldEmpty(image))
			return false;
		return true;
	}
}
