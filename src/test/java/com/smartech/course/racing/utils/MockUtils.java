/**
 * 
 */
package com.smartech.course.racing.utils;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;
import com.smartech.course.racing.vehicle.Vehicle;
import com.smartech.course.racing.vehicle.VehicleState;

/**
 * Several help methods for mocking
 * @author Alexey Solomatin
 *
 */
public class MockUtils {

	public static Movable mockVehicle() throws MovingVehicleException {
		Movable vehicle = mock(Movable.class);
		when(vehicle.move(any(VehicleState.class), anyDouble())).thenAnswer(new Answer<VehicleState>() {
			@Override
			public VehicleState answer(InvocationOnMock invocation) throws Throwable {
				if (invocation.getArguments() != null &&
					invocation.getArguments().length == 2 &&
					invocation.getArguments()[0] instanceof VehicleState &&
					invocation.getArguments()[0] != null &&
					invocation.getArguments()[1] instanceof Double &&
					invocation.getArguments()[1] != null) {
					VehicleState curVS = invocation.getArgumentAt(0, VehicleState.class);
					Double timeStep = (Double) invocation.getArgumentAt(1, Double.class);
					return new VehicleState(curVS.getTime() + timeStep, 
							curVS.getSpeed() + timeStep, 
							curVS.getPosition() + timeStep);
				} else
					return null;
			}			
		});
		return vehicle;
	}

}
