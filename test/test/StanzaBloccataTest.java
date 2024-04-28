package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private Stanza stanza;
	
	@Before 
	public void setUp() {
		stanza= new StanzaBloccata("stanza", "Passpartout", "sud");
	}

	@Test
	public void testGetStanzaAdiacente_Bloccata() {
		assertEquals(stanza, stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacente_ConChiaveGiusta() {
		Attrezzo chiave = new Attrezzo("Passpartout", 1);
		stanza.addAttrezzo(chiave);
		assertNull(stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacente_ConChiaveErrata() {
		Attrezzo chiave = new Attrezzo("chiaveErrata", 1);
		stanza.addAttrezzo(chiave);
		assertEquals(stanza,stanza.getStanzaAdiacente("sud"));
	}
	
	

}
