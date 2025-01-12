package com.solvd.airport.airplane;

import java.util.List;

import com.solvd.airport.business.Operator;
import com.solvd.airport.business.Route;

public class FighterAirplane extends Airplane {
	private Integer missileCountCapacity;
	private Integer bombWeightCapacity;
	private Boolean afterburners = false;
	
	public FighterAirplane() {
		
	}
	
	public FighterAirplane(AirplaneModels airplaneModel, Long id, Operator operator, Route route, Integer missileCountCapacity, 
			Integer bombWeightCapacity, Boolean afterburners) {
		super.setAirplaneModel(airplaneModel);
		super.setId(id);
		super.setRoute(route);
		setMissileCountCapacity(missileCountCapacity);
		setBombWeightCapacity(bombWeightCapacity);
		setAfterburners(afterburners);
		
		List<Airplane> temp = operator.getAirplanes();
		temp.add(this);
		operator.setAirplanes(temp);
	}

	public Integer getMissileCountCapacity() {
		return missileCountCapacity;
	}

	public void setMissileCountCapacity(Integer missileCountCapacity) {
		this.missileCountCapacity = missileCountCapacity;
	}

	public Integer getBombWeightCapacity() {
		return bombWeightCapacity;
	}

	public void setBombWeightCapacity(Integer bombWeightCapacity) {
		this.bombWeightCapacity = bombWeightCapacity;
	}

	public Boolean getAfterburners() {
		return afterburners;
	}

	public void setAfterburners(Boolean afterburners) {
		this.afterburners = afterburners;
	}
}
