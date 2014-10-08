package sip.states;


public class StateDisconnected extends SIPState{

	@Override
	public String getState() {
		return "Disconnected";
	}

	
	public SIPState receivedReadyToConnect(){
		return new StateIdle();
	}

}
