package sip;

import java.util.Scanner;

import sip.server.SIPServer;
import sip.states.SIPHandler;

public class SIPMain {
	
	public static void main(String[] args){
		
		SIPHandler sh = new SIPHandler(); 
		String choice = "Hello";
		
		SIPServer ss = new SIPServer(sh, 15060);
		//ss.run();
		
		new Thread(ss).start(); 
		
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.println("\n" + "State: " + sh.getState());
			
			choice = scan.nextLine();			
			System.out.println("Your Input was: "+choice);
			
		}while(choice.length() != 0);
		
		System.out.println("Main stopped");
		ss.stop();
		scan.close();
		
		
		
	}
}
