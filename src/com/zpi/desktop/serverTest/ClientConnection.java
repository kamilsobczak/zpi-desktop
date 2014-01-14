package com.zpi.desktop.serverTest;

import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.zpi.desktop.gameLogic.FieldSet;
import com.zpi.desktop.gameLogic.FieldSetFactory;
import com.zpi.desktop.gameLogic.Player;
import com.zpi.desktop.gameLogic.Players;
import com.zpi.desktop.gameLogic.PointsManager;
import com.zpi.desktop.gameLogic.Shape;
import com.zpi.desktop.gamePanel.GamePanel;

public class ClientConnection implements Runnable {

	private ListenConnection listeningThread;
	private Socket socket;
	    
    private PrintWriter out;
    private BufferedReader in;
	private Player player;
    private String msg;
    private JFrame gameFrame;
	public ClientConnection(ListenConnection listeningThread, Socket socket, Player player, JFrame gameFrame ){
		this.listeningThread = listeningThread;
		this.socket = socket;
		this.player=player;
		this.gameFrame = gameFrame;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("!!jeste w runie!!!!!!!!!!!3!!!!!!!");
        
            //send the message to the server
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);

            //receive the message which the server sends back
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            
            //in this while the client listens for the messages sent by the server
            while (true) {
         
                msg = in.readLine();
                	System.out.println(msg+"!!!!!!!!!!!!!!!!!!!!");
                if (msg != null) {
                    //call the method messageReceived from MyActivity class
                	
                	System.out.println("odebrano: "+msg);
                	
                	
                	
                	Shape shape = null;
                	
                	
                	switch(msg){
                	
                	case "kwadrat":
                		shape = Shape.SQUARE;
                		break;
                	case "kolko":
                		shape = Shape.CIRCLE;
                		break;
					case "trojkat":
						shape = Shape.TRIANGLE;           		
					    break;
					case "krzyzyk":
						shape = Shape.CROSS;
						break;
                	}
        	
                	if(shape != null){

                		if(!player.isFinish() && player != null){
                			player.selectShape(shape);	
            			}else{
            				System.out.println("KONIEC!");
            			}
            			
                		gameFrame.repaint();
                		
	
                		
                	}else{
                		System.out.println("nie moja wina");
                	}
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
//                	sendMsg("Server - Odebra³em: "+msg);
                }
                msg = null;
            }

        } catch (Exception e) {

        	e.printStackTrace();

        } finally {
            //the socket must be closed. It is not possible to reconnect to this socket
            // after it is closed, which means a new socket instance has to be created.
            try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }		
	}
	
	public void sendMsg(String message){
		out.write(message+"\n");
		out.flush();
	}
	
}
