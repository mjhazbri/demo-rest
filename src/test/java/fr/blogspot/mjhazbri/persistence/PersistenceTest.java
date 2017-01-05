package fr.blogspot.mjhazbri.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PersistenceTest {

	private static EntityManagerFactory entityManagerFactory;

	@Test
	public void testAddPencils() {
		System.out.println("Inside testAddPencils()");
	}

	@BeforeClass
	public static void setUpEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory("demoPU");
	}

	@AfterClass
	public static void closeEntityManagerFactory() {
		entityManagerFactory.close();
	}
}