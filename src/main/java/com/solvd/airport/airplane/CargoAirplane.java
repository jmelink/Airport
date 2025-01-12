package com.solvd.airport.airplane;

import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.business.Operator;
import com.solvd.airport.business.Route;
import com.solvd.airport.people.CargoHandler;

public class CargoAirplane extends Airplane {
	private Integer cargoWeightCapacity;
	private Integer currentCargoWeight;
	private List<CargoHandler> cargoHandlers = new ArrayList<>();
	private Boolean sufficientlyStaffedCargoHandlers = false;
	
	public CargoAirplane() {
		
	}
	
	public CargoAirplane(AirplaneModels airplaneModel, Long id, Operator operator, Route route, Integer cargoWeightCapacity) {
		setAirplaneModel(airplaneModel);
		super.setAirplaneModel(airplaneModel);
		super.setId(id);
		super.setRoute(route);
		setCargoWeightCapacity(cargoWeightCapacity);
		setCurrentCargoWeight(0);
		
		List<Airplane> temp = operator.getAirplanes();
		temp.add(this);
		operator.setAirplanes(temp);
	}

	public Integer getCargoWeightCapacity() {
		return cargoWeightCapacity;
	}

	public void setCargoWeightCapacity(Integer cargoWeightCapacity) {
		this.cargoWeightCapacity = cargoWeightCapacity;
	}

	public Integer getCurrentCargoWeight() {
		return currentCargoWeight;
	}

	public void setCurrentCargoWeight(Integer currentCargoWeight) {
		this.currentCargoWeight = currentCargoWeight;
	}

	public List<CargoHandler> getCargoHandlers() {
		return cargoHandlers;
	}

	public void setCargoHandlers(List<CargoHandler> cargoHandlers) {
		this.cargoHandlers = cargoHandlers;
	}

	public Boolean getSufficientlyStaffedCargoHandlers() {
		return sufficientlyStaffedCargoHandlers;
	}

	public void setSufficientlyStaffedCargoHandlers(Boolean sufficientlyStaffedCargoHandlers) {
		this.sufficientlyStaffedCargoHandlers = sufficientlyStaffedCargoHandlers;
	}
}
