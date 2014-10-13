package sip;

import java.util.Scanner;

import sip.state.SIPHandler;


public class SIPClient {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String input = "Hello";
		SIPHandler sipHandler = new SIPHandler();
		
		SIPClientThread client = null;
		Thread clientT = null;
		
		SIPServer server = new SIPServer(sipHandler);
		Thread serverT = new Thread(server);
		
		
		//t.start();
		int temp = 0;
		do{
			//input = scan.nextLine();

			System.out.println("\n" + "State: " + sipHandler.getState());
			System.out.println("1. recivedConnection");
			System.out.println("2. sendIvinte");
			System.out.println("4. answerCall");
			System.out.println("6. diconnect");
			System.out.println("0. Quit");
			temp = scan.nextInt();
			
			switch (temp) {
			case 1:
				sipHandler.recivedConnection(null);
				break;
			case 2:
				sipHandler.sendIvinte(null,"");
				break;
			case 4:
				sipHandler.answerCall();
				break;
			case 6:
				sipHandler.diconnect();
				break;
			case 7:
				client = new SIPClientThread(sipHandler,"thomas.lind@sth.kth.se anders.lindstrom@sth.kth.se 127.0.0.1");
				clientT = new Thread(client);
				clientT.start();
				break;
			case 17:
				
				serverT.start();
				break;

			case 0:
				server.stop();
				if(client!=null)
					client.stop();
				break;

			default:
				break;
			}
			
			System.out.println(temp);
		}while(!input.toLowerCase().startsWith("exit")|| temp==0);
		scan.close();
	}

}