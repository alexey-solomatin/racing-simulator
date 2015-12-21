/**
 * 
 */
package com.smartech.course.racing.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

import com.smartech.course.racing.exception.DataAccessException;

/**
 * @author Alexey Solomatin
 *
 */
public interface JdbcManager {
	<T> List<T> select(String sql, Function<ResultSet, T> rowMapper, Object... parameters) throws DataAccessException;
	long insertAndGetId(final String sql, final Object... parameters) throws DataAccessException;
	int update(final String sql, final Object... parameters) throws DataAccessException;
}
