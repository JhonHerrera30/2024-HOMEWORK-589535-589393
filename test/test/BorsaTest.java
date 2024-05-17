package test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo attrezzo;
	private Attrezzo piombo;
	private Attrezzo piuma;
	private Attrezzo ps;
	private Attrezzo libro;
	private Attrezzo piuma2;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa();
		this.attrezzo = new Attrezzo("attrezzo",5);
		this.piombo = new Attrezzo("piombo", 5);
		this.piuma = new Attrezzo("piuma", 1);
		this.ps = new Attrezzo("ps", 3);
		this.libro = new Attrezzo("libro", 3);
		this.piuma2 = new Attrezzo("piuma", 2);
		
	}

	@Test
	public void testAddAttrezzo() {
		assertFalse(this.borsa.hasAttrezzo("attrezzo"));
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo("attrezzo"));
	}

	@Test
	public void testGetPesoMax() {
		assertEquals(10, this.borsa.getPesoMax());
	}

	@Test
	public void testGetAttrezzo_Assente() {
		assertNull(this.borsa.getAttrezzo("assente"));
	}
	
	@Test
	public void testGetAttrezzo_Presente() {
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(attrezzo, this.borsa.getAttrezzo("attrezzo"));
	}

	@Test
	public void testGetPeso_BorsaVuota() {
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPeso_BorsaNonVuota() {
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(5, this.borsa.getPeso());
	}

	@Test
	public void testIsEmpty_BorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsEmpty_BorsaNonVuota() {
		this.borsa.addAttrezzo(attrezzo);
		assertFalse(this.borsa.isEmpty());
	}

	@Test
	public void testRemoveAttrezzo_conAttrezzo() {
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo("attrezzo"));
		assertEquals(attrezzo, this.borsa.removeAttrezzo("attrezzo"));
		assertFalse(this.borsa.hasAttrezzo("attrezzo"));
		
	}
	
	@Test
	public void testGetNumeroAttrezzi_BorsaVuota() {
		assertEquals(0,this.borsa.getNumeroAttrezzi());
	}
	
	@Test
	public void testGetNumeroAttrezzi_BorsaNonVuota() {
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(1,this.borsa.getNumeroAttrezzi());
	}
	
	@Test
	public void testgetContenutoOrdinatoPerPeso_BorsaVuota() {
		assertTrue(this.borsa.getContenutoOrdinatoPerPeso().isEmpty());
	}
	
	@Test
	public void testgetContenutoOrdinatoPerPeso_1Attrezzo() {
		assertTrue(this.borsa.addAttrezzo(piombo));
		List<Attrezzo> ListaOrdinata=this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(ListaOrdinata.get(0), piombo);
	}
	
	@Test
	public void testgetContenutoOrdinatoPerPeso_2Attrezzi_pesoDiverso() {
		assertTrue(this.borsa.addAttrezzo(piombo));
		assertTrue(this.borsa.addAttrezzo(libro));
		List<Attrezzo> ListaOrdinata=this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(ListaOrdinata.get(0), libro);
		assertEquals(ListaOrdinata.get(1), piombo);
	}
	
	@Test
	public void testgetContenutoOrdinatoPerPeso_2Attrezzi_StessoPeso() {
		assertTrue(this.borsa.addAttrezzo(ps));
		assertTrue(this.borsa.addAttrezzo(libro));
		List<Attrezzo> ListaOrdinata=this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(ListaOrdinata.get(0), libro);
		assertEquals(ListaOrdinata.get(1), ps);
	}
	
	@Test
	public void testgetContenutoOrdinatoPerNome_BorsaVuota() {
		assertTrue(this.borsa.getContenutoOrdinatoPerNome().isEmpty());
	}
	
	@Test
	public void testgetContenutoOrdinatoPerNome_1Attrezzo() {
		assertTrue(this.borsa.addAttrezzo(libro));
		Set<Attrezzo> InsiemeOrdinato=this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> i = InsiemeOrdinato.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(),libro);
	}
	
	@Test
	public void testgetContenutoOrdinatoPerNome_2Attrezzi() {
		assertTrue(this.borsa.addAttrezzo(piuma));
		assertTrue(this.borsa.addAttrezzo(libro));
		Set<Attrezzo> InsiemeOrdinato=this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> i = InsiemeOrdinato.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(),libro);
		assertTrue(i.hasNext());
		assertEquals(i.next(),piuma);
	}
	
	@Test
	public void testgetContenutoOrdinatoPerNome_2Attrezzi_stessoNome_PesoUguale() {
		assertTrue(this.borsa.addAttrezzo(piuma));
		assertTrue(this.borsa.addAttrezzo(piuma));
		Set<Attrezzo> InsiemeOrdinato=this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> i = InsiemeOrdinato.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(),piuma);
		assertFalse(i.hasNext());
	}
	
	@Test
	public void testgetContenutoOrdinatoPerNome_2Attrezzi_stessoNome_PesoDiverso() {
		assertTrue(this.borsa.addAttrezzo(piuma));
		assertTrue(this.borsa.addAttrezzo(piuma2));
		Set<Attrezzo> InsiemeOrdinato=this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> i = InsiemeOrdinato.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(),piuma);
		assertTrue(i.hasNext());
		assertEquals(i.next(),piuma2);
	}
		
	@Test
	public void testContenutoRaggruppatoPerPeso_vuoto(){
		assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().isEmpty());
	}
	
	@Test
	public void testContenutoRaggruppatoPerPeso_1Attrezzo() {
		assertTrue(this.borsa.addAttrezzo(piuma));
		Map<Integer,Set<Attrezzo>> MappaOrdinata= this.borsa.getContenutoRaggruppatoPerPeso();
		Set<Attrezzo> peso1=MappaOrdinata.get(piuma.getPeso());
		Iterator<Attrezzo> i = peso1.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(),piuma);
		assertFalse(i.hasNext());
	}
	
	@Test
	public void testContenutoRaggruppatoPerPeso_2Attrezzi_PesoDiverso() {
		assertTrue(this.borsa.addAttrezzo(piuma));
		assertTrue(this.borsa.addAttrezzo(libro));
		Map<Integer,Set<Attrezzo>> MappaOrdinata= this.borsa.getContenutoRaggruppatoPerPeso();
		Set<Attrezzo> peso1=MappaOrdinata.get(piuma.getPeso());
		Iterator<Attrezzo> i1 = peso1.iterator();
		assertTrue(i1.hasNext());
		assertEquals(i1.next(),piuma);
		assertFalse(i1.hasNext());
		Set<Attrezzo> peso3=MappaOrdinata.get(libro.getPeso());
		Iterator<Attrezzo> i3 = peso3.iterator();
		assertTrue(i3.hasNext());
		assertEquals(i3.next(),libro);
		assertFalse(i3.hasNext());
	}
	
	@Test
	public void testContenutoRaggruppatoPerPeso_2Attrezzi_PesoUguale() {
		assertTrue(this.borsa.addAttrezzo(ps));
		assertTrue(this.borsa.addAttrezzo(libro));
		Map<Integer,Set<Attrezzo>> MappaOrdinata= this.borsa.getContenutoRaggruppatoPerPeso();
		Set<Attrezzo> peso3=MappaOrdinata.get(ps.getPeso());
		Iterator<Attrezzo> i3 = peso3.iterator();
		assertTrue(i3.hasNext());
		assertEquals(i3.next(),ps);
		assertTrue(i3.hasNext());
		assertEquals(i3.next(), libro);
		assertFalse(i3.hasNext());
	}
	
	@Test
	public void testContenutoRaggruppatoPerPeso_3Attrezzi() {
		assertTrue(this.borsa.addAttrezzo(piuma));
		assertTrue(this.borsa.addAttrezzo(ps));
		assertTrue(this.borsa.addAttrezzo(libro));
		Map<Integer,Set<Attrezzo>> MappaOrdinata= this.borsa.getContenutoRaggruppatoPerPeso();
		Set<Attrezzo> peso1=MappaOrdinata.get(piuma.getPeso());
		Iterator<Attrezzo> i1 = peso1.iterator();
		assertTrue(i1.hasNext());
		assertEquals(i1.next(),piuma);
		assertFalse(i1.hasNext());
		Set<Attrezzo> peso3=MappaOrdinata.get(ps.getPeso());
		Iterator<Attrezzo> i3 = peso3.iterator();
		assertTrue(i3.hasNext());
		assertEquals(i3.next(),ps);
		assertTrue(i3.hasNext());
		assertEquals(i3.next(), libro);
		assertFalse(i3.hasNext());
	}
	
	@Test
	public void getSortedSetOrdinatoPerPeso_Vuoto() {
		assertTrue(this.borsa.getSortedSetOrdinatoPerPeso().isEmpty());
	}
	
	@Test
	public void getSortedSetOrdinatoPerPeso_1Attrezzo() {
		assertTrue(this.borsa.addAttrezzo(libro));
		Set<Attrezzo> InsiemeOrdinato=this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = InsiemeOrdinato.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(),libro);
		assertFalse(i.hasNext());
	}
	
	@Test
	public void getSortedSetOrdinatoPerPeso_2Attrezzo_PesoDiverso() {
		assertTrue(this.borsa.addAttrezzo(libro));
		assertTrue(this.borsa.addAttrezzo(piuma));
		Set<Attrezzo> InsiemeOrdinato=this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = InsiemeOrdinato.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(),piuma);
		assertTrue(i.hasNext());
		assertEquals(i.next(),libro);
		assertFalse(i.hasNext());
	}
	
	@Test
	public void getSortedSetOrdinatoPerPeso_2Attrezzo_PesoUguale_NomeDiverso() {
		assertTrue(this.borsa.addAttrezzo(libro));
		assertTrue(this.borsa.addAttrezzo(ps));
		Set<Attrezzo> InsiemeOrdinato=this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = InsiemeOrdinato.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(),libro);
		assertTrue(i.hasNext());
		assertEquals(i.next(),ps);
		assertFalse(i.hasNext());
	}
	
	@Test
	public void getSortedSetOrdinatoPerPeso_2Attrezzo_PesoUgale_NomeUguale() {
		assertTrue(this.borsa.addAttrezzo(piuma));
		assertTrue(this.borsa.addAttrezzo(piuma));
		Set<Attrezzo> InsiemeOrdinato=this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = InsiemeOrdinato.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(),piuma);
		assertFalse(i.hasNext());
	}
}
