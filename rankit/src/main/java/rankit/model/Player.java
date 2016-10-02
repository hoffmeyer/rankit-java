package rankit.model;

public class Player {
	private int id;
	private String name;
	private int gamesPlayed = 0;
	private int points = 0;
	private int lostGames = 0;
	private int wonGames = 0;
	private int mostWinsInRow = 0;
	private int mostLosesInRow = 0;
	private int currentWinsInRow = 0;
	private int currentLossesInRow = 0;
	
	public Player(){ }

	public Player( String name ){
		this.name = name;
	}

	public Player( int id, String name, int points ){
		this.id = id;
		this.name = name;
		this.points = points;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public int getPoints() {
		return points;
	}
	public void addPoints(double points) {
		this.points += points;
	}
	public int getLostGames() {
		return lostGames;
	}
	public void setLostGames(int lostGames) {
		this.lostGames = lostGames;
	}
	public int getWonGames() {
		return wonGames;
	}
	public void setWonGames(int wonGames) {
		this.wonGames = wonGames;
	}
	public int getMostWinsInRow() {
		return mostWinsInRow;
	}
	public void setMostWinsInRow(int mostWinsInRow) {
		this.mostWinsInRow = mostWinsInRow;
	}
	public int getMostLosesInRow() {
		return mostLosesInRow;
	}
	public void setMostLosesInRow(int mostLosesInRow) {
		this.mostLosesInRow = mostLosesInRow;
	}
	public int getCurrentWinsInRow() {
		return currentWinsInRow;
	}
	public void setCurrentWinsInRow(int currentWinsInRow) {
		this.currentWinsInRow = currentWinsInRow;
	}
	public int getCurrentLossesInRow() {
		return currentLossesInRow;
	}
	public void setCurrentLossesInRow(int currentLossesInRow) {
		this.currentLossesInRow = currentLossesInRow;
	}
}
