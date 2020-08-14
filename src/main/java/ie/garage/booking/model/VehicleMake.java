package ie.garage.booking.model;

public enum VehicleMake {

	FORD("Ford"), TOYOTA("Toyota"), BMW("BMW"), TESLA("Tesla"), KIA("Kia"), HONDA("Honda"), MAZDA("Mazda"), PORSCHE("Porsche");

	private String description;

	VehicleMake(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
