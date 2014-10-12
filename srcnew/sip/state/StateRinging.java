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

		//clear more stuff
		SIPHandler.setState(SIPHandler.getStateIdle());
		
	}
	
	@Override
	public States getState() {
		// TODO Auto-generated method stub
		return States.RINGING;
	}


}
