package com.jagan.AnalyzerService.topology;

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

import com.jagan.AnalyzerService.config.Configuration;
import com.jagan.AnalyzerService.consumers.MessageAnalyzerBolt;
import com.jagan.AnalyzerService.consumers.MessageCleanerBolt;

public class EatCleanAnalyzeQueueTopology {

	public static void main(String[] args) {

		// spout configuration
		ZkHosts zkHosts = new ZkHosts(Configuration.ZOOKEEPER_ADDRESS);
		String topic_name = Configuration.CONSUMER_TOPIC_NAME;
		String consumer_group_id = "ecaConsumer";
		String zookeeper_root = "";
		SpoutConfig kafkaConfig = new SpoutConfig(zkHosts, topic_name, zookeeper_root, consumer_group_id);
		kafkaConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

		// broker properties
		Properties props = new Properties();
		props.put("bootstrap.servers", Configuration.BROKER_ADDRESS);
		props.put("acks", "1");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// kafka elastic-bolt configuration
		KafkaBolt elasticBolt = new KafkaBolt().withProducerProperties(props)
				.withTopicSelector(new DefaultTopicSelector(Configuration.PRODUCER_ELASTIC_TOPIC_NAME))
				.withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper("analyzedRecord", "analyzedRecord"));
		// kafka persistence bolt
		KafkaBolt persistenceBolt = new KafkaBolt().withProducerProperties(props)
				.withTopicSelector(new DefaultTopicSelector(Configuration.PRODUCER_PERSISTENCE_TOPIC_NAME))
				.withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper("analyzedRecord", "analyzedRecord"));
		
		// kafka log bolt
				KafkaBolt statsBolt = new KafkaBolt().withProducerProperties(props)
						.withTopicSelector(new DefaultTopicSelector(Configuration.PRODUCER_STATS_TOPIC_NAME))
						.withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper("analyzedRecord", "analyzedRecord"));

		// topology
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("consumer-spout", new KafkaSpout(kafkaConfig), 1);
		builder.setBolt("cleaner-bolt", new MessageCleanerBolt()).shuffleGrouping("consumer-spout");
		builder.setBolt("analyzer-bolt", new MessageAnalyzerBolt()).shuffleGrouping("cleaner-bolt");
		builder.setBolt("elastic-bolt", elasticBolt).shuffleGrouping("analyzer-bolt", "valid-analyzer-bolt");
		builder.setBolt("persistence-bolt", persistenceBolt).shuffleGrouping("analyzer-bolt","valid-analyzer-bolt");
		builder.setBolt("stats-bolt", statsBolt).shuffleGrouping("analyzer-bolt");

		Config config = new Config();

		LocalCluster cluster = new LocalCluster();

		cluster.submitTopology("AnalyzerStorm", config, builder.createTopology());

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
