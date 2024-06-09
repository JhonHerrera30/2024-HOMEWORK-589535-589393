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
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosaTest {
	
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
	private ComandoPosa posa;
	private Labirinto labirinto;
	private Attrezzo attrezzo;
	private Partita partita;
	private Borsa borsa;
	
	
	@Before
	public void setUp() throws Exception{
		this.labirinto= Labirinto.newBuilder("labirinto1.txt").getLabirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo = new Attrezzo("attrezzo", 5);
		this.posa = new ComandoPosa();   
		this.posa.setIO(new IOConsole(new Scanner(System.in)));
		this.borsa = partita.getGiocatore().getBorsa();
	}
	@Test
	public void testEsegui_attrezzoPresente() {
		assertFalse(borsa.hasAttrezzo("attrezzo"));
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo("attrezzo"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
		posa.setParametro("attrezzo");
		posa.esegui(partita);
		assertFalse(borsa.hasAttrezzo("attrezzo"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
		
	}
	
	@Test
	public void testEsegui_attrezzoAssente() {
		posa.setParametro(attrezzo.getNome());
		posa.esegui(partita);
	}
	
	@Test
	public void testComandoPosaEasy() throws Exception {
		List<String> cmd = new ArrayList<String>();
		cmd.add("prendi spada");
		cmd.add("posa spada");
		cmd.add("vai nord");
		console = new Fixture().testPartitaEasy(cmd);
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),this.MESSAGGIO_BENVENUTO);
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"Hai raccolto spada (3kg)!");
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"Hai posato spada (3kg) nella stanza atrio");
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"biblioteca");
		assertTrue(this.console.messaggiHasNext());
		assertEquals(this.console.messaggiNext(),"Hai vinto!");
	
	}
	
	

}
