/**
 * 
 */
package com.smartech.course.racing.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.function.Function;

/**
 * @author Alexey Solomatin
 *
 */
public class AppProperties {
	private static volatile AppProperties instance;
	
	private Properties properties;
	private static final String PROPERTY_SOURCE = "/racing-simulator.properties";

	private AppProperties() {		
		try {
			URL url = getClass().getResource(PROPERTY_SOURCE);					
			properties = new Properties();
			properties.load(new FileInputStream(new File(url.toURI())));	        			
		} catch (Exception e) {
			throw new RuntimeException("Unable to load " + PROPERTY_SOURCE + " file", e);
		}
	}
	
	public static AppProperties getInstance() {
		AppProperties localInstance = instance;
		if (localInstance == null) {
			synchronized (AppProperties.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new AppProperties();
				}
			}
		}
		return localInstance;
	}
	
	public <T> T getProperty(String propertyName, Function<String, T> converter) {
		if (properties == null)
			return null;
		String strValue = properties.getProperty(propertyName);		
		return strValue != null ? converter.apply(strValue) : null;
	}
	
	public double getDouble(String propertyName) {
		return getProperty(propertyName, Double::parseDouble);
	}
	
	public String getString(String propertyName) {		
		return getProperty(propertyName, (s)->s);
	}
	
	public long getLong(String propertyName) {
		return getProperty(propertyName, Long::parseLong);
	}

}
