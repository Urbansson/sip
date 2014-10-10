package sip;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import sip.states.SIPChat;
import sip.states.StateIdle;
import sip.states.States;

public class SIPWorkerThread implements Runnable{

	private Socket 		clientSocket 	= null;
	private SIPChat		sipChat 		= null;
	private boolean     isStopped    	= false;
	private boolean 	answered		= false;


	public SIPWorkerThread(Socket client, SIPChat newSIPChat){
		this.sipChat = newSIPChat;
		this.clientSocket = client;
	}

	@Override
	public void run() {
		System.out.println("Worker thread is up");

		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());

			if(!sipChat.reciveInvite(clientSocket)){
				outToClient.writeBytes("BUSY");
				this.stop();
			}
			
			
			while(! isStopped()){
				System.out.println(inFromClient.readLine());
				outToClient.writeBytes("Hello there little fellow" + '\n');
				//Set up ringing and change state and wait for pickup from user	
			}
						
			inFromClient.close();
			outToClient.close();
		}catch(Exception e){
			System.out.println("Stopped");
		}
		

	}
	
	
	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop(){
		this.isStopped = true;
		System.out.println("Stopping");
		try {
			this.clientSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error closing server", e);
		}
	}

}
