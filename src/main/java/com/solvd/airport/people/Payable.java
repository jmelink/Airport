package com.solvd.airport.people;

@FunctionalInterface
public interface Payable {
	public Integer calculatePayOwed(Integer rate);
}
