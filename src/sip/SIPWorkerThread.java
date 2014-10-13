package sip;

import java.io.IOException;
import java.net.Socket;

import sip.state.SIPHandler;

public class SIPWorkerThread implements Runnable{

	private Socket 		clientSocket 	= null;
	private SIPHandler	sipHandler 		= null;

	public SIPWorkerThread(Socket client, SIPHandler newSipHandler){
		this.sipHandler = newSipHandler;
		this.clientSocket = client;
	}

	@Override
	public void run() {

		try {
			sipHandler.recivedConnection(this.clientSocket);	
			System.out.println("Closing Thread all is done");
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("Stopped");
		}

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
