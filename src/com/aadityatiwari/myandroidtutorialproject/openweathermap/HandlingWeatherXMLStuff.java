package com.aadityatiwari.myandroidtutorialproject.openweathermap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlingWeatherXMLStuff extends DefaultHandler {

	private WeatherXMLCollectedData info = new WeatherXMLCollectedData();

	private String getInformation() {
		return info.dataToString();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
	}
}
