package com.zpi.desktop.gamePanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.zpi.desktop.gameLogic.Players;

public class GameFrame extends JFrame {

	
	public GameFrame zz = this;
	private static final String WAITING_FOR_PPL = "Oczekiwanie na graczy...";
	private static final String START_GAMING = "ZACZYNAMY!";

	private JLabel informationLabel = new JLabel("Rozpoczynanie...");
	private Players ppls;
	public GamePanel gamePanel;

	public GameFrame(Players ppl) {

		super("GRA");
		ppls = ppl;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10, 10, 600, 500);
		this.setLayout(new BorderLayout());
		this.add(informationLabel, BorderLayout.PAGE_START);
		setText(WAITING_FOR_PPL);
		this.setVisible(true);

	}

	public void setText(String text) {
		informationLabel.setText(text);
	}
 

	public void initializeGame() {
		setText(START_GAMING);
		gamePanel = new GamePanel(ppls, this);
		this.add(gamePanel, BorderLayout.CENTER);
		gamePanel.setVisible(true);
		this.addKeyListener((KeyListener) gamePanel);
		this.setVisible(true);
	}

	public void itsAFinnalCountdown() {
		 
		(this.gamePanel).countDown=4;
		do {	
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}

			this.repaint();
			(this.gamePanel).countDown--;

		} while (( this.gamePanel).countDown > 0);

	}

}
