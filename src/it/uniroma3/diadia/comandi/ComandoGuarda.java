package it.uniroma3.diadia.comandi;

import java.util.SortedSet;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda extends AbstractComando{
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getDescrizione().equals("Qui c'è buio pesto"))
			this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		else {
			this.console.mostraMessaggio("Stanza attuale: "+partita.getStanzaCorrente().getDescrizione()+"\n"+partita.getGiocatore());
			SortedSet<Attrezzo> AttrezziOrdinati = partita.getGiocatore().getBorsa().getContenutoOrdinatoPerNome();
			this.console.mostraMessaggio("Borsa:");
			for(Attrezzo attrezzo : AttrezziOrdinati) {
				this.console.mostraMessaggio(attrezzo.toString());
			}
			if(partita.getStanzaCorrente().hasPersonaggio())
				this.console.mostraMessaggio("C'è "+ partita.getStanzaCorrente().getPersonaggio().getNome() +" nella stanza");
		}
	} 
 
	@Override
	public void setParametro(String parametro) {
		return;
		
	}

}
