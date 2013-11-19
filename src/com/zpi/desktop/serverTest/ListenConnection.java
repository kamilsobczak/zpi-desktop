package com.zpi.desktop.serverTest;

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
import javax.swing.JPanel;

import com.zpi.desktop.gameLogic.FieldSet;
import com.zpi.desktop.gameLogic.FieldSetFactory;
import com.zpi.desktop.gameLogic.Player;
import com.zpi.desktop.gameLogic.Players;
import com.zpi.desktop.gameLogic.PointsManager;
import com.zpi.desktop.gamePanel.GamePanel;

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
		this.connList = new ArrayList<Socket>();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		running = true;
		
		try {
			ss = new ServerSocket(this.port);
			
			System.out.println("listening started");
			JFrame gameWindow = new JFrame("Serwer");
			Players ppl = new Players();
			int ileGraczy = 0;
			Player player = null;
			JFrame okno = new JFrame("oczekiwanie");
			okno.setBounds(10, 10, 600, 400);
			okno.show();
			while(running && (connCount < connLimit))	{
				try{				
					cs = ss.accept();
					System.out.println("ACCEPT !!!!!!!!!!!!!!!!!!!!");
					player = new Player(cs.getInetAddress().toString());
					ppl.addPlayer(player);
					System.out.println(player);
					ileGraczy++;

				} catch(Exception e){
					System.out.println("ServerSocket.accept() interrupted");
					break;
				}		
				addConn(cs);
				Thread clientConnection = new Thread(new ClientConnection(this, cs, player, gameWindow));
				clientConnection.start();		
//				System.out.println(cs.toString()+" connected");		
				if(ileGraczy == 2){
					okno.hide();
					break;
				}
			}			

			
			 
			PointsManager pointsManager = new PointsManager();
			pointsManager.setInvalid(-1);
			pointsManager.setValid(1);
			
			
			FieldSetFactory factory = new FieldSetFactory(FieldSetFactory.MODE_RANDOM);
			FieldSet fieldSet= factory.generateFieldSet(13);
			System.out.println(fieldSet.toString());
			
			
			//ppl.getPlayerByName("adam").setFieldSet(fieldSet.getFieldSetCopy());
			//ppl.getPlayerByName("adam").setPointsManager(pointsManager);
			
			
			for (Player player1 : ppl.getCollection()) {
				player1.setFieldSet(fieldSet.getFieldSetCopy());
				player1.setPointsManager(pointsManager);
			}
			
			
			gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameWindow.setBounds(10, 10, 500, 500);
			JPanel pp = new GamePanel(ppl); 
			gameWindow.add(pp);
			gameWindow.addKeyListener((KeyListener)pp);
			gameWindow.setVisible(true);

			
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
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
