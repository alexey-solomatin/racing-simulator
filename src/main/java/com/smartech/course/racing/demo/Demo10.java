/**
 * 
 */
package com.smartech.course.racing.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.demo.greeting.PoliteRacingSimulatorUserGreeter;
import com.smartech.course.racing.demo.greeting.RestrictedAccessUserGreeter;
import com.smartech.course.racing.demo.greeting.UserGreeter;
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
public class Demo10 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: what if we want politeness for all the greeters? Refactor it!
		new Demo10().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private UserGreeter greeter;
	private UserGreeter restrictedAccessGreeter; 
	
	private static final int RESTRICTED_ACCESS_YEARS = 18;
	
	public Demo10() {
		greeter = new PoliteRacingSimulatorUserGreeter();
		restrictedAccessGreeter = new RestrictedAccessUserGreeter();
	}
	
	/**
	 * Runs the demo
	 */
	public void run() {
		try {
			log.info("Starting Racing Simulator demo.");
			Utils.checkConsole();				
			User user = new ConsoleUserBuilder().name().birthday().build();
			log.debug("User age: {}.", user.getAge().normalized());			
			if (user.getAge().getYears() < RESTRICTED_ACCESS_YEARS)			
				restrictedAccessGreeter.greet(user);
			else
				greeter.greet(user);
		} catch (Exception e) {
			log.error("The application has stopped due to problems.", e);
			System.err.println("The application has stopped: " + e.getMessage());
		} finally {
			Utils.delayBeforeExit();
			log.info("Racing Simulator demo stopped.");
		}
	}

}
