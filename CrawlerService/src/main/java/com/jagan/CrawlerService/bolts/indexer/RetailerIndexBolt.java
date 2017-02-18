package com.jagan.CrawlerService.bolts.indexer;

import java.io.FileReader;
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

import com.jagan.CrawlerService.models.ScrappedUrl;
import com.jagan.CrawlerService.persistence.PersistDAO;
import com.jagan.CrawlerService.utils.RecordParser;

public class RetailerIndexBolt implements IRichBolt {

	private OutputCollector collector;
	private String temp;
	ClassPathXmlApplicationContext applicationcontext;
	PersistDAO cacheDAO;

	private List<String> parsedList;

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		applicationcontext = new ClassPathXmlApplicationContext("applicationContext.xml");
		cacheDAO = (PersistDAO) applicationcontext.getBean("persistDAO");
	}

	public void execute(Tuple tuple) {
		temp = tuple.toString();
		temp = temp.substring(temp.indexOf("[") + 1, temp.lastIndexOf("]"));

		// crawl the data and emit to kafka queue - analyzerQ
		parsedList = RecordParser.parse(temp);

		// check db for visited
		ScrappedUrl scrapped = cacheDAO.getUrl(parsedList.get(1));

		if (scrapped != null && scrapped.isVisited())
			return;

		ScrappedUrl newUrl = new ScrappedUrl();
		newUrl.setUrl(parsedList.get(1));
		newUrl.setUrlType(parsedList.get(2));
		newUrl.setVisited(false);
		newUrl.setRetailerName(parsedList.get(0));

		cacheDAO.insertUrl(newUrl);

		collector.emit(new Values(parsedList.get(0), parsedList.get(1)));

	}

	public void cleanup() {

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("retailerName", "url"));
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
