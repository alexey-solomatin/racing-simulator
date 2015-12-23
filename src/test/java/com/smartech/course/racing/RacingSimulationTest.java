/**
 * 
 */
package com.smartech.course.racing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.simulation.Racer;
import com.smartech.course.racing.simulation.Racing;
import com.smartech.course.racing.simulation.RacingSimulation;
import com.smartech.course.racing.simulation.SingleThreadRacingSimulation;
import com.smartech.course.racing.utils.MockUtils;
import com.smartech.course.racing.vehicle.Movable;
import com.smartech.course.racing.vehicle.VehicleState;

/**
 * Tests for {@link SingleThreadRacingSimulation}
 * @author Alexey Solomatin
 *
 */
public class RacingSimulationTest {

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
	 * Test method for {@link com.smartech.course.racing.simulation.SingleThreadRacingSimulation#register(com.smartech.course.racing.vehicle.Movable)}.
	 * @throws MovingVehicleException 
	 */
	@Test
	public void testRegister() throws MovingVehicleException {		
		SingleThreadRacingSimulation simulation = new SingleThreadRacingSimulation(new Racing(1L), 1);
		Movable vehicle = MockUtils.mockVehicle();
		simulation.register("TestRacer", vehicle);
		Collection<Racer> racers = simulation.getRacers();
		assertNotNull(racers);
		assertEquals(1, racers.size());
		assertTrue(racers.iterator().hasNext());
		assertSame(vehicle, ((Racer)racers.iterator().next()).getVehicle());
	}

	/**
	 * Test method for {@link com.smartech.course.racing.simulation.SingleThreadRacingSimulation#run()}.
	 * @throws MovingVehicleException 
	 */
	@Test
	public void testRun() throws MovingVehicleException {
		Racing racing = new Racing(1L, "Racing #1", 4.5);		
		double timeStep = 1;
		RacingSimulation simulation = new SingleThreadRacingSimulation(racing, timeStep);
		Movable vehicle1 = MockUtils.mockVehicle();
		Movable vehicle2 = MockUtils.mockVehicle();
		simulation.register("TestRacer1", vehicle1);		
		simulation.register("TestRacer2", vehicle2);				
		simulation.run();
		verify(vehicle1, times(5)).move(any(VehicleState.class), anyDouble());
		verify(vehicle2, times(5)).move(any(VehicleState.class), anyDouble());
	}

}
