package sip;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import sip.states.SIPChat;
import sip.states.States;

public class SIPServer implements Runnable {

	private SIPChat sipChat = null;
	private int          serverPort   = 15060;
	private ServerSocket serverSocket = null;
	private boolean      isStopped    = false;
	private Thread       runningThread= null;
	
	private SIPWorkerThread SIPwt 	= null;


	public SIPServer(SIPChat newSIPChat){
		this.sipChat = newSIPChat;
		this.serverPort = 15060;
	}

	@Override
	public void run() {
		synchronized(this){
			this.runningThread = Thread.currentThread();
		}
		this.startServerSocket();

		System.out.println("Server thread started");

		while(! isStopped()){
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();

			} catch (IOException e) {
				if(isStopped()) {
					System.out.println("Server Stopped.") ;
					return;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}
			if(sipChat.getState() == States.IDLE){
				SIPwt = (new SIPWorkerThread(clientSocket, sipChat));
				new Thread(SIPwt).start();
			}else{
				new Thread(new SIPWorkerThread(clientSocket, sipChat)).start();
			}
			
			//System.out.println("New connection recived");
		}
		System.out.println("Server Stopped.") ;
	}


	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop(){
		this.isStopped = true;
		
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error closing server", e);
		}
		
		if(SIPwt!= null)
			SIPwt.stop();
	}

	private void startServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port "+serverPort, e);
		}
	}


}
