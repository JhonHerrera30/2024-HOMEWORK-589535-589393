package test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	

	@Test
	public void testGetAttrezzo_stanzaVuota() {
		Stanza vuota= new Stanza("vuota");
		assertNull(vuota.getAttrezzo("inesistente"));
	}
	
	@Test
	public void testGetAttrezzo_stanzaNonVuota_Presente() {
		Stanza stanza= new Stanza("stanza");
		Attrezzo attrezzo= new Attrezzo("attrezzo", 0);
		stanza.addAttrezzo(attrezzo);
		assertNotNull(stanza.getAttrezzo("attrezzo"));
	}
	 
	@Test
	public void testGetAttrezzo_stanzaNonVuota_Assente() {
		Stanza stanza= new Stanza("stanza");
		Attrezzo attrezzo= new Attrezzo("attrezzo", 0);
		stanza.addAttrezzo(attrezzo);
		assertNull(stanza.getAttrezzo("nomeDiAttrezzoNonPresente"));
	}
	
	@Test
	public void testGetstanzaAdiacente_stanzaAdiacente_NonPresente() {
		Stanza stanza= new Stanza("primastanza");
		assertNull(stanza.getStanzaAdiacente("direzioneEsistente"));
	}
	
	@Test
	public void testGetstanzaAdiacente_stanzaAdiacente_Presente() {
		Stanza stanza= new Stanza("primastanza");
		Stanza stanzaAdiacente= new  Stanza("stanzaAdiacente");
		stanza.impostaStanzaAdiacente("direzioneEsistente", stanzaAdiacente);
		assertEquals( stanzaAdiacente, stanza.getStanzaAdiacente("direzioneEsistente"));
	}
	
	@Test
	public void testAddAttrezzo_ConAttrezzoAggiunto() {
		Stanza stanza= new Stanza("stanza");
		Attrezzo attrezzo= new Attrezzo("attrezzo", 0);
		assertFalse(stanza.hasAttrezzo("attrezzo"));
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertTrue(stanza.hasAttrezzo("attrezzo"));
		
	}
	
	@Test
	public void testRemoveAttrezzo_ConAttrezzoAggiunto() {
		Stanza stanza= new Stanza("stanza");
		Attrezzo attrezzo= new Attrezzo("attrezzo", 0);
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.hasAttrezzo("attrezzo"));
		stanza.removeAttrezzo(attrezzo);
		assertFalse(stanza.hasAttrezzo("attrezzo"));
	}
}
