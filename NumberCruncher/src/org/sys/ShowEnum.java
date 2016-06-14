package org.sys;

import java.util.HashSet;
import java.util.Set;

public enum ShowEnum {
	
	INSTANCE;
	
	private static Set<String> availableSeats = new HashSet<String>();
	
	/*private ShowEnum(){
		//availableSeats = new HashSet<String>();
		availableSeats.add("1A");
		availableSeats.add("1B");
	}*/
	
	private boolean bookSeat(String seat){
		return availableSeats.remove(seat);
	}
	
	private static void ticketAgentsBooks(String seat){
		final ShowEnum show = ShowEnum.INSTANCE;
		System.out.println(show.bookSeat(seat));
	}
	
	public static void addSeats(String seat){
		availableSeats.add(seat);
	}
	
	public static void main(String[] args){
		
		ShowEnum.addSeats("1A");
		
		ticketAgentsBooks("1A");
		ticketAgentsBooks("1A");
	}

}
