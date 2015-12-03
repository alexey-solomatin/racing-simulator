/**
 * 
 */
package com.smartech.course.racing.builder.vehicle;

import java.util.function.Supplier;

import com.smartech.course.racing.exception.CreatingVehicleException;

/**
 * @author Alexey Solomatin
 *
 */
@FunctionalInterface
public interface DynamicBuilder<ValueType, BuilderType> {
	BuilderType buildPart(Supplier<ValueType> valueSupplier) throws Exception;
}
