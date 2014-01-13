package com.zpi.desktop.clientTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {

	private Client clientThread;
	private Socket socket;
	    
    private PrintWriter out;
    private BufferedReader in;
	
    private String msg;
    
	public ServerConnection(Client clientThread, Socket socket){
		this.clientThread = clientThread;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

            while (true) {
                msg = in.readLine();

                if (msg != null) {                	
                	System.out.println("odebrano: "+msg);
                }
                msg = null;
            }

        } catch (Exception e) {

        	e.printStackTrace();

        } finally {
            try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }		
	}
	
	public void sendMsg(String message){
		out.write(message+"\n");
		out.flush();
	}
	
	public void close(){
		
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.close();
		
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
