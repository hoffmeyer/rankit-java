package rankit.model.event;

import java.util.Date;

abstract public class Event {
	private static int count = 0;
	private Date eventTime = new Date();
	private int id;
	private final String type;

	abstract protected String defineType();

	protected Event() {
		id = ++count;
		type = defineType();
	}

	public int getId() {
		return id;
	}

	public void setId(int newId) {
		id = newId;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public String getType() {
		return type;
	}
}
