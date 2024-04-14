package it.uniroma3.diadia;
 

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};

	private Partita partita;
	private Labirinto labirinto;
	private IOConsole console;

	public DiaDia() {
		this.labirinto= new Labirinto();
		this.partita = new Partita(labirinto);
		this.console=new IOConsole();
	}

	public void gioca() {
		String istruzione; 

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if(istruzione.isEmpty())
			return false;

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("prendi"))
		{	
			if(this.partita.getStanzaCorrente().getNumeroAttrezzi()==0)
				this.console.mostraMessaggio("Questa stanza non ha attrezzi ");
			else 
				this.prendi(comandoDaEseguire.getParametro());

		}
		else if(comandoDaEseguire.getNome().equals("posa"))
		{	
			if(this.partita.getGiocatore().getBorsa().getNumeroAttrezzi()!=0)
				this.posa(comandoDaEseguire.getParametro());
			else
				this.console.mostraMessaggio("Non hai attrezzi da posare");
		}
		else
			console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");
		//console.mostraMessaggio();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			console.mostraMessaggio("Dove vuoi andare ?");
			direzione=this.console.leggiRiga();
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		console.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
	}

	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			console.mostraMessaggio("Quale attrezzo vuoi prendere ?");
			nomeAttrezzo=this.console.leggiRiga();
			}
		if(this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a=new Attrezzo(nomeAttrezzo, this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo).getPeso());
			this.partita.getStanzaCorrente().removeAttrezzo(a);
			this.partita.getGiocatore().getBorsa().addAttrezzo(a);
			console.mostraMessaggio("Hai raccolto "+a+"!");
		}
		else
			console.mostraMessaggio("L'attrezzo non è presente in questa stanza");
	}

	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			console.mostraMessaggio("Quale attrezzo vuoi posare ?");
			nomeAttrezzo=this.console.leggiRiga();
		}
		if(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a= this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(this.partita.getStanzaCorrente().addAttrezzo(a))
				console.mostraMessaggio("hai posato "+a+" nella stanza "+this.partita.getStanzaCorrente());
			else
				console.mostraMessaggio("Non c'è abbastanza spazio per posare l'attrezzo in questa stanza");
		}
		else
			console.mostraMessaggio("Non possiedi "+nomeAttrezzo+".");
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}