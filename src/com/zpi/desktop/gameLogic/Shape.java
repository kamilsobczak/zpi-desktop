package com.zpi.desktop.gameLogic;

public enum Shape {

	SQUARE("square.png"), 
	CIRCLE("circle.png"), 
	TRIANGLE("triangle.png"), 
	CROSS("cross.png");

	String imagePath;
	
	public String getImagePath(){
		return this.imagePath;
	}

	private Shape(String path) {
		imagePath = path;
	}
}
