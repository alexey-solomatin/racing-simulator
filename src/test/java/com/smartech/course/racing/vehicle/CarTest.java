/**
 * 
 */
package com.smartech.course.racing.vehicle;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.smartech.course.racing.exception.MovingException;

/**
 * Tests for {@link Car}
 * @author Alexey Solomatin
 *
 */
public class CarTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.smartech.course.racing.vehicle.Vehicle#move(double)}.
	 * @throws MovingException 
	 */
	@Test
	public void testMove() throws MovingException {		
		double weight = 500;
		double acceleration = 5;
		double maxSpeed = 20;
		double time = 1;
		double expectedSpeed = acceleration * time;
		double expectedPosition = acceleration * time * time / 2;
		Car car = new Car("TestCar", weight, acceleration, maxSpeed);
		car.move(1);
		assertEquals(expectedSpeed, car.getSpeed(), 0.0001);
		assertEquals(expectedPosition, car.getPosition(), 0.0001);
		assertEquals(time, car.getTime(), 0.0001);
	}

}
