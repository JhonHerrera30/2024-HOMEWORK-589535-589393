package it.uniroma3.diadia.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_PERMALOSO = "Questa è la punizione per avermi ignorata!";
	private static final String MESSAGGIO_GRAZIE = "Sei stato carino quindi non ti trasformerò in una rana per oggi";
	private static final String MESSAGGIO_MALVAGIO = "Sei proprio ingenuo AHAHAHAHAH";
	public Strega(String nome, String presentazione) {
		super(nome,presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		List<Stanza> stanzeAdiacentiOrdinate = partita.getStanzaCorrente().getStanzaAdiacenteOrdinatoPerNumeroDiAttrezzi();
	
		if (!this.haSalutato()) {
			partita.setStanzaCorrente(stanzeAdiacentiOrdinate.get(stanzeAdiacentiOrdinate.size()-1));
			msg = MESSAGGIO_PERMALOSO;
		}
			
		else {
			partita.setStanzaCorrente(stanzeAdiacentiOrdinate.get(0));
			msg = MESSAGGIO_GRAZIE;
		}
		return msg;
	}

	@Override
	public String reazioneRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_MALVAGIO;
	}
}
