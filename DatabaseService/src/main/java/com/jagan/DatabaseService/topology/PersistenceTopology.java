package com.jagan.DatabaseService.topology;

import java.util.Properties;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

import com.jagan.DatabaseService.config.Configuration;
import com.jagan.DatabaseService.consumers.MessagePersistenceBolt;


public class PersistenceTopology {
	public static void main(String[] args) {

		// spout configuration
		ZkHosts zkHosts = new ZkHosts(Configuration.ZOOKEEPER_ADDRESS);
		String topic_name = Configuration.CONSUMER_TOPIC_NAME;
		String consumer_group_id = "persistenceConsumer";
		String zookeeper_root = "";
		SpoutConfig kafkaConfig = new SpoutConfig(zkHosts, topic_name, zookeeper_root, consumer_group_id);
		kafkaConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

		// broker properties
		Properties props = new Properties();
		props.put("bootstrap.servers", Configuration.BROKER_ADDRESS);
		props.put("acks", "1");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// topology
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("consumer-spout", new KafkaSpout(kafkaConfig), 1);
		builder.setBolt("persist-bolt", new MessagePersistenceBolt()).shuffleGrouping("consumer-spout");

		Config config = new Config();

		LocalCluster cluster = new LocalCluster();

		cluster.submitTopology("persistStorm", config, builder.createTopology());

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
