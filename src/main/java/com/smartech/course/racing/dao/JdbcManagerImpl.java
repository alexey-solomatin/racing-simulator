/**
 * 
 */
package com.smartech.course.racing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.sql.DataSource;

import com.smartech.course.racing.exception.DataAccessConnectionFailureException;
import com.smartech.course.racing.exception.DataAccessException;
import com.smartech.course.racing.exception.DataAccessOperationErrorException;

/**
 * @author Alexey Solomatin
 *
 */
public class JdbcManagerImpl implements JdbcManager {
	private final DataSource dataSource;
	private final PreparedStatementParameterSetter preparedStatementParameterSetter;

	/**
	 * 
	 */
	public JdbcManagerImpl(DataSource dataSource,
			final PreparedStatementParameterSetter preparedStatementParameterSetter) {
		this.dataSource = dataSource;
		this.preparedStatementParameterSetter = preparedStatementParameterSetter;
	}

	protected final Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (final SQLException e) {
			throw new DataAccessConnectionFailureException(e);
		}

	}

	private void closeQuietly(final Connection connection, final PreparedStatement statement,
			final ResultSet resultSet) {
		if (null != resultSet)
			try {
				resultSet.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}

		if (null != statement)
			try {
				statement.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}

		if (null != connection)
			try {
				connection.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
	}

	private void rollback(final Connection connection) {
		if (null != connection) {
			try {
				connection.rollback();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smartech.course.racing.dao.JdbcManager#select(java.lang.String,
	 * java.util.function.Function, java.lang.Object[])
	 */
	@Override
	public <T> List<T> select(String sql, Function<ResultSet, T> rowMapper, Object... parameters)
			throws DataAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		final List<T> result = new ArrayList<T>();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			preparedStatementParameterSetter.setParameters(statement, parameters);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(rowMapper.apply(resultSet));
			}
		} catch (final SQLException e) {
			throw new DataAccessOperationErrorException(e);
		} finally {
			closeQuietly(connection, statement, resultSet);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smartech.course.racing.dao.JdbcManager#update(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public int update(String sql, Object... parameters) throws DataAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		final ResultSet resultSet = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			preparedStatementParameterSetter.setParameters(statement, parameters);
			final int result = statement.executeUpdate();
			connection.commit();
			return result;
		} catch (final DataAccessException e) {
			rollback(connection);
			throw e;
		} catch (final Exception e) {
			rollback(connection);
			throw new DataAccessOperationErrorException(e);
		} finally {
			closeQuietly(connection, statement, resultSet);
		}
	}

	@Override
	public long insertAndGetId(String sql, Object... parameters) throws DataAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatementParameterSetter.setParameters(statement, parameters);
			final int result = statement.executeUpdate();
			Long id = null;
			if (0 != result) {
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getLong(1);
				}
			}
			if (null == id) {
				throw new DataAccessOperationErrorException("No id is returned");
			}
			connection.commit();
			return id;
		} catch (final DataAccessException e) {
			rollback(connection);
			throw e;
		} catch (final Exception e) {
			rollback(connection);
			throw new DataAccessOperationErrorException(e);
		} finally {
			closeQuietly(connection, statement, resultSet);
		}
	}

}
