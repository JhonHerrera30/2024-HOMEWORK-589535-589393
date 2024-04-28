package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
	private Partita partita;
	private Stanza stanza;
	private Labirinto labirinto;
	private ComandoVai vai;
	private String direzione;
	private IO console;
	
	@Before
	public void setUp() {
		this.labirinto= new Labirinto();
		this.partita= new Partita(labirinto);
		this.stanza= partita.getStanzaCorrente();
		this.console= new IOConsole();
		this.vai= new ComandoVai();
		this.vai.setIO(console);	 
	}
	
	@Test
	public void testEsegui_stanzaAdiacente_Presente() {
		Stanza stanzaAdiacente= new Stanza("Stanza Adiacente");
		direzione= new String("nord");
		stanza.impostaStanzaAdiacente(direzione , stanzaAdiacente);
		assertEquals(stanzaAdiacente, stanza.getStanzaAdiacente(direzione));
		vai.setParametro(direzione);
		vai.esegui(partita);
		assertEquals(stanzaAdiacente, partita.getStanzaCorrente());
	}
	
	@Test
	public void testEsegui_direzioneInesistente() {
		direzione= new String("inesistente");
		vai.setParametro(direzione);
		vai.esegui(partita);
	}
	
	@Test
	public void testEsegui_direzioneNulla() {
		direzione=null;
		vai.setParametro(direzione);
		vai.esegui(partita);
	}

}
