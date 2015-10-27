/**
 * 
 */
package com.smartech.course.racing.demo;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.demo.greeting.simple.Greeter;
import com.smartech.course.racing.demo.greeting.simple.PoliteRacingSimulatorGreeter;
import com.smartech.course.racing.demo.util.Utils;

/**
 * @author Alexey Solomatin
 *
 */
public class Demo07 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: implement restricted access greeter
		// TODO: implement entering user's birthday
		new Demo07().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private Greeter greeter;
	
	public Demo07() {
		greeter = new PoliteRacingSimulatorGreeter();
	}
	
	/**
	 * Runs the demo
	 */
	public void run() {
		try {
			log.info("Starting Racing Simulator demo.");
			Utils.checkConsole();				
			String username = retrieveUsername();
			greeter.greet(username);
		} catch (Exception e) {
			log.error("The application has stopped due to problems.", e);
			System.err.println("The application has stopped: " + e.getMessage());
		} finally {
			Utils.delayBeforeExit();
			log.info("Racing Simulator demo stopped.");
		}
	}
	
	private String retrieveUsername() {
		// getting user name
		String username = null;
		while (StringUtils.isBlank(username)) {
			username = System.console().readLine("Please enter your name: ");
			if (StringUtils.isBlank(username))
				System.console().printf("Ooops! Looks like you've entered the incorrect name.\nPlease try again.\n");					
		}		
		return username;
	}	

}
