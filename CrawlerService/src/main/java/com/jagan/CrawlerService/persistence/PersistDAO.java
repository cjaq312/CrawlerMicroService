package com.jagan.CrawlerService.persistence;

import java.util.List;

import com.jagan.CrawlerService.models.ScrappedUrl;

public interface PersistDAO {
	void insertUrl(ScrappedUrl url);
	
	void insertUrls(List<ScrappedUrl> urls);

	void deleteUrl(String url);
	
	void deleteUrls(List<String>urls);
	
	ScrappedUrl getUrl(String url);

	List<ScrappedUrl> getUnvisitedUrls(String retailerName, String urlType);
	
	List<ScrappedUrl> getVisitedUrls(String retailerName, String urlType);
	
	void setVisited(String url);
	
	void setVisited(ScrappedUrl url);
}
