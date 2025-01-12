package com.solvd.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.airplane.Airplane;

public class Pilot extends Employee {
	private final static int mask = 5;
	private Boolean primary;
	private Integer payPerFlight;
	private Integer flightsWorked;
	
	private final static Logger logger = LogManager.getLogger(Pilot.class.getClass());
	
	public Pilot() {
		
	}
	
	public Pilot(Boolean primary, Integer payPerFlight) {
		super.setPosition("Pilot");
		setPrimary(primary);
		setPayPerFlight(payPerFlight);
	}
	
	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
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
		if (getPrimary()) {
			if (((Airplane) airplane).isSufficientlyStaffed()) {
				if (((Airplane) airplane).getClearedForTakeoff()) {
					((Airplane) airplane).fly();
				} else {
					logger.info(getName() + " is awaiting clearance from Air Traffic Control to take off.");
				}
			} else {
				logger.info("[" + airplane.toString() + "] is insufficiently staffed and cannot takeoff.");
			}
		}
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
		
		Pilot pilot = (Pilot) obj;
		if (this.getPayPerFlight() != pilot.getPayPerFlight()) {
			return false;
		}
		return true;
	}
}
