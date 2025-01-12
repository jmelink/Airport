package com.solvd.airport.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.airplane.Airplane;
import com.solvd.airport.airplane.CargoAirplane;
import com.solvd.airport.airplane.PassengerAirplane;
import com.solvd.airport.people.CargoHandler;
import com.solvd.airport.people.Ceo;
import com.solvd.airport.people.FlightAttendant;
import com.solvd.airport.people.Passenger;
import com.solvd.airport.people.Pilot;

public class Operator {
	private String name;
	private String abbreviation;
	
	private List<Airplane> airplanes = new ArrayList<>();
	private Ceo ceo;
	
	private List<CargoHandler> cargoHandlers = new ArrayList<>();
	private List<FlightAttendant> flightAttendants = new ArrayList<>();
	private List<Pilot> pilots = new ArrayList<>();
	
	private List<CargoHandler> unassignedCargoHandlers = new ArrayList<>();
	private List<FlightAttendant> unassignedFlightAttendants = new ArrayList<>();
	private List<Pilot> unassignedPilots = new ArrayList<>();
	
	private List<Passenger> passengers = new ArrayList<>();
	
	private final static Logger logger = LogManager.getLogger(Operator.class.getClass());
	
	public Operator() {
		
	}
	
	public Operator(String name, String abbreviation, Integer numCargoHandlers, Integer numFlightAttendants, Integer numPilots) {
		setName(name);
		setAbbreviation(abbreviation);
		hireEmployees(numCargoHandlers, numFlightAttendants, numPilots);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public List<Airplane> getAirplanes() {
		return airplanes;
	}

	public void setAirplanes(List<Airplane> airplanes) {
		this.airplanes = airplanes;
	}

	public Ceo getCeo() {
		return ceo;
	}

	public void setCeo(Ceo ceo) {
		this.ceo = ceo;
	}

	public List<CargoHandler> getCargoHandlers() {
		return cargoHandlers;
	}

	public void setCargoHandlers(List<CargoHandler> cargoHandlers) {
		this.cargoHandlers = cargoHandlers;
	}

	public List<FlightAttendant> getFlightAttendants() {
		return flightAttendants;
	}

	public void setFlightAttendants(List<FlightAttendant> flightAttendants) {
		this.flightAttendants = flightAttendants;
	}

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}

	public List<CargoHandler> getUnassignedCargoHandlers() {
		return unassignedCargoHandlers;
	}

	public void setUnassignedCargoHandlers(List<CargoHandler> unassignedCargoHandlers) {
		this.unassignedCargoHandlers = unassignedCargoHandlers;
	}

	public List<FlightAttendant> getUnassignedFlightAttendants() {
		return unassignedFlightAttendants;
	}

	public void setUnassignedFlightAttendants(List<FlightAttendant> unassignedFlightAttendants) {
		this.unassignedFlightAttendants = unassignedFlightAttendants;
	}

	public List<Pilot> getUnassignedPilots() {
		return unassignedPilots;
	}

