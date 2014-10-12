package sip.state;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import audioStreamUDP.AudioStreamUDP;
import sip.pdu.InviteParser;
import sip.pdu.PDU;
import sip.pdu.PDUParser;
import sip.pdu.data.SIPInviteData;



public class StateTrying extends SIPState{

	private SIPHandler SIPHandler;

	public StateTrying(SIPHandler newSIPHandler){
		this.SIPHandler = newSIPHandler;
	}

	public void setUpResources(String Command){

		System.out.println("Trying to set up resources");

		String inData = null;

		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(SIPHandler.getClientSocket().getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(SIPHandler.getClientSocket().getOutputStream());

			inData = inFromClient.readLine();

			outToClient.writeBytes(PDU.TRYING.toString()+"\n");

			System.out.println("Recived: " + inData);

			if(PDUParser.parse(inData)==PDU.INVITE){


				SIPInviteData tempData =  InviteParser.parse(inData);


				if(tempData==null)
					throw new Exception("Wrong formatted data");

				SIPHandler.setClientData(tempData);

				AudioStreamUDP streamer = new AudioStreamUDP();
				SIPHandler.setStreamer(streamer);
				int port = streamer.getLocalPort();
				System.out.println("Bound to local port = " + port);	

				streamer.connectTo(SIPHandler.getClientData().getIp_from(), SIPHandler.getClientData().getVoice_port());
			
				System.out.println("Streamer is set");


				outToClient.writeBytes(PDU.RINGING.toString()+" "+port+"\n");

			}else{
				throw new Exception("Not and INVITE package");
			}

		}catch(Exception e){
			e.printStackTrace();
			SIPHandler.diconnect();
		}finally{

		}	

		SIPHandler.setState(SIPHandler.getStateRinging());
		SIPHandler.notifyUser();

	}

	public void diconnect(){
		System.out.println("Disconnecting from Trying");


		SIPHandler.setClientData(null);
		try {
			SIPHandler.getClientSocket().close();
		} catch (Exception e) {
		}

		SIPHandler.setClientSocket(null);
		SIPHandler.setCallAnswered(false);

		SIPHandler.setState(SIPHandler.getStateIdle());
	}


	@Override
	public States getState() {
		// TODO Auto-generated method stub
		return States.TRYING;
	}


}
