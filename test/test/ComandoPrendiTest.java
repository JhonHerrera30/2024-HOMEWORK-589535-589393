package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {
	
	final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	private IOSimulator console;
	private ComandoPrendi prendi;
	private Labirinto labirinto;
	private Partita partita;
	
	
	@Before
	public void setUp() {
		this.labirinto= new LabirintoBuilder()
				.addStanzaIniziale("start")
				.addAttrezzo("attrezzo", 5)
				.addStanzaVincente("end")
				.addAdiacenza("start", "end", "direzione")
				.getLabirinto();
		this.partita= new Partita(labirinto);
		this.prendi= new ComandoPrendi();   
		this.prendi.setIO(new IOConsole());
	}

	@Test
	public void testEsegui_attrezzoPresente() {
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
		prendi.setParametro("attrezzo");
		prendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void testEsegui_attrezzoAssente() {
		prendi.setParametro("attrezzo assente");
		prendi.esegui(partita);
	}
	
	@Test
	public void testEseguiEasy() {
		List<String> cmd = new ArrayList<String>();
		cmd.add("prendi spada");
		cmd.add("vai nord");
		console = new Fixture().testPartitaEasy(cmd);
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),this.MESSAGGIO_BENVENUTO);
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"Hai raccolto spada (3kg)!");
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"biblioteca");
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"Hai vinto!");
	}
	

}
