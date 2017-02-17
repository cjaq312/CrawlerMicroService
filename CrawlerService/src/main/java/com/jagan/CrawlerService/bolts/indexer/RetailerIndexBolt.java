package com.jagan.CrawlerService.bolts.indexer;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

public class RetailerIndexBolt implements IRichBolt {

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

	}

	public void execute(Tuple input) {
		// index emitted data urls as unvisited
		// emit the url if not visited

	}

	public void cleanup() {

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
