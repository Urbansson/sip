package sip;

import java.util.Scanner;

import sip.state.SIPHandler;


public class SIPMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String input = null;
		SIPHandler sipHandler = new SIPHandler();

		SIPClientThread client = null;
		Thread clientT = null;

		SIPServer server = null;
		Thread serverT = null;


		//t.start();
		int temp = 0;
		String sipId = null;
		String tempSipId = null;
		String tempSipIp= null;

		do{
			System.out.println("Enter your sip id! ");
			sipId = scan.nextLine();
			System.out.println("Is "+sipId +" correct! (yes/no)");
			input = scan.nextLine();

		}while(!input.equalsIgnoreCase("yes"));

		do{


			System.out.println("\n" + "State: " + sipHandler.getState());
			System.out.println("1. Start Server");
			System.out.println("2. Send invinte");
			System.out.println("3. Answer Call");
			System.out.println("4. Disconnect Call");
			System.out.println("0. Quit");
			input = scan.nextLine();

			if(input.equals("1")){
				server = new SIPServer(sipHandler);
				serverT = new Thread(server);
				serverT.start();
			}
			else if(input.equals("2")){

				
				System.out.println("Enter Sip id");
				tempSipId = scan.nextLine();
				System.out.println("Enter Sip ip");
				tempSipIp = scan.nextLine();

				client = new SIPClientThread(sipHandler,sipId+ " "+tempSipId+" "+tempSipIp);
				clientT = new Thread(client);
				clientT.start();
			
			
			}
			
			else if(input.equals("3")){
				sipHandler.answerCall();
			}
			else if(input.equals("4")){
				sipHandler.diconnect();
			}
			else if(input.equals("0")){
				if(server != null)
					server.stop();
				if(client!=null)
					client.stop();
				break;
			}else{
				System.out.println("Not a valid command");
			}

		}while(!input.toLowerCase().startsWith("exit")|| temp==0);
		scan.close();
	}

}