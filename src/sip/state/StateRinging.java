package sip.state;


public class StateRinging extends SIPState{

	private SIPHandler SIPHandler;
	
	
	public StateRinging(SIPHandler newSIPHandler){
		this.SIPHandler = newSIPHandler;
	}
	
	public void notifyUser(){
		int i = 0;
		
		
		while(!SIPHandler.isCallAnswered() && i<5){
			System.out.println("Incoming call from: " + SIPHandler.getClientData().getSip_from());
			try {
				synchronized (this) {
					this.wait(2000);
				} 
		
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			i+=1;
		}
		
		
		
		if(SIPHandler.isCallAnswered() && i<5){
			SIPHandler.keepAlive();
		}else{
			//TODO: might be executed twice, fix!
			SIPHandler.diconnect();
		}
	}
	
	public void answerCall(){
		System.out.println("Trying to answer");
		
		synchronized (this) { 
			this.notify(); 
		}
		SIPHandler.setCallAnswered(true);
		//start streamer
		//TODO: might cause errors
		SIPHandler.setState(SIPHandler.getStateConnected());
	}

	public void diconnect(){
		System.out.println("Disconnecting from Ringing");

		
		SIPHandler.setClientData(null);
		try {
			SIPHandler.getClientSocket().close();
		} catch (Exception e) {
		}
		
		SIPHandler.setClientSocket(null);
		SIPHandler.setCallAnswered(false);
		SIPHandler.setStreamer(null);
		//clear more stuff
		SIPHandler.setState(SIPHandler.getStateIdle());
		
	}
	
	@Override
	public States getState() {
		return States.RINGING;
	}


}
