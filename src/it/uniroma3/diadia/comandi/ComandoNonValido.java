package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		this.console.mostraMessaggio("il comando inserito non è valido");
		
	}

	@Override
	public void setParametro(String parametro) {
		return;
	}
	
}
