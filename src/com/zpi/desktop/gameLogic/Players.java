package com.zpi.desktop.gameLogic;

import java.util.ArrayList;

public class Players {

	private ArrayList<Player> collection;

	public Players() {
		collection = new ArrayList<Player>();
	}

	public void addPlayer(Player p) {	
		collection.add(p);
	}
	
	public ArrayList<Player> getCollection(){
		return collection;
	}

	public Player getPlayerByName(String name) {

		for (Player player : this.collection) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

	public boolean exists(String name) {

		if (this.getPlayerByName(name) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEndGame(){
		for (Player player : this.collection) {
			if (!player.isFinish()) {
				return false;
			}
		}
		return  true;
	}
	
 
	
	
	public int getPlayersCount() {
		return this.collection.size();		
	}
		
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Player player : this.collection) {
			result.append(player.toString());
			result.append("\n");
		}
		return result.toString();

	}

}
