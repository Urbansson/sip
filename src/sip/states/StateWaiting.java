package sip.states;

public class StateWaiting extends SIPState{
	
	@Override
	public String getState() {
		return "Waiting";
	}

	public SIPState receivedOk(){
		return new StateConnected();
	}

	
	public SIPState receivedDecline(){
		return new StateDisconnected();
	}


	public SIPState receivedError(){
		return new StateDisconnected();
	}
	
}
