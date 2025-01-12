package com.solvd.airport.business;

import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.people.AirTrafficController;
import com.solvd.airport.people.SecurityAgent;

public class Airport {
	private String name;
	private String abbreviation;
	private Double latitude;
	private Double longitude;
	private Integer elevation; // In meters
	
	private List<Operator> operators = new ArrayList<>();
	private List<AirTrafficController> airTrafficControllers = new ArrayList<>();
	private List<SecurityAgent> securityAgents = new ArrayList<>();
	
	public Airport() {
		
	}
	
	public Airport (String name, String abbreviation, Double latitude, Double longitude, Integer elevation, 
			Integer numAirTrafficControllers, Integer numSecurityAgents) throws NullCoordinateException {
		setName(name);
		setAbbreviation(abbreviation);
		if (latitude == null || longitude == null || elevation == null) {
			throw new NullCoordinateException(latitude, longitude, elevation);
		} else {
			setLatitude(latitude);
			setLongitude(longitude);
			setElevation(elevation);
		}
		hireEmployees(numAirTrafficControllers, numSecurityAgents);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Integer getElevation() {
		return elevation;
	}

	public void setElevation(Integer elevation) {
		this.elevation = elevation;
	}

	public List<Operator> getOperators() {
		return operators;
	}

	public void setOperators(List<Operator> operators) {
		this.operators = operators;
	}

	public List<AirTrafficController> getAirTrafficControllers() {
		return airTrafficControllers;
	}

	public void setAirTrafficControllers(List<AirTrafficController> airTrafficControllers) {
		this.airTrafficControllers = airTrafficControllers;
	}

	public List<SecurityAgent> getSecurityAgents() {
		return securityAgents;
	}

	public void setSecurityAgents(List<SecurityAgent> securityAgents) {
		this.securityAgents = securityAgents;
	}
	
	public void hireEmployees(Integer numAirTrafficControllers, Integer numSecurityAgents) {
		List<AirTrafficController> airTrafficControllers = getAirTrafficControllers();
		List<SecurityAgent> securityAgents = getSecurityAgents();
		
		for (int i = 0; i < numAirTrafficControllers; i++) {
			airTrafficControllers.add(new AirTrafficController(125000));
			setAirTrafficControllers(airTrafficControllers);
		}
		
		for (int i = 0; i < numSecurityAgents; i++) {
			securityAgents.add(new SecurityAgent(75000));
			setSecurityAgents(securityAgents);
		}
	}
}
