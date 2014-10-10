package sip.states;

public enum States {
	IDLE("IDLE"),
	TRYING("TRYING"),
	RINGING("RINGING"),
	WATING("WATING"),
	CONNECTED("CONNECTED"),
	DISCONNECTED("DISCONNECTED");
	
	private String name;
	
	States(String name){
		this.name = name;
	}
	
	String getName(){
		return this.name;
	}
}
