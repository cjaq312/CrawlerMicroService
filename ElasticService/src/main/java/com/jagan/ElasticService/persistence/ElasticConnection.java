package com.jagan.ElasticService.persistence;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;

public class ElasticConnection {

	private Client client;

	public ElasticConnection() {
		
		setClient(NodeBuilder.nodeBuilder()
				.settings(Settings.settingsBuilder().put("path.home", "/path/to/elasticsearch/home/dir")).node()
				.client());
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
