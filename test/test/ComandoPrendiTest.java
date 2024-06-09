package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
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
	public void setUp() throws Exception {
		this.labirinto= Labirinto.newBuilder("labirinto1.txt").getLabirinto();
		this.partita= new Partita(labirinto);
		this.prendi= new ComandoPrendi();   
		this.prendi.setIO(new IOConsole(new Scanner(System.in)));
	}

	@Test
	public void testEsegui_attrezzoPresente() {
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		prendi.setParametro("spada");
		prendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void testEsegui_attrezzoAssente() {
		prendi.setParametro("attrezzo assente");
		prendi.esegui(partita);
	}
	
	@Test
	public void testEseguiEasy() throws Exception {
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
