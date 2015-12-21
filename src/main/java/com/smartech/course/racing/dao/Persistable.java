package com.smartech.course.racing.dao;

import java.io.Serializable;

/**
 * 
 * @author Alexey Solomatin
 *
 * @param <PK>
 */
public interface Persistable<PK extends Serializable> {
	PK getId();
}
