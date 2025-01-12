package com.solvd.airport;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.airplane.Airplane;
import com.solvd.airport.airplane.AirplaneModels;
import com.solvd.airport.airplane.CargoAirplane;
import com.solvd.airport.airplane.FighterAirplane;
import com.solvd.airport.airplane.PassengerAirplane;
import com.solvd.airport.business.Airport;
import com.solvd.airport.business.NullCoordinateException;
import com.solvd.airport.business.Operator;
import com.solvd.airport.business.Route;
import com.solvd.airport.people.AirTrafficController;
import com.solvd.airport.people.CargoHandler;
import com.solvd.airport.people.FlightAttendant;
import com.solvd.airport.people.Passenger;
import com.solvd.airport.people.Pilot;
import com.solvd.airport.people.SecurityAgent;

public class Main {
	
	private final static Logger logger = LogManager.getLogger(Main.class.getClass());
	
	public static void main(String[] args) throws IOException {
		List<Airport> airports = new ArrayList<>();
		List<Operator> operators = new ArrayList<>();
		List<Route> routes = new ArrayList<>();
		List<Airplane> airplanes = new ArrayList<>();
		
		// Read airport details from file
		File airportsFile = new File("Airports.txt");
		
		List<String> airportDetails = new ArrayList<>();
		
		try (FileInputStream fis = new FileInputStream(airportsFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedReader br = new BufferedReader(new InputStreamReader(bis))) {
			String readLine;
			while ((readLine = br.readLine()) != null) {
				airportDetails.add(readLine);
			}
		} catch (IOException e) {
			logger.error("File cannot be read.");
		}
		
		// Parse the details of the file and use them to create individual airports
		for (String airportDetailRecord: airportDetails) {
	        int delimiterIndex = airportDetailRecord.indexOf(",");
	       
	        String name = airportDetailRecord.substring(0, delimiterIndex);
	        
	        String partialString = airportDetailRecord.substring(delimiterIndex + 1);
	        delimiterIndex = partialString.indexOf(",");
	        String abbreviation = partialString.substring(0, delimiterIndex);
	       
	        partialString = partialString.substring(delimiterIndex + 1);
	        delimiterIndex = partialString.indexOf(",");
	        Double latitude;
	        if (partialString.substring(0, delimiterIndex) == "") {
	        	latitude = null;
	        } else {
	        	latitude = Double.parseDouble(partialString.substring(0, delimiterIndex));
	        }
	        
	        partialString = partialString.substring(delimiterIndex + 1);
	        delimiterIndex = partialString.indexOf(",");
	        Double longitude; 
	        if (partialString.substring(0, delimiterIndex) == "") {
	        	longitude = null;
	        } else {
	        	longitude = Double.parseDouble(partialString.substring(0, delimiterIndex));
	        }
	        
	        partialString = partialString.substring(delimiterIndex + 1);
	        delimiterIndex = partialString.indexOf(",");
	        Integer elevation;
	        if (partialString.substring(0, delimiterIndex) == "") {
	        	elevation = null;
	        } else {
	        	elevation = Integer.parseInt(partialString.substring(0, delimiterIndex));
	        }
	        
	        partialString = partialString.substring(delimiterIndex + 1);
	        delimiterIndex = partialString.indexOf(",");
	        Integer numAirTrafficControllers;
	        if (partialString.substring(0, delimiterIndex) == "") {
	        	numAirTrafficControllers = null;
	        } else {
	        	numAirTrafficControllers = Integer.parseInt(partialString.substring(0, delimiterIndex));
	        }
	        
	        partialString = partialString.substring(delimiterIndex + 1);
	        Integer numSecurityAgents;
	        if (partialString == "") {
	        	numSecurityAgents = null;
	        } else {
	        	numSecurityAgents = Integer.parseInt(partialString);
	        }
	        
			try {
				airports.add(new Airport(name, abbreviation, latitude, longitude, elevation, 
						numAirTrafficControllers, numSecurityAgents));
			} catch (NullCoordinateException e) {
				logger.error(e.toString());
			}
		}
		
		operators.add(new Operator("American Airlines", "AA", 5, 3, 4));
		operators.add(new Operator("United Airlines", "UA", 4, 4, 4));
		operators.add(new Operator("FedEx", "FDX", 9, 0, 4));
		operators.add(new Operator("United States Air Force", "USAF", 0, 0, 1));
		
		for (Airport airport: airports) {
			routes.add(new Route(airports.get(0), airport));
		}
		
		airplanes.add(new PassengerAirplane(AirplaneModels.AIRBUS_A380, Helpers.getRandomId(4), operators.get(0), 
				routes.get((int) (Math.random() * routes.size())), 520));
		airplanes.add(new PassengerAirplane(AirplaneModels.BOEING_737, Helpers.getRandomId(4), operators.get(0), 
				routes.get((int) (Math.random() * routes.size())), 170));
		
		airplanes.add(new PassengerAirplane(AirplaneModels.BOEING_737, Helpers.getRandomId(4), operators.get(1), 
				routes.get((int) (Math.random() * routes.size())), 170));
		airplanes.add(new PassengerAirplane(AirplaneModels.BOEING_737, Helpers.getRandomId(4), operators.get(1), 
				routes.get((int) (Math.random() * routes.size())), 170));
		
		airplanes.add(new CargoAirplane(AirplaneModels.BOEING_747_8F, Helpers.getRandomId(4), operators.get(2), 
				routes.get((int) (Math.random() * routes.size())), 300000));
		airplanes.add(new CargoAirplane(AirplaneModels.AIRBUS_A330_200F, Helpers.getRandomId(4), operators.get(2), 
				routes.get((int) (Math.random() * routes.size())), 140000));
		
		airplanes.add(new FighterAirplane(AirplaneModels.LOCKHEED_MARTIN_F22_RAPTOR, Helpers.getRandomId(4), 
				operators.get(3), routes.get((int) (Math.random() * routes.size())), 8, 2000, true));
		
		operators.get(0).assignCargoHandlersToFlight(airplanes.get(0), 3);
		operators.get(0).assignFlightAttendantsToFlight(airplanes.get(0), 2);
		operators.get(0).assignPilotsToFlight(airplanes.get(0), 2);
		
		operators.get(0).assignCargoHandlersToFlight(airplanes.get(1), 2);
		operators.get(0).assignFlightAttendantsToFlight(airplanes.get(1), 1);
		operators.get(0).assignPilotsToFlight(airplanes.get(1), 2);
		
		operators.get(1).assignCargoHandlersToFlight(airplanes.get(2), 2);
		operators.get(1).assignFlightAttendantsToFlight(airplanes.get(2), 2);
		operators.get(1).assignPilotsToFlight(airplanes.get(2), 2);
		
		operators.get(1).assignCargoHandlersToFlight(airplanes.get(3), 2);
		operators.get(1).assignFlightAttendantsToFlight(airplanes.get(3), 2);
		operators.get(1).assignPilotsToFlight(airplanes.get(3), 2);
		
		operators.get(2).assignCargoHandlersToFlight(airplanes.get(4), 5);
		operators.get(2).assignPilotsToFlight(airplanes.get(4), 2);
		
		operators.get(2).assignCargoHandlersToFlight(airplanes.get(5), 4);
		operators.get(2).assignPilotsToFlight(airplanes.get(5), 2);
		
		operators.get(3).assignPilotsToFlight(airplanes.get(6), 1);
		
		// Sell all passenger tickets for all flights for all operators
		for (Operator operator: operators) {
			List<PassengerAirplane> passengerAirplanes = new ArrayList<>();
			for (Airplane airplane: operator.getAirplanes()) {
				if (airplane.getClass().isInstance(new PassengerAirplane())) {
					passengerAirplanes.add((PassengerAirplane) airplane);
				}
			}
			
			for (PassengerAirplane passengerAirplane: passengerAirplanes) {
				for (int k = 0; k < passengerAirplane.getNumSeats(); k++) {
					operator.sellTicket(passengerAirplane, new Passenger(operator, passengerAirplane));
				}
			}
		}
		
		// All airline employees check in
		for (Operator operator: operators) {
			operator.getCeo().setClockedIn(true);
			for (CargoHandler cargoHandler: operator.getCargoHandlers()) {
				cargoHandler.setClockedIn(true);
			}
			for (FlightAttendant flightAttendant: operator.getFlightAttendants()) {
				flightAttendant.setClockedIn(true);
			}
			for (Pilot pilot: operator.getPilots()) {
				pilot.setClockedIn(true);
			}
		}
		
		// Passengers check in
		for (Operator operator: operators) {
			for (Passenger passenger: operator.getPassengers()) {
				passenger.setCheckedIn(true);
			}
		}
		
		// All passengers regardless of airline are combined together
		List<Passenger> allPassengers = new ArrayList<>();
		for (Operator operator: operators) {
			allPassengers.addAll(operator.getPassengers());
		}
		
		// All passengers are split up evenly and go through security
		int securityRemainder = allPassengers.size() % airports.get(0).getSecurityAgents().size();
		int securityRemaindersAllocated = 0;
		int securityPreviousEndIndex = 0;
		for (SecurityAgent securityAgent: airports.get(0).getSecurityAgents()) {
			List<Passenger> passengerSubset = new ArrayList<>();
			if (securityRemainder != 0) {
				if (securityRemaindersAllocated < securityRemainder)
				{
					// Extra +1 on the end of subList's "toIndex" parameter is because subList doesn't
					// include the value of the original ArrayList at index "toIndex"
					passengerSubset.addAll(allPassengers.subList(securityPreviousEndIndex, 
							securityPreviousEndIndex + (allPassengers.size() / airports.get(0).getSecurityAgents().size()) + 1));
					securityPreviousEndIndex = securityPreviousEndIndex + 
							(allPassengers.size() / airports.get(0).getSecurityAgents().size()) + 1;
					securityRemaindersAllocated++;
				} else {
					// Extra +1 on the end of subList's "toIndex" parameter is because subList doesn't
					// include the value of the original ArrayList at index "toIndex"
					passengerSubset.addAll(allPassengers.subList(securityPreviousEndIndex, 
							(securityPreviousEndIndex + 
							(allPassengers.size() / airports.get(0).getSecurityAgents().size()) - 1) + 1));
					securityPreviousEndIndex = securityPreviousEndIndex + 
							(allPassengers.size() / airports.get(0).getSecurityAgents().size());
				}
			} else {
				// Extra +1 on the end of subList's "toIndex" parameter is because subList doesn't
				// include the value of the original ArrayList at index "toIndex"
				passengerSubset.addAll(allPassengers.subList(securityPreviousEndIndex, 
						(securityPreviousEndIndex + (allPassengers.size() / airports.get(0).getSecurityAgents().size()) - 1) + 1));
				securityPreviousEndIndex = securityPreviousEndIndex + 
						(allPassengers.size() / airports.get(0).getSecurityAgents().size());
			}
			for (Passenger passenger: passengerSubset) {
				securityAgent.work(passenger);
			}
		}	
		
		// All passengers board their respective planes
		for (Operator operator: operators) {
			for (Passenger passenger: operator.getPassengers()) {
				passenger.setBoarded(true);
			}
		}
		
		// Make last call for boarding for all passengers
		for (Operator operator: operators) {
			operator.makeLastCall();
		}
		
		// All baggage handlers across all operators load their respective planes with work evenly distributed
		for (Operator operator: operators) {
			for (Airplane airplane: operator.getAirplanes()) {
				if (airplane.getClass().isInstance(new CargoAirplane())) {
					CargoAirplane cargoAirplane = (CargoAirplane) airplane;
					for (CargoHandler cargoHandler: cargoAirplane.getCargoHandlers()) {
						cargoHandler.work(cargoAirplane);
					}
				}
				if (airplane.getClass().isInstance(new PassengerAirplane())) {
					PassengerAirplane passengerAirplane = (PassengerAirplane) airplane;
					for (CargoHandler cargoHandler: passengerAirplane.getCargoHandlers()) {
						cargoHandler.work(passengerAirplane);
					}
				}
			}
		}
		
		// Split up the list of airplanes evenly among all air traffic controllers
		int atcRemainder = airplanes.size() % airports.get(0).getAirTrafficControllers().size();
		int atcRemaindersAllocated = 0;
		int atcPreviousEndIndex = 0;
		
		for (AirTrafficController airTrafficController: airports.get(0).getAirTrafficControllers()) {
			List<Airplane> airplaneSubset = new ArrayList<>();
			if (atcRemainder != 0) {
				if (atcRemaindersAllocated < atcRemainder)
				{								
					// Extra +1 on the end of subList's "toIndex" parameter is because subList doesn't
					// include the value of the original ArrayList at index "toIndex"
					airplaneSubset.addAll(airplanes.subList(atcPreviousEndIndex, 
							atcPreviousEndIndex + (airplanes.size() / airports.get(0).getAirTrafficControllers().size()) + 1));
					atcPreviousEndIndex = atcPreviousEndIndex + (airplanes.size() / 
							airports.get(0).getAirTrafficControllers().size()) + 1;
					atcRemaindersAllocated++;
				} else {
					// Extra +1 on the end of subList's "toIndex" parameter is because subList doesn't
					// include the value of the original ArrayList at index "toIndex"
					airplaneSubset.addAll(airplanes.subList(atcPreviousEndIndex, 
							(atcPreviousEndIndex + (airplanes.size() / 
							airports.get(0).getAirTrafficControllers().size()) - 1) + 1));
					atcPreviousEndIndex = atcPreviousEndIndex + (airplanes.size() / 
							airports.get(0).getAirTrafficControllers().size());
				}
			} else {
				// Extra +1 on the end of subList's "toIndex" parameter is because subList doesn't
				// include the value of the original ArrayList at index "toIndex"
				airplaneSubset.addAll(airplanes.subList(atcPreviousEndIndex, 
						(atcPreviousEndIndex + (airplanes.size() / 
						airports.get(0).getAirTrafficControllers().size()) - 1) + 1));
				atcPreviousEndIndex = atcPreviousEndIndex + (airplanes.size() / 
						airports.get(0).getAirTrafficControllers().size());
			}
			
			for (Airplane airplane: airplaneSubset) {
				airTrafficController.work(airplane);
			}
		}
		
		// Fly the airplane
		for (Operator operator: operators) {
			for (Airplane airplane: operator.getAirplanes()) {
				for (Pilot pilot: airplane.getPilots()) {
					pilot.work(airplane);
				}
			}
		}
		
		// All passengers disembark their respective planes
		for (Operator operator: operators) {
			for (Passenger passenger: operator.getPassengers()) {
				passenger.setBoarded(false);
			}
		}
	}
}