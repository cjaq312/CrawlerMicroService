package com.jagan.CrawlerService.persistence;

import java.util.List;

import com.jagan.CrawlerService.models.ScrappedUrl;

public interface PersistDAO {
	void insertUrl(ScrappedUrl url);
	
	void insertUrls(List<ScrappedUrl> urls);

	void deleteUrl(String url);
	
	void deleteUrls(List<String>urls);

	List<ScrappedUrl> getUnvisitedUrls(String retailerName, String urlType);
	
	List<ScrappedUrl> getVisitedUrls(String retailerName, String urlType);
}
