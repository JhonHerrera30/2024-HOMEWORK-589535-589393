package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {
    private Labirinto.LabirintoBuilder labirinto;


    @Before
    public void setUp() throws Exception {
        //labirinto = Labirinto.newBuilder("labirinto.txt");
        labirinto = new Labirinto.LabirintoBuilder("labirinto.txt");
    } 

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testGetLabirinto() {
        assertNotNull(labirinto.getLabirinto());
        assertEquals(Labirinto.class, labirinto.getLabirinto().getClass());
    }

    @Test
    public void testAddStanza() {
        labirinto.addStanza("stanzetta");
        Stanza expected = new Stanza("stanzetta");
        assertEquals(expected, labirinto.getNome2Stanza().get("stanzetta"));
    }

    @Test
    public void testAddAttrezzoSenzaUltimaStanzaAggiunta(){

        //labirinto.addAttrezzo("cacciavite", 3);
        //Attrezzo expected = new Attrezzo("cacciavite", 3);
        assertEquals(Labirinto.LabirintoBuilder.class, labirinto.addAttrezzo("cacciavite", 3).getClass());
    }

    @Test
    public void testAddAttrezzoConUltimaStanzaAggiunta(){
        labirinto.addStanzaIniziale("stanzetta").addAttrezzo("cacciavite", 3);
        Attrezzo expected = new Attrezzo("cacciavite", 3);
        assertEquals(expected, labirinto.getLabirinto().getStanzaIniziale().getAttrezzo("cacciavite"));
    }

    @Test
    public void testAddAttrezzoConStanza() {
        labirinto.addStanza("stanzetta");
        labirinto.addAttrezzo("cacciavite", 3);
        assertTrue(labirinto.getNome2Stanza().get("stanzetta").hasAttrezzo("cacciavite"));
    }
}
