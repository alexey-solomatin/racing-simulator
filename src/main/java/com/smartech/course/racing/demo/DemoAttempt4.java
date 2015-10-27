/**
 * 
 */
package com.smartech.course.racing.demo;

import java.io.IOException;

/**
 * @author Alexey Solomatin
 *
 */
public class DemoAttempt4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (!existsConsole())
			System.exit(1);
		String username = retrieveUsername();
		if (username == null || username.isEmpty())
			System.exit(0);
		greetUser(username);		
		prepareForExit();
	}

	private static boolean existsConsole() {
		// checking if there's a console
		if (System.console() == null) {
			System.err.println("ERROR: There is no console.");
			return false; 
		} else
			return true;
	}
	
	private static String retrieveUsername() {
		// getting user name
		String username = System.console().readLine("Please enter your name: ");
		// checking that it's not empty
		if (username == null || username.isEmpty())
			System.console().printf("I'm sorry but you've not represented yourself. Bye!..\n");					
		return username;
	}
	
	private static void greetUser(String username) {
		// greeting the user 
		System.console().printf("Hello %s! This is Racing Simulator!\n", username);
	}
	
	private static void prepareForExit() {
		// delaying the program execution
		try {
			System.console().printf("Press <Enter> to exit...");
			System.console().reader().read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
