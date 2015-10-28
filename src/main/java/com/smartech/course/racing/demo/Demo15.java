/**
 * 
 */
package com.smartech.course.racing.demo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.demo.greeting.UserGreeterFactory;
import com.smartech.course.racing.demo.user.ConsoleDistinguishingUserBuilder;
import com.smartech.course.racing.demo.user.User;
import com.smartech.course.racing.demo.user.UserType;
import com.smartech.course.racing.demo.util.Utils;

/**
 * @author Alexey Solomatin
 *
 */
public class Demo15 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: use a list to store users
		new Demo15().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());		
	
	private Map<String, UserType> loginTypes;
	
	public Demo15() {
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
			User[] users = createSomeUsers();
			log.debug("Users to be greeted: {}.", (Object)users);
			for (User user : users) {
				log.debug("For user {}:", user);
				UserGreeterFactory.createUserGreeter(user).greet(user);
			}
		} catch (Exception e) {
			log.error("The application has stopped due to problems.", e);
			System.err.println("The application has stopped: " + e.getMessage());
		} finally {
			Utils.delayBeforeExit();
			log.info("Racing Simulator demo stopped.");
		}
	}
	
	private User[] createSomeUsers() {	
		User[] users = new User[3];
		System.console().printf("Please specify details for %d users.\n", users.length);
		for (int i = 0; i < users.length; ++i)
			users[i] = new ConsoleDistinguishingUserBuilder(loginTypes).name().birthday().type().build();
		return users;
	}

}
