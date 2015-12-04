/**
 * 
 */
package com.smartech.course.racing.dialog;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class ConsoleDialog<ResultType> implements Supplier<ResultType> {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	protected String questionMessage;
	protected String errorMessage;
	

	/**
	 * 
	 */
	public ConsoleDialog(String questionMessage, String errorMessage) {
		// TODO Auto-generated constructor stub
		this.questionMessage = questionMessage;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public ResultType get() {
		while (true) {
			System.console().printf(questionMessage + "\n");
			try {
				return buildObject();
			}  catch (RuntimeException e) {
				log.error("Fatal error.", e);
				System.console().printf("Fatal error during vehicle creation!\n");
				throw e;
			} catch (Exception e) {
				log.error(errorMessage, e);
				System.console().printf(errorMessage + " Please try again.\n");
			}
		}
	}
	
	protected abstract ResultType buildObject() throws Exception;

}
