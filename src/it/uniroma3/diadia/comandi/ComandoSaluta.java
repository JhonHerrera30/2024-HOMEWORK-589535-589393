package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		this.console.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
	}
	
	@Override
	public void setParametro(String parametro) {
		return;
	}

}
