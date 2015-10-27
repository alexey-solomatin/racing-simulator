/**
 * 
 */
package com.smartech.course.racing.demo.util;

import java.io.IOException;

/**
 * Several help utils
 * @author Alexey Solomatin
 *
 */
public class Utils {
	/**
	 * Checks if the console exists. If it doesn't, an exceptions is thrown. 
	 * @throws Exception
	 */
	public static void checkConsole() throws Exception {
		// checking if there's a console		
		if (System.console() == null)
			throw new Exception("There is no console.");
	}
	
	public static void delayBeforeExit() {
		try {
			// delaying the program execution		
			System.console().printf("\nPress <Enter> to exit...");
			System.console().reader().read();
		} catch (IOException e) {			
		}					
	}
	
}
