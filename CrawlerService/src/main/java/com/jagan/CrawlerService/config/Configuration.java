package com.jagan.CrawlerService.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Configuration {
	public static final HashMap<String, Integer> PRODUCTMAP = new HashMap<String, Integer>();
	public static final HashMap<String, Integer> URLMAP = new HashMap<String, Integer>();

	public static final String ZOOKEEPER_ADDRESS = "localhost:2181";
	public static final String BROKER_ADDRESS = "localhost:9092";

	public static final String PRODUCER_ANALYZER_TOPIC_NAME = "dataAnalyzer";

	static {
		PRODUCTMAP.put("pid", 0);
		PRODUCTMAP.put("name", 1);
		PRODUCTMAP.put("longdescription", 2);
		PRODUCTMAP.put("smalldescription", 3);
		PRODUCTMAP.put("price", 4);
		PRODUCTMAP.put("skuid", 5);
		PRODUCTMAP.put("url", 6);
		PRODUCTMAP.put("color", 7);
		PRODUCTMAP.put("size", 8);
		PRODUCTMAP.put("primary", 9);
		PRODUCTMAP.put("secondary", 10);
		PRODUCTMAP.put("tertiary", 11);
		PRODUCTMAP.put("manufacturer", 12);
		PRODUCTMAP.put("retailername", 13);
		PRODUCTMAP.put("retailerid", 14);
		PRODUCTMAP.put("largeimage", 15);
		PRODUCTMAP.put("smallimage", 16);

		URLMAP.put("url", 0);
		URLMAP.put("urltype", 1);
		URLMAP.put("retailerName", 2);
		URLMAP.put("visited", 3);
	}

	public static int getProductMapping(String key) {
		if (PRODUCTMAP.containsKey(key))
			return PRODUCTMAP.get(key);
		else
			return -1;
	}
	
	public static int getUrlMapping(String key) {
		if (URLMAP.containsKey(key))
			return URLMAP.get(key);
		else
			return -1;
	}

}
