package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	private String nomeAttrezzo;
	private IO console;

	@Override
	public void esegui(Partita partita) {
		if(this.nomeAttrezzo==null) {
			this.console.mostraMessaggio("Quale attrezzo vuoi prendere ?");
			this.nomeAttrezzo=this.console.leggiRiga();
			}
		if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a=new Attrezzo(nomeAttrezzo, partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo).getPeso());
			partita.getStanzaCorrente().removeAttrezzo(a);
			partita.getGiocatore().getBorsa().addAttrezzo(a);
			console.mostraMessaggio("Hai raccolto "+a+"!");
		}
		else
			console.mostraMessaggio("L'attrezzo non è presente in questa stanza");

		
		
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
