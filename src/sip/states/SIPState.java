package sip.states;

public abstract class SIPState {

	public abstract String getState();
	
	public SIPState receivedInvite(){return this;}
	public SIPState SendingRinging(){return this;}
	public SIPState receivedAck(){return this;}
	
	public SIPState SendingInvite(){return this;}
	public SIPState receivedOk(){return this;}
	public SIPState receivedDecline(){return this;}

	public SIPState receivedError(){return this;}
	public SIPState receivedBye(){return this;}
	
	public SIPState receivedReadyToConnect(){return this;}

	

}
