package sip.pdu;

public class PDUParser {
	public static PDU parse(String toParse) throws Exception{
		if(toParse==null)
			throw new Exception("Trying to Parse NULL");
		String pduStr = toParse.split(" ")[0]; 
		switch(pduStr){
		case "INVITE": return PDU.INVITE;
		case "TRYING": return PDU.TRYING;
		case "RINGING": return PDU.RINGING;
		case "OK": return PDU.OK;
		case "ACK": return PDU.ACK;
		case "BYE": return PDU.BYE;
		case "ALIVE": return PDU.ALIVE;
		case "BUSY": return PDU.BUSY;
		default: return PDU.ERROR;
		}
	}
}
