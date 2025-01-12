package com.solvd.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.Helpers;

public abstract class Employee extends Person {
	private String position;
	private Long employeeId = Helpers.getRandomId(6);
	private Boolean clockedIn = false;
	private Integer earnings;
	
	private final static Logger logger = LogManager.getLogger(Employee.class.getClass());

	public Employee() {
		
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeID) {
		this.employeeId = employeeID;
	}
	
	public Boolean getClockedIn() {
		return clockedIn;
	}
	
	public void setClockedIn(Boolean clockedIn) {
		if (!this.clockedIn && clockedIn) {
			logger.info(toString() + " clocked in for their shift.");
		}
		if (this.clockedIn && !clockedIn) {
			logger.info(toString() + " clocked out for the day.");
		}
		this.clockedIn = clockedIn;
	}

	public Integer getEarnings() {
		return earnings;
	}

	public void setEarnings(Integer earnings) {
		this.earnings = earnings;
	}
	
	public abstract void collectPaycheck();

	public abstract void work(Object entity);
	
	public String toString() {
		return super.toString() + " (" + getPosition() + " - ID: " + getEmployeeId() + ")";
	}
}
