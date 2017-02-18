package com.jagan.CrawlerService.spouts;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.xpath.XPathExpressionException;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.CrawlerService.utils.StringUtils;
import com.jagan.CrawlerService.base.BaseUrlsCrawler;
import com.jagan.CrawlerService.config.Configuration;
import com.jagan.CrawlerService.models.ScrappedUrl;
import com.jagan.CrawlerService.persistence.PersistDAO;
import com.jagan.CrawlerService.utils.RecordParser;

public class RetailerUrlsExtractionSpout implements IRichSpout {

	private SpoutOutputCollector collector;
	private TopologyContext context;
	ClassPathXmlApplicationContext applicationcontext;
	PersistDAO cacheDAO;
	FileReader input = null;
	private Properties prop;

	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.context = context;
		this.collector = collector;

		applicationcontext = new ClassPathXmlApplicationContext("applicationContext.xml");
		cacheDAO = (PersistDAO) applicationcontext.getBean("persistDAO");

		try {
			input = new FileReader(new File("src/main/resources/retailers.properties"));
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// index seedurl
		ScrappedUrl seed = new ScrappedUrl();
		seed.setUrl(prop.getProperty("seedUrl"));
		seed.setUrlType("crawl");
		seed.setVisited(false);
		seed.setRetailerName(prop.getProperty("retailerName"));
		cacheDAO.insertUrl(seed);
	}

	public void close() {
	}

	public void activate() {
	}

	public void deactivate() {
	}

	public void nextTuple() {

		// get unvisited crawl list
		List<ScrappedUrl> urls = cacheDAO.getUnvisitedUrls(prop.getProperty("retailerName"), "crawl");

		while (urls.size() > 0) {
			for (ScrappedUrl j : urls) {
				try {
					List<String> crawlUrls = BaseUrlsCrawler.getCrawlUrls(j.getUrl(), prop.getProperty("crawlXpath"));
					List<String> dataUrls = BaseUrlsCrawler.getCrawlUrls(j.getUrl(), prop.getProperty("dataXpath"));

					for (String i : crawlUrls)
						collector.emit(new Values(prop.getProperty("retailerName"), i, "crawl"));
					for (String i : dataUrls)
						collector.emit(new Values(prop.getProperty("retailerName"), i, "data"));

				} catch (XPathExpressionException e) {
					e.printStackTrace();
				}
				cacheDAO.setVisited(j);
			}
			urls = cacheDAO.getUnvisitedUrls(prop.getProperty("retailerName"), "crawl");
		}
	}

	public void ack(Object msgId) {
	}

	public void fail(Object msgId) {
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("retailerName", "url", "urlType"));
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
