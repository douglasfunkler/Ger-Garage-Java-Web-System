package ie.garage.booking.model;

public enum BookingType {

	ANNUAL("Annual Service: €1200.0"), MAJOR("Major Service: €350.0"), REPAIR_FAULT("Repair/Fault: €100.0"), MAJOR_REPAIR("Major Repair: €180.0");

	private String description;

	BookingType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
