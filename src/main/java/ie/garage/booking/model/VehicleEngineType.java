package ie.garage.booking.model;

public enum VehicleEngineType {

	DIESEL("Diesel"), PETROL("Petrol"), HYBRID("Hybrid"), ELECTRIC("Electric");

	private String description;

	VehicleEngineType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
