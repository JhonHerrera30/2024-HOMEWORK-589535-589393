package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
    private static final String MESSAGGIO_MORSO="WOOF WOOF! (Non ti avvicinare senza avere uno spuntino!)";
    private static final String MESSAGGIO_NONMIPIACE="Grrr... (Questo spuntino è disgustoso, portamene un altro)";
    private static final String MESSAGGIO_BRAVO="Slurp (Squisito, eccoti un premio)";
    private static final String MESSAGGIO_SCUSA="Slurp (Squisito, ma ho esaurito i premi)";
    private String ciboPreferito="osso";
    private String premio="palla";

    public Cane(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public String agisci(Partita partita) {
        String msg=MESSAGGIO_MORSO;
        partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
        return msg;
    }

    @Override
    public String reazioneRegalo(Attrezzo attrezzo, Partita partita) {
        String msg=null;
        if(!this.ciboPreferito.equals(this.regalo)){
            partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
            msg=MESSAGGIO_NONMIPIACE;
        }
        else {
            if(this.premio!=null) {
                partita.getStanzaCorrente().addAttrezzo(new Attrezzo(premio, 1));
                this.premio=null;
                msg=MESSAGGIO_BRAVO;
            }
            else {
                msg=MESSAGGIO_SCUSA;
            }
        }
        return msg;
    }

}
