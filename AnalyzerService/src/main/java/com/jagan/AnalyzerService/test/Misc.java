package com.jagan.AnalyzerService.test;

import com.jagan.AnalyzerService.utils.Analyzer;

public class Misc {
	public static void main(String[] args) {
		String result = "234.453";
		float a = 0;
		try {
			a = Float.parseFloat(result);
			System.out.println(a);
		} catch (Exception e) {
			System.out.println(a);
		}

		if (a > 0)
			System.out.println("is a number thats not 0");
		else
			System.out.println("is a not number or thats a 0");

		
		System.out.println(Analyzer.validateField(""));
		
	}
}
