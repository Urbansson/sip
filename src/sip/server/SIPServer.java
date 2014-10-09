package sip.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import sip.states.SIPHandler;

public class SIPServer implements Runnable {

	private SIPHandler sh = null;
	private int          serverPort   = 15060;
	private ServerSocket serverSocket = null;
	private boolean      isStopped    = false;
	private Thread       runningThread= null;



	public SIPServer(SIPHandler sh, int port){
		this.sh = sh;
		this.serverPort = port;

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
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            new Thread( new SIPWorkerThread(clientSocket, sh)).start();
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
    }
	
    private void startServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port "+serverPort, e);
        }
    }
    
    
}
