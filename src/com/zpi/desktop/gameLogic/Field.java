package com.zpi.desktop.gameLogic;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Field {

	
	public BufferedImage pattern;
	public Shape shape;
	
	


	public Field(Shape shape){

		this.shape = shape;
		
		
		try {
			pattern = ImageIO.read(getClass()
					.getResource(
							"/images/"+shape.getImagePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	@Override
	public String toString() {
		return "Field [shape=" + shape + "]";
	}
	

	
	public BufferedImage getPattern() {
		return pattern;
	}



	public void setPattern(BufferedImage pattern) {
		this.pattern = pattern;
	}



	public Shape getShape() {
		return shape;
	}



	public void setShape(Shape shape) {
		this.shape = shape;
	}

	
}
