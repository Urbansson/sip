package sip.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import sip.states.SIPHandler;

public class SIPWorkerThread implements Runnable{

	private Socket 		clientCocket 	= null;
	private SIPHandler	sh 				= null;
	private boolean     isStopped    	= false;

	
	public SIPWorkerThread(Socket client, SIPHandler sh){
		this.clientCocket = client;
		this.sh = sh;
	}
	
	@Override
	public void run() {
		System.out.println("Worker thread is up");
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientCocket.getInputStream())); 
            DataOutputStream outToClient = new DataOutputStream(clientCocket.getOutputStream());
            
            while(! isStopped()){
            	System.out.println(inFromClient.readLine());
            	outToClient.writeBytes("Hello there little fellow" + '\n');           
            }
            
        }catch(Exception e){
        	
        }

	}
	
    private synchronized boolean isStopped() {
        return this.isStopped;
    }
    
    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.clientCocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }
	
}
