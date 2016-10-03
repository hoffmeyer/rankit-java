package rankit.model.event;

public class CreatePlayerEvent extends Event {
	
	private String playerName;
	private int playerId;
	
	public CreatePlayerEvent() {}

	public CreatePlayerEvent(int playerId, String playerName) {
		this.playerName = playerName;
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	@Override
	protected String defineType() {
		return "createPlayer";
	}

	public int getPlayerId() {
		return playerId;
	}

}
