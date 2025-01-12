package com.solvd.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightAttendant extends Employee {
	private final static int mask = 4;
	private Integer payPerFlight;
	private Integer flightsWorked;
	
	private final static Logger logger = LogManager.getLogger(FlightAttendant.class.getClass());
	
	public FlightAttendant() {
		
	}
	
	public FlightAttendant(Integer payPerFlight) {
		super.setPosition("Flight Attendant");
		setPayPerFlight(payPerFlight);
		setFlightsWorked(0);
	}

	public Integer getPayPerFlight() {
		return payPerFlight;
	}

	public void setPayPerFlight(Integer payPerFlight) {
		this.payPerFlight = payPerFlight;
	}

	public Integer getFlightsWorked() {
		return flightsWorked;
	}

	public void setFlightsWorked(Integer flightsWorked) {
		this.flightsWorked = flightsWorked;
	}

	@Override
	public void work(Object passenger) {
		logger.info(toString() + " is serving refreshments to " + passenger.toString() + ".");
		setFlightsWorked(getFlightsWorked() + 1);
	}
	
	@Override
	public void collectPaycheck() {
		Payable payable = (Integer rate) -> {
			return rate * getFlightsWorked();};
		super.setEarnings(payable.calculatePayOwed(this.getPayPerFlight()));
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public int hashCode() {
		return mask + super.hashCode() + payPerFlight.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this != obj) {
			return false;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		if (this.hashCode() != obj.hashCode()) {
			return false;
		}
		
		FlightAttendant flightAttendant = (FlightAttendant) obj;
		if (this.getPayPerFlight() != flightAttendant.getPayPerFlight()) {
			return false;
		}
		return true;
	}
}
