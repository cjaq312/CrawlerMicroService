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

	@Override
	public void cleanup() {
	}

	@Override
	public void execute(Tuple tuple) {
		parsedList = RecordParser.parse(tuple.getString(0));

		if (parsedList.size() == 0)
			return;
		
		this.collector.emit(new Values(DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("pid"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("name"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("longdescription"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("smalldescription"))),
				DataSanitizer.cleanPrice(parsedList.get(Configuration.getMapping("price"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("skuid"))),
				DataSanitizer.cleanURL(parsedList.get(Configuration.getMapping("url"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("color"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("size"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("primary"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("secondary"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("tertiary"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("manufacturer"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("retailername"))),
				DataSanitizer.cleanField(parsedList.get(Configuration.getMapping("retailerid"))),
				DataSanitizer.cleanImage(parsedList.get(Configuration.getMapping("largeimage"))),
				DataSanitizer.cleanImage(parsedList.get(Configuration.getMapping("smallimage")))));
		
		parsedList.clear();
	}

	@Override
	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("pid", "name", "longdescription", "smalldescription", "price", "skuid", "url",
				"color", "size", "primary", "secondary", "tertiary", "manufacturer", "retailername", "retailerid",
				"largeimage", "smallimage"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
