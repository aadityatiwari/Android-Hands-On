package com.aadityatiwari.learningandroid.firstproject.openweathermap;

public class WeatherXMLCollectedData {
	private String temp = null;
	private String city = null;
	private String country = null;

	public void setCity(String c) {
		city = c;
	}

	public void setTemp(String t) {
		temp = t;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String dataToString() {
		return "Place: " + city + "," + country + " :: Temperatue is " + temp
				+ "C";
	}

}
