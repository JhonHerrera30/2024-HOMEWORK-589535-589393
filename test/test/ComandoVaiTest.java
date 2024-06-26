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
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
	
	final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	private Partita partita;
	//private Stanza stanza;
	private Labirinto labirinto;
	private ComandoVai vai;
	private IOSimulator console;
	
	@Before
	public void setUp() throws Exception {
		this.labirinto= Labirinto.newBuilder("labirinto1.txt").getLabirinto();
		this.partita= new Partita(labirinto);
		//this.stanza= partita.getStanzaCorrente();
		this.vai= new ComandoVai();
		this.vai.setIO(new IOConsole(new Scanner(System.in)));	 
	}
	
	@Test
	public void testEsegui_stanzaAdiacente_Presente() {
		assertSame(this.partita.getStanzaCorrente(),this.labirinto.getStanzaIniziale());
		vai.setParametro("nord");
		vai.esegui(partita);
		assertSame(this.partita.getStanzaCorrente(),this.labirinto.getStanzaVincente());	
	}
	
	@Test
	public void testComandoVaiEasy() throws Exception {
		List<String> cmd = new ArrayList<String>();
		cmd.add("vai nord");
		console = new Fixture().testPartitaEasy(cmd);
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),this.MESSAGGIO_BENVENUTO);
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"biblioteca");
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"Hai vinto!");
	}
	
	@Test
	public void testComandoVaiMid() throws Exception {
		List<String> cmd = new ArrayList<String>();
		cmd.add("vai nord");
		cmd.add("vai ovest");
		cmd.add("vai nord");
		console = new Fixture().testPartitaMid(cmd);
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),this.MESSAGGIO_BENVENUTO);
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"n10");
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"n9");
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"biblioteca");
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"Hai vinto!");
	}
	

	
 
	

}
