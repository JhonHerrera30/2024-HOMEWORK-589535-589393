package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

public class Giocatore {
	
	static final private int CFU_INIZIALI = Configuratore.getCFU();
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.cfu=CFU_INIZIALI;
		this.borsa= new Borsa();
	}
	
	public int getCfu() { 
		return this.cfu;
	}
	
	public void setCfu(int Cfu) {
		this.cfu=Cfu;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public String toString() {
		StringBuilder risultato= new StringBuilder();
		risultato.append("CFU attuali: "+this.cfu);
		/*risultato.append("\nAttrezzi posseduti:\n");
		for(int i=0; i<this.getBorsa().getNumeroAttrezzi(); i++) {
			risultato.append(this.getBorsa().getAttrezzi(i)+ "\n");
		}*/
		return risultato.toString();
	}
}
