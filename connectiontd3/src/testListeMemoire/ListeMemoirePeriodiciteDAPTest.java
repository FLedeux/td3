package testListeMemoire;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.DAOFactory;
import factory.Persistance;
import metier.Periodicite;


class ListeMemoirePeriodiciteDAPTest {

	private DAOFactory daos;
	private Periodicite test;

	@BeforeEach
	public void setUp() {
		this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);	
	}
	
	
	@Test
	void test_create()
	{
		test = new Periodicite(0,"test");
		assertTrue(this.daos.getPeriodiciteDAO().create(test));
	}
	
	
	@Test
	void testGetById()
	{
		test = new Periodicite(0,"test");
		this.daos.getPeriodiciteDAO().create(test);
		assertEquals(this.daos.getPeriodiciteDAO().getById(test.getId()),test);
	}
	
	@Test
	void testGetByNom()
	{
		test = new Periodicite(0,"banane");
		this.daos.getPeriodiciteDAO().create(test);
		assertEquals(this.daos.getPeriodiciteDAO().getByNom(test),test);
	}
	
	
	@Test
	void test_update()
	{
		test = new Periodicite(0,"test");
		this.daos.getPeriodiciteDAO().create(test);
		Periodicite test2 = new Periodicite(test.getId(),"test2");
		assertTrue(this.daos.getPeriodiciteDAO().update(test2));
	}
	
	@Test
	void test_delete()
	{
		test = new Periodicite(0,"test");
		this.daos.getPeriodiciteDAO().create(test);
		assertTrue(this.daos.getPeriodiciteDAO().delete(test));
	}
	
	
	
	
	
	
	@Test
	void testGetById_impossible()
	{
		test = new Periodicite(-1,"test");
		try {
			assertEquals(this.daos.getPeriodiciteDAO().getById(test.getId()),test);
			fail();
		}catch(IllegalArgumentException e) {
			assertTrue(true);
		}

	}
	
	@Test
	void testGetByNom_impossible()
	{
		test = new Periodicite(-1,"mandarine");
		try {
			assertEquals(this.daos.getPeriodiciteDAO().getByNom(test),test);
			fail();
		}catch(IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	
	@Test
	void test_update_impossible()
	{
		test = new Periodicite(-1,"test");
		try {
			assertEquals(this.daos.getPeriodiciteDAO().update(test),test);
			fail();
		}catch(IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void test_delete_impossible()
	{
		test = new Periodicite(-1,"test");
		try {
			assertEquals(this.daos.getPeriodiciteDAO().delete(test),test);
			fail();
		}catch(IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	
	


}
