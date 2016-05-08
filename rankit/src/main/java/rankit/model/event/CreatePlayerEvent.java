package rankit.model.event;

import rankit.model.Player;

public class CreatePlayerEvent extends Event {
	private Player player;

	public CreatePlayerEvent(Player newPlayer) {
		player = newPlayer;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
