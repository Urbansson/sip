package sip;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import sip.pdu.PDU;
import sip.pdu.PDUParser;
import sip.state.SIPHandler;
import sip.state.States;

public class SIPWorkerThread implements Runnable{

	private Socket 		clientSocket 	= null;
	private SIPHandler	sipHandler 		= null;
	private boolean     isStopped    	= false;
	private boolean 	answered		= false;

	public SIPWorkerThread(Socket client, SIPHandler newSipHandler){
		this.sipHandler = newSipHandler;
		this.clientSocket = client;
	}

	@Override
	public void run() {
		System.out.println("Worker thread is up");
		
		BufferedReader inFromClient;
		DataOutputStream outToClient;
		String input;
		
		try {
			
			synchronized(sipHandler){
				sipHandler.recivedConnection(this.clientSocket);	
			}
			System.out.println("Closing Thread all is done");
			
		}catch(Exception e){
			e.printStackTrace();
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
