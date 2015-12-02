/**
 * 
 */
package com.smartech.course.racing.dialog;

import java.util.function.Predicate;

/**
 * @author Alexey Solomatin
 *
 */
public class StringValueConsoleDialog extends AbstractSimpleConsoleDialog<String> {

	/**
	 * @param questionMessage
	 * @param errorMessage
	 * @param validator
	 * @param convertor
	 */
	public StringValueConsoleDialog(String questionMessage, String errorMessage, Predicate<String> validator) {
		super(questionMessage, errorMessage, validator, (s)->s);
	}

}
