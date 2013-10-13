package com.zpi.desktop.gameLogic;

import java.util.ArrayList;

public class FieldSet {
	
	public ArrayList<Field> collection;
	
	public FieldSet(int size){
		this.collection= new ArrayList<Field>(size);
		
	}
	
	public void addField(Field f){
		this.collection.add(f);
	}
	
	public boolean isValidShape(Shape shape){
		Field lastField = collection.get(this.getSize()-1);
		if(lastField.getShape().equals(shape)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean popShape(){
		
		int currentSize = this.getSize();
		if( currentSize == 0){
			return false;
		}else{
			collection.remove(currentSize-1);
			return true;
		}
	
	}
		
	
	public boolean isFinished(){
		return (this.getSize()==0)?true:false;
	}
	
	
	public int getSize(){
		return this.collection.size();
	}
	
	public String toString(){
		
		StringBuilder result = new StringBuilder();
		for (Field field : this.collection) {
			result.append(field.toString());
			result.append("\n");
		}
		return result.toString();
	}
	

}
