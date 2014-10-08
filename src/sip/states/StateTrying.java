package sip.states;


public class StateTrying extends SIPState{

	@Override
	public String getState() {
		return "Trying";
	}

	public SIPState SendingRinging(){
		return new StateRinging();
	}

	public SIPState receivedError(){
		return new StateDisconnected();
	}
	

	
}
