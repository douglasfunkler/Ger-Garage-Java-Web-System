package ie.garage.booking.model;

public enum VehicleModel {

	KA("Ka"), FIESTA("Fiesta"), FOCUS("Focus"), MONDEO("Mondeo"), MUSTANG("Mustang"), GT("GT"), PUMA("Puma"), EDGE("Edge"),
	EXPLORER("Explorer"), RANGER("Ranger"), F_150("F-150"), ECOSPORT("EcoSport"), BRONCO("Bronco"), EXPEDITION("Expedition"), GALAXY("Galaxy"), SUPER_DUTY("Super Duty");;

	private String description;

	VehicleModel(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
