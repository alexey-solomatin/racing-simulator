/**
 * 
 */
package com.smartech.course.racing.dialog.simple;

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
		super(questionMessage + " (yes/no) ", "You've entered the unknown answer.", (v)->v != null, YesNoConsoleDialog::parseYesNoChoise);
	}
	
	private static Boolean parseYesNoChoise(String choise) {
		switch (choise.toLowerCase()) {
		case "yes":
		case "y":
			return true;
		case "no":
		case "n":
			return false;
		default:
			return null;
		}
	}

}
