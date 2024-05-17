package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String luce;

	public StanzaBuia(String nome, String luce) {
		super(nome);
		this.luce= luce;
	} 
	
	@Override
	public String getDescrizione() {
		if(!super.hasAttrezzo(luce)) {
			return "Qui c'è buio pesto";
		}
		return super.getDescrizione();
	}
	
	

}
