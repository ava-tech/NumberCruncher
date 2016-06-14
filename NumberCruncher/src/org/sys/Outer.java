package org.sys;

public class Outer {
	
	public enum ShowSum{
		INSTANCE;
		
		int a,b,result;
		
		ShowSum(){
			a = 0;
			b = 0;
		}
		private void total(int a, int b){
			result = a + b;
			System.out.println(result);
		}
	}
	
	public void grantTotal(){
		a = ShowSum.INSTANCE;
		a.total(5, 1);
	}
	
	public void subTotal(){
		a.total(25, 35);
	}
	
	Outer.ShowSum a;

	public static void main(String[] args) {
		ShowSum s = ShowSum.INSTANCE;
		s.total(10, 20);
		
		Outer outer = new Outer();
		outer.grantTotal();
		outer.subTotal();
		
	}
}
