package sip.pdu;

import java.net.InetAddress;

import sip.pdu.data.SIPData;

public class InviteParser {

	public static SIPData parse(String inviteMessage){

		String sip_to;
		String sip_from ;
		InetAddress ip_to;
		InetAddress ip_from; 
		int voice_port; 

		System.out.println(inviteMessage);
		String []dataStr = inviteMessage.split(" "); 
		//System.out.println(dataStr[1]+" "+dataStr[2]+" "+dataStr[3]+" "+dataStr[4]+" "+dataStr[5]);

		try {
			sip_to = dataStr[1];
			sip_from = dataStr[2];
			ip_to = InetAddress.getByName(dataStr[3]);
			ip_from = InetAddress.getByName(dataStr[4]);
			voice_port = Integer.parseInt(dataStr[5]);
		} catch (Exception e) {
			System.out.println("Failed to parse");
			//e.printStackTrace();
			return null;
		}	
		
		return new SIPData(sip_to, sip_from, ip_to, ip_from, voice_port);
	}


}

