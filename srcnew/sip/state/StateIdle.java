package sip.state;

import java.net.Socket;


public class StateIdle extends SIPState{

	private SIPHandler SIPHandler;
	
	public StateIdle(SIPHandler newSIPHandler){
		this.SIPHandler = newSIPHandler;
	}	
	
	public void recivedConnection(Socket clientSocket){
		SIPHandler.setState(SIPHandler.getStateTrying());
		SIPHandler.setClientSocket(clientSocket);
		SIPHandler.setUpResources(" ");
	}
	
	public void sendIvinte(Socket clientSocket, String command){
		SIPHandler.setState(SIPHandler.getStateWating());
		SIPHandler.setClientSocket(clientSocket);
		SIPHandler.setUpResources( command);

	}
	
	@Override
	public States getState() {
		// TODO Auto-generated method stub
		return States.IDLE;
	}
	
}
