/**
 * 
 */
package com.smartech.course.racing.demo;

import java.io.IOException;

/**
 * @author Alexey Solomatin
 *
 */
public class Demo05 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: extract the greeter functionality from the demo class
		// TODO: enable logging
		new Demo05().run();		
	}
	
	/**
	 * Runs the demo
	 */
	public void run() {
		try {
			checkConsole();				
			String username = retrieveUsername();			
			greetUser(username);		
			prepareForExit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkConsole() throws Exception {
		// checking if there's a console		
		if (System.console() == null)
			throw new Exception("There is no console.");
	}
	
	private String retrieveUsername() throws Exception {
		// getting user name
		String username = System.console().readLine("Please enter your name: ");
		// checking that it's not empty
		if (username == null || username.isEmpty())
			throw new Exception("I'm sorry but you've not represented yourself. Bye!..");
		return username;
	}
	
	private void greetUser(String username) {
		// greeting the user 
		System.console().printf("Hello %s! This is Racing Simulator!\n", username);
	}
	
	private void prepareForExit() {
		// delaying the program execution
		try {
			System.console().printf("Press <Enter> to exit...");
			System.console().reader().read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
