package sip.states;


public class StateConnected extends SIPState{

	@Override
	public String getState() {
		return "Connected";
	}
	
	public SIPState receivedError(){
		return new StateDisconnected();
	}
	
	public SIPState receivedBye(){
		return new StateDisconnected();
	}
	
	
}
