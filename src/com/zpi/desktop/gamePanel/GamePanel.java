package com.zpi.desktop.gamePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.zpi.desktop.gameLogic.Field;
import com.zpi.desktop.gameLogic.FieldSet;
import com.zpi.desktop.gameLogic.Player;
import com.zpi.desktop.gameLogic.Players;
import com.zpi.desktop.gameLogic.Shape;



public class GamePanel extends JPanel implements KeyListener {
	
	Players ppl;
	
    public GamePanel(Players ppl) {
        setPreferredSize(new Dimension(400, 400));
        this.ppl= ppl;
        
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int zz= 0;
        
        ArrayList<Field> fs = this.ppl.getPlayerByName("adam").getFieldSet().collection;
        int size = fs.size();
        int visibleSize = 5;
        int diff = 0;
        if(visibleSize > size){
        	diff= visibleSize-size;
        	
        	for(int i=0;i<diff;i++){
        		zz+=50;
        	} 
        	for(int i=0;i<size;i++){
        		zz+=50;
        		g.drawImage(fs.get(i).pattern, 0, zz, this);
        	} 
        	
        	
        	
    	}else{	
    		for(int i=0;i<visibleSize;++i){		
    			zz+=50;
    			g.drawImage(fs.get((size-visibleSize+i)).pattern, 0, zz, this);
    		}    		
    	}

        // PROSTOKAT
        Rectangle2D rectangle = new Rectangle2D.Double(10, 10, 380, 380);
        // KOLO
        Ellipse2D circle = new Ellipse2D.Double(10, 10, 380, 380);
 
        g2d.draw(rectangle);
        g2d.draw(circle);
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
		System.out.println("keyTyped");
		
		Player p = this.ppl.getPlayerByName("adam");
		FieldSet fs = p.getFieldSet();
		
		if(fs.isFinished()){
			System.out.println("is Finished");
			return;
		}
		
		
		boolean validShape = false;
		switch(arg0.getKeyChar()){
		case 'w':
			validShape= fs.isValidShape(Shape.TRIANGLE);
			break;
		case 's':
			validShape= fs.isValidShape(Shape.CROSS);
			break;
		case 'a':
			validShape= fs.isValidShape(Shape.SQUARE);
			break;
		case 'd':
			validShape= fs.isValidShape(Shape.CIRCLE);
			break;
		}
		
		if(validShape){
			fs.popShape();
		}
		
		this.repaint();
		
	}
}
