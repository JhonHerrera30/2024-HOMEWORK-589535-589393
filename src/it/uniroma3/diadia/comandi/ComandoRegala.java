package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if(parametro==null) {
			this.console.mostraMessaggio("Quale attrezzo vuoi reagalare?");
			for(Attrezzo attrezzi : partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso()) 
				this.console.mostraMessaggio(attrezzi.toString());
			this.parametro=this.console.leggiRiga();
		}
		if(partita.getGiocatore().getBorsa().hasAttrezzo(parametro)) 
			this.console.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(partita.getGiocatore().getBorsa().removeAttrezzo(parametro), partita));	
		else
			this.console.mostraMessaggio("non possiedi "+parametro);
	}

}
