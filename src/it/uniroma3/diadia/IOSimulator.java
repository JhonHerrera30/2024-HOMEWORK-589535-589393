package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOSimulator implements IO{
	private List<String> comandiLetti;
	private int indiceComandiLetti;
	private List<String> messaggiStampati;
	
	
	public IOSimulator(List<String> cmd) {
		comandiLetti=cmd;
		indiceComandiLetti=0;
		messaggiStampati=new ArrayList<>();
	}
	
	@Override
	public String leggiRiga() {
		String out=this.comandiLetti.get(indiceComandiLetti);
		this.indiceComandiLetti++;
		return out;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiStampati.add(messaggio);	
	}
	
	public boolean messaggiHasNext() {
		return this.messaggiStampati.iterator().hasNext();
	}
	
	public String messaggiNext(){
		Iterator<String> it = messaggiStampati.iterator();
		String out = it.next();
		it.remove();
		return out;
	}
	
}
