package com.solvd.airport.business;

public class NullCoordinateException extends Exception {
	private Double latitude;
	private Double longitude;
	private Integer elevation;
	
	public NullCoordinateException() {
		
	}

	public NullCoordinateException(Double latitude, Double longitude, Integer elevation) {
		setLatitude(latitude);
		setLongitude(longitude);
		setElevation(elevation);
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getElevation() {
		return elevation;
	}

	public void setElevation(Integer elevation) {
		this.elevation = elevation;
	}
	
	@Override
	public String toString() {
		return "An airport has a null coordinate - check file to ensure all airport's coordinates are properly set.\n" +
				"Latitude - " + getLatitude() + " || Longitude - " + getLongitude() + " || Elevation - " + getElevation();
	}
}
