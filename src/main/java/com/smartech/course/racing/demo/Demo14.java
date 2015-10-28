/**
 * 
 */
package com.smartech.course.racing.demo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.demo.greeting.PoliteUserGreeterWrapper;
import com.smartech.course.racing.demo.greeting.RacingSimulatorDistinguishingUserGreeter;
import com.smartech.course.racing.demo.greeting.RestrictedAccessUserGreeter;
import com.smartech.course.racing.demo.greeting.UserGreeter;
import com.smartech.course.racing.demo.greeting.UserGreeterFactory;
import com.smartech.course.racing.demo.user.ConsoleDistinguishingUserBuilder;
import com.smartech.course.racing.demo.user.User;
import com.smartech.course.racing.demo.user.UserType;
import com.smartech.course.racing.demo.util.Utils;

/**
 * @author Alexey Solomatin
 *
 */
public class Demo14 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: greet three users
		new Demo14().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());		
	
	private Map<String, UserType> loginTypes;
	
	public Demo14() {
		loginTypes = new HashMap<>();
		loginTypes.put("admin", UserType.ADMINISTRATOR);
		loginTypes.put("admininstrator", UserType.ADMINISTRATOR);
		loginTypes.put("programmer", UserType.ADMINISTRATOR);
		loginTypes.put("user", UserType.USER);
		loginTypes.put("tester", UserType.USER);
		loginTypes.put("customer", UserType.USER);		
	}
	
	/**
	 * Runs the demo
	 */
	public void run() {
		try {
			log.info("Starting Racing Simulator demo.");
			Utils.checkConsole();				
			User user = new ConsoleDistinguishingUserBuilder(loginTypes).name().birthday().type().build();
			log.debug("User to be greeted: {}.", user);
			UserGreeterFactory.createUserGreeter(user).greet(user);			
		} catch (Exception e) {
			log.error("The application has stopped due to problems.", e);
			System.err.println("The application has stopped: " + e.getMessage());
		} finally {
			Utils.delayBeforeExit();
			log.info("Racing Simulator demo stopped.");
		}
	}

}
