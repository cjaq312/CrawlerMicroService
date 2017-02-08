package com.jagan.AnalyzerService.consumers;

import java.util.Map;

import org.apache.storm.topology.IRichBolt;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.jagan.AnalyzerService.config.Configuration;
import com.jagan.AnalyzerService.utils.Analyzer;

public class MessageAnalyzerBolt implements IRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private String status;
	String temp;

	public void cleanup() {
	}

	public void execute(Tuple tuple) {
		temp = tuple.toString();
		temp = temp.substring(temp.indexOf("[") + 1, temp.lastIndexOf("]"));

		if (Analyzer.validateTuple(tuple)) {
			this.collector.emit("valid-analyzer-bolt", new Values(temp));
			/*
			 * this.collector.emit("log", new
			 * Values(tuple.getString(Configuration.getMapping("pid")),
			 * tuple.getString(Configuration.getMapping("name")),
			 * tuple.getString(Configuration.getMapping("longdescription")),
			 * tuple.getString(Configuration.getMapping("smalldescription")),
			 * tuple.getFloat(Configuration.getMapping("price")),
			 * tuple.getString(Configuration.getMapping("skuid")),
			 * tuple.getString(Configuration.getMapping("url")),
			 * tuple.getString(Configuration.getMapping("color")),
			 * tuple.getString(Configuration.getMapping("size")),
			 * tuple.getString(Configuration.getMapping("primary")),
			 * tuple.getString(Configuration.getMapping("secondary")),
			 * tuple.getString(Configuration.getMapping("tertiary")),
			 * tuple.getString(Configuration.getMapping("manufacturer")),
			 * tuple.getString(Configuration.getMapping("retailername")),
			 * tuple.getString(Configuration.getMapping("retailerid")),
			 * tuple.getString(Configuration.getMapping("largeimage")),
			 * tuple.getString(Configuration.getMapping("smallimage")),
			 * "Valid"));
			 */
		} else {
			this.status = "InValid";
			this.collector.emit("invalid-analyzer-bolt", new Values(temp));
		}
		this.collector.emit(new Values(temp + ",\"" + status + "\""));

		// reset
		temp = "";

		this.collector.ack(tuple);

	}

	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		this.status = "";
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream("valid-analyzer-bolt", new Fields("analyzedRecord"));
		declarer.declareStream("invalid-analyzer-bolt", new Fields("analyzedRecord"));
		declarer.declare(new Fields("analyzedRecord"));
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
