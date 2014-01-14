package com.zpi.desktop.gameLogic;

public class Player {

	private String name;
	private FieldSet fieldSet;
	private PlayerStatistics statistics;
	private int id;
	public boolean dodano = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public class PlayerStatistics {

		private static final int RAGE_COUNT = 6;
		
		private static final int RAGE_POINTS = 3;
		private static final int SUCCESS_POINTS = 1;
		private static final int FAIL_POINTS = -1;
		private int points = 0;
		private int rageCount = 0;

		public PlayerStatistics() {

		}
		
		private boolean onRage(){
			return (rageCount == RAGE_COUNT);
		}
		public void restart(){
			points = 0;
			 rageCount = 0;
		}

		public void setSuccessedHit() {
			this.points += SUCCESS_POINTS;
			this.rageCount++;
			
			if(this.onRage()){
				this.points += RAGE_POINTS;
				this.rageCount=0;
			}
			
		}

		public void setFailedHit() {
			this.points += FAIL_POINTS;
			this.rageCount=0;
		}

		public int getPoints() {
			return this.points;
		}

	}

	public FieldSet getFieldSet() {
		return fieldSet;
	}

	public void setFieldSet(FieldSet fieldSet) {
		this.fieldSet = fieldSet;
	}

	public Player(String name) {
		this.name = name;
		this.statistics = new PlayerStatistics();

	}

	public void addPoints() {
		this.statistics.setSuccessedHit();
	}

	public void addPoints(int points) {
		this.statistics.points +=points;		
	}
	
	
	public void removePoints() {
		this.statistics.setFailedHit();
	}

	// ----------------------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return this.statistics.getPoints();
	}

	public void selectShape(Shape shape) {
		if (this.fieldSet.isValidShape(shape)) {
			this.fieldSet.popShape();

			this.addPoints();
		} else {
			this.removePoints();
		}
	}

	public boolean isFinish() {
		return (this.fieldSet.getSize() == 0);
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", points=" + this.statistics.getPoints() + "]";
	}

	public void restartStatistics() {
		// TODO Auto-generated method stub
		this.statistics.restart();
	}



}
