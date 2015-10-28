/**
 * 
 */
package com.smartech.course.racing.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class Demo16 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: use a list to store users
		new Demo16().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());		
	
	private Map<String, UserType> loginTypes;
	
	public Demo16() {
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
			List<User> users = createSomeUsers();
			log.debug("Users to be greeted: {}.", (Object)users);
			users.stream().forEach((u) -> UserGreeterFactory.createUserGreeter(u).greet(u));			
		} catch (Exception e) {
			log.error("The application has stopped due to problems.", e);
			System.err.println("The application has stopped: " + e.getMessage());
		} finally {
			Utils.delayBeforeExit();
			log.info("Racing Simulator demo stopped.");
		}
	}
	
	private List<User> createSomeUsers() {	
		List<User> users = new ArrayList<>();		
		while (true) {			
			try {
				users.add(new ConsoleDistinguishingUserBuilder(loginTypes).name().birthday().type().build());
			} catch (Exception e) {				
				log.error("Error during user creation.", e);
				System.err.println("Cannot create the user: " + e.getMessage());
			}
			System.console().printf("Would you like to specify one more user? (y/n)\n");
			String answer = System.console().readLine().trim();
			if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("yes"))
				break;
		}
		return users;
	}

}
