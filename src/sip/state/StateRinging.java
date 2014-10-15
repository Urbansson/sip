package sip.state;

import AudioPlayer.Player;


public class StateRinging extends SIPState{

	private SIPHandler SIPHandler;


	public StateRinging(SIPHandler newSIPHandler){
		this.SIPHandler = newSIPHandler;
	}

	public void notifyUser(){
		int i = 0;


		Player player = new Player("RingingSound.wav");
		new Thread(player).start();

		System.out.println("Incoming call from: " + SIPHandler.getClientData().getSip_from());

		try{
			synchronized (this) {
				this.wait(20000);
			} 
		}catch (Exception e) {
			//e.printStackTrace();
		}
		
/*
		while(!SIPHandler.isCallAnswered() && i<5){

			try {
				System.out.println("Incoming call from: " + SIPHandler.getClientData().getSip_from());
				//this.play("RingingSound.wav");


			} catch (Exception e) {
				//e.printStackTrace();
				break;
			}
			i+=1;
		}
*/
		
		
		player.stop();

		if(SIPHandler.isCallAnswered() && i<5){
			SIPHandler.setState(SIPHandler.getStateConnected());
			SIPHandler.keepAlive();
		}else{
			SIPHandler.diconnect();
		}

	}

	public void answerCall(){
		System.out.println("Trying to answer");

		synchronized (this) { 
			this.notify(); 
			SIPHandler.setCallAnswered(true);
		}
	}

	public void diconnect(){
		System.out.println("Disconnecting from Ringing");

		synchronized (this) { 
			this.notify(); 
		}
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

	@Override
	public States getState() {
		return States.RINGING;
	}

}
