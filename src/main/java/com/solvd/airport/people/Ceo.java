package com.solvd.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ceo extends Employee {
	private final static int mask = 1;
	private Integer salary;
	
	private final static Logger logger = LogManager.getLogger(Ceo.class.getClass());
	
	public Ceo() {
		
	}
	
	public Ceo(Integer salary) {
		super.setPosition("Chief Executive Officer");
		setSalary(salary);
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public void work(Object operator) {
		logger.info(toString() + " is managing " + operator.toString() + ".");
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
		
		Ceo ceo = (Ceo) obj;
		if (this.getSalary() != ceo.getSalary()) {
			return false;
		}
		return true;
	}
}
