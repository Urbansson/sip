package sip;


import java.io.IOException;
import java.net.Socket;

import sip.state.SIPHandler;

public class SIPClientThread implements Runnable{

	private SIPHandler 		sipHandler = null;
	private int          	remoteServerPort   = 15060;
	private Socket 	clientSocket = null;
	private String command = null;
	//private BufferedReader inFromServer = null;
	//private DataOutputStream outToServer = null;

	public SIPClientThread(SIPHandler newsipHandler, String command){
		this.sipHandler = newsipHandler;
		this.remoteServerPort = 15060;
		this.command = command;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String remoteIp = command.split(" ")[2]; 
			
			System.out.println("Trying to connect");
			clientSocket = new Socket(remoteIp, this.remoteServerPort);
			
			this.sipHandler.sendIvinte(clientSocket,command);
			
			System.out.println("Closing Client thread All is done");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Your invite was wrongly formatted");
			
		}
		System.out.println("Client thread stopped7");
		System.out.println(sipHandler.getState());
	}	

	public synchronized void stop(){
		System.out.println("Stopping");
		try {
			this.clientSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error closing server", e);
		}
	}



}
