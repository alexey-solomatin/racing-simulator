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
public class DemoAttempt1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// getting a console
		Console console = System.console();
		
		// getting a user name
		String username = console.readLine("Please enter your name: ");
		
		// greeting the user 
		System.out.println(String.format("Hello %s! This is Racing Simulator!", username));
		
		// delaying the program execution
		try {
			System.out.println("Press <Enter> to exit...");
			console.reader().read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
