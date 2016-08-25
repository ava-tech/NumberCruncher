package org.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RequestParameter {
	
	String filePath = "C:\\Logs\\output.csv";
	BufferedReader br = null;
	String line = "";
	String delim = ",";
	
	public HashMap<?,?> getDataDictObject(){
		HashMap<String, ArrayList<String>> map = new HashMap<>();
		ArrayList<String> paramData = null;
		
		try{
			br = new BufferedReader(new FileReader(filePath));
			while((line = br.readLine()) != null){
				
				String[] data = line.split(delim);
				paramData = new ArrayList<>();
				paramData.add(data[0]);
				paramData.add(data[1]);
				map.put(data[2], paramData);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public void getRequestParameter(){
		
		ArrayList<String> string = new ArrayList<>();
		
		RequestParameter rp = new RequestParameter();
		Map<?,?> map = rp.getDataDictObject();

		for(Entry<?, ?> e: map.entrySet()){
			string = (ArrayList<String>) e.getValue();
			System.out.println(e.getKey()+"-->"+string.get(0)+","+string.get(1));
			//System.out.print(e.getKey());
			//System.out.println(e.getValue());
		}
		
	}

	public static void main(String[] args) {
		new RequestParameter().getRequestParameter();
	}
}
