package com.jagan.CrawlerService.base;

import java.util.List;
import java.util.Properties;

import javax.xml.xpath.XPathExpressionException;

import com.jagan.CrawlerService.models.Product;
import com.jagan.CrawlerService.utils.StringUtils;
import com.jagan.CrawlerService.utils.WebDriverUtil;
import com.jagan.CrawlerService.utils.WebElementExtractor;
import com.jagan.CrawlerService.utils.XpathUtil;

public class BaseWebElementsCrawler {

	public static Product loadUrl(String url, Properties prop) {
		Product product = new Product();
		String content;
		List<String> categories;
		
		content = WebDriverUtil.getContent(url, true);

		try {
			if (prop.containsKey("pid"))
				product.setPid(WebElementExtractor.getPid(content, prop.getProperty("pid")));
			if (prop.containsKey("productname"))
				product.setName(WebElementExtractor.getProductName(content, prop.getProperty("productname")));
			if (prop.containsKey("longdescription"))
				product.setLongDescription(
						WebElementExtractor.getLongDescription(content, prop.getProperty("longdescription")));
			if (prop.containsKey("smalldescription"))
				product.setSmallDescription(
						WebElementExtractor.getSmallDescription(content, prop.getProperty("smalldescription")));
			if (prop.containsKey("price"))
				product.setPrice(
						WebElementExtractor.getPrice(content, prop.getProperty("price")));
			if (prop.containsKey("skuid"))
				product.setSkuId(
						WebElementExtractor.getSkuId(content, prop.getProperty("skuid")));
			product.setUrl(url);
			if (prop.containsKey("color"))
				product.setColor(
						WebElementExtractor.getColor(content, prop.getProperty("color")));
			if (prop.containsKey("size"))
				product.setSize(
						WebElementExtractor.getSize(content, prop.getProperty("size")));

			if (prop.containsKey("categories")) {
				categories = WebElementExtractor.getCategories(content, prop.getProperty("categories"));
				if (categories.size() > 2) {
					product.getCategories().setPrimaryCategory(categories.get(0));
					product.getCategories().setSecondaryCategory(categories.get(1));
					product.getCategories().setTertiaryCategory(categories.get(2));
				} else if (categories.size() > 1) {
					product.getCategories().setPrimaryCategory(categories.get(0));
					product.getCategories().setSecondaryCategory(categories.get(1));
				} else {
					product.getCategories().setPrimaryCategory(categories.get(0));
				}
			} else {
				if (prop.containsKey("primarycategory"))
					product.getCategories().setPrimaryCategory(WebElementExtractor.getPrimaryCategory(content, prop.getProperty("primary")));
				if (prop.containsKey("secondarycategory"))
					product.getCategories().setSecondaryCategory(
							WebElementExtractor.getSecondaryCategory(content, prop.getProperty("secondary")));
				if (prop.containsKey("tertiarycategory"))
					product.getCategories().setTertiaryCategory(
							WebElementExtractor.getTertiaryCategory(content, prop.getProperty("tertiary")));
			}
			if (prop.containsKey("manufacturer"))
				product.setManufacturer(WebElementExtractor.getManufacturer(content, prop.getProperty("manufacturer")));
			if (prop.containsKey("retailername"))
				product.setRetailerName(WebElementExtractor.getRetailerName(content, prop.getProperty("retailername")));
			if (prop.containsKey("retailerid"))
				product.setRetailerId(WebElementExtractor.getRetailerId(content, prop.getProperty("retailerid")));
			if (prop.containsKey("largeimage"))
				product.setLargeImage(WebElementExtractor.getLargeImage(content, prop.getProperty("largeimage")));
			if (prop.containsKey("smallimage"))
				product.setSmallImage(WebElementExtractor.getSmallImage(content, prop.getProperty("smallimage")));
		} catch (Exception ex) {

		}
		return product;
	}
}
