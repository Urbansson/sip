package sip.state;

import java.net.Socket;

import audioStreamUDP.AudioStreamUDP;
import sip.pdu.data.SIPInviteData;



public class SIPHandler {

	private SIPState StateIdle;	
	private SIPState StateTrying; 
	private SIPState StateRinging;	
	private SIPState StateWaiting;	
	private SIPState StateConnected;

	private SIPState currentState;

	private Socket clientSocket;
	private SIPInviteData data;
	private boolean callAnswered;
	private AudioStreamUDP streamer;


	public SIPHandler(){

		this.init();
		this.currentState = new StateIdle(this);	

	}

	protected void init(){
		this.StateIdle = new StateIdle(this);
		this.StateTrying = new StateTrying(this);	

		this.StateRinging = new StateRinging(this);		
		this.StateWaiting = new StateWaiting(this);		
		this.StateConnected = new StateConnected(this);	

		this.clientSocket = null;
		this.data = null;
		this.setStreamer(null);
		this.streamer = null;
	}

	public synchronized States getState(){
		return this.currentState.getState();
	}

	public void recivedConnection(Socket clientSocket){
		currentState.recivedConnection(clientSocket);
	}

	protected void setUpResources(String command){
		currentState.setUpResources(command);
	}

	protected void notifyUser(){
		currentState.notifyUser();
	}

	public void answerCall(){
		currentState.answerCall();
	}

	public void sendIvinte(Socket clientSocket,String command){
		currentState.sendIvinte(clientSocket, command);
	}

	public void diconnect(){
		currentState.diconnect();
	}

	protected void keepAlive(){
		currentState.keepAlive();
	}

	protected void setState(SIPState newSIPState){
		this.currentState = newSIPState;
	}


	protected void setClientSocket(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	protected Socket getClientSocket(){
		return this.clientSocket;
	}

	protected void setClientData(SIPInviteData data){
		this.data = data;
	}

	protected SIPInviteData getClientData(){
		return this.data;
	}

	protected AudioStreamUDP getStreamer() {
		return streamer;
	}

	protected void setStreamer(AudioStreamUDP streamer) {
		this.streamer = streamer;
	}

	protected boolean isCallAnswered() {
		return callAnswered;
	}

	protected void setCallAnswered(boolean callAnswered) {
		this.callAnswered = callAnswered;
	}

	protected synchronized SIPState getStateIdle(){return this.StateIdle;}
	protected synchronized SIPState getStateTrying(){return this.StateTrying;}
	protected synchronized SIPState getStateRinging(){return this.StateRinging;}
	protected synchronized SIPState getStateWating(){return this.StateWaiting;}
	protected synchronized SIPState getStateConnected(){return this.StateConnected;}

}
