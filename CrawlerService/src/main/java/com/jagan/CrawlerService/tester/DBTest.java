package com.jagan.CrawlerService.tester;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jagan.CrawlerService.models.ScrappedUrl;
import com.jagan.CrawlerService.persistence.PersistDAO;

public class DBTest {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		PersistDAO cacheDAO = (PersistDAO) context.getBean("persistDAO");

		ScrappedUrl url = new ScrappedUrl();
		url.setRetailerName("TestRetailer");
		url.setUrl("testUrl1");
		url.setUrlType("test");
		url.setVisited(true);

		cacheDAO.insertUrl(url);
//		 for (ScrappedUrl i : cacheDAO.getVisitedUrls("TestRetailer", "test"))
//		 System.out.println(i.toString());
		System.out.println(cacheDAO.getUrl("testUrl"));
		context.close();

	}
}
