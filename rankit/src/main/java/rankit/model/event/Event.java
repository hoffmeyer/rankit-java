package rankit.model.event;

import java.util.Date;

public class Event {
	private static int count = 0;
	private Date created = new Date();
	private int id;
	
	public Event() {
		id = ++count;
	}

	public int getId() {
		return id;
	}
	
	public void setId( int newId ) {
		id = newId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
