package sip.states;


public class StateIdle extends SIPState{

	@Override
	public String getState() {
		return "Idle";
	}
	
	public SIPState receivedInvite(){
		return new StateTrying();
	}

	public SIPState SendingInvite(){
		return new StateWaiting();
	}

	
}
