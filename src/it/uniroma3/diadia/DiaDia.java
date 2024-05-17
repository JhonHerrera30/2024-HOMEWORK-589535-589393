package it.uniroma3.diadia;
 

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

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

	//static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};

	private Partita partita;
	//private Labirinto labirinto;
	private IO console;

	/*public DiaDia(IO io) {
		this.labirinto = new Labirinto();
		this.partita = new Partita(labirinto);
		this.console = io;
	}
	*/
	
	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.console = io;
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
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione,this.console);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())

		this.console.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo()) 

		this.console.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();
		}

	public static void main(String[] argc) {
		IO io= new IOConsole();
		Labirinto labirinto=new LabirintoBuilder()
                .addStanzaIniziale("atrio")
                .addAttrezzo("lanterna", 3)
                .addStanzaVincente("biblioteca")
                .addStanzaBuia("n10", "lanterna")
                .addAttrezzo("chiave", 1)
                .addStanzaBloccata("campus", "chiave", "sud")
                .addStanzaMagica("n11")
                .addAttrezzo("diamante", 2)
                .addAdiacenza("atrio", "biblioteca", "nord")
                .addAdiacenza("atrio", "n10", "est")
                .addAdiacenza("n10", "atrio", "ovest")
                .addAdiacenza("atrio", "campus", "ovest")
                .addAdiacenza("campus", "atrio", "est")
                .addAdiacenza("campus", "n11", "sud")
                .addAdiacenza("n11", "campus", "nord")
                .getLabirinto();
		DiaDia gioco = new DiaDia(labirinto,io);
		gioco.gioca();

	}
	
	
}