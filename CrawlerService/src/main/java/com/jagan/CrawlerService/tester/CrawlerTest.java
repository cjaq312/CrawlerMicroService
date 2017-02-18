package com.jagan.CrawlerService.tester;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Properties;

import com.jagan.CrawlerService.base.BaseWebElementsCrawler;
import com.jagan.CrawlerService.models.Product;

public class CrawlerTest {

	public void test() {
		Properties prop = new Properties();
		FileReader input = null;


		try {
			input = new FileReader(new File("src/main/resources/test.properties"));
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Product product = BaseWebElementsCrawler
				.loadUrl("https://www.beautyboutique.ca/Categories/Makeup/Face/Foundation/Miracle-Cushion/p/LZ27?variantCode=4935421601924", prop);

		System.out.println(product.getPid());

	}

	public static void main(String[] args) {
		
		CrawlerTest test = new CrawlerTest();
		test.test();

	}
}
