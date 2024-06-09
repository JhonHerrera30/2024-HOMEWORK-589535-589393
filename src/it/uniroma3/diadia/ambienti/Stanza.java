package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	//static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	//static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
    private Set<Attrezzo> attrezzi;
    //private int numeroAttrezzi;
    private Map<Direzione,Stanza> stanzeAdiacenti;
    private AbstractPersonaggio npc;
    //private int numeroStanzeAdiacenti;
	//private String[] direzioni;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        //this.numeroStanzeAdiacenti = 0;
        //this.numeroAttrezzi = 0;
        //this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashSet<>();
        this.npc=null;
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
    	this.stanzeAdiacenti.put(direzione,stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Set<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }
    
    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
       return this.attrezzi.add(attrezzo);
    }
    
    public AbstractPersonaggio getPersonaggio() {
    	return this.npc;
    }
    
    public void setPersonaggio(AbstractPersonaggio personaggio) {
    	this.npc = personaggio;
    }
    
    public boolean hasPersonaggio() {
    	if(this.npc==null)
    		return false;
    	else
    		return true;
    }


   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite:\n");
    	Set<Direzione> direzioni = this.stanzeAdiacenti.keySet();
    	for(Direzione direzione : direzioni) {
    		risultato.append(direzione + "->"+ this.stanzeAdiacenti.get(direzione).getNome()+ "\n");
    	}
    	risultato.append("Attrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi) {
    		risultato.append(attrezzo.toString()+" ");
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				trovato = true;
		}
		return trovato;
	} 

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo: this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				attrezzoCercato = attrezzo;
		}
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo);
	}


	public Set<Direzione> getDirezioni() {
		Set<Direzione> direzioni = this.stanzeAdiacenti.keySet();
	    return direzioni;
    }
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza)o;
		return this.getNome().equals(that.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode()+this.getNumeroAttrezzi()+this.attrezzi.hashCode()+this.stanzeAdiacenti.hashCode();
	}
	
	public List<Stanza> getStanzaAdiacenteOrdinatoPerNumeroDiAttrezzi() {
		ComparatorePerNumeroDiAttrezzi cmp = new ComparatorePerNumeroDiAttrezzi();
		List<Stanza> stanzeOrdinate = new ArrayList<>();
		stanzeOrdinate.addAll(this.stanzeAdiacenti.values());
		Collections.sort(stanzeOrdinate,cmp);
		return stanzeOrdinate;
	}
	
	

}