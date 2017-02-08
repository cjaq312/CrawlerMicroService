package com.jagan.DatabaseService.consumers;

import java.util.List;
import java.util.Map;

import org.apache.storm.topology.IRichBolt;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.DatabaseService.config.Configuration;
import com.jagan.DatabaseService.models.Product;
import com.jagan.DatabaseService.persistence.PersistDAO;
import com.jagan.DatabaseService.utils.RecordParser;

public class MessagePersistenceBolt implements IRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private ClassPathXmlApplicationContext xmlcontext;
	private PersistDAO cache;
	private Product product;
	private List<String> parsedList;
	private String temp;

	public void cleanup() {
		this.xmlcontext.close();
	}

	public void execute(Tuple tuple) {
		temp = tuple.toString();
		temp = temp.substring(temp.indexOf("[") + 1, temp.lastIndexOf("]"));

		// ***************temp block
		if (temp.contains("]"))
			temp = temp.substring(temp.indexOf("[") + 1, temp.lastIndexOf("]"));
		else if (temp.contains("["))
			temp = temp.replace("[", "");
		System.out.println(tuple.toString());

		parsedList = RecordParser.parse(temp);

		product.setPid(parsedList.get(Configuration.getMapping("pid")));
		product.setName(parsedList.get(Configuration.getMapping("name")));
		product.setLongDescription(parsedList.get(Configuration.getMapping("longdescription")));
		product.setSmallDescription(parsedList.get(Configuration.getMapping("smalldescription")));
		product.setPrice(Float.parseFloat(parsedList.get(Configuration.getMapping("price"))));
		product.setSkuId(parsedList.get(Configuration.getMapping("skuid")));
		product.setUrl(parsedList.get(Configuration.getMapping("url")));
		product.setColor(parsedList.get(Configuration.getMapping("color")));
		product.setSize(parsedList.get(Configuration.getMapping("size")));
		product.getCategories().setPrimaryCategory(parsedList.get(Configuration.getMapping("primary")));
		product.getCategories().setSecondaryCategory(parsedList.get(Configuration.getMapping("secondary")));
		product.getCategories().setTertiaryCategory(parsedList.get(Configuration.getMapping("tertiary")));
		product.setManufacturer(parsedList.get(Configuration.getMapping("manufacturer")));
		product.setRetailerName(parsedList.get(Configuration.getMapping("retailername")));
		product.setRetailerId(parsedList.get(Configuration.getMapping("retailerid")));
		product.setLargeImage(parsedList.get(Configuration.getMapping("largeimage")));
		product.setSmallImage(parsedList.get(Configuration.getMapping("smallimage")));

		cache.insertProduct(product);

		// reset
		product.reset();
		temp = "";

		this.collector.ack(tuple);

	}

	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		this.xmlcontext = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.cache = (PersistDAO) xmlcontext.getBean("cacheDAO");
		this.product = new Product();
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
