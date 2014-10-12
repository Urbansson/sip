package sip.pdu.data;

import java.net.InetAddress;

public class SIPInviteData {

	private String sip_to;
	private String sip_from;
	private InetAddress ip_to;
	private InetAddress ip_from; 
	private int voice_port;



	public SIPInviteData(String sip_to,String sip_from,InetAddress ip_to,InetAddress ip_from, int voice_port){
		this.sip_to = sip_to;
		this.sip_from = sip_from;
		this.ip_to = ip_to;
		this.ip_from = ip_from; 
		this.voice_port = voice_port;
	}
	
	public String getSip_to() {
		return sip_to;
	}

	public String getSip_from() {
		return sip_from;
	}

	public InetAddress getIp_to() {
		return ip_to;
	}

	public InetAddress getIp_from() {
		return ip_from;
	}

	public int getVoice_port() {
		return voice_port;
	}
	
	public String toString(){
		return this.sip_to+" "+this.sip_from+" "+this.ip_to.toString().substring(1)+" "+this.ip_from.toString().substring(1)+" "+this.voice_port+"\n";
	}
	
	
}
