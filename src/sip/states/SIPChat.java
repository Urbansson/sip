package sip.states;

import java.net.Socket;


public class SIPChat {

	private SIPState StateIdle;	
	private SIPState StateTrying;	
	private SIPState StateRinging;	
	private SIPState StateWaiting;	
	private SIPState StateConnected;	
	private SIPState StateDisconnected;	

	SIPState currentState;
	
	private Socket clientSocket;
	//UDP streamer
	
	
	public SIPChat(){
		
		this.StateIdle = new StateIdle(this);
		this.StateTrying = new StateTrying(this);		
		this.StateRinging = new StateRinging(this);		
		this.StateWaiting = new StateWaiting(this);		
		this.StateConnected = new StateConnected(this);		
		this.StateDisconnected = new StateDisconnected(this);		
		this.currentState = new StateIdle(this);
		
	}	
	
	public void setSIPState(SIPState newSIPState){
		this.currentState = newSIPState;
	}
	
	public boolean reciveInvite(Socket clientSocket){
		return currentState.recivedInvite(clientSocket);		
	}
	
	public void tryingSucces(){
		currentState.tryingCompleated();	
	}
	
	public void recivedAck(){
		currentState.recivedAck();	
	}
	
	public void sendInvite(){
		currentState.sendingInvite();;	
	}
	
	public void reciveOk(){
		currentState.recivedOK();
	}
	
	public void reciveDecline(){
		currentState.recivedDecline();
	}
	
	public void recivedBye(){
		currentState.receivedBye();
	}
	
	public void recivedError(){
		currentState.receivedError();
	}
	
	public void cleanupCompleated(){
		currentState.cleanupCompleated();
	}
	
	public States getState(){
		return this.currentState.getState();
	}
	
	protected void setClient(Socket clientSocket){
		this.clientSocket = clientSocket;
	}
	
	protected SIPState getStateIdle(){return StateIdle;}
	protected SIPState getStateTrying(){return StateTrying;}
	protected SIPState getStateRinging(){return StateRinging;}
	protected SIPState StateWating(){return StateWaiting;}
	protected SIPState getStateConnected(){return StateConnected;}
	protected SIPState getStateDisconnected(){return StateDisconnected;}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SIPChat SIPC = new SIPChat();
		//Create server thread(SIPC)
		
		
		
		
	}

}
