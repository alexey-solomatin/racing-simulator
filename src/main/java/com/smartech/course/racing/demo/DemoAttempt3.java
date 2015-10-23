/**
 * 
 */
package com.smartech.course.racing.demo;

import java.io.Console;
import java.io.IOException;

/**
 * @author Alexey Solomatin
 *
 */
public class DemoAttempt3 {
	private static Console console;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		console = retrieveConsole();
		String username = retrieveUsername();
		greetUser(username);		
		prepareForExit();
	}

	private static Console retrieveConsole() {
		// checking if there's a console
		Console console = System.console();
		if (console == null) {
			System.err.println("ERROR: There is no console.");
			System.exit(1); 
		}
		return console;
	}
	
	private static String retrieveUsername() {
		// getting user name
		String username = console.readLine("Please enter your name: ");
		// checking that it's not empty
		if (username == null || username.isEmpty()) {
			console.printf("I'm sorry but you've not represented yourself. Bye!..\n");
			System.exit(0);			
		}
		return username;
	}
	
	private static void greetUser(String username) {
		// greeting the user 
		console.printf("Hello %s! This is Racing Simulator!\n", username);
	}
	
	private static void prepareForExit() {
		// delaying the program execution
		try {
			console.printf("Press <Enter> to exit...");
			console.reader().read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
