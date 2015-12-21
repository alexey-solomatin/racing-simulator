/**
 * 
 */
package com.smartech.course.racing.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.smartech.course.racing.exception.DataAccessConnectionFailureException;
import com.smartech.course.racing.exception.DataAccessException;

/**
 * @author Alexey Solomatin
 *
 */
public abstract class AbstractJDBCDao<T extends Persistable<PK>, PK extends Serializable> implements GenericDao<T, PK> {
	private DataSource dataSource;

	/**
	 * 
	 */
	public AbstractJDBCDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	protected abstract String getSelectQuery();
	protected abstract String getCreateQuery();
	protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DataAccessException;
	protected abstract List<T> parseResultSet(ResultSet rs) throws DataAccessException;
	
	@Override
	public PK create(T object) throws SQLException {
		if (object.getId() != null)
			throw new DataAccessException("The object cannot be created.");
		
		try (Connection connection = getConnection()) {
			T persistInstance;
	        String sql = getCreateQuery();
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            prepareStatementForInsert(statement, object);
	            int count = statement.executeUpdate();
	            if (count != 1) {
	                throw new DataAccessException("On persist modify more then 1 record: " + count);
	            }
	        } catch (Exception e) {
	            throw new DataAccessException(e);
	        }
	        
	        sql = getSelectQuery() + " WHERE id = last_insert_id();";
	        try (PreparedStatement statement = connection.prepareStatement(sql); 
        		ResultSet rs = statement.executeQuery()) {	            
	            List<T> list = parseResultSet(rs);
	            if ((list == null) || (list.size() != 1)) {
	                throw new DataAccessException("Exception on findByPK new persist data.");
	            }
	            persistInstance = list.iterator().next();
	        } catch (Exception e) {
	            throw new DataAccessException(e);
	        }
	        return persistInstance.getId();
		}		
	}
	
	@Override
	public T read(PK key) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<T> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(T object) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(T object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	protected final Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (final SQLException e) {
			throw new DataAccessConnectionFailureException(e);
		}

	}
}
