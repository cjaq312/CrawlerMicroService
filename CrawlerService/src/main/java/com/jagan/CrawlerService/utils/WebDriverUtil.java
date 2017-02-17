package com.jagan.CrawlerService.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebDriverUtil {

	private static WebDriver driver = new HtmlUnitDriver();
	private static String content = "";

	public static String getContent(String url, boolean clean) {
		content = "";
		driver.get(url);
		content = driver.getPageSource();

		if (clean)
			content = clean(content);
		else
			content = content;

		return content;
	}

	public static String clean(String content) {
		return content;
	}

}
