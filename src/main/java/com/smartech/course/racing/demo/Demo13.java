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
import com.smartech.course.racing.demo.user.ConsoleDistinguishingUserBuilder;
import com.smartech.course.racing.demo.user.ConsoleUserBuilder;
import com.smartech.course.racing.demo.user.User;
import com.smartech.course.racing.demo.user.UserType;
import com.smartech.course.racing.demo.util.Utils;

/**
 * @author Alexey Solomatin
 *
 */
public class Demo13 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: refactor creating a user greeter
		new Demo13().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private UserGreeter greeter;
	private UserGreeter restrictedAccessGreeter; 
	
	private static final int RESTRICTED_ACCESS_YEARS = 18;
	private Map<String, UserType> loginTypes;
	
	public Demo13() {
		loginTypes = new HashMap<>();
		loginTypes.put("admin", UserType.ADMINISTRATOR);
		loginTypes.put("admininstrator", UserType.ADMINISTRATOR);
		loginTypes.put("programmer", UserType.ADMINISTRATOR);
		loginTypes.put("user", UserType.USER);
		loginTypes.put("tester", UserType.USER);
		loginTypes.put("customer", UserType.USER);
		greeter = new PoliteUserGreeterWrapper(new RacingSimulatorDistinguishingUserGreeter());
		restrictedAccessGreeter = new PoliteUserGreeterWrapper(new RestrictedAccessUserGreeter());
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
