package com.jagan.DatabaseService.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Configuration {
	public static final HashMap<String, Integer> MAP = new HashMap<String, Integer>();
	public static final Collection<Integer> SKIP_LIST = new ArrayList<Integer>();
	public static final Collection<Integer> EXCLUDE_LIST = new ArrayList<Integer>();
	
	public static final String ZOOKEEPER_ADDRESS= "localhost:2181";
	public static final String BROKER_ADDRESS= "localhost:9092";
	
	public static final String CONSUMER_GROUPID= "ecaConsumer";

	public static final String CONSUMER_TOPIC_NAME = "persistence";
	
	static {
		MAP.put("pid", 0);
		MAP.put("name", 1);
		MAP.put("longdescription", 2);
		MAP.put("smalldescription", 3);
		MAP.put("price", 4);
		MAP.put("skuid", 5);
		MAP.put("url", 6);
		MAP.put("color", 7);
		MAP.put("size", 8);
		MAP.put("primary", 9);
		MAP.put("secondary", 10);
		MAP.put("tertiary", 11);
		MAP.put("manufacturer", 12);
		MAP.put("retailername", 13);
		MAP.put("retailerid", 14);
		MAP.put("largeimage", 15);
		MAP.put("smallimage", 16);

		SKIP_LIST.add(MAP.get("url"));
		SKIP_LIST.add(MAP.get("largeimage"));
		SKIP_LIST.add(MAP.get("smallimage"));
		SKIP_LIST.add(MAP.get("price"));
	}

	public static int getMapping(String key) {
		if (MAP.containsKey(key))
			return MAP.get(key);
		else
			return -1;
	}

}
