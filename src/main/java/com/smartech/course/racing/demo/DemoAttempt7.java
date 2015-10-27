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
import com.smartech.course.racing.demo.greeting.simple.RacingSimulatorGreeter;

/**
 * @author Alexey Solomatin
 *
 */
public class DemoAttempt7 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: implement restricted access greeter
		// TODO: implement entering user's birthday
		new DemoAttempt7().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private Greeter greeter;
	
	public DemoAttempt7() {
		greeter = new PoliteRacingSimulatorGreeter();
	}
	
	/**
	 * Runs the demo
	 */
	public void run() {
		try {
			log.info("Starting Racing Simulator demo.");
			checkConsole();				
			String username = retrieveUsername();
			greeter.greet(username);
		} catch (Exception e) {
			log.error("The application has stopped due to problems.", e);
			System.err.println("The application has stopped: " + e.getMessage());
		} finally {
			prepareForExit();
			log.info("Racing Simulator demo stopped.");
		}
	}

	private void checkConsole() throws Exception {
		// checking if there's a console		
		if (System.console() == null)
			throw new Exception("There is no console.");
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
	
	private void prepareForExit() {
		// delaying the program execution
		try {
			System.console().printf("\nPress <Enter> to exit...");
			System.console().reader().read();			
		} catch (IOException e) {
			log.error("Error during preparing for exit.", e);
		}
	}

}
