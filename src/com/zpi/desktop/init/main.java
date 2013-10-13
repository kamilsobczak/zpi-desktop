package com.zpi.desktop.init;

import java.awt.EventQueue;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.zpi.desktop.gameLogic.FieldSet;
import com.zpi.desktop.gameLogic.FieldSetFactory;
import com.zpi.desktop.gameLogic.Player;
import com.zpi.desktop.gameLogic.Players;
import com.zpi.desktop.gamePanel.GamePanel;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello 3!");

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
			
				Players ppl = new Players();
				ppl.addPlayer(new Player("adam"));	
				System.out.println(ppl.toString());
					
				
				FieldSetFactory factory = new FieldSetFactory(FieldSetFactory.MODE_RANDOM_NOT_REPEATABLE);
				FieldSet fieldSet= factory.generateFieldSet(3);
				System.out.println(fieldSet.toString());
				
				
				ppl.getPlayerByName("adam").setFieldSet(fieldSet);
				JFrame gameWindow = new JFrame("ex");
				gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameWindow.setBounds(10, 10, 500, 500);
				JPanel pp = new GamePanel(ppl); 
				gameWindow.add(pp);
				gameWindow.addKeyListener((KeyListener)pp);
				gameWindow.setVisible(true);
				  
				
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
	}

}
