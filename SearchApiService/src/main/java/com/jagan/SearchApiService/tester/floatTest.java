package com.jagan.SearchApiService.tester;

import java.util.HashMap;

import com.jagan.SearchApiService.utils.QueryGenerator;

public class floatTest {
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startprice", "32");
		map.put("endprice", "34");
		map.put("sdon", "don");
		System.out.println(QueryGenerator.generateConditions(map,"persist"));
	}
}
