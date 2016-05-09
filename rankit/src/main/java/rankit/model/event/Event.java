package rankit.model.event;

import java.util.Date;

abstract public class Event {
	private static int count = 0;
	private Date created = new Date();
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
	
	public void setId( int newId ) {
		id = newId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getType() {
		return type;
	}
}
