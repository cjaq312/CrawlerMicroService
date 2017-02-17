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

import com.jagan.CrawlerService.config.Configuration;
import com.jagan.CrawlerService.consumers.CrawlUrlBolt;

public class CrawlerTopology {

	public static void main(String[] args) {

		// broker properties
		Properties props = new Properties();
		props.put("bootstrap.servers", Configuration.BROKER_ADDRESS);
		props.put("acks", "1");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

//		KafkaSpout crawlUrlSpout = new KafkaSpout().withProducerProperties(props)
//				.withTopicSelector(new DefaultTopicSelector(Configuration.PRODUCER_ANALYZER_TOPIC_NAME))
//				.withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper("analyzedRecord", "analyzedRecord"));
		
		
		// kafka persistence bolt
		KafkaBolt dataUrlBolt = new KafkaBolt().withProducerProperties(props)
				.withTopicSelector(new DefaultTopicSelector(Configuration.PRODUCER_ANALYZER_TOPIC_NAME))
				.withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper("crawlURL", "crawlURL"));
		
		
		// topology
		TopologyBuilder builder = new TopologyBuilder();
//		builder.setSpout("crawlurl-spout", new KafkaSpout(kafkaConfig), 1);
		builder.setBolt("dataurl-bolt", dataUrlBolt).shuffleGrouping("crawlurl-spout");
		

		Config config = new Config();

		LocalCluster cluster = new LocalCluster();

		cluster.submitTopology("CrawlURLStorm", config, builder.createTopology());

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
