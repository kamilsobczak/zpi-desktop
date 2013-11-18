package com.zpi.desktop.gamePanel;

import java.awt.Color;
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
                
        
       
        
        int totalPlayers = this.ppl.getPlayersCount();
        System.out.println("Total Players: "+totalPlayers);
  
        ArrayList<Player> playerCollection= this.ppl.getCollection();
        
        this.setBackground(Color.MAGENTA);
         
        
        int TOTAL_WIDTH = this.getWidth();
        int TOTAL_HEIGHT = this.getHeight();
        
        int TOTAL_X_POSSITION = 100;
        int TOTAL_Y_POSSITION = 0;
        int TOTAL_SHAPE_SIZE = 60;
        int PADDING_BETWEEN_PLAYERS = 10;
        int PADDING_BETWEEN_SHAPES = 10;
        
        System.out.println("TOTAL_WIDTH: "+TOTAL_WIDTH);
        System.out.println("GAME PANEL: "+   ((PADDING_BETWEEN_PLAYERS + TOTAL_SHAPE_SIZE)*totalPlayers)      );
        
        
        
        TOTAL_X_POSSITION=  (TOTAL_WIDTH - ((PADDING_BETWEEN_PLAYERS + TOTAL_SHAPE_SIZE)*totalPlayers) )/2;
        System.out.println(TOTAL_X_POSSITION);
        
        
        
        
        int xAxis= TOTAL_X_POSSITION;
        int yAxis= TOTAL_Y_POSSITION;

        for (Player player : playerCollection) {
        	yAxis+=12;
        	g.drawString(player.getName(), xAxis, yAxis);
        	
        	  int size = player.getFieldSet().getSize();
              int visibleSize = 5;
              int diff = 0;
              if(visibleSize > size){
              	diff= visibleSize-size;            	
              	for(int i=0;i<diff;i++){
              		yAxis+=TOTAL_SHAPE_SIZE+PADDING_BETWEEN_SHAPES;
              	} 
              	for(int i=0;i<size;i++){
              		g.drawImage(player.getFieldSet().collection.get(i).pattern, xAxis, yAxis, this);   
              		yAxis+=TOTAL_SHAPE_SIZE+PADDING_BETWEEN_SHAPES;              		           		          		  	         		
              	}            	
          	}else{	
          		for(int i=0;i<visibleSize;++i){		         			
          			g.drawImage(player.getFieldSet().collection.get((size-visibleSize+i)).pattern,xAxis, yAxis, this);	
          			yAxis+=TOTAL_SHAPE_SIZE+PADDING_BETWEEN_SHAPES;
          		}    		
          	}      	
             g.drawString(player.getName(), xAxis, yAxis);
          	xAxis+=TOTAL_SHAPE_SIZE+ PADDING_BETWEEN_PLAYERS;
          	yAxis=TOTAL_Y_POSSITION;
          	
		 }//players loop!
        
        
      

 
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
		
		FieldSet adam =  this.ppl.getPlayerByName("adam").getFieldSet();
		FieldSet adam2 =  this.ppl.getPlayerByName("adam2").getFieldSet();
		
		if(adam.isFinished()){
		//	System.out.println("is Finished");
		//	return;
		}
		
		//System.out.println(arg0.getKeyChar());
		
		
		boolean validShapeAdam = false;
		boolean validShapeAdam2 = false;
		switch(arg0.getKeyChar()){
		case 'w':
			validShapeAdam= adam.isValidShape(Shape.TRIANGLE);
			break;
		case 's':
			validShapeAdam= adam.isValidShape(Shape.CROSS);
			break;
		case 'a':
			validShapeAdam= adam.isValidShape(Shape.SQUARE);
			break;
		case 'd':
			validShapeAdam= adam.isValidShape(Shape.CIRCLE);
			break;
		}
		
		if(validShapeAdam){
			adam.popShape();
		}
		
		
		switch(arg0.getKeyChar()){
		case '8':
			validShapeAdam2= adam2.isValidShape(Shape.TRIANGLE);
			break;
		case '5':
			validShapeAdam2= adam2.isValidShape(Shape.CROSS);
			break;
		case '4':
			validShapeAdam2= adam2.isValidShape(Shape.SQUARE);
			break;
		case '6':
			validShapeAdam2= adam2.isValidShape(Shape.CIRCLE);
			break;
		}
		
		if(validShapeAdam2){
			adam2.popShape();
		}
		
		
		
		this.repaint();
		
	}
}
