package com.jagan.ElasticService.persistence;


import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

public interface ElasticDAO {

	public boolean putRecord(String index, String type, String id, Map<String, Object> objectMap);

	public boolean postRecord(String index, String type, Map<String, Object> objectMap);

	public GetResponse getRecord(String index, String type, String id);

	public SearchResponse searchRecord(String index, String type, Map<String, String> fieldMap);

	public DeleteResponse deleteRecord(String index, String type, String id);

	public UpdateResponse updateRecord(String index, String type, String id, Map<String, Object> objectMap);

}
