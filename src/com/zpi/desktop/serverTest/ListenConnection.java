package com.zpi.desktop.serverTest;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.zpi.desktop.gameLogic.Config;
import com.zpi.desktop.gameLogic.FieldSet;
import com.zpi.desktop.gameLogic.FieldSetFactory;
import com.zpi.desktop.gameLogic.Player;
import com.zpi.desktop.gameLogic.Players;
import com.zpi.desktop.gamePanel.GameFrame;
import com.zpi.desktop.gamePanel.GamePanel;

public class ListenConnection implements Runnable {

	private boolean running;
	private static final int  CLIENT_COUNT_CAP = 1;
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
		this.connList = new ArrayList<Socket>();
	}
	
	@Override
	public void run() {
		running = true;
		
		try {
			ss = new ServerSocket(this.port);
		
			System.out.println("listening started");
			Players ppl = new Players();
			GameFrame gameWindow = new GameFrame(ppl);
			
			
			
			int ileGraczy = 0;
			Player player = null; 
			while(running && (connCount < connLimit))	{
				try{				
					cs = ss.accept();
					System.out.println("ACCEPT !!!!!!!!!!!!!!!!!!!!");
					player = new Player(cs.getInetAddress().toString());
					ppl.addPlayer(player);
					player.setId(ppl.getPlayersCount());
					System.out.println(player);
					ileGraczy++;

				} catch(Exception e){
					System.out.println("ServerSocket.accept() interrupted");
					break;
				}		
				addConn(cs);
				Thread clientConnection = new Thread(new ClientConnection(this, cs, player, gameWindow));
				clientConnection.start();			
				if(ileGraczy == Config.ppl){
					gameWindow.initializeGame();
					break;
				}
			}			

			FieldSetFactory factory = new FieldSetFactory(FieldSetFactory.MODE_RANDOM);
			FieldSet fieldSet= factory.generateFieldSet(Config.klocki);
			System.out.println(fieldSet.toString());
			
					
			for (Player player1 : ppl.getCollection()) {
				player1.setFieldSet(fieldSet.getFieldSetCopy());			
			}
			
			gameWindow.repaint();			
			gameWindow.itsAFinnalCountdown();

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
	public void sendMsg(String message){
		for(Socket s : connList)
		{
			PrintWriter out;
			try {
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8")), true);
				out.write(message+"\n");
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
