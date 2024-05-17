package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{

	public String direzioneBloccata;
	public String chiave;

	public StanzaBloccata(String nome, String chiave, String direzione) {
		super(nome);
		this.direzioneBloccata= direzione;
		this.chiave= chiave;
	} 
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(direzioneBloccata)&&!(super.hasAttrezzo(chiave))) 
			return this;
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		return super.getDescrizione() + "\nDirezione bloccata: "+ this.direzioneBloccata +", ti serve: " + this.chiave + " per aprirla.";
	}

		
}
