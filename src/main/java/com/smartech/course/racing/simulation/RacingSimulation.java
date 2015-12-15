package com.smartech.course.racing.simulation;

import java.util.function.BiConsumer;

import com.smartech.course.racing.exception.MovingVehicleException;
import com.smartech.course.racing.vehicle.Movable;

public interface RacingSimulation {

	void register(String racerName, Movable vehicle);

	void addRacerEventCallback(BiConsumer<Racer, Object> callback);

	void removeRacerEventCallback(BiConsumer<Racer, Object> callback);

	void run() throws MovingVehicleException;

	boolean isRunning();

}