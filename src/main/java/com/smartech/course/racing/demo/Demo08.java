/**
 * 
 */
package com.smartech.course.racing.demo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.demo.greeting.simple.Greeter;
import com.smartech.course.racing.demo.greeting.simple.PoliteRacingSimulatorGreeter;
import com.smartech.course.racing.demo.greeting.simple.RestrictedAccessGreeter;
import com.smartech.course.racing.demo.util.Utils;

/**
 * @author Alexey Solomatin
 *
 */
public class Demo08 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: refactor working with users
		new Demo08().run();		
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private Greeter greeter;
	private Greeter restrictedAccessGreeter; 
	
	private static final int RESTRICTED_ACCESS_YEARS = 18;
	
	public Demo08() {
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
			String userName = retrieveUserName();
			LocalDate userBirthday = retrieveUserBirthday();
			if (userBirthday.plusYears(RESTRICTED_ACCESS_YEARS).isAfter(LocalDate.now()))
				restrictedAccessGreeter.greet(userName);
			else
				greeter.greet(userName);
		} catch (Exception e) {
			log.error("The application has stopped due to problems.", e);
			System.err.println("The application has stopped: " + e.getMessage());
		} finally {
			Utils.delayBeforeExit();
			log.info("Racing Simulator demo stopped.");
		}
	}

	@Deprecated
	private String retrieveUserName() {
		// getting user name
		String username = null;
		while (StringUtils.isBlank(username)) {
			username = System.console().readLine("Please enter your name: ");
			if (StringUtils.isBlank(username))
				System.console().printf("Ooops! Looks like you've entered the incorrect name.\nPlease try again.\n");					
		}		
		return username;
	}
	
	@Deprecated
	private LocalDate retrieveUserBirthday() {
		// getting user birthday					
		LocalDate date = null;
		do {
			String strBirthday = System.console().readLine("Please enter your birthday in the yyyy-mm-dd format, e.g. 1990-01-01: ");			
			try {
				// only since Java 8
				date = LocalDate.parse(strBirthday, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (DateTimeParseException e) {
				System.console().printf("Ooops! Looks like you've entered the incorrect birthday.\nPlease try again.\n");
				date = null;
			} catch (Exception e) {
				System.console().printf("Ooops! Looks like you've entered the birthday in the wrong format.\nPlease try again.\n");
				date = null;
			}
		} while (date == null);		
		return date;
	}	
}