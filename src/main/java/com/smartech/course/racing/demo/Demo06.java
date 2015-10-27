/**
 * 
 */
package com.smartech.course.racing.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.demo.greeting.simple.Greeter;
import com.smartech.course.racing.demo.greeting.simple.RacingSimulatorGreeter;

/**
 * @author Alexey Solomatin
 *
 */
public class Demo06 {	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: move checkConsole() and prepareForExit() into the Utils class
		// TODO: remove greetUser()
		// TODO: enhance entering a user name, do not throw an exception from that method
		// TODO: use StringUtils for string checks
		// TODO: implement and use polite racing simulator greeter
		new Demo06().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private Greeter greeter;
	
	public Demo06() {
		greeter = new RacingSimulatorGreeter();
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

	@Deprecated
	private void checkConsole() throws Exception {
		// checking if there's a console		
		if (System.console() == null)
			throw new Exception("There is no console.");
	}
	
	// TODO: do not allow to enter empty username!
	private String retrieveUsername() throws Exception {
		// getting user name
		String username = System.console().readLine("Please enter your name: ");
		// checking that it's not empty
		if (username == null || username.isEmpty())
			throw new Exception("I'm sorry but you've not represented yourself. Bye!..");
		return username;
	}
	
	// should not be used now because there are greeters
	@Deprecated
	private void greetUser(String username) {
		// greeting the user 
		System.console().printf("Hello %s! This is Racing Simulator!\n", username);
	}
	
	@Deprecated
	private void prepareForExit() {
		// delaying the program execution
		try {
			System.console().printf("Press <Enter> to exit...");
			System.console().reader().read();			
		} catch (IOException e) {
			log.error("Error during preparing for exit.", e);
		}
	}
}
