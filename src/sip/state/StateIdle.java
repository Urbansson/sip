package sip.state;

import java.net.Socket;


public class StateIdle extends SIPState{

	private SIPHandler SIPHandler;

	public StateIdle(SIPHandler newSIPHandler){
		this.SIPHandler = newSIPHandler;
	}	

	public void recivedConnection(Socket clientSocket){
		synchronized(SIPHandler){
			SIPHandler.setState(SIPHandler.getStateTrying());
			SIPHandler.setClientSocket(clientSocket);
		}
		SIPHandler.setUpResources(null);
	}

	public void sendIvinte(Socket clientSocket, String command){
		synchronized(SIPHandler){
			SIPHandler.setState(SIPHandler.getStateWating());
			SIPHandler.setClientSocket(clientSocket);
		}
		SIPHandler.setUpResources( command);
	}

	@Override
	public States getState() {
		return States.IDLE;
	}

}
