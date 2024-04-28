package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {
	private IO console;
	private ComandoPosa posa;
	private Labirinto labirinto;
	private Attrezzo attrezzo;
	private Partita partita;
	
	
	@Before
	public void setUp() {
		this.labirinto= new Labirinto();
		this.partita= new Partita(labirinto);
		this.console= new IOConsole();
		this.attrezzo= new Attrezzo("attrezzo",5);
		this.posa= new ComandoPosa();
		this.posa.setIO(console);
	}

	@Test
	public void testEsegui_attrezzoPresente() {
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
		posa.setParametro(attrezzo.getNome());
		posa.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	public void testEsegui_attrezzoAssente() {
		posa.setParametro(attrezzo.getNome());
		posa.esegui(partita);
	}
	
	

}
