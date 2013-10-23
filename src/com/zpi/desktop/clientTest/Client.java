package com.zpi.desktop.clientTest;

public class Client {
	private boolean quit;
	
	public Client(){
		
		quit = false;
		
		Thread ui = new Thread(new UI(this));
		ui.start();		
		System.out.println("Client");
		
	}
	public void quit(){
		quit = true;
	}
}
