/*
 * 
 * Incomplete skeleton for the Client
 * 
 */


package sip.client;
import sip.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import sip.pdu.*;
import sip.states.SIPState;

public class SIPClient implements Runnable{
	private String commandStr;
	private InetAddress dstAddr;
	private PDU currentPDU;
	private Socket clientSocket = null; 
	private static final int port = 15060;
	private DataOutputStream dos;
	private DataInputStream dis;
	private BufferedWriter toServer;
	private BufferedReader fromServer;
	
	
	public SIPClient(String str, SIPHandler sh) throws UnknownHostException{
		if(str.split(" ").length < 2)
			throw new IllegalArgumentException();
		String[] split = str.split(" ");
		this.commandStr = split[0];
		currentPDU = PDUParser.parse(str);
		dstAddr = InetAddress.getByName(split[1]);
				
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			clientSocket = new Socket(dstAddr, port);
			dos = new DataOutputStream(clientSocket.getOutputStream());
			dos.writeBytes(commandStr + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		
	}
}