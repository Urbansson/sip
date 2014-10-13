package sip.state;

import java.net.Socket;

public abstract class SIPState {

	public abstract States getState();

	public void recivedConnection(Socket clientSocket){}

	public void setUpResources(String command){}
	
	public void notifyUser(){}
	
	public void answerCall(){}

	public void keepAlive(){}

	public void sendIvinte(Socket clientSocket, String command){}
		
	//public void recivedOk(){}

	public void diconnect(){}

	
}
