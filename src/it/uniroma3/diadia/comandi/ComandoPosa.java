package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if(parametro==null) {
			this.console.mostraMessaggio("Quale attrezzo tra i segueti vuoi posare ?");
			for(int i=0; i<partita.getGiocatore().getBorsa().getNumeroAttrezzi(); i++) {
				this.console.mostraMessaggio(partita.getGiocatore().getBorsa().getAttrezzi(i));
				}
			parametro=this.console.leggiRiga();
		}
		if(partita.getGiocatore().getBorsa().hasAttrezzo(parametro)) {
			Attrezzo a= partita.getGiocatore().getBorsa().removeAttrezzo(parametro);
			if(partita.getStanzaCorrente().addAttrezzo(a))
				console.mostraMessaggio("Hai posato "+a+" nella stanza "+partita.getStanzaCorrente().getNome());
			else
				console.mostraMessaggio("Non c'è abbastanza spazio per posare l'attrezzo in questa stanza");
		}
		else {
			console.mostraMessaggio("Non possiedi "+parametro+".");
		}
		
	}
}
