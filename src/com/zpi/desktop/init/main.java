package com.zpi.desktop.init;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.zpi.desktop.gameLogic.ActionXXX;
import com.zpi.desktop.gameLogic.Config;
import com.zpi.desktop.serverTest.Server;

public class main {

	public static JFrame config;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("INIT");

		config = new JFrame("Konfiguracja");
		config.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		config.setLayout(new BorderLayout());
		config.add(new JLabel("Ustawienia konfiguracyje"));
		config.setBounds(10, 10, 300, 200);

		JPanel zza = new JPanel(new GridLayout(2, 2));

		JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL, 0, 5, 2);
		framesPerSecond.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JSlider source = (JSlider) e.getSource();
				int fps = (int) source.getValue();
				Config.ppl = fps;

			}
		});

		// Create the radio buttons.
		JRadioButton easy = new JRadioButton("£atwy");
		easy.setActionCommand("£atwy");
		easy.setSelected(true);

		JRadioButton hard = new JRadioButton("Trudny");
		hard.setActionCommand("Trudny");
		hard.setSelected(false);

		JRadioButton middle = new JRadioButton("Œredni");
		middle.setActionCommand("Œredni");
		middle.setSelected(false);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(easy);
		group.add(middle);
		group.add(hard);

		easy.addActionListener(new ActionXXX(1));
		middle.addActionListener(new ActionXXX(2));
		hard.addActionListener(new ActionXXX(3));

		JPanel jPanel = new JPanel(new GridLayout(3, 1));
		group.add(easy);
		group.add(middle);
		group.add(hard);

		jPanel.add(easy);
		jPanel.add(middle);
		jPanel.add(hard);

		zza.add(new JLabel("Liczba graczy:"));
		zza.add(framesPerSecond);
		zza.add(new JLabel("opcja1"));
		zza.add(jPanel);

		framesPerSecond.setMajorTickSpacing(5);
		framesPerSecond.setMinorTickSpacing(1);
		framesPerSecond.setPaintTicks(true);
		framesPerSecond.setPaintLabels(true);

		config.add(new JLabel("Wybierz:"), BorderLayout.PAGE_START);
		config.add(zza, BorderLayout.CENTER);

		JButton zz = new JButton("GRAJ!");
		zz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Server serv = new Server();
				serv.setPort(6666);
				serv.startListening();
				config.setVisible(false);
			}
		});
		config.add(zz, BorderLayout.PAGE_END);

		config.setVisible(true);

	
	}

}
