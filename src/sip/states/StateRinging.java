package sip.states;


public class StateRinging extends SIPState{

	@Override
	public String getState() {
		return "Ringing";
	}
	
	public SIPState receivedAck(){
		return new StateConnected();
	}
	
	public SIPState receivedDecline(){
		return new StateDisconnected();
	}


	public SIPState receivedError(){
		return new StateDisconnected();
	}
	


}
