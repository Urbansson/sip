package sip.state;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;

import audioStreamUDP.AudioStreamUDP;
import sip.pdu.PDU;
import sip.pdu.PDUParser;
import sip.pdu.data.SIPInviteData;

public class StateWaiting extends SIPState{

	private SIPHandler SIPHandler;

	public StateWaiting(SIPHandler newSIPHandler){
		this.SIPHandler = newSIPHandler;
	}

	public void setUpResources(String command){

		String inData;

		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(SIPHandler.getClientSocket().getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(SIPHandler.getClientSocket().getOutputStream());

			//Set up audio Streamer get all data create data object
			//parse 
			
			String []dataStr = command.split(" "); 
			
			AudioStreamUDP streamer = new AudioStreamUDP();
			SIPHandler.setStreamer(streamer);

			int port = streamer.getLocalPort();
			System.out.println("Bound to local port = " + port);			
			
			
			SIPHandler.setClientData(new SIPInviteData(dataStr[0], dataStr[1], InetAddress.getByName(dataStr[2]), SIPHandler.getClientSocket().getLocalAddress(), port));
			outToClient.writeBytes(PDU.INVITE+" "+SIPHandler.getClientData().toString()+"\n");
			
			do{
				inData = inFromClient.readLine();
				System.out.println(inData);
				
				if(PDUParser.parse(inData)==PDU.RINGING){
					String remoteVoicePort = inData.split(" ")[1]; 
					int i = Integer.parseInt(remoteVoicePort);
					streamer.connectTo(SIPHandler.getClientData().getIp_to(), i);
					
					System.out.println("Streamer is set");

				}	
				
			}while(PDUParser.parse(inData)!=PDU.OK);
			
			SIPHandler.setState(SIPHandler.getStateConnected());
			SIPHandler.keepAlive();
			System.out.println("Last of Waiting: "+ SIPHandler.getState());
			
		}catch(Exception e){	
			e.printStackTrace();
			SIPHandler.diconnect();
		}
	}

	public void diconnect(){
		System.out.println("Disconnecting from Waiting");
		
		SIPHandler.setClientData(null);
		try {
			SIPHandler.getClientSocket().close();
		} catch (Exception e) {
		}
		
		SIPHandler.setClientSocket(null);
		SIPHandler.setCallAnswered(false);
		
		//SIPHandler.init();

		
		SIPHandler.setState(SIPHandler.getStateIdle());
	}

	@Override
	public States getState() {
		return States.WAITING;
	}
}
