package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	private Giocatore giocatore;
	
	@Before
	public void setUp() {
		this.giocatore= new Giocatore();
	}
	
	@Test
	public void testGetCfu() {
		assertEquals(20, this.giocatore.getCfu());
	}

	@Test
	public void testSetCfu() {
		this.giocatore.setCfu(10);
		assertEquals(10,this.giocatore.getCfu() );
	}

	@Test
	public void testGetBorsa() {
		assertNotNull(this.giocatore.getBorsa());
	}

}
