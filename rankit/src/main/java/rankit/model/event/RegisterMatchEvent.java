package rankit.model.event;

import rankit.model.Match;

public class RegisterMatchEvent extends Event{
	private Match match;

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
}
