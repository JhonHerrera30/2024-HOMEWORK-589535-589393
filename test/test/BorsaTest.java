package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() {
		this.borsa=new Borsa();
		this.attrezzo= new Attrezzo("attrezzo",5);
	}

	@Test
	public void testAddAttrezzo() {
		assertFalse(this.borsa.hasAttrezzo("attrezzo"));
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo("attrezzo"));
	}

	@Test
	public void testGetPesoMax() {
		assertEquals(10, this.borsa.getPesoMax());
	}

	@Test
	public void testGetAttrezzo_Assente() {
		assertNull(this.borsa.getAttrezzo("assente"));
	}
	
	@Test
	public void testGetAttrezzo_Presente() {
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(attrezzo, this.borsa.getAttrezzo("attrezzo"));
	}

	@Test
	public void testGetPeso_BorsaVuota() {
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPeso_BorsaNonVuota() {
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(5, this.borsa.getPeso());
	}

	@Test
	public void testIsEmpty_BorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsEmpty_BorsaNonVuota() {
		this.borsa.addAttrezzo(attrezzo);
		assertFalse(this.borsa.isEmpty());
	}

	@Test
	public void testRemoveAttrezzo_conAttrezzo() {
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo("attrezzo"));
		assertEquals(attrezzo, this.borsa.removeAttrezzo("attrezzo"));
		assertFalse(this.borsa.hasAttrezzo("attrezzo"));
		
	}
	
	@Test
	public void testGetNumeroAttrezzi_BorsaVuota() {
		assertEquals(0,this.borsa.getNumeroAttrezzi());
	}
	
	@Test
	public void testGetNumeroAttrezzi_BorsaNonVuota() {
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(1,this.borsa.getNumeroAttrezzi());
	}
	
	

}
