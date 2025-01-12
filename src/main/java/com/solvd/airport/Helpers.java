package com.solvd.airport;

import java.util.Random;

import com.solvd.airport.people.Name;

public class Helpers {
	
	public static String getRandomName() {
		Random random = new Random();
	    Name[] names = Name.values();
	    return names[random.nextInt(names.length)].toString();
	}
	
	public static Long getRandomId(Integer digits) {	
		Long id;
		do {
			id = (long) (Math.random() * Math.pow(10, digits));
		} while (id < Math.pow(10, digits - 1) && id > Math.pow(10, digits));
		return id;
	}
	
	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * Start point: lat1, lon1
	 * Start elevation: el1 (in meters)
	 * End point: lat2, lon2
	 * End elevation: el2 (in meters)
	 * 
	 * @returns Distance in miles
	 */
	public static int getDistance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth
	    final double metersInMile = 1609.34;
	    
	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return (int) (Math.sqrt(distance) / metersInMile); // return miles
	}
}
