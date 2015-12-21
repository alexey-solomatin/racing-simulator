/**
 * 
 */
package com.smartech.course.racing.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Alexey Solomatin
 *
 */
public interface GenericDao<T, PK extends Serializable> {
	PK create(T object) throws SQLException;	
	T read(PK key) throws SQLException;
	List<T> readAll() throws SQLException;
	void update(T object) throws SQLException;
	void delete(T object) throws SQLException;
}
