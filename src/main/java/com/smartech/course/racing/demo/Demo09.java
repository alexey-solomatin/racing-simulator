/**
 * 
 */
package com.smartech.course.racing.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.demo.greeting.simple.Greeter;
import com.smartech.course.racing.demo.greeting.simple.PoliteRacingSimulatorGreeter;
import com.smartech.course.racing.demo.greeting.simple.RestrictedAccessGreeter;
import com.smartech.course.racing.demo.user.ConsoleUserBuilder;
import com.smartech.course.racing.demo.user.User;
import com.smartech.course.racing.demo.util.Utils;

/**
 * @author Alexey Solomatin
 *
 */
public class Demo09 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: what if we want to use all the User class data when greeting? Refactor it!			
		new Demo09().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private Greeter greeter;
	private Greeter restrictedAccessGreeter; 
	
	private static final int RESTRICTED_ACCESS_YEARS = 18;
	
	public Demo09() {
		greeter = new PoliteRacingSimulatorGreeter();
		restrictedAccessGreeter = new RestrictedAccessGreeter();
	}
	
	/**
	 * Runs the demo
	 */
	public void run() {
		try {
			log.info("Starting Racing Simulator demo.");
			Utils.checkConsole();				
			User user = new ConsoleUserBuilder().name().birthday().build();
			log.debug("Specified user: {}.", user);
			log.debug("User age: {}.", user.getAge().normalized());			
			if (user.getAge().getYears() < RESTRICTED_ACCESS_YEARS)			
				restrictedAccessGreeter.greet(user.getName());
			else
				greeter.greet(user.getName());
		} catch (Exception e) {
			log.error("The application has stopped due to problems.", e);
			System.err.println("The application has stopped: " + e.getMessage());
		} finally {
			Utils.delayBeforeExit();
			log.info("Racing Simulator demo stopped.");
		}
	}

}
