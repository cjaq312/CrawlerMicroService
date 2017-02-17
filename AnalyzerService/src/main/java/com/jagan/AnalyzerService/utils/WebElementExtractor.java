package com.jagan.AnalyzerService.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

public class WebElementExtractor {
	private static List<String> list = new ArrayList<String>();

	public static String getPid(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getProductName(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getLongDescription(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getSmallDescription(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getPrice(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getSkuId(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getURL(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getColor(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getSize(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getRetailerName(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getRetailerId(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getManufacturer(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getPrimaryCategory(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getSecondaryCategory(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static String getTertiaryCategory(String content, String xpath) throws XPathExpressionException {
		return StringUtils.doubleQuote(XpathUtil.xPathEvalSingle(content, xpath));
	}

	public static List<String> getCategories(String content, String xpath) throws XPathExpressionException {
		list.clear();
		for (String i : XpathUtil.xPathEvalList(content, xpath)) {
			list.add(StringUtils.doubleQuote(i));
		}
		return list;
	}
}
