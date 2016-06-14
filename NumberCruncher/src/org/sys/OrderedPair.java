package org.sys;

import java.util.HashMap;
import java.util.Map;

public class OrderedPair<K, V> implements Pair<K, V>{
	
	private K key;
	private V value;
	
	public OrderedPair(K key, V value){
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {

		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	public static void main(String[] args){
		
		Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8); 
		Pair<String, String> p2 = new OrderedPair<String, String>("Foo", "Boo");
		
		//System.out.println(p1.getKey() + " : " + p2.getValue());
		
		Pair<Character, Double> p3 = new OrderedPair<Character, Double>('A', 0.0);
		
		Map<String, OrderedPair> map = new HashMap<String, OrderedPair>();
		map.put("001", new OrderedPair<String, Integer>("Alam", 8));
		map.put("002", new OrderedPair<String, Integer>("Mohammad", 100));
		
		for(Map.Entry<String, OrderedPair> entry : map.entrySet() ){
			
			//System.out.println(entry.getKey());
			
			System.out.println( entry.getValue().getKey() + " : " + entry.getValue().getValue());
		}
	
	}
	
	
}

