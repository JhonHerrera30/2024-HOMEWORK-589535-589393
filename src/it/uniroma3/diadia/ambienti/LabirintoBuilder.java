package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String,Stanza> nome2stanza;
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
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
	
	public LabirintoBuilder addAdiacenza(String stanza, String stanzaAdiacente, String direzione) {
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
	
	public LabirintoBuilder addStanzaBloccata(String stanza, String chiave, String direzione) {
		Stanza stanzaBloccata = new StanzaBloccata(stanza, chiave, direzione);
		this.UltimaStanzaAggiuntaEAggiorna(stanzaBloccata);
		return this;
	}
	
	
}
