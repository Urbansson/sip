package sip;

import java.util.Scanner;

import sip.states.SIPChat;

public class SIPClient {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String input;
		SIPChat sipChat = new SIPChat();
		SIPServer s = new SIPServer(sipChat);
		Thread t = new Thread(s);
		t.start();
		System.out.println("Hello");
		do{
			input = scan.nextLine();
			System.out.println(sipChat.getState());
			System.out.println(input);
			
		}while(!input.toLowerCase().startsWith("exit"));
		s.stop();
		scan.close();
	}

}