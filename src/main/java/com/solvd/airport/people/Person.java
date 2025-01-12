package com.solvd.airport.people;

import com.solvd.airport.Helpers;

public abstract class Person {
	private String name = Helpers.getRandomName();
	private Integer age = (int) (Math.random() * 100);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String toString() {
		return getName();
	}
}
