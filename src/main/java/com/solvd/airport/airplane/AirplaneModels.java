package com.solvd.airport.airplane;

public enum AirplaneModels {
	AIRBUS_A380(AirplaneTypes.PASSENGER, "Airbus", "A380", 175, 160, 560, 43000, 4000, 9400), 
	BOEING_737(AirplaneTypes.PASSENGER, "Boeing", "737", 150, 140, 580, 37000, 3500, 4200),
	BOEING_747_8F(AirplaneTypes.CARGO, "Boeing", "747-8F", 180, 170, 660, 40000, 2000, 4300), 
	AIRBUS_A330_200F(AirplaneTypes.CARGO, "Airbus", "A330-200F", 150, 120, 540, 38000, 2500, 4600),
	LOCKHEED_MARTIN_F22_RAPTOR(AirplaneTypes.FIGHTER, "Lockheed Martin", "F-22 Raptor", 205, 160, 1400, 45000, 12700, 1600);
	
	private AirplaneTypes airplaneType;
	private String manufacturer;
	private String model;
	private Integer takeOffSpeed;
	private Integer cruisingSpeed;
	private Integer landingSpeed;
	private Integer cruisingAltitude;
	private Integer rateOfClimb;
	private Integer rateOfAcceleration;
	private Integer range;
	
	AirplaneModels() {
		
	}
	
	AirplaneModels(AirplaneTypes airplaneType, String manufacturer, String model, Integer takeOffSpeed, Integer landingSpeed, 
			Integer cruisingSpeed, Integer cruisingAltitude, Integer rateOfClimb, Integer range) {
		this.airplaneType = airplaneType;
		this.manufacturer = manufacturer;
		this.model = model;
		this.takeOffSpeed = takeOffSpeed;
		this.landingSpeed = landingSpeed;
		this.cruisingSpeed = cruisingSpeed;
		this.cruisingAltitude = cruisingAltitude;
		this.rateOfClimb = rateOfClimb;
		this.rateOfAcceleration = (cruisingSpeed - landingSpeed) / (cruisingAltitude / rateOfClimb);
		this.range = range;
	}

	public AirplaneTypes getAirplaneType() {
		return airplaneType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getModel() {
		return model;
	}

	public Integer getTakeOffSpeed() {
		return takeOffSpeed;
	}

	public Integer getCruisingSpeed() {
		return cruisingSpeed;
	}

	public Integer getLandingSpeed() {
		return landingSpeed;
	}

	public Integer getCruisingAltitude() {
		return cruisingAltitude;
	}

	public Integer getRateOfClimb() {
		return rateOfClimb;
	}

	public Integer getRateOfAcceleration() {
		return rateOfAcceleration;
	}

	public Integer getRange() {
		return range;
	}
}
