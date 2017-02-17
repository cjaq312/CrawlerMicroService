package com.jagan.CrawlerService.spouts;

import java.util.List;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import com.jagan.CrawlerService.utils.StringUtils;

import com.jagan.CrawlerService.config.Configuration;
import com.jagan.CrawlerService.utils.RecordParser;

public class RetailerUrlsExtractionSpout implements IRichSpout {

	private SpoutOutputCollector collector;
	private TopologyContext context;

	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.context = context;
		this.collector = collector;
		// create object to crawler that indexes seedurl
	}

	public void close() {
	}

	public void activate() {
	}

	public void deactivate() {
	}

	public void nextTuple() {
		// get unvisited crawl list
		// crawl t o get more crawl urls
		// index in database
		// when found data url emit to indexer

	}

	public void ack(Object msgId) {
	}

	public void fail(Object msgId) {
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("retailerName", "url"));
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
