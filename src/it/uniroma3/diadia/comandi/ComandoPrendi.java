package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if(this.parametro==null) {
			this.console.mostraMessaggio("Quale attrezzo vuoi prendere ?");
			this.parametro=this.console.leggiRiga();
			}
		if(partita.getStanzaCorrente().hasAttrezzo(parametro)) {
			Attrezzo a=new Attrezzo(parametro, partita.getStanzaCorrente().getAttrezzo(parametro).getPeso());
			partita.getStanzaCorrente().removeAttrezzo(a);
			partita.getGiocatore().getBorsa().addAttrezzo(a);
			console.mostraMessaggio("Hai raccolto "+a+"!");
		}
		else
			console.mostraMessaggio("L'attrezzo non è presente in questa stanza");

	}
}
