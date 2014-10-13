package sip;

import java.util.Scanner;

import sip.state.SIPHandler;


public class SIPClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String input = null;
		SIPHandler sipHandler = new SIPHandler();

		SIPClientThread client = null;
		Thread clientT = null;

		SIPServer server = new SIPServer(sipHandler);
		Thread serverT = new Thread(server);


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
				server.stop();
				if(client!=null)
					client.stop();
				break;
			}else{
				System.out.println("Not a valid command");
			}
			/*
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

			 */
		}while(!input.toLowerCase().startsWith("exit")|| temp==0);
		scan.close();
	}

}