/**
 * 
 */
package com.smartech.course.racing.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Alexey Solomatin
 *
 */
public class DefaultPreparedStatementParameterSetter implements PreparedStatementParameterSetter {

	/**
	 * 
	 */
	public DefaultPreparedStatementParameterSetter() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smartech.course.racing.dao.PreparedStatementParameterSetter#
	 * setParameters(java.sql.PreparedStatement, java.lang.Object[])
	 */
	@Override
	public void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
		for (int i = 0, length = parameters.length; i < length; i++) {
			final Object parameter = parameters[i];
			final int parameterIndex = i + 1;
			if (null == parameter) {
				statement.setObject(parameterIndex, null);
			} else if (parameter instanceof Boolean) {
				statement.setBoolean(parameterIndex, (Boolean) parameter);
			} else if (parameter instanceof Character) {
				statement.setString(parameterIndex, String.valueOf(parameter));
			} else if (parameter instanceof Byte) {
				statement.setByte(parameterIndex, (Byte) parameter);
			} else if (parameter instanceof Short) {
				statement.setShort(parameterIndex, (Short) parameter);
			} else if (parameter instanceof Integer) {
				statement.setInt(parameterIndex, (Integer) parameter);
			} else if (parameter instanceof Long) {
				statement.setLong(parameterIndex, (Long) parameter);
			} else if (parameter instanceof Float) {
				statement.setFloat(parameterIndex, (Float) parameter);
			} else if (parameter instanceof Double) {
				statement.setDouble(parameterIndex, (Double) parameter);
			} else if (parameter instanceof String) {
				statement.setString(parameterIndex, (String) parameter);
			} else if (parameter instanceof Date) {
				statement.setDate(parameterIndex, new java.sql.Date(((Date) parameter).getTime()));
			} else if (parameter instanceof Calendar) {
				statement.setDate(parameterIndex, new java.sql.Date(((Calendar) parameter).getTimeInMillis()));
			} else if (parameter instanceof BigDecimal) {
				statement.setBigDecimal(parameterIndex, (BigDecimal) parameter);
			} else {
				throw new IllegalArgumentException(
						String.format("Unknown type of the parameter is found. [param: %s, paramIndex: %s]", parameter,
								parameterIndex));
			}
		}
	}

}
