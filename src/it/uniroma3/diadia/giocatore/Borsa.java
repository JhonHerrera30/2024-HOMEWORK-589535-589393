package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA=10;
	private List<Attrezzo> attrezzi;
	//private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax=pesoMax;
		this.attrezzi=new ArrayList<Attrezzo>();
		//this.numeroAttrezzi=0;
	} 

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso()+ attrezzo.getPeso()>this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a=null;
		for(Attrezzo attrezzo : this.attrezzi)
			if(attrezzo.getNome().equals(nomeAttrezzo))
				a=attrezzo;
		return a;
	}

	public int getPeso() {
		int peso=0;
		for(Attrezzo attrezzo : this.attrezzi)
			peso+=attrezzo.getPeso();
		return peso;
	}	

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a=null;
		Iterator<Attrezzo> i = this.attrezzi.iterator();
		while(i.hasNext()) {
			a=i.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				i.remove();
				break;
			}
		}
		return a;
	}

	public String toString() {
		StringBuilder s= new StringBuilder();
		if(!this.isEmpty()) {
			s.append("Contenuto borsa("+this.getPeso()+"Kg/"+this.getPesoMax()+"kg): ");
			for(Attrezzo attrezzo : this.attrezzi)
				s.append(attrezzo.toString()+ " ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();

	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public String getAttrezzi(int i) {
		return this.attrezzi.get(i).getNome();
	}


	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List<Attrezzo> ordinata = new ArrayList<>(this.attrezzi);
		final ComparatorePerPeso cmp = new ComparatorePerPeso();
		Collections.sort(ordinata,cmp);
		return ordinata;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final SortedSet<Attrezzo> ordinata = new TreeSet<>(this.attrezzi);
		return ordinata;
	}

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer,Set<Attrezzo>> peso2attrezzi = new HashMap<Integer, Set<Attrezzo>>();
		for(Attrezzo attrezzo : this.attrezzi) {
			if(peso2attrezzi.containsKey(attrezzo.getPeso())) {
				//quest'attrezzo ha un peso già visto in prcedenza
				//pesco il vecchio Set con questo peso e aggiungo il nuovo arrivato
				final Set<Attrezzo> stessoPeso = peso2attrezzi.get(attrezzo.getPeso());
				stessoPeso.add(attrezzo);
			}
			else {
				//quest'attrezzo ha un peso mai visto prima
				//creo un nuovo Set per ospitare tutti gli attrezzi correnti e fututi con questo peso
				final Set<Attrezzo> nuovoSet = new HashSet<Attrezzo>();
				nuovoSet.add(attrezzo);
				peso2attrezzi.put(attrezzo.getPeso(), nuovoSet);
			}
		}
		return peso2attrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		final ComparatorePerPeso cmp = new ComparatorePerPeso();
		final SortedSet<Attrezzo> ordinata = new TreeSet<>(cmp);
		ordinata.addAll(this.attrezzi);
		return ordinata;
	}
}
