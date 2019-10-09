package testListeMemoire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.DAOFactory;
import factory.Persistance;
import metier.Revue;

class ListeMemoireRevueDAOTest {
	
	
	private DAOFactory daos;
	private Revue test;

	@BeforeEach
	public void setUp() {
		this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);	
	}
	
	
	@Test
	void test_create()
	{
		test = new Revue(0,"test","ceci est un test",3,"test.jpg",3);
		assertTrue(this.daos.getRevueDAO().create(test));
	}
	@Test
	void test_update()
	{
		Revue test2 = new Revue(test.getId(),"tt","rr",3,"tt.jpg",3);
		assertTrue(this.daos.getRevueDAO().update(test2));
	}
	@Test
	void test_delete()
	{
		assertTrue(this.daos.getRevueDAO().delete(test));
	}
	
	
	
	@Test
	void test_update_impossible()
	{
		try {
		this.daos.getRevueDAO().update(new Revue(10,"test","ceci est un test",3,"test.jpg",3));
		fail();
		}
		catch(IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	@Test
	void test_delete_impossible()
	{
		try {
			this.daos.getRevueDAO().delete(new Revue(10,"test","ceci est un test",3,"test.jpg",3));
			fail();
			}
			catch(IllegalArgumentException e) {
				assertTrue(true);
			}
	}
	
	
	@Test
	void testGetById()
	{
		fail("a faire");
	}
	
	
	@Test
	void testGetByTitre()
	{
		fail("a faire");
	}
	@Test
	void testperiodicite()
	{
		fail("a faire");
	}

}
