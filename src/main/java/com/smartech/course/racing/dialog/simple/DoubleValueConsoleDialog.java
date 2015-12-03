/**
 * 
 */
package com.smartech.course.racing.dialog.simple;

import java.util.function.Predicate;

/**
 * @author Alexey Solomatin
 *
 */
public class DoubleValueConsoleDialog extends AbstractSimpleConsoleDialog<Double> {

	/**
	 * @param questionMessage
	 * @param errorMessage
	 * @param validator
	 * @param convertor
	 */
	public DoubleValueConsoleDialog(String questionMessage, String errorMessage, Predicate<Double> validator) {
		super(questionMessage, errorMessage, validator, Double::parseDouble);
	}

}