	public void setUnassignedPilots(List<Pilot> unassignedPilots) {
		this.unassignedPilots = unassignedPilots;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void hireEmployees(Integer numCargoHandlers, Integer numFlightAttendants, Integer numPilots) {
		List<CargoHandler> cargoHandlers = getCargoHandlers();
		List<FlightAttendant> flightAttendants = getFlightAttendants();
		List<Pilot> pilots = getPilots();
		
		setCeo(new Ceo(350000));
		
		for (int i = 0; i < numCargoHandlers; i++) {
			cargoHandlers.add(new CargoHandler(400));
		}
		setCargoHandlers(cargoHandlers);
		setUnassignedCargoHandlers(cargoHandlers);
		
		for (int i = 0; i < numFlightAttendants; i++) {
			flightAttendants.add(new FlightAttendant(500));
			
		}
		setFlightAttendants(flightAttendants);
		setUnassignedFlightAttendants(flightAttendants);
		
		for (int i = 0; i < numPilots; i++) {
			if (i % 2 == 0) {
				pilots.add(new Pilot(true, 800));
			} else {
				pilots.add(new Pilot(false, 800));
			}
		}
		setPilots(pilots);
		setUnassignedPilots(pilots);
	}
	
	public void assignCargoHandlersToFlight(Airplane airplane, Integer numCargoHandlers) {
		if (airplane.getClass().isInstance(new CargoAirplane())) {
			CargoAirplane cargoAirplane = (CargoAirplane) airplane;
			List<CargoHandler> cargoHandlers = new ArrayList<>();			
			
			for (CargoHandler unassignedCargoHandler: getUnassignedCargoHandlers()) {
				if(cargoHandlers.size() < numCargoHandlers) {
					cargoHandlers.add(unassignedCargoHandler);
					setUnassignedCargoHandlers(getUnassignedCargoHandlers().stream().skip(1).toList());
				}
			}
			cargoAirplane.setCargoHandlers(cargoHandlers);
			
			if (cargoHandlers.size() == numCargoHandlers) {
				cargoAirplane.setSufficientlyStaffedCargoHandlers(true);
			} else if (cargoHandlers.size() < numCargoHandlers && cargoHandlers.size() != 0) {
				logger.info(airplane.toString() + " is understaffed (insufficient Cargo Handlers) and will take longer to load.");
				cargoAirplane.setSufficientlyStaffedCargoHandlers(true);
			} else {
				logger.info(airplane.toString() + " does not have any Cargo Handlers staffed to load cargo onto the airplane.");
				cargoAirplane.setSufficientlyStaffedCargoHandlers(false);
			}
		}
		if (airplane.getClass().isInstance(new PassengerAirplane())) {
			PassengerAirplane passengerAirplane = (PassengerAirplane) airplane;
			List<CargoHandler> cargoHandlers = new ArrayList<>();
			for (CargoHandler unassignedCargoHandler: getUnassignedCargoHandlers()) {
				if (cargoHandlers.size() < numCargoHandlers) {
					cargoHandlers.add(unassignedCargoHandler);
					setUnassignedCargoHandlers(getUnassignedCargoHandlers().stream().skip(1).toList());
				}
			}
			passengerAirplane.setCargoHandlers(cargoHandlers);
			
			if (cargoHandlers.size() == numCargoHandlers) {
				passengerAirplane.setSufficientlyStaffedCargoHandlers(true);
			} else if (cargoHandlers.size() < numCargoHandlers && cargoHandlers.size() != 0) {
				logger.info(airplane.toString() + " is understaffed (insufficient Cargo Handlers) and will take longer to load.");
				passengerAirplane.setSufficientlyStaffedCargoHandlers(true);
			} else {
				logger.info(airplane.toString() + " does not have any Cargo Handlers staffed to load cargo onto the airplane.");
				passengerAirplane.setSufficientlyStaffedCargoHandlers(false);
			}
		}
	}
	
	public void assignFlightAttendantsToFlight(Airplane airplane, Integer numFlightAttendants) {
		if (airplane.getClass().isInstance(new PassengerAirplane())) {	
			PassengerAirplane passengerAirplane = (PassengerAirplane) airplane;
			List<FlightAttendant> flightAttendants = new ArrayList<>();
			for (FlightAttendant unassignedFlightAttendant: getUnassignedFlightAttendants()) {
				if (flightAttendants.size() < numFlightAttendants) {
					flightAttendants.add(unassignedFlightAttendant);
					setUnassignedFlightAttendants(getUnassignedFlightAttendants().stream().skip(1).toList());
				}
			}
			passengerAirplane.setFlightAttendants(flightAttendants);
			
			if (flightAttendants.size() == numFlightAttendants) {
				passengerAirplane.setSufficientlyStaffedFlightAttendants(true);
			} else if (flightAttendants.size() < numFlightAttendants && flightAttendants.size() != 0) {
				logger.info(airplane.toString() + " is understaffed (insufficient Flight Attendants) and will take longer to "
						+ "serve refreshments to passengers.");
				passengerAirplane.setSufficientlyStaffedFlightAttendants(true);
			} else {
				logger.info(airplane.toString() + " does not have any Flight Attendants to serve refreshments to the passengers.");
				passengerAirplane.setSufficientlyStaffedFlightAttendants(false);
			}
		}
	}
	
	public void assignPilotsToFlight(Airplane airplane, Integer numPilots) {
		List<Pilot> pilots = new ArrayList<>();
		for (Pilot unassignedPilot: getUnassignedPilots()) {
			if (pilots.size() < numPilots) {
				pilots.add(unassignedPilot);
				setUnassignedPilots(getUnassignedPilots().stream().skip(1).toList());
			}
		}
		airplane.setPilots(pilots);
		
		if (pilots.size() == numPilots) {
			airplane.setSufficientlyStaffedPilots(true);
		} else if (pilots.size() < numPilots && pilots.size() != 0) {
			logger.info(airplane.toString() + " is understaffed (insufficient Pilots) and cannot takeoff.");
			airplane.setSufficientlyStaffedPilots(false);
		} else {
			logger.info(airplane.toString() + " does not have any Pilots to fly the plane.");
			airplane.setSufficientlyStaffedPilots(false);
		}
	}
	
	public void sellTicket(PassengerAirplane passengerAirplane, Passenger passenger) {
		if (passengerAirplane.getSoldSeats() != passengerAirplane.getNumSeats()) {
			getPassengers().add(passenger);
			
			getPassengers().getLast().setSeatNum(passengerAirplane.getSoldSeats() + 1);
			passengerAirplane.setSoldSeats(passengerAirplane.getSoldSeats() + 1);
			passengerAirplane.getPassengers().add(passenger);
			
			logger.info("Ticket #" + getPassengers().getLast().getSeatNum() + " sold! || Operator: " + 
					getName() + " || Flight: "+ getAbbreviation() + " " + passengerAirplane.getId() + " || " + 
					passengerAirplane.getRoute().toString() + " || Passenger: " + 
					getPassengers().getLast().getName() + " || Seat: " + getPassengers().getLast().getSeatNum());
		} else {
			logger.info("There are no more empty seats - all tickets have been sold.");
		}
	}
	
	public void makeLastCall() {
		for (Airplane airplane: getAirplanes().stream().filter(airplane -> airplane.getClass().isInstance(new PassengerAirplane())).collect(Collectors.toList())) {
			for (Passenger passenger: ((PassengerAirplane) airplane).getPassengers().stream().filter(passenger -> (passenger.isCheckedIn() && !passenger.isBoarded())).collect(Collectors.toList())) {
				logger.info("This is the last chance for " + passenger.getName() + " to board " + passenger.getPassengerAirplane().toString() + ".");
			}
		}
	}
}
