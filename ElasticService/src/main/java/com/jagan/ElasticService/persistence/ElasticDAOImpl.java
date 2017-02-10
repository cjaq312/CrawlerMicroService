package com.jagan.ElasticService.persistence;


import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;

public class ElasticDAOImpl implements ElasticDAO {
	private ElasticConnection connex = new ElasticConnection();
	private Client client = connex.getClient();
	private static ElasticDAOImpl dao = new ElasticDAOImpl();

	private ElasticDAOImpl() {
	}

	public static ElasticDAOImpl getInstance() {
		return dao;
	}

		public boolean putRecord(String index, String type, String id, Map<String, Object> objectMap) {
		return client.prepareIndex(index, type, id).setSource(objectMap).execute().actionGet().isCreated();
	}

	public boolean postRecord(String index, String type, Map<String, Object> objectMap) {
		return client.prepareIndex(index, type).setSource(objectMap).execute().actionGet().isCreated();
	}

	public GetResponse getRecord(String index, String type, String id) {
		return client.prepareGet(index, type, id).execute().actionGet();
	}

	public SearchResponse searchRecord(String index, String type, Map<String, String> fieldMap) {
		return client.prepareSearch(index).setTypes(type).setSearchType(SearchType.QUERY_AND_FETCH).setQuery(fieldMap)
				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
	}

	public DeleteResponse deleteRecord(String index, String type, String id) {
		return client.prepareDelete(index, type, id).execute().actionGet();
	}

	public UpdateResponse updateRecord(String index, String type, String id, Map<String, Object> objectMap) {
		return client.prepareUpdate(index, type, id).setDoc(objectMap).execute().actionGet();
	}

}
