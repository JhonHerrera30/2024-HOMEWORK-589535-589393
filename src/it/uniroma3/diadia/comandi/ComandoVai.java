package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private String direzione;
	private IO console; 

	@Override 
    public void esegui(Partita partita) {
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Stanza prossimaStanza = null;
        if(this.direzione==null) {
        	this.console.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
            return;   
        }
        if( stanzaCorrente == stanzaCorrente.getStanzaAdiacente(this.direzione)) {
        	this.console.mostraMessaggio("Qeusta direzione è blocacta, ti serve una chiave speciale");
        }
        prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
        if(prossimaStanza==null) {
            this.console.mostraMessaggio("Direzione inesistente");
            return;
        }
        partita.setStanzaCorrente(prossimaStanza);
        this.console.mostraMessaggio(partita.getStanzaCorrente().getNome());
        partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
    }
	
    @Override
    public void setParametro(String parametro) {
        this.direzione = parametro;
    }
    
    @Override
	public void setIO(IO io) {
		this.console=io;
		
	}
}