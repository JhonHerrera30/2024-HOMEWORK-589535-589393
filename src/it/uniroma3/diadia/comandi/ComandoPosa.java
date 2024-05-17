package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	private String nomeAttrezzo;
	private IO console;

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null) {
			this.console.mostraMessaggio("Quale attrezzo tra i segueti vuoi posare ?");
			for(int i=0; i<partita.getGiocatore().getBorsa().getNumeroAttrezzi(); i++) {
				this.console.mostraMessaggio(partita.getGiocatore().getBorsa().getAttrezzi(i));
				}
			nomeAttrezzo=this.console.leggiRiga();
		}
		if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a= partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			if(partita.getStanzaCorrente().addAttrezzo(a))
				console.mostraMessaggio("Hai posato "+a+" nella stanza "+partita.getStanzaCorrente().getNome());
			else
				console.mostraMessaggio("Non c'è abbastanza spazio per posare l'attrezzo in questa stanza");
		}
		else {
			console.mostraMessaggio("Non possiedi "+nomeAttrezzo+".");
		}
		
	}
 
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
		
	}
	
	@Override
	public void setIO(IO io) {
		this.console=io;
		
	}

}
