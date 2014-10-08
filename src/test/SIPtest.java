package test;
import java.util.*;

import sip.SIPHandler;
public class SIPtest {



	public static void main(String[] args) {
		SIPHandler sh = new SIPHandler(); int choice = -1;
		
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("\n" + "State: " + sh.getState());
			
			System.out.println("1. receivedInvite");
			System.out.println("2. SendingInvite");
			System.out.println("3. SendingRinging");
			System.out.println("4. receivedAck");
			System.out.println("5. receivedOk");
			System.out.println("6. receivedReadyToConnect");
			System.out.println("7. receivedDecline");
			System.out.println("8. receivedReadyToConnect");
			System.out.println("9. receivedError");

			System.out.println("0. Quit");
			choice = scan.nextInt();
			
			switch(choice) {
			case 1: sh.receivedInvite(); break;
			case 2: sh.SendingInvite(); break;
			case 3: sh.SendingRinging(); break;
			case 4: sh.receivedAck(); break;
			case 5: sh.receivedOk(); break;
			case 6: sh.receivedReadyToConnect(); break;
			case 7: sh.receivedDecline(); break;
			case 8: sh.receivedReadyToConnect(); break;
			case 9: sh.receivedError();; break;
			}
		}while(choice != 0);
		scan.close();
	}
}
