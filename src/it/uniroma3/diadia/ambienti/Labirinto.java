package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

//import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	 
	/*public Labirinto() {
		creaStanze(); 
	}*/
	
	protected Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
        CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
        c.carica();
        this.stanzaIniziale = c.getStanzaIniziale();
        this.stanzaVincente = c.getStanzaVincente();
    }
	 
	/*public void creaStanze() {

		/* crea gli attrezzi *//* 
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo chiave = new Attrezzo("chiave", 0);
    	
		/* crea stanze del labirinto *//*
		Stanza atrio = new StanzaBloccata("Atrio", "chiave", "sud");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new StanzaBuia("Aula N10","lanterna");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze *//*
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze *//*
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		aulaN11.addAttrezzo(chiave);

		// il gioco comincia nell'atrio
        this.stanzaIniziale = atrio;  
		this.stanzaVincente = biblioteca;
    }*/

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente=stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
        return new LabirintoBuilder(labirinto);
    } 
	
	public  static class LabirintoBuilder {
		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String,Stanza> nome2stanza;
		
		public LabirintoBuilder(String lab) throws FileNotFoundException, FormatoFileNonValidoException  {
			this.labirinto = new Labirinto(lab);
			this.nome2stanza = new HashMap<>();
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
		public Map<String, Stanza> getNome2Stanza(){
			return this.nome2stanza;
		}
		
		public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
			this.ultimaStanzaAggiunta = stanza;
			this.nome2stanza.put(stanza.getNome(), stanza);
		}
		
		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			Stanza stanzaI = new Stanza(stanzaIniziale);
			this.labirinto.setStanzaIniziale(stanzaI);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaI);
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza stanzaV = new Stanza(stanzaVincente);
			this.labirinto.setStanzaVincente(stanzaV);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaV);
			return this;
		}
		
		public LabirintoBuilder addStanza(String stanzaNuova) {
			Stanza stanza = new Stanza(stanzaNuova);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
			if(this.ultimaStanzaAggiunta==null)
				return this;
			else {
				Attrezzo a = new Attrezzo(attrezzo, peso);
				this.ultimaStanzaAggiunta.addAttrezzo(a);
				return this;
			}
		}
		
		public LabirintoBuilder addAdiacenza(String stanza, String stanzaAdiacente, Direzione direzione) {
			if(this.nome2stanza.containsKey(stanza)&&this.nome2stanza.containsKey(stanzaAdiacente)) {
				Stanza c = this.nome2stanza.get(stanza);
				Stanza a = this.nome2stanza.get(stanzaAdiacente);
				c.impostaStanzaAdiacente(direzione, a);
			}
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String stanza) {
			Stanza stanzaMagica = new StanzaMagica(stanza);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaMagica);
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String stanza, String luce) {
			Stanza stanzaBuia = new StanzaBuia(stanza, luce);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaBuia);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String stanza, String chiave, Direzione direzione) {
			Stanza stanzaBloccata = new StanzaBloccata(stanza, chiave, direzione);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaBloccata);
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione, String nomeAttrezzo, int pesoA) {
			this.ultimaStanzaAggiunta.setPersonaggio(new Mago(nome , presentazione, new Attrezzo(nomeAttrezzo, pesoA)));
			return this;
		}
		
		public LabirintoBuilder addCane(String nome, String presentazione, String ciboPreferito, String nomePremio, int peso) {
			this.ultimaStanzaAggiunta.setPersonaggio(new Cane(nome , presentazione));
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String presentazione, String regalo) {
			this.ultimaStanzaAggiunta.setPersonaggio(new Strega(nome , presentazione));
			return this;
		}
		
		
	}


}
