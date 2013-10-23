package com.zpi.desktop.serverTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ListenConnection implements Runnable {

	private boolean running;
	
	private int port;
	private int connLimit;
	private int connCount;
	private ServerSocket ss;
	private Socket cs;
	
	private ArrayList<Socket> connList;
	
	public ListenConnection(int port, int connLimit){
		this.port = port;
		this.connLimit = connLimit;
		this.connCount = 0;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		running = true;
		
		try {
			ss = new ServerSocket(this.port);
			
			System.out.println("listening started");
			
			while(running && (connCount < connLimit))	{
				try{				
					cs = ss.accept();
				} catch(Exception e){
					System.out.println("ServerSocket.accept() interrupted");
					break;
				}
				
				this.addConn(cs);
				Thread clientConnection = new Thread(new ClientConnection(this, cs));
				clientConnection.start();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		running = false;
		try {
			ss.close();
		} catch (IOException e) {
//			e.printStackTrace();
		}
		System.out.println("listening stopped");
	}
	public void addConn(Socket s){
		connCount++;
		connList.add(s);
		System.out.println(s.toString()+" connected");
	}
	public void removeConn(Socket s){		
		connList.remove(s);
		connCount--;
		System.out.println(s.toString()+" disconnected");
	}

}
