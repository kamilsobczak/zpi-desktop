package com.zpi.desktop.serverTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI implements Runnable{

	private Server server;
	private boolean quit;
	
	public UI(Server server){
		this.server = server;
		this.quit = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String cmd;
			while(!quit) {
				cmd = br.readLine();
				switch(cmd){
					case "quit":
						cmdQuit();
						break;
					case "set port":
						cmdSetPort();
						break;
					case "start listen":
						cmdStartListen();
						break;
					case "stop listen":
						cmdStopListen();
						break;
					case "send":
						cmdSend("Hello from server");
						break;
					default:
						System.out.println("Unknown command: "+cmd);
						break;
				}
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
		
	private void cmdQuit(){
		quit = true;
		server.stopListening();
		server.quit();
	}
	
	private void cmdSetPort(){
		
		int port = 0;
		
		try{
			System.out.print("Port: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			port = Integer.parseInt(br.readLine());			
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		
		server.setPort(port);
	}
	
	private void cmdStartListen(){
		server.startListening();
	}
	private void cmdStopListen(){
		server.stopListening();
	}
	private void cmdSend(String message){
		server.sendMsg(message);
	}

}
