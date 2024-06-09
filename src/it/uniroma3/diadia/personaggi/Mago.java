package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Non dubito la tua dote, ma le mie tasche ora son vuote!";
	private static final String MESSAGGIO_REGALO = "Un regalo molto apprezzato, il suo peso è ora dimezzato!";
	private static final String MESSAGGIO_NONMODIFICATO = "Sono alquanto desolato, ma il mio potere è limitato!";
	private Attrezzo attrezzo;
	private boolean hasModificato;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
		this.hasModificato=false;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String reazioneRegalo(Attrezzo attrezzo, Partita partita) {
		String msg=null;
		if(this.hasModificato==false) {
			Attrezzo modificato = new Attrezzo(this.regalo, this.pesoRegalo/2);
			partita.getStanzaCorrente().addAttrezzo(modificato);
			this.regalo=null;
			this.pesoRegalo=0;
			this.hasModificato=true;
			msg = MESSAGGIO_REGALO;
		}
		else {
			msg= MESSAGGIO_NONMODIFICATO;
		}
		return msg;
	}
}
