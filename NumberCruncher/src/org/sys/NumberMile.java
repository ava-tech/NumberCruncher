package org.sys;

public class NumberMile implements SkyMile {

	private int result;

	@Override
	public void getRanAddition(int... is) {
		for (int i : is) {
			result += i;
		}
	}

	@Override
	public void getRanSubtraction(int num1, int num2) {

		if (num1 > num2) {
			result = num1 - num2;
		} else if (num1 < num2) {
			result = num2 - num1;
		} else {
			result = 0;
		}
	}

	@Override
	public void getCarzyBig(int... is) {

		int first = 0;
		int last = is.length - 1;

		for (; first <= last; first++) {
			result += is[first] + is[last];
			last--;
		}
	}
	
	public int getResult(){
		return result;
	}
	
/*	public static void main(String...strings){
		SkyMile sm = new NumberMile();
		int[] arr = {1,2,3,4,5};
		
		//sm.getCarzyBig(arr);
		//System.out.println(((NumberMile) sm).getResult());
		
		sm.getRanAddition(arr);
		System.out.println(((NumberMile) sm).getResult());
		
	}
*/}
