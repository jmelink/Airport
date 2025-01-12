package com.solvd.airport.airplane;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.business.Route;
import com.solvd.airport.people.FlightAttendant;
import com.solvd.airport.people.Passenger;
import com.solvd.airport.people.Pilot;

public abstract class Airplane implements Flyable {
	private AirplaneModels airplaneModel;
	private Long id;
	private Route route;
	private List<Pilot> pilots;
	private Integer currentSpeed;
	private Integer currentAltitude;
	private Integer currentDistanceFromDestination;
	private Boolean sufficientlyStaffedPilots = false;
	private Boolean clearedForTakeoff = false;
	private Integer powerLevel = 0;
	private Boolean wheelsDeployed = true;
	private Integer wheelSpeed = 0;
	private Boolean spoilersDeployed = false;
	
	private final static Logger logger = LogManager.getLogger(Airplane.class.getClass());
	
	public Airplane() {
		
	}

	public AirplaneModels getAirplaneModel() {
		return airplaneModel;
	}
	
	public void setAirplaneModel(AirplaneModels airplaneModel) {
		this.airplaneModel = airplaneModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
		setCurrentAltitude(route.getDeparting().getElevation());
	}

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}
	
	public Integer getCurrentDistanceFromDestination() {
		return currentDistanceFromDestination;
	}

	public void setCurrentDistanceFromDestination(Integer currentDistanceFromDestination) {
		this.currentDistanceFromDestination = currentDistanceFromDestination;
	}
	
	public Integer getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(Integer currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public Integer getCurrentAltitude() {
		return currentAltitude;
	}

	public void setCurrentAltitude(Integer currentAltitude) {
		this.currentAltitude = currentAltitude;
	}

	public Boolean getSufficientlyStaffedPilots() {
		return sufficientlyStaffedPilots;
	}

	public void setSufficientlyStaffedPilots(Boolean sufficientlyStaffedPilots) {
		this.sufficientlyStaffedPilots = sufficientlyStaffedPilots;
	}

	public Boolean getClearedForTakeoff() {
		return clearedForTakeoff;
	}

	public void setClearedForTakeoff(Boolean clearedForTakeoff) {
		this.clearedForTakeoff = clearedForTakeoff;
	}
	
	public Integer getPowerLevel() {
		return powerLevel;
	}
	
	public void setPowerLevel(Integer powerLevel) {
		if (powerLevel >= 0 && powerLevel < PowerLevels.values().length) {
			while (powerLevel > getPowerLevel()) {
				this.powerLevel = powerLevel;
				logger.info("[" + toString() + "] Engine power level: " + PowerLevels.values()[getPowerLevel()]);
			}
			while(powerLevel < getPowerLevel()) {
				this.powerLevel = powerLevel;
				logger.info("[" + toString() + "] Engine power level: " + PowerLevels.values()[getPowerLevel()]);
			}
		} else {
			System.out.println("[" + toString() + "] Invalid power level selected - current power level maintained for engine.");
		}
	}
	
	public boolean getWheelsDeployed() {
		return wheelsDeployed;
	}

	public void setWheelsDeployed(boolean wheelsDeployed) {
		this.wheelsDeployed = wheelsDeployed;
		
		if (wheelsDeployed) {
			logger.info("[" + toString() + "] Landing gear protracted.");
		} else {
			logger.info("[" + toString() + "] Landing gear retracted.");
		}
		
		setWheelSpeed(0);
	}

	public int getWheelSpeed() {
		return wheelSpeed;
	}

	public void setWheelSpeed(int wheelSpeed) {
		this.wheelSpeed = wheelSpeed;
	}
	
	public Boolean getSpoilersDeployed() {
		return spoilersDeployed;
	}

	public void setSpoilersDeployed(Boolean spoilersDeployed) {
		this.spoilersDeployed = spoilersDeployed;
		
		if (this.spoilersDeployed) {
			logger.info("[" + toString() + "] Spoiler extended.");
		} else {
			logger.info("[" + toString() + "] Spoilers retracted.");
		}
	}

	public void start() {
		setPowerLevel(1);
		logger.info("[" + toString() + "] Engines have been started.");
	}
	
	public void stop() {
		setPowerLevel(0);
		logger.info("[" + toString() + "] Engines have been stopped.");
	}
	
	public void increasePower(Integer desiredPowerLevel) {
		if (desiredPowerLevel < PowerLevels.values().length) {
			while (getPowerLevel() < desiredPowerLevel) {
				setPowerLevel(getPowerLevel() + 1);
			}
		}
	}
	
	public void decreasePower(Integer desiredPowerLevel) {
		if (desiredPowerLevel >= 0) {
			while (getPowerLevel() > desiredPowerLevel) {
				setPowerLevel(getPowerLevel() - 1);
			}
		}
	}
	
	public void accelerate(int speed, boolean reverse) {
		if (speed != 0) {	
			if (!reverse) {
				while (getWheelSpeed() < speed) {
					setWheelSpeed(getWheelSpeed() + 1);
					setCurrentSpeed(getWheelSpeed());
					if (getWheelSpeed() % 10 == 0) {
						logger.info("[" + toString() + "] is accelerating forwards - current speed: "
								+ getWheelSpeed() + " mph");
					}
				}
			} else {
				while (getWheelSpeed() > speed) {
					setWheelSpeed(getWheelSpeed() - 1);
					setCurrentSpeed(getWheelSpeed());
					if (getWheelSpeed() % 10 == 0) {
						logger.info("[" + toString() + "] is accelerating backwards - current speed: " 
								+ getWheelSpeed() + " mph");
					}
				}
			}
		}
	}
	
	public void brake(int speed) {
		while (getWheelSpeed() > speed) {
			setWheelSpeed(getWheelSpeed() - 1);
			setCurrentSpeed(getWheelSpeed());
			if (getWheelSpeed() % 10 == 0) {
				logger.info("[" + toString() + "] is decelerating - current speed: " + getWheelSpeed() + " mph");
			}
		} 
	}
	
	public Boolean isSufficientlyStaffed() {
		Boolean flightSufficientlyStaffed = false;
		if (this.getSufficientlyStaffedPilots() == true && !this.getClass().isInstance(new FighterAirplane())) {
			if (this.getClass().isInstance(new PassengerAirplane())) {
				PassengerAirplane airplane = (PassengerAirplane) this;
				if (airplane.getSufficientlyStaffedCargoHandlers() && airplane.getSufficientlyStaffedFlightAttendants()) {
					flightSufficientlyStaffed = true;
				}
			}
			if (this.getClass().isInstance(new CargoAirplane())) {
				CargoAirplane airplane = (CargoAirplane) this;
				if (airplane.getSufficientlyStaffedCargoHandlers()) {
					flightSufficientlyStaffed = true;
				}
			}
		} else if (this.getSufficientlyStaffedPilots() == true && this.getClass().isInstance(new FighterAirplane())) {
			flightSufficientlyStaffed = true;
		}
		return flightSufficientlyStaffed;
	}
	
	@Override
	public void fly() {		
		// Leave Gate
		setCurrentDistanceFromDestination(getRoute().getDistance());

		start();
		accelerate(20, true); // 20 = taxiing speed
		brake(0);
		logger.info("[" + toString() + "] Pulled away from the gate.");

		accelerate(20, false); // 20 = taxiing speed
		logger.info("[" + toString() + "] Taxiing down the runway.");

		// Take Off
		increasePower(PowerLevels.values().length - 1);
		
		accelerate(this.getAirplaneModel().getTakeOffSpeed(), false);
		logger.info("[" + toString() + "] Achieved liftoff!");

		setCurrentSpeed(getWheelSpeed());

		// Fly
		int climbingDistanceTraveled = 0;
		//int descendingDistanceTraveled = 0;

		// Climbing
		while (getCurrentSpeed() < getAirplaneModel().getCruisingSpeed() || getCurrentAltitude() < getAirplaneModel().getCruisingAltitude()) {

			if (getWheelsDeployed() && getCurrentAltitude() > 0) {
				setWheelsDeployed(false);
			}

			setCurrentSpeed(getCurrentSpeed() + getAirplaneModel().getRateOfAcceleration());
			setCurrentAltitude(getCurrentAltitude() + getAirplaneModel().getRateOfClimb() - getRoute().getDeparting().getElevation());

			if (getCurrentSpeed() > getAirplaneModel().getCruisingSpeed()) {
				setCurrentSpeed(getAirplaneModel().getCruisingSpeed());
			}

			if (getCurrentAltitude() > getAirplaneModel().getCruisingAltitude()) {
				setCurrentAltitude(getAirplaneModel().getCruisingAltitude());
			}

			climbingDistanceTraveled++;
			setCurrentDistanceFromDestination(getCurrentDistanceFromDestination() - 1);

			logger.info("[" + toString() + "] Current Speed: " + getCurrentSpeed() + " mph || Altitude: " + 
					getCurrentAltitude() + " ft");
		}

		// Cruising
		decreasePower(getPowerLevel() - 1);

		boolean passengersServed = false;
		while (getCurrentDistanceFromDestination() > climbingDistanceTraveled * 10) {
			setCurrentDistanceFromDestination(getCurrentDistanceFromDestination() - 1);
			if (getCurrentDistanceFromDestination() % 50 == 0) {
				logger.info("[" + toString() + "] Air Speed: " + getCurrentSpeed() + " mph || Altitude: " + 
						getCurrentAltitude() + " ft " + "|| Distance Remaining: " + getCurrentDistanceFromDestination() + " miles");
			}

			if (this.getClass().isInstance(new PassengerAirplane())) {
				// Once we're 35% of the way through total distance, flight attendants serve refreshments
				if (passengersServed == false && getCurrentDistanceFromDestination() < 
						(getRoute().getDistance() - climbingDistanceTraveled - (climbingDistanceTraveled * 10)) * 0.65) { // 40 = distance traveled during descent
					// All passengers are split up evenly between the flight attendants
					PassengerAirplane temp = (PassengerAirplane) this;

					int remainder = temp.getPassengers().size() % temp.getFlightAttendants().size();
					int remaindersAllocated = 0;
					int previousEndIndex = 0;
					for (FlightAttendant flightAttendant: temp.getFlightAttendants()) {
						List<Passenger> passengerSubset = new ArrayList<>();
						if (remainder != 0) {
							if (remaindersAllocated < remainder)
							{
								// Extra +1 on the end of subList's "toIndex" parameter is because subList doesn't
								// include the value of the original ArrayList at index "toIndex"
								passengerSubset.addAll(temp.getPassengers().subList(previousEndIndex, 
										previousEndIndex + (temp.getPassengers().size() / temp.getFlightAttendants().size()) + 1));
								previousEndIndex = previousEndIndex + 
										(temp.getPassengers().size() / temp.getFlightAttendants().size()) + 1;
								remaindersAllocated++;
							} else {
								// Extra +1 on the end of subList's "toIndex" parameter is because subList doesn't
								// include the value of the original ArrayList at index "toIndex"
								passengerSubset.addAll(temp.getPassengers().subList(previousEndIndex, 
										(previousEndIndex + 
												(temp.getPassengers().size() / temp.getFlightAttendants().size()) - 1) + 1));
								previousEndIndex = previousEndIndex + 
										(temp.getPassengers().size() / temp.getFlightAttendants().size());
							}
						} else {
							// Extra +1 on the end of subList's "toIndex" parameter is because subList doesn't
							// include the value of the original ArrayList at index "toIndex"
							passengerSubset.addAll(temp.getPassengers().subList(previousEndIndex, 
									(previousEndIndex + (temp.getPassengers().size() / temp.getFlightAttendants().size()) - 1) + 1));
							previousEndIndex = previousEndIndex + 
									(temp.getPassengers().size() / temp.getFlightAttendants().size());
						}

						for (Passenger passenger: passengerSubset) {
							flightAttendant.work(passenger);
						}
						passengersServed = true;
					}
				}	
			}
		}

		// Descending
		decreasePower(getPowerLevel() - 1);

		while (getCurrentSpeed() > getAirplaneModel().getLandingSpeed() || getCurrentAltitude() > getRoute().getDestination().getElevation()) { // 40 = distance traveled during descent

			if (!getWheelsDeployed() && getCurrentAltitude() < getRoute().getDestination().getElevation() + 1000) {
				setWheelsDeployed(true);
				setSpoilersDeployed(true);
			}

			// Reduce speed & altitude by reduced rate since rate of descent is slower
			// than rate of ascent due to being spread over a much longer distance.
			setCurrentSpeed((int) (getCurrentSpeed() - (getAirplaneModel().getRateOfAcceleration() * 0.1))); // 50 = speed lost per mile traveled
			setCurrentAltitude((int) (getCurrentAltitude() - (getAirplaneModel().getRateOfClimb() * 0.1))); // 4500 = altitude lost per mile traveled

			if (getCurrentSpeed() < getAirplaneModel().getLandingSpeed()) {
				setCurrentSpeed(getAirplaneModel().getLandingSpeed());
			}

			if (getCurrentAltitude() < getRoute().getDestination().getElevation()) {
				setCurrentAltitude(getRoute().getDestination().getElevation());
			}

			//descendingDistanceTraveled++;
			setCurrentDistanceFromDestination(getCurrentDistanceFromDestination() - 1);

			if (getCurrentDistanceFromDestination() % 10 == 0) {
				logger.info("[" + toString() + "] Air Speed: " + getCurrentSpeed() + " mph || Altitude: " + 
						getCurrentAltitude() + " ft");
			}
		}

		// Land
		logger.info("[" + toString() + "] Touched down successfully!");

		setWheelSpeed(getCurrentSpeed());
		brake(20);

		setSpoilersDeployed(false);
		logger.info("[" + toString() + "] Taxiing down the runway.");

		// Arrive At Gate
		brake(0);
		stop();
		logger.info("[" + toString() + "] Arrived at " + getRoute().getDestination().getName() + "'s destination gate.");		
	}

	@Override
	public String toString() {
		return "Flight " + getId() + " - " + getAirplaneModel().getManufacturer() + " " + getAirplaneModel().getModel();
	}
}
