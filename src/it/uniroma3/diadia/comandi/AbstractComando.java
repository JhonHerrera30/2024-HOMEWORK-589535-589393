package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	
	protected IO console;
	protected String parametro;

	public abstract void esegui(Partita partita);

	public void setParametro(String parametro) {
		this.parametro=parametro;
	}

	public void setIO(IO io) {
		this.console=io;
	}

}
