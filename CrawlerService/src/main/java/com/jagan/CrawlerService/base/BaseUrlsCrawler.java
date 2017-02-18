package com.jagan.CrawlerService.base;

import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import com.jagan.CrawlerService.utils.WebDriverUtil;
import com.jagan.CrawlerService.utils.WebElementExtractor;

public class BaseUrlsCrawler {

	public static List<String> getCrawlUrls(String url, String xpath) throws XPathExpressionException {
		String content = WebDriverUtil.getContent(url, true);
		List<String> crawlUrls = WebElementExtractor.getUrls(content, xpath);
		return crawlUrls;
	}

	public static List<String> getDataUrls(String url, String xpath) throws XPathExpressionException {
		String content = WebDriverUtil.getContent(url, true);
		List<String> dataUrls = WebElementExtractor.getUrls(content, xpath);
		return dataUrls;
	}
}
