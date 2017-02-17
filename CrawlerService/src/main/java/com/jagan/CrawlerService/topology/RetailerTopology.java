package com.jagan.CrawlerService.topology;

import java.util.Properties;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.kafka.bolt.KafkaBolt;
import org.apache.storm.kafka.bolt.mapper.FieldNameBasedTupleToKafkaMapper;
import org.apache.storm.kafka.bolt.selector.DefaultTopicSelector;

import com.jagan.CrawlerService.bolts.extraction.RetailerDataExtractionBolt;
import com.jagan.CrawlerService.bolts.indexer.RetailerIndexBolt;
import com.jagan.CrawlerService.config.Configuration;
import com.jagan.CrawlerService.spouts.RetailerUrlsExtractionSpout;

public class RetailerTopology {

	public static void main(String[] args) {

		// broker properties
		Properties props = new Properties();
		props.put("bootstrap.servers", Configuration.BROKER_ADDRESS);
		props.put("acks", "1");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// kafka persistence bolt
		KafkaBolt dataBolt = new KafkaBolt().withProducerProperties(props)
				.withTopicSelector(new DefaultTopicSelector(Configuration.PRODUCER_ANALYZER_TOPIC_NAME))
				.withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper("dataRecord", "dataRecord"));
		
		
		// topology
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("crawlurl-spout", new RetailerUrlsExtractionSpout());
		builder.setBolt("dataurl-indexer-bolt", new RetailerIndexBolt()).shuffleGrouping("crawlurl-spout");
		builder.setBolt("data-extraction-bolt", new RetailerDataExtractionBolt()).shuffleGrouping("dataurl-indexer-bolt");
		builder.setBolt("data-bolt", dataBolt).shuffleGrouping("data-extraction-bolt");
		

		Config config = new Config();

		LocalCluster cluster = new LocalCluster();

		cluster.submitTopology("CrawlStorm", config, builder.createTopology());

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Stop the topology
		// cluster.killTopology("KafkaConsumerTopology");
		// cluster.shutdown();
	}

}
