/**
 * 
 */
package com.smartech.course.racing.dialog.simple;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Alexey Solomatin
 *
 */
public class YesNoConsoleDialog extends AbstractSimpleConsoleDialog<Boolean> {

	/**
	 * @param questionMessage
	 * @param errorMessage
	 * @param validator
	 * @param convertor
	 */
	public YesNoConsoleDialog(String questionMessage) {
		super(questionMessage + " (yes/no) ", "Unknown error.", (v)->true, YesNoConsoleDialog::parseYesNoChoise);
	}
	
	private static boolean parseYesNoChoise(String choise) {
		switch (choise.toLowerCase()) {
		case "yes":
		case "y":
			return true;
		default:
			return false;
		}
	}

}
