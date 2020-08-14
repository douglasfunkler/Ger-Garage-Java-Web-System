package ie.garage.booking.model;

public enum StatusBooking {

	PROGRESS("In Progress"), FINISHED("Finished");

	private String description;

	StatusBooking(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
