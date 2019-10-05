package testMYSQL;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.DAOFactory;
import factory.Persistance;
import metier.Revue;

class MySQLRevueDAOTest {

	private DAOFactory daos;
	
	@BeforeEach
	public void setUp() {
		this.daos = DAOFactory.getDAOFactory(Persistance.MYSQL);	
	}
	
	
	@Test
	void testcreate()
	{
		assertTrue(this.daos.getRevueDAO().create(new Revue(0,"test2","ceci est un test",3,"test.jpg",3)));
	}
	
	@Test
	void testupdate()
	{
		assertTrue(this.daos.getRevueDAO().update(new Revue(4,"test","ceci est un test",3,"test.jpg",3)));
	}
	@Test
	void testdelete()
	{
		assertTrue(this.daos.getRevueDAO().delete(new Revue(6,"","",0,"",0)));
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
