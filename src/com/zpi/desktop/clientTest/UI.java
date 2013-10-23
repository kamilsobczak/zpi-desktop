package com.zpi.desktop.clientTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI implements Runnable{

	private Client client;
	private boolean quit;
	
	public UI(Client client){
		this.client = client;
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
					default: break;
				}
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	private void cmdQuit(){
		quit = true;
		client.quit();
	}
	
}
