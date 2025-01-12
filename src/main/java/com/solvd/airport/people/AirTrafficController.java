package com.solvd.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.airplane.Airplane;

public class AirTrafficController extends Employee {
	private final static int mask = 2;
	private Integer salary;
	
	private final static Logger logger = LogManager.getLogger(AirTrafficController.class.getClass());

	public AirTrafficController() {
		
	}
	
	public AirTrafficController(Integer salary) {
		super.setPosition("Air Traffic Controller");
		setSalary(salary);
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public void work(Object airplane) {
		Airplane newAirplane = (Airplane) airplane;
		newAirplane.setClearedForTakeoff(true);
		logger.info(toString() + " has cleared for takeoff flight " + newAirplane.toString() + ".");
	}
	
	@Override
	public void collectPaycheck() {
		Payable payable = (Integer rate) -> {
			return rate / 26;};
		super.setEarnings(payable.calculatePayOwed(this.getSalary()));
	}
	
	@Override
	public String toString() {
		return super.getName() + " (" + super.getPosition() + " - ID: " + super.getEmployeeId() + ")";
	}
	
	@Override
	public int hashCode() {
		return mask + super.hashCode() + salary.hashCode();
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
		
		AirTrafficController airTrafficController = (AirTrafficController) obj;
		if (this.getSalary() != airTrafficController.getSalary()) {
			return false;
		}
		return true;
	}
}
