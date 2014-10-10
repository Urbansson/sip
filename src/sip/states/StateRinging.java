package sip.states;

import java.net.Socket;

public class StateRinging  implements SIPState{

	private SIPChat sipChat;
	
	public StateRinging(SIPChat newSIPChat){
		this.sipChat = newSIPChat;
	}
	
	@Override
	public boolean recivedInvite(Socket clientSocket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void tryingCompleated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recivedAck() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendingInvite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recivedOK() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recivedDecline() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivedError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivedBye() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanupCompleated() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public States getState() {
		return States.RINGING;
	}

}
