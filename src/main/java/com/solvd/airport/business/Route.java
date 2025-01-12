package com.solvd.airport.business;

import com.solvd.airport.Helpers;

public class Route {
	private Airport departing;
	private Airport destination;
	private int distance; // In miles
	
	public Route() {
		
	}
	
	public Route(Airport departing, Airport destination) {
		setDeparting(departing);
		setDestination(destination);
		setDistance(Helpers.getDistance(departing.getLatitude(), destination.getLatitude(), departing.getLongitude(), 
				destination.getLongitude(), departing.getElevation(), destination.getElevation()));
	}

	public Airport getDeparting() {
		return departing;
	}

	public void setDeparting(Airport departing) {
		this.departing = departing;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "Departure: " + departing.getName() + " (" + departing.getAbbreviation() + 
				") || Arrival: " + destination.getName() + " (" + destination.getAbbreviation() + ")";
	}
}
