package com.zpi.desktop.serverTest;

import java.net.Socket;

public class ClientConnection implements Runnable {

	private ListenConnection listeningThread;
	private Socket socket;
	
	public ClientConnection(ListenConnection listeningThread, Socket socket){
		this.listeningThread = listeningThread;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
