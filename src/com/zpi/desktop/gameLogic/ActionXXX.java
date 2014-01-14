package com.zpi.desktop.gameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionXXX implements ActionListener {

	int latwy = 1;

	public ActionXXX(int ss) {
		this.latwy = ss;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch(latwy){
		case 1:
			Config.klocki = 10;
			Config.rozne = false;
			Config.poziom = 2;
			break;
			
		case 2:
			Config.klocki = 25;
			Config.rozne = false;
			Config.poziom = 3;
			break;
			
		case 3:
			Config.klocki = 50;
			Config.poziom = 4;
			Config.rozne = true;
			break;
		}
		
		
	}

}
