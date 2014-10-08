package sip;

import sip.states.SIPState;
import sip.states.StateIdle;

public class SIPHandler {

	private SIPState currentState;

	public SIPHandler(){
		this.currentState = new StateIdle();
	}

	public String getState(){
		return currentState.getState();
	}


	public void receivedInvite(){
		currentState = currentState.receivedInvite();
	}
	
	public void SendingRinging(){
		currentState = currentState.SendingRinging();
	}
	
	public void receivedAck(){
		currentState = currentState.receivedAck();
	}

	public void SendingInvite(){
		currentState = currentState.SendingInvite();
	}
	
	public void receivedOk(){
		currentState = currentState.receivedOk();
	}
	
	public void receivedDecline(){
		currentState = currentState.receivedDecline();
	}

	public void receivedError(){
		currentState = currentState.receivedError();
	}
	
	public void receivedBye(){
		currentState = currentState.receivedBye();
	}

	public void receivedReadyToConnect(){		
		currentState = currentState.receivedReadyToConnect();
	}





}
