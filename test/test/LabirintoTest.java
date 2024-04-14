package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza stanza;
	
	@Before
	public void setUp() {
		labirinto= new Labirinto();
		stanza =new Stanza("stanza");
	}

	@Test
	public void testGetStanzaIniziale() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}

	@Test
	public void testSetStanzaIniziale() {
		assertNotEquals(stanza, labirinto.getStanzaIniziale());
		this.labirinto.setStanzaIniziale(stanza);
		assertEquals(stanza, this.labirinto.getStanzaIniziale());
	}

	@Test
	public void testGetStanzaVincente() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}

}
