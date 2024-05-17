package test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoBuilderTest {
	

	@Test
	public void testGetLabirinto() {
		Labirinto labirintoVuoto = new LabirintoBuilder().getLabirinto();
		assertNotNull(labirintoVuoto);
		assertNull(labirintoVuoto.getStanzaIniziale());
	}

	@Test
	public void testGetNome2Stanza() {
		assertNotNull(new LabirintoBuilder().getNome2Stanza());
	}
	
	@Test
	public void testAddStanzaIniziale() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("start")
				.getLabirinto();
		assertEquals("start",monolocale.getStanzaIniziale().getNome());
	}

	@Test
	public void testUltimaStanzaAggiuntaEAggiorna() {
		Stanza ultimaStanzaAggiunta = new Stanza("ultimaStanzaAggiunta");
		LabirintoBuilder monolocale = new LabirintoBuilder();
		Map<String,Stanza> stanzeDelLabirinto = monolocale.getNome2Stanza();
		assertEquals(0,stanzeDelLabirinto.size());
		monolocale.UltimaStanzaAggiuntaEAggiorna(ultimaStanzaAggiunta);
		assertEquals(1,stanzeDelLabirinto.size());
		assertEquals(stanzeDelLabirinto.get("ultimaStanzaAggiunta").getNome(),"ultimaStanzaAggiunta");
	}

	@Test
	public void testAddStanzaVincente() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("start")
				.addStanzaVincente("end")
				.getLabirinto();
		assertEquals("end",bilocale.getStanzaVincente().getNome());
	}

	@Test
	public void testAddStanza() {
		LabirintoBuilder monolocale = new LabirintoBuilder()
				.addStanza("stanza");
		Map<String,Stanza> stanzeDelLabirinto = monolocale.getNome2Stanza();
		assertEquals(stanzeDelLabirinto.get("stanza").getNome(),"stanza");
	}

	@Test
	public void testAddAttrezzo() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("start")
				.addAttrezzo("attrezzo", 3)
				.getLabirinto();
		assertTrue(monolocale.getStanzaIniziale().hasAttrezzo("attrezzo"));
	}

	@Test
	public void testAddAdiacenza() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("start")
				.addStanzaVincente("end")
				.addAdiacenza("start", "end", "nord")
				.getLabirinto();
		assertEquals(bilocale.getStanzaIniziale().getStanzaAdiacente("nord").getNome(),"end");
	}


}
