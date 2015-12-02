/**
 * 
 */
package com.smartech.course.racing.dialog;

import java.util.function.Predicate;

/**
 * @author Alexey Solomatin
 *
 */
public class LongValueConsoleDialog extends AbstractSimpleConsoleDialog<Long> {

	/**
	 * @param questionMessage
	 * @param errorMessage
	 * @param validator
	 * @param convertor
	 */
	public LongValueConsoleDialog(String questionMessage, String errorMessage, Predicate<Long> validator) {
		super(questionMessage, errorMessage, validator, Long::parseLong);
	}

}
