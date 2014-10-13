package sip.pdu;

public enum PDU {
	INVITE("INVITE"),
	TRYING("TRYING"),
	RINGING("RINGING"),
	OK("OK"),
	BYE("BYE"),
	ACK("ACK"),
	ALIVE("ALIVE"),
	BUSY("BUSY"),
	ERROR("ERROR");
	
	private String name;
	
	PDU(String name){
		this.name = name;
	}
	
	String getName(){
		return this.name;
	}
}
