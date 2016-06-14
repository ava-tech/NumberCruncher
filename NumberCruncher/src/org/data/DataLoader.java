package org.data;

public class DataLoader {
	
	private String filePath;
	
	public DataLoader(String filepath){
		this.filePath = filepath;
	}
	
	public void openFile(){
		
		System.out.println("Opening file:: " + filePath);
		
	}
	
	public void closeFile(){
		
		System.out.println("Closing file:: " + filePath);
		
	}
	
	public static void main(String...strings ){
		DataLoader dl = new DataLoader("C:\\temp\\Testplan.xlsx");
		dl.closeFile();
	}

}
