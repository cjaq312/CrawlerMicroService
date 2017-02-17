package com.jagan.CrawlerService.consumers;

import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import com.jagan.CrawlerService.utils.StringUtils;

import com.jagan.CrawlerService.config.Configuration;
import com.jagan.CrawlerService.utils.RecordParser;

public class CrawlUrlBolt implements IRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private List<String> dataURLList;

	public void cleanup() {
	}

	public void execute(Tuple tuple) {
			}

	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(""));
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
