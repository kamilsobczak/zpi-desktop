package com.zpi.desktop.gamePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.zpi.desktop.gameLogic.Config;
import com.zpi.desktop.gameLogic.FieldSet;
import com.zpi.desktop.gameLogic.FieldSetFactory;
import com.zpi.desktop.gameLogic.Player;
import com.zpi.desktop.gameLogic.Players;
import com.zpi.desktop.gameLogic.Shape;

public class GamePanel extends JPanel implements KeyListener {

	Players ppl;
	public String result;
	public int countDown = 4;
	public GameFrame gf;

	public GamePanel(Players ppl, GameFrame gf) {
		setPreferredSize(new Dimension(400, 400));
		this.gf = gf;
		this.ppl = ppl;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (countDown > 0) {

			g.setColor(new Color(134, 211, 111));

			g.setFont(new Font("Arial", Font.PLAIN, 200));
			g.drawString(countDown + "", 300, 300);

			return;
		}

		int totalPlayers = this.ppl.getPlayersCount();

		ArrayList<Player> playerCollection = this.ppl.getCollection();

		this.setBackground(new Color(230, 230, 230));

		int TOTAL_WIDTH = this.getWidth();

		int TOTAL_X_POSSITION = 100;
		int TOTAL_Y_POSSITION = 30;
		int TOTAL_SHAPE_SIZE = 60;
		int PADDING_BETWEEN_PLAYERS = 10;
		int PADDING_BETWEEN_SHAPES = 10;
 

		TOTAL_X_POSSITION = (TOTAL_WIDTH - ((PADDING_BETWEEN_PLAYERS + TOTAL_SHAPE_SIZE) * totalPlayers)) / 2;

		int xAxis = TOTAL_X_POSSITION;
		int yAxis = TOTAL_Y_POSSITION;

		for (Player player : playerCollection) {
			
			yAxis += 12;
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(new Color(196,196,196));
			g.drawString("Gracz:", 0, yAxis);
			
			g.drawString(player.getId()+"", xAxis+29, yAxis);

			int size = player.getFieldSet().getSize();
			int visibleSize = 5;
			int diff = 0;
			if (visibleSize > size) {
				diff = visibleSize - size;
				for (int i = 0; i < diff; i++) {
					yAxis += TOTAL_SHAPE_SIZE + PADDING_BETWEEN_SHAPES;
				}
				for (int i = 0; i < size; i++) {
					g.drawImage(player.getFieldSet().collection.get(i).pattern,
							xAxis, yAxis, this);
					yAxis += TOTAL_SHAPE_SIZE + PADDING_BETWEEN_SHAPES;
				}
			} else {
				for (int i = 0; i < visibleSize; ++i) {
					g.drawImage(
							player.getFieldSet().collection.get((size
									- visibleSize + i)).pattern, xAxis, yAxis,
							this);
					yAxis += TOTAL_SHAPE_SIZE + PADDING_BETWEEN_SHAPES;
				}
			} 
			
			if (player.isFinish() && !player.dodano) {
				int points = 0;
				
			for (Player player1 : ppl.getCollection()) {
					System.out.println("tera gracz"+player1.getId()+" z sizem"+player1.getFieldSet().getSize());
					if(player1.getId() != player.getId()){
						points +=player1.getFieldSet().getSize();
					}					

			}
				System.out.println("Gracz"+player.getId()+"skonczyl i dostal"+points);
				player.addPoints(points);
				player.dodano=true;
			}
			g.drawString(player.getPoints()+"", xAxis+0, yAxis+35);
			g.drawString("Punkty:", 0, yAxis+35);
		
			xAxis += TOTAL_SHAPE_SIZE + PADDING_BETWEEN_PLAYERS;
			yAxis = TOTAL_Y_POSSITION;


		}// players loop!
		if(ppl.isEndGame()){
			
			
			
			System.out.println("KONIECq :)");
			result = "Wyniki: \n";
			for (Player player1 : ppl.getCollection()) {
				result+="Gracz"+player1.getId()+": "+player1.getPoints()+" p.\n";
	
			}
			result+="Czy wy³¹czyæ grê?\n";
			
			

			
					
			EventQueue.invokeLater(new Runnable() {			
				@Override
				public void run() { 
					gf.setText("Gramy...");
					gf.remove(gf.gamePanel);
					gf.repaint();
					
					
					if(JOptionPane.showConfirmDialog(gf, result) == 1){
						for (Player player1 : ppl.getCollection()) {
							player1.restartStatistics();
						}
						FieldSetFactory factory = new FieldSetFactory(FieldSetFactory.MODE_RANDOM);
						FieldSet fieldSet= factory.generateFieldSet(Config.klocki);
								
						for (Player player1 : ppl.getCollection()) {
							player1.setFieldSet(fieldSet.getFieldSetCopy());	
							player1.dodano = false;
						}
						gf.add(gf.gamePanel, BorderLayout.CENTER);
						gf.gamePanel.countDown=4;
						gf.repaint();
						gf.revalidate();
						gf.validate();
						gf.itsAFinnalCountdown();
						
					}else{
						System.exit(1);
					}
					
					
					// TODO Auto-generated method stub					
				//	JOptionPane.showMessageDialog(gf, result);	 
				}
			});
		}
		
		
		
		
	}

	public boolean isCountdownDone(){
		return (countDown == 0);
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("keyPressed");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("keyReleased");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

//		Player adam = this.ppl.getPlayerByName("adam");
//		Player adam2 = this.ppl.getPlayerByName("adam2");
//
//		Shape shape = null;
//
//		switch (arg0.getKeyChar()) {
//		case 'w':
//			shape = Shape.TRIANGLE;
//			break;
//		case 's':
//			shape = Shape.CROSS;
//			break;
//		case 'a':
//			shape = Shape.SQUARE;
//			break;
//		case 'd':
//			shape = Shape.CIRCLE;
//			break;
//		}
//
//		if (!adam.isFinish() && shape != null) {
//			adam.selectShape(shape);
//		} else {
//			System.out.println("KONIEC!");
//		}
//
//		shape = null;
//		switch (arg0.getKeyChar()) {
//		case '8':
//			shape = Shape.TRIANGLE;
//			break;
//		case '5':
//			shape = Shape.CROSS;
//			break;
//		case '4':
//			shape = Shape.SQUARE;
//			break;
//		case '6':
//			shape = Shape.CIRCLE;
//			break;
//		}
//		if (!adam2.isFinish() && shape != null) {
//			adam2.selectShape(shape);
//		} else {
//
//			System.out.println("KONIEC!");
//		}
//
//		this.repaint();

	}
}
