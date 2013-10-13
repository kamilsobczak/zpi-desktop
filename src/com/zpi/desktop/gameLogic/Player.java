package com.zpi.desktop.gameLogic;

public class Player {

	private String name;
	private int points;
	private FieldSet fieldSet;

	public FieldSet getFieldSet() {
		return fieldSet;
	}

	public void setFieldSet(FieldSet fieldSet) {
		this.fieldSet = fieldSet;
	}

	public Player(String name) {
		this.points = 0;
		this.name = name;
	}

	public void addPoints(int value) {
		this.points += value;
	}

	// ----------------------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", points=" + points + "]";
	}

}
