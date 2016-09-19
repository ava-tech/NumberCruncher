package com.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class POSTSenderExample {
	
	public String getMessages()throws IOException{
		URL url = new URL("http://localhost:8088/messanger/webapi/messages/");
		String postData = 
				"{"
						+ "'author':'Kaiser',"
						+ "'created':'2016-09-19T00:31:46.386-07:00',"
						+ "'message':'New York'"
				+ "}";
		String encodedQuery = URLEncoder.encode(postData, "UTF-8");
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		//con.setRequestProperty("Content-Length", String.valueOf(postData));
		
		OutputStream os = con.getOutputStream();
		os.write(encodedQuery.getBytes());
		
		
		StringBuilder response = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String line;
		
		while((line = br.readLine()) != null){
			response.append(line);
		}
		
		br.close();
		os.close();
		
		return response.toString();
	}
	
	public String echoCuties(String query) throws IOException{
		
		System.out.println(query.length());
		
		String encodedQuery = URLEncoder.encode(query, "UTF-8");
		String postData = "e=" + encodedQuery;
		
		System.out.println(postData.length());
		
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
/*		try{
			System.out.println(new POSTSenderExample().echoCuties("12345"));
		}catch(IOException e){
			e.printStackTrace();
		}
*/		
		try {
			new POSTSenderExample().getMessages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*String param1 = "http://localhost:";
		String param2 = "8088";
		String param3 = "/servlets/com.mercurytours.servlet.SignonServlet/";
		
		FlightReservationLogin frl = new FlightReservationLogin(param1, param2, param3);
		frl.loginFlightReservation();*/


	}
}

class FlightReservationLogin{
	
	private final String USER_AGENT = "Mozilla/5.0";
	private String baseURL;
	private String port;
	private String urlSignon;
	
	public FlightReservationLogin(String baseURL, String port, String urlSignon){
		this.baseURL = baseURL;
		this.port = port;
		this.urlSignon = urlSignon;
		
	}
	
	public void loginFlightReservation(){
		String urlString = this.baseURL + this.port + this.urlSignon;
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Cookie","JSESSIONID=" + "gd2o2zdil1");

			String urlParameters = "userName=qa_test&password=mercury&login.x=23&login.y=13";
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			System.out.println(responseCode);
			con.disconnect();
			System.out.println(responseCode);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getLogin() throws Exception{
		URL url = new URL("http://localhost:8088/servlets/com.mercurytours.servlet.SignonServlet/");
		
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        CookieStore cookieJar =  manager.getCookieStore();
        
        HttpCookie cookie = new HttpCookie("UserName", "qa_test");
        cookieJar.add(url.toURI(), cookie);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Cookie", cookie.getValue());
		
		String urlParameters = "userName=qa_test&password=mercury&login.x=23&login.y=13";
		

		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		System.out.println(con.getRequestProperty("Cookie"));
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.print(response.toString());
		
		
		
		
		url = new URL("http://localhost:8088/servlets/com.mercurytours.servlet.ReservationServlet");
		con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Cookie", cookie.getValue());

		String urlParam = "procSub=1&pg=1";
		con.setDoOutput(true);
		DataOutputStream wr2 = new DataOutputStream(con.getOutputStream());
		wr2.writeBytes(urlParam);
		wr2.flush();
		wr2.close();
		
		int resCode = con.getResponseCode();
		System.out.println(resCode);
		System.out.println(con.getRequestProperty("Cookie"));

		BufferedReader in2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine2;
		StringBuffer response2 = new StringBuffer();

		while ((inputLine2 = in2.readLine()) != null) {
			response2.append(inputLine2);
		}
		in2.close();

		//print result
		System.out.print(response2.toString());

	}
}
