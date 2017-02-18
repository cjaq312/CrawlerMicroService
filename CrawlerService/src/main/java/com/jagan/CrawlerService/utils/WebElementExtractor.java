package com.jagan.CrawlerService.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

public class WebElementExtractor {
	public static String getPid(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}
	
	public static List<String> getUrls(String content, String xpath) throws XPathExpressionException {
		List<String> list = new ArrayList<String>();
		for (String i : XpathUtil.xPathEvalList(content, xpath)) {
			list.add(i.trim());
		}
		return list;
	}

	public static String getProductName(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getLongDescription(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getSmallDescription(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getPrice(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getSkuId(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getURL(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getColor(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getSize(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getRetailerName(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getRetailerId(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getManufacturer(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getPrimaryCategory(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getSecondaryCategory(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getTertiaryCategory(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getLargeImage(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static String getSmallImage(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath).trim());
	}

	public static List<String> getCategories(String content, String xpath) throws XPathExpressionException {
		List<String> list = new ArrayList<String>();
		for (String i : XpathUtil.xPathEvalList(content, xpath)) {
			list.add(StringUtils.doubleQuote(i.trim()));
		}
		return list;
	}
}
