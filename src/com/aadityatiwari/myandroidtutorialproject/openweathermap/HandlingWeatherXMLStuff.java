package com.aadityatiwari.myandroidtutorialproject.openweathermap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlingWeatherXMLStuff extends DefaultHandler {

	private WeatherXMLCollectedData collectedinfo = new WeatherXMLCollectedData();

	public String getInformation() {
		return collectedinfo.dataToString();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equals("city")) {
			String city = attributes.getValue("name");
			collectedinfo.setCity(city);
		} else if (localName.equals("country")) {
			String country = attributes.getValue("name");
			collectedinfo.setCountry(country);
		} else if (localName.equals("temperature")) {
			String temp = attributes.getValue("value");
			//int t = Integer.parseInt(temp);
			collectedinfo.setTemp(temp);
		}
	}
}
