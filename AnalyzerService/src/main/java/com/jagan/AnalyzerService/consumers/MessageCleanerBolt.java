package com.jagan.AnalyzerService.consumers;

import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import com.jagan.AnalyzerService.utils.StringUtils;

import com.jagan.AnalyzerService.config.Configuration;
import com.jagan.AnalyzerService.utils.DataSanitizer;
import com.jagan.AnalyzerService.utils.RecordParser;

public class MessageCleanerBolt implements IRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private List<String> parsedList;

	public void cleanup() {
	}

	public void execute(Tuple tuple) {
		parsedList = RecordParser.parse(tuple.getString(0));

		if (parsedList.size() == 0)
			return;

		this.collector.emit(new Values(
				StringUtils.doubleQuote(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("pid")))),
				StringUtils.doubleQuote(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("name")))),
				StringUtils.doubleQuote(
						DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("longdescription")))),
				StringUtils.doubleQuote(
						DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("smalldescription")))),
				StringUtils.doubleQuote(
						String.valueOf(DataSanitizer.cleanPrice(parsedList.get(Configuration.getMapping("price"))))),
				StringUtils.doubleQuote(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("skuid")))),
				StringUtils.doubleQuote(DataSanitizer.cleanURL(parsedList.get(Configuration.getMapping("url")))),
				StringUtils.doubleQuote(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("color")))),
				StringUtils.doubleQuote(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("size")))),
				StringUtils.doubleQuote(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("primary")))),
				StringUtils
						.doubleQuote(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("secondary")))),
				StringUtils.doubleQuote(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("tertiary")))),
				StringUtils.doubleQuote(
						DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("manufacturer")))),
				StringUtils.doubleQuote(
						DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("retailername")))),
				StringUtils
						.doubleQuote(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("retailerid")))),
				StringUtils
						.doubleQuote(DataSanitizer.cleanImage(parsedList.get(Configuration.getMapping("largeimage")))),
				StringUtils.doubleQuote(
						DataSanitizer.cleanImage(parsedList.get(Configuration.getMapping("smallimage"))))));

		parsedList.clear();
	}

	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
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
