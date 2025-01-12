package com.solvd.airport.airplane;

import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.business.Operator;
import com.solvd.airport.business.Route;
import com.solvd.airport.people.CargoHandler;
import com.solvd.airport.people.FlightAttendant;
import com.solvd.airport.people.Passenger;

public class PassengerAirplane extends Airplane {
	private Integer numSeats;
	private Integer soldSeats;
	private List<Passenger> passengers = new ArrayList<>();
	
	private List<CargoHandler> cargoHandlers = new ArrayList<>();
	private List<FlightAttendant> flightAttendants = new ArrayList<>();
	private Boolean sufficientlyStaffedCargoHandlers = false;
	private Boolean sufficientlyStaffedFlightAttendants = false;
	
	public PassengerAirplane() {
		
	}
	
	public PassengerAirplane(AirplaneModels airplaneModel, Long id, Operator operator, Route route, Integer numSeats) {
		super.setAirplaneModel(airplaneModel);
		super.setId(id);
		super.setRoute(route);
		setNumSeats(numSeats);
		setSoldSeats(0);
		
		List<Airplane> temp = operator.getAirplanes();
		temp.add(this);
		operator.setAirplanes(temp);
	}

	public Integer getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(Integer numSeats) {
		this.numSeats = numSeats;
	}

	public Integer getSoldSeats() {
		return soldSeats;
	}

	public void setSoldSeats(Integer soldSeats) {
		this.soldSeats = soldSeats;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public List<CargoHandler> getCargoHandlers() {
		return cargoHandlers;
	}

	public void setCargoHandlers(List<CargoHandler> cargoHandlers) {
		this.cargoHandlers = cargoHandlers;
	}
	
	public List<FlightAttendant> getFlightAttendants() {
		return flightAttendants;
	}

	public void setFlightAttendants(List<FlightAttendant> flightAttendants) {
		this.flightAttendants = flightAttendants;
	}
	
	public Boolean getSufficientlyStaffedCargoHandlers() {
		return sufficientlyStaffedCargoHandlers;
	}

	public void setSufficientlyStaffedCargoHandlers(Boolean sufficientlyStaffedCargoHandlers) {
		this.sufficientlyStaffedCargoHandlers = sufficientlyStaffedCargoHandlers;
	}

	public Boolean getSufficientlyStaffedFlightAttendants() {
		return sufficientlyStaffedFlightAttendants;
	}

	public void setSufficientlyStaffedFlightAttendants(Boolean sufficientlyStaffedFlightAttendants) {
		this.sufficientlyStaffedFlightAttendants = sufficientlyStaffedFlightAttendants;
	}
}
