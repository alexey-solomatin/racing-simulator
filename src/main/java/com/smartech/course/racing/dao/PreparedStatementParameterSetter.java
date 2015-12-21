/**
 * 
 */
package com.smartech.course.racing.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Alexey Solomatin
 *
 */
public interface PreparedStatementParameterSetter {
	void setParameters(final PreparedStatement statement, final Object... parameters) throws SQLException;
}
