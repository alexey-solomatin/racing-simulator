/**
 * 
 */
package com.smartech.course.racing.dialog;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartech.course.racing.dialog.simple.DoubleValueConsoleDialog;
import com.smartech.course.racing.dialog.simple.StringValueConsoleDialog;
import com.smartech.course.racing.exception.CreatingVehicleException;

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
			System.console().printf(questionMessage);
			try {
				return buildObject();
			} catch (Exception e) {
				log.error(errorMessage, e);
				System.console().printf(errorMessage + " Please try again.");
			}
		}
	}
	
	protected abstract ResultType buildObject() throws Exception;

}
