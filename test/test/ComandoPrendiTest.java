package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {
	private IO console;
	private ComandoPrendi prendi;
	private Labirinto labirinto;
	private Attrezzo attrezzo;
	private Partita partita;
	
	
	@Before
	public void setUp() {
		this.labirinto= new Labirinto();
		this.partita= new Partita(labirinto);
		this.console= new IOConsole();
		this.attrezzo= new Attrezzo("attrezzo",5);
		this.prendi= new ComandoPrendi();   
		this.prendi.setIO(console);
	}

	@Test
	public void testEsegui_attrezzoPresente() {
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		prendi.setParametro(attrezzo.getNome());
		prendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	public void testEsegui_attrezzoAssente() {
		prendi.setParametro(attrezzo.getNome());
		prendi.esegui(partita);
	}
	

}
