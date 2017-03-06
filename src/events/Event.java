package events;

abstract public class Event {
	private String details;

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return this.details;
	}

	abstract public int execute();
}
