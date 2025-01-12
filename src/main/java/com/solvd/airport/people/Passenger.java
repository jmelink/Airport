package com.solvd.airport.people;

import com.solvd.airport.business.Operator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.airplane.PassengerAirplane;

public class Passenger extends Person {	
	private final static int mask = 7;
	private Operator operator;
	private PassengerAirplane passengerAirplane;
	private Integer seatNum;
	private Boolean checkedIn = false;
	private Boolean boarded = false;
	
	private final static Logger logger = LogManager.getLogger(Passenger.class.getClass());
	
	public Passenger() {
		
	}
	
	public Passenger(Operator operator, PassengerAirplane passengerAirplane) {
		setOperator(operator);
		setPassengerAirplane(passengerAirplane);
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public PassengerAirplane getPassengerAirplane() {
		return passengerAirplane;
	}

	public void setPassengerAirplane(PassengerAirplane passengerAirplane) {
		this.passengerAirplane = passengerAirplane;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public boolean isCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(boolean checkedIn) {
		if (!this.checkedIn) {
			this.checkedIn = checkedIn;
			logger.info(getName() + " has checked in for flight " + operator.getAbbreviation() + " " + 
					passengerAirplane.getId() + " (seat #" + getSeatNum() + ").");
		} else {
			logger.info(getName() + "has already checked in.");
		}
	}

	public boolean isBoarded() {
		return boarded;
	}

	public void setBoarded(boolean boarded) {
		if (!this.boarded && boarded) {
			this.boarded = boarded;
			logger.info(toString() + " boarded the plane and sat down in their seat.");
		}
		if (!this.boarded && boarded) {
			this.boarded = boarded;
			logger.info(toString() + " stood up from their seat, disembarked the plane, and has arrived in" +
					getPassengerAirplane().getRoute().getDestination().getName() + "'s terminal.");
		}
		this.boarded = boarded;
	}
	
	@Override
	public String toString() {
		return super.toString() + " (" + getPassengerAirplane().toString() + " - Seat #" + getSeatNum() + ")";
		
	}
	
	@Override
	public int hashCode() {
		return mask + super.hashCode() + operator.hashCode() + passengerAirplane.hashCode() + seatNum.hashCode() + 
				checkedIn.hashCode() + boarded.hashCode();
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
		
		Passenger passenger = (Passenger) obj;
		if (this.getOperator() != passenger.getOperator() || this.getPassengerAirplane() != passenger.getPassengerAirplane() ||
				this.getSeatNum() != passenger.getSeatNum() || this.isCheckedIn() != passenger.isCheckedIn() || 
				this.isBoarded() != passenger.isBoarded()) {
			return false;
		}
		return true;
	}
}
