package sip.state;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.SocketException;

import sip.pdu.PDU;
import sip.pdu.PDUParser;


public class StateConnected extends SIPState{


	private SIPHandler SIPHandler;
	private boolean active = false;

	public StateConnected(SIPHandler newSIPHandler){
		this.SIPHandler = newSIPHandler;
	}


	public void keepAlive(){

		active = true;
		SIPHandler.getStreamer().startStreaming();


		System.out.println("Stream is open");

		SIPHandler.getClientSocket();
		String input;

		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(SIPHandler.getClientSocket().getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(SIPHandler.getClientSocket().getOutputStream());

			SIPHandler.getClientSocket().setSoTimeout(2000);
			outToClient.writeBytes(PDU.OK.toString()+"\n");

			while(true){
				outToClient.writeBytes(PDU.ALIVE.toString()+"\n");
				input = inFromClient.readLine();
				//System.out.println(input);

				if(input == null)
					break;

				synchronized (this) {
					this.wait(1000);
				}

				if(PDUParser.parse(input)==PDU.BYE){
					outToClient.writeBytes(PDU.OK.toString()+"\n");
					break;
				}

				if(active == false){

					outToClient.writeBytes(PDU.BYE.toString()+"\n");
					
					this.waitForOk(inFromClient);
					break;
				}
			}

			//System.out.println("Resetting to IDLE");
		} catch (Exception e) {
			//e.printStackTrace();
		}finally{

			try {
				SIPHandler.getClientSocket().setSoTimeout(0);
			} catch (SocketException e) {
				//e.printStackTrace();
			}
		}

		SIPHandler.getStreamer().stopStreaming();
		System.out.println("Connection closed");
		synchronized (SIPHandler) { 

			SIPHandler.setClientData(null);
			try {
				SIPHandler.getClientSocket().close();
			} catch (Exception e) {
			}

			SIPHandler.setClientSocket(null);
			SIPHandler.setCallAnswered(false);
			SIPHandler.setStreamer(null);
			SIPHandler.setState(SIPHandler.getStateIdle());
		}
	}

	public void diconnect(){
		//System.out.println("Disconnecting from Connected");

		synchronized (this) { 
			this.notify(); 
			this.active = false;
		}


	}

	@Override
	public States getState() {
		// TODO Auto-generated method stub
		return States.CONNECTED;
	}
	
	private void waitForOk(BufferedReader inFromClient) throws Exception{
		int timeOut = 1000;
		long startTime = System.currentTimeMillis();
		String input;
		
		SIPHandler.getClientSocket().setSoTimeout(timeOut);

		while(true){					
			input = inFromClient.readLine();
			if(PDUParser.parse(input) != PDU.OK){

				timeOut -=(long) (System.currentTimeMillis() - startTime);
				if(timeOut <= 0){
					SIPHandler.getClientSocket().setSoTimeout(0);
					throw new Exception("No invite recieved before timeout");
				}
				startTime = System.currentTimeMillis();
				SIPHandler.getClientSocket().setSoTimeout(timeOut);
			}else{
				break;
			}
		}
	}


}
