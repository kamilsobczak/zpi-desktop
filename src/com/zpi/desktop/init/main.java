package com.zpi.desktop.init;

import java.awt.EventQueue;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.zpi.desktop.gameLogic.FieldSet;
import com.zpi.desktop.gameLogic.FieldSetFactory;
import com.zpi.desktop.gameLogic.Player;
import com.zpi.desktop.gameLogic.Players;
import com.zpi.desktop.gameLogic.PointsManager;
import com.zpi.desktop.gamePanel.GamePanel;
import com.zpi.desktop.serverTest.*;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("INIT");

		
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("jeden");
		list1.add("dwa");
		
		ArrayList<String> list2 = new ArrayList<String>(list1);
		list2.add("trzy");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
			
//				Players ppl = new Players();
//				
//				//ppl.addPlayer(new Player("gracz2"));	
//				ppl.addPlayer(new Player("adam"));	
//				ppl.addPlayer(new Player("adam2"));	 
//				System.out.println(ppl.toString());
//				 
//				PointsManager pointsManager = new PointsManager();
//				pointsManager.setInvalid(-1);
//				pointsManager.setValid(1);
//				
//				
//				FieldSetFactory factory = new FieldSetFactory(FieldSetFactory.MODE_RANDOM);
//				FieldSet fieldSet= factory.generateFieldSet(13);
//				System.out.println(fieldSet.toString());
//				
//				
//				ppl.getPlayerByName("adam").setFieldSet(fieldSet.getFieldSetCopy());
//				ppl.getPlayerByName("adam").setPointsManager(pointsManager);
//				ppl.getPlayerByName("adam2").setFieldSet(fieldSet);
//				ppl.getPlayerByName("adam2").setPointsManager(pointsManager);
//				
//				JFrame gameWindow = new JFrame("ex");
//				gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				gameWindow.setBounds(10, 10, 500, 500);
//				JPanel pp = new GamePanel(ppl); 
//				gameWindow.add(pp);
//				gameWindow.addKeyListener((KeyListener)pp);
//				gameWindow.setVisible(true);
				
				Server serv = new Server();
				serv.setPort(6666);
				serv.startListening();
				
				
			
				  
				
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
	}

}
