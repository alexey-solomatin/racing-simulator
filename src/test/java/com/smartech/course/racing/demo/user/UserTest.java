/**
 * 
 */
package com.smartech.course.racing.demo.user;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link User}
 * @author Alexey Solomatin
 *
 */
public class UserTest {

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
	 * Test method for {@link com.smartech.course.racing.demo.user.User#User(java.lang.String, java.time.LocalDate, com.smartech.course.racing.demo.user.UserType)}.
	 */
	@Test
	@Ignore
	public void testUser() {
		fail("Not yet implemented");
		// TODO: here construction of users should be tested
	}

	/**
	 * Test method for {@link com.smartech.course.racing.demo.user.User#getAge(java.time.LocalDate)}.
	 */
	@Test
	public void testGetAgeLocalDate() {		
		User user = new User("name", LocalDate.of(2000, 1, 1), UserType.USER);
		assertEquals(Period.of(1, 1, 1), user.getAge(LocalDate.of(2001, 2, 2)));
	}

	/**
	 * Test method for {@link com.smartech.course.racing.demo.user.User#getAge()}.
	 */
	@Test
	public void testGetAge() {		
		LocalDate birthday = LocalDate.now().minusYears(1);		
		User user = new User("name", birthday, UserType.USER);
		assertEquals(Period.of(1, 0, 0), user.getAge());
	}

	/**
	 * Test method for {@link com.smartech.course.racing.demo.user.User#isCelebratingToday()}.
	 */
	@Test
	public void testIsCelebratingToday() {
		LocalDate birthday = LocalDate.now().withYear(2000);
		User user = new User("name", birthday, UserType.USER);
		assertTrue(user.isCelebratingToday());		
	}

}
