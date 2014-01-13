package com.zpi.desktop.gameLogic;

import java.util.Random;

public class FieldSetFactory {

	public static int MODE_RANDOM = 0;
	public static int MODE_RANDOM_NOT_REPEATABLE = 1;
	
	
	private int mode= 0;
	private int previousRandomValue= 0;
	private	Random generator = null;
	
	
	public FieldSetFactory(int mode){
		this.mode=mode;
		generator = new Random();
	}

	private int getRandomInt(){	 
		return this.generator.nextInt(Config.poziom);
	}
	
	
	private Field getRandomField(){
		
		int randomNumber = 0;
		
		
		if(this.mode == FieldSetFactory.MODE_RANDOM_NOT_REPEATABLE){
			do{
				randomNumber= this.getRandomInt();
			}while(this.previousRandomValue == randomNumber);
			System.out.println(randomNumber);
		}else{
			
			randomNumber= this.getRandomInt();
			System.out.println(randomNumber);
		}
		this.previousRandomValue = randomNumber;
		
		
		switch(randomNumber){
		case 0:
			return new Field(Shape.CIRCLE);
		case 1:
			return new Field(Shape.CROSS); 
		case 2:
			return new Field(Shape.SQUARE);
		case 3:
			return new Field(Shape.TRIANGLE);	
		}
		return null;
		
	}
	
	public FieldSet generateFieldSet(int size){
		
		FieldSet newFieldSet = new FieldSet(size);
		
		for(int i=0; i<size; ++i){
			newFieldSet.addField(this.getRandomField());
		}
		
		return newFieldSet;
		
	}
}
