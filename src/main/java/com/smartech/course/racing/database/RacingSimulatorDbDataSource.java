/**
 * 
 */
package com.smartech.course.racing.database;

import java.util.function.Supplier;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.smartech.course.racing.config.AppProperties;

/**
 * @author Alexey Solomatin
 *
 */
public class RacingSimulatorDbDataSource implements Supplier<DataSource> {
	private static volatile RacingSimulatorDbDataSource instance;
	
	private BasicDataSource dataSource;

	/**
	 * 
	 */
	private RacingSimulatorDbDataSource() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("racing_simulator");
		dataSource.setPassword("racing_simulator");
		dataSource.setUrl("jdbc:mysql://localhost/racing_simulator?useSSL=false");
		// &noAccessToProcedureBodies=true
	}
	
	public static RacingSimulatorDbDataSource getInstance() {
		RacingSimulatorDbDataSource localInstance = instance;
		if (localInstance == null) {
			synchronized (RacingSimulatorDbDataSource.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new RacingSimulatorDbDataSource();
				}
			}
		}
		return localInstance;
	}

	@Override
	public DataSource get() {
		return dataSource;
	}	

}
