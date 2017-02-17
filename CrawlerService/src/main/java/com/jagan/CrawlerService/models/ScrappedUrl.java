package com.jagan.CrawlerService.models;

import org.apache.storm.shade.org.eclipse.jetty.util.StringUtil;
import com.jagan.CrawlerService.utils.StringUtils;

public class ScrappedUrl {
	private String retailerName;
	private String url;
	private String urlType;
	private boolean visited;
	private StringBuffer buffer;

	public ScrappedUrl() {
		buffer = new StringBuffer();
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
