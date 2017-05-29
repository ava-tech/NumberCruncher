package org.sys;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperty {

	public static void main(String[] args) {
		
		Properties prop = new Properties();

		InputStream input = ReadProperty.class.getClassLoader().getResourceAsStream("config.properties");
		
		try {
			prop.load(input);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("url"));

	}

}
