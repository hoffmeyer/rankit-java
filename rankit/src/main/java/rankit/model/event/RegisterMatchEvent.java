package rankit.model.event;

import rankit.model.Match;

public class RegisterMatchEvent extends Event{
	private Match _match;

	public RegisterMatchEvent() {}

	public RegisterMatchEvent(Match match) {
		_match = match;
	}

	public Match getMatch() {
		return _match;
	}

	public void setMatch(Match match) {
		this._match = match;
	}

	@Override
	protected String defineType() {
		return this.getClass().getName();
	}
}
