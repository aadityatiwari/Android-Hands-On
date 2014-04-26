package com.aadityatiwari.myandroidtutorialproject.openweathermap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlingWeatherXMLStuff extends DefaultHandler {

	private WeatherXMLCollectedData collectedinfo = new WeatherXMLCollectedData();
	boolean ELEMENT_COUNTRY = false;

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
			ELEMENT_COUNTRY = true;
		} else if (localName.equals("temperature")) {
			String temp = attributes.getValue("value");
			collectedinfo.setTemp(temp);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (ELEMENT_COUNTRY) {
			String country = new String(ch, start, length);
			collectedinfo.setCountry(country);
			ELEMENT_COUNTRY = false;
		}
	}
}
