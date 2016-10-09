package com.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

public class DRGHttpsURLCookieHandle {

	private final static Logger logger = Logger.getLogger(DRGHttpsURLCookieHandle.class);
	
	private final String USER_AGENT = "Mozilla/5.0";
	private final String URL_PREFIX = "https://qa.remedysystems.com/secure/admin/facility/";
	private final String URL_SUFFIX = "/bundles";
	private final String FILE_NAME = "/Users/malam/jira_test/Team Health CCN DRGs 20150706 0431p.csv";
	
	

	public void getCookieUsingCookieHandler() { 
	    try {       
	        // Instantiate CookieManager;
	        // make sure to set CookiePolicy
	        CookieManager manager = new CookieManager();
	        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
	        CookieHandler.setDefault(manager);

	        // get content from URLConnection;
	        // cookies are set by web site
	        URL url = new URL("https://qa.remedysystems.com/secure/admin/facility/5594a1dfbc11bf2621c2a91e/bundles");
	        URLConnection connection = url.openConnection();
	        connection.getContent();

	        // get cookies from underlying
	        // CookieStore
	        CookieStore cookieJar =  manager.getCookieStore();
	        List <HttpCookie> cookies =
	            cookieJar.getCookies();
	        for (HttpCookie cookie: cookies) {
	          System.out.println("CookieHandler retrieved cookie: " + cookie);
	        }
	    } catch(Exception e) {
	        System.out.println("Unable to get cookie using CookieHandler");
	        e.printStackTrace();
	    }
	} 
	
	
	
	public void setCookieUsingCookieHandler() {
	    try {
	        // instantiate CookieManager
	        CookieManager manager = new CookieManager();
	        CookieHandler.setDefault(manager);
	        CookieStore cookieJar =  manager.getCookieStore();

	        // create cookie
	        HttpCookie cookie = new HttpCookie("UserName", "malam@remedypartners.com");

	        // add cookie to CookieStore for a
	        // particular URL
	        URL url = new URL("https://qa.remedysystems.com/secure/admin/facility/5594a1dfbc11bf2621c2a91e/bundles");
	        cookieJar.add(url.toURI(), cookie);
	        System.out.println("Added cookie using cookie handler");
	    } catch(Exception e) {
	        System.out.println("Unable to set cookie using CookieHandler");
	        e.printStackTrace();
	    }
	}
	
	
	
	
	
	private void sendGet() throws Exception {

		CSVParser parser = new CSVParser(new FileReader(FILE_NAME), CSVFormat.DEFAULT.withHeader());
		
		for (CSVRecord record : parser) {

			String urlString = URL_PREFIX + record.get("mongoID") + URL_SUFFIX;
			//System.out.println(URL_PREFIX + record.get("mongoID") + URL_SUFFIX);

			URL obj = new URL(URL_PREFIX + record.get("mongoID") + URL_SUFFIX);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			String cookies = con.getHeaderField("Set-Cookie");

			con.setRequestProperty("Cookie", cookies);
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();

			if (responseCode == 200) {
				logger.info("\nSending 'GET' request to URL : ");
				logger.info(urlString + "<---Response Code : " + responseCode);
			} else {
				logger.info(urlString + "<---Invalid: " + responseCode + "<---URL:" + URL_PREFIX + record.get("mongoID") + URL_SUFFIX);
			}
			
			System.out.println(urlString + "<---Response Code : " + responseCode);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());


		}
		parser.close();

	}
	

	public static void main(String[] args) throws Exception {

		DRGHttpsURLCookieHandle http = new DRGHttpsURLCookieHandle();

		//System.out.println("Testing 1 - Send Http GET request");
		//http.sendGet();
		
		//http.getCookieUsingCookieHandler();
		
		//http.setCookieUsingCookieHandler();
		//http.getCookieUsingCookieHandler();
		http.sendGet();
	}

}