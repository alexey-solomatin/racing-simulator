/**
 * 
 */
package com.smartech.course.racing.dialog;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: the composite pattern can be probably used for creation complicated objects like a list of vehicles and so forth
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractSimpleConsoleDialog<ValueType> implements Supplier<ValueType> {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String questionMessage;
	private String errorMessage;
	private Predicate<ValueType> validator;
	private Function<String, ValueType> convertor;

	/**
	 * 
	 */
	public AbstractSimpleConsoleDialog(String questionMessage, String errorMessage, Predicate<ValueType> validator, Function<String, ValueType> convertor) {
		this.questionMessage = questionMessage;
		this.errorMessage = errorMessage;
		this.validator = validator;
		this.convertor = convertor;
	}

	@Override
	public ValueType get() {
		while (true) {
			try {
				ValueType value = convertor.apply(System.console().readLine(questionMessage));
				if (!validator.test(value)) {
					System.console().printf(errorMessage + ". Please try again.\n\n");
					continue;
				}
				return value;
			} catch (Exception e) {
				log.error("Error in the dialog.", e);
				System.console().printf(errorMessage + ". Please try again.\n\n");
			}
		}		
	}

}
