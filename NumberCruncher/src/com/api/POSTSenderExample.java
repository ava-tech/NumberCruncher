package com.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class POSTSenderExample {
	
	public String echoCuties(String query) throws IOException{
		
		String encodedQuery = URLEncoder.encode(query, "UTF-8");
		String postData = "e=" + encodedQuery;
		
		URL url = new URL("http://echo.itcuties.com/");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
		
		OutputStream os = connection.getOutputStream();
		os.write(postData.getBytes());
		
		
		StringBuilder response = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String line;
		
		while((line = br.readLine()) != null){
			response.append(line);
		}
		
		br.close();
		os.close();
		
		return response.toString();
	}

	public static void main(String[] args) {
		try{
			System.out.println(new POSTSenderExample().echoCuties("Hi There"));
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
