package com.solvd.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SecurityAgent extends Employee {
	private final static int mask = 6;
	private Integer salary;
	
	private final static Logger logger = LogManager.getLogger(SecurityAgent.class.getClass());

	public SecurityAgent() {
		
	}
	
	public SecurityAgent(Integer salary) {
		super.setPosition("Security Agent");
		setSalary(75000);
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public void work(Object passenger) {
		logger.info(toString() + " has passed passenger " + passenger.toString() + " through the metal detector.");
	}
	
	@Override
	public void collectPaycheck() {
		Payable payable = (Integer rate) -> {
			return rate / 26;};
		super.setEarnings(payable.calculatePayOwed(this.getSalary()));
	}
	
	@Override
	public String toString() {
		return super.toString();
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
		
		SecurityAgent securityAgent = (SecurityAgent) obj;
		if (this.getSalary() != securityAgent.getSalary()) {
			return false;
		}
		return true;
	}
}
