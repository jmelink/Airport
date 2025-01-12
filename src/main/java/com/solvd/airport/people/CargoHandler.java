package com.solvd.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CargoHandler extends Employee {
	private final static int mask = 3;
	private Integer payPerFlight;
	private Integer flightsWorked;
	
	private final static Logger logger = LogManager.getLogger(CargoHandler.class.getClass());
	
	public CargoHandler() {
		
	}
	
	public CargoHandler(Integer payPerFlight) {
		super.setPosition("Cargo Handler");
		setPayPerFlight(payPerFlight);
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
	public void work(Object airplane) {
		logger.info(toString() + " is loading cargo onto flight " + airplane.toString());
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
		
		CargoHandler cargoHandler = (CargoHandler) obj;
		if (this.getPayPerFlight() != cargoHandler.getPayPerFlight()) {
			return false;
		}
		return true;
	}
}
