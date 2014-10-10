package sip.states;

import java.net.Socket;

public interface SIPState {
	
	public boolean recivedInvite(Socket clientSocket);
	public void tryingCompleated();
	public void recivedAck();
	
	public void sendingInvite();
	public void recivedOK();
	public void recivedDecline();

	public void receivedError();
	public void receivedBye();

	public void cleanupCompleated();
	
	public States getState();


}
