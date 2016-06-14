package org.sys;

public class A {
	
	int maxValue;
	
	public int getMaxValue(int a, int b){
		maxValue = (a<b) ? b:a;
		return maxValue;
	}
	
	public int hitCount(){
		int n = (int) (Math.random() * 100);
		System.out.println(n);
		return n; 
	}
	
	public static void main(String...strings ){
		
		A a= new A();
		int num = Integer.parseInt(strings[0]);
		
		System.out.println(a.getMaxValue(num, a.hitCount()));
		
		for(Car c:Car.values()){
			
			System.out.println(c.name() + "=" + c.getPrice());
		}
		
		
		
	}
}

enum Car{
	
	Lamborgini(900),
	Honda(500),
	Toyota(400);
	
	private int price;
	
	Car(int p){
		price = p;
	}
	
	int getPrice(){
		return price;
	}
}
