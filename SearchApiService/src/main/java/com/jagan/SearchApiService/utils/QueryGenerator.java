package com.jagan.SearchApiService.utils;

import java.util.Map;
import java.util.Map.Entry;

public class QueryGenerator {

	private static StringBuffer result = new StringBuffer();

	public static String generateConditions(Map<String, String> conditionsMap) {
		int count = 1;
		if (result.length() > 0)
			result.delete(0, result.length());
		for (Entry<String, String> i : conditionsMap.entrySet()) {
			if (i.equals("startprice")) {
				result.append("price >= ").append(i.getValue());
			} else if (i.equals("endprice")) {
				result.append("price <= ").append(i.getValue());
			} else {
				result.append(i.getKey()).append(" = ").append(i.getValue());
			}
			if (count < conditionsMap.size())
				result.append(" and ");
			count++;
		}
		return result.toString();
	}

}
