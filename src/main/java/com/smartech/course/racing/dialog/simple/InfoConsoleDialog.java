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
public class InfoConsoleDialog extends AbstractSimpleConsoleDialog<Void> {

	/**
	 * @param questionMessage
	 * @param errorMessage
	 * @param validator
	 * @param convertor
	 */
	public InfoConsoleDialog(String questionMessage) {
		super(questionMessage, null, (v)->true, (s->null));		
	}

}
