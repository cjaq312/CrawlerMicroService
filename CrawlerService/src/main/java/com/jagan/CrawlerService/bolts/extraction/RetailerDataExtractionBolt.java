package com.jagan.CrawlerService.bolts.extraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.CrawlerService.base.BaseWebElementsCrawler;
import com.jagan.CrawlerService.models.Product;
import com.jagan.CrawlerService.models.ScrappedUrl;
import com.jagan.CrawlerService.persistence.PersistDAO;
import com.jagan.CrawlerService.utils.RecordParser;

public class RetailerDataExtractionBolt implements IRichBolt {

	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private String temp;
	FileReader input = null;
	ClassPathXmlApplicationContext applicationcontext;
	PersistDAO cacheDAO;


	private Properties prop;
	private List<String> parsedList;

	public void cleanup() {
	}

	public void execute(Tuple tuple) {
		temp = tuple.toString();
		temp = temp.substring(temp.indexOf("[") + 1, temp.lastIndexOf("]"));

		// crawl the data and emit to kafka queue - analyzerQ
		parsedList = RecordParser.parse(temp);

		try {
			input = new FileReader(new File("src/main/resources/"+parsedList.get(0)+".properties"));
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Product product = BaseWebElementsCrawler.loadUrl(parsedList.get(1), prop);

		this.collector.emit(new Values(product.getPid(), product.getName(), product.getLongDescription(),
				product.getSmallDescription(), product.getPrice(), product.getSkuId(), product.getUrl(),
				product.getColor(), product.getSize(), product.getCategories().getPrimaryCategory(),
				product.getCategories().getSecondaryCategory(), product.getCategories().getTertiaryCategory(),
				product.getManufacturer(), product.getRetailerName(), product.getRetailerId(), product.getLargeImage(),
				product.getSmallImage()));
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		prop = new Properties();
		applicationcontext = new ClassPathXmlApplicationContext("applicationContext.xml");
		cacheDAO = (PersistDAO) applicationcontext.getBean("persistDAO");
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("pid", "name", "longdescription", "smalldescription", "price", "skuid", "url",
				"color", "size", "primary", "secondary", "tertiary", "manufacturer", "retailername", "retailerid",
				"largeimage", "smallimage"));
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
