package sip.state;

public enum States {
	IDLE("IDLE"),
	TRYING("TRYING"),
	RINGING("RINGING"),
	WAITING("WAITING"),
	CONNECTED("CONNECTED");
	
	private String name;
	
	States(String name){
		this.name = name;
	}
	
	String getName(){
		return this.name;
	}
}
