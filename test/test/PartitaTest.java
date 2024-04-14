package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	private Partita partita;
	private Labirinto labirinto;
	
	
	@Before
	public void setUp() {
		this.labirinto= new Labirinto();
		this.partita= new Partita(labirinto);
	}
	
	
	@Test
	public void testGetStanzaCorrente() {
		assertNotNull(partita.getStanzaCorrente());
	}
	

	@Test
	public void testVinta_Persa() {
		assertFalse(partita.vinta());
		
	}
	
	@Test
	public void testVinta_Vinta() {
		Stanza stanza=this.partita.getLabirinto().getStanzaVincente();
		this.partita.setStanzaCorrente(stanza);
		assertTrue(partita.vinta());
	}

	@Test
	public void testIsFinita_CfuFiniti() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_Fine() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_Vinta() {
		Stanza stanza=this.partita.getLabirinto().getStanzaVincente();
		this.partita.setStanzaCorrente(stanza);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_NonFinita() {
		assertFalse(partita.isFinita());
	}

	@Test
	public void testSetStanzaCorrente() {
		Stanza stanza= new Stanza("stanza");
		partita.setStanzaCorrente(stanza);
		assertEquals(stanza, partita.getStanzaCorrente());
	}

}
