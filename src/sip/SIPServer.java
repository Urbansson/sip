package sip;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import sip.state.SIPHandler;
import sip.state.States;

public class SIPServer implements Runnable {

	private SIPHandler 		sipHandler = null;
	private int          	serverPort   = 15060;
	private ServerSocket 	serverSocket = null;
	private boolean      	isStopped    = false;
	private DataOutputStream outToClient = null;
	private SIPWorkerThread SIPwt 	= null;


	public SIPServer(SIPHandler newsipHandler){
		this.sipHandler = newsipHandler;
		this.serverPort = 15060;
	}

	@Override
	public void run() {
		if(!this.startServerSocket()){
			System.out.println("Failed to start server");
			return;
		}
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


			if(sipHandler.getState() == States.IDLE){
				SIPwt = (new SIPWorkerThread(clientSocket, sipHandler));
				new Thread(SIPwt).start();
			}else{
				
				try {
					outToClient = new DataOutputStream(clientSocket.getOutputStream());
					outToClient.writeBytes("BUSY\n");
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						clientSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}		
			}
			

		}
		System.out.println("Server Stopped.") ;
	}


	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop(){
		this.isStopped = true;
		SIPwt.stop();
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			//throw new RuntimeException("Error closing server", e);
		}

		if(SIPwt!= null)
			SIPwt.stop();
	}

	private boolean startServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e) {
			//throw new RuntimeException("Cannot open port "+serverPort, e);
			return false;
		}
		return true;
	}


}
