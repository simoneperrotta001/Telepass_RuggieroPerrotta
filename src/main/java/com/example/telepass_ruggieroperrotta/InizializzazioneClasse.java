package com.example.telepass_ruggieroperrotta;
//per l'interfaccia tutti i metodi sono astratti e vanno implementati, mentre per le classi astratte
//ci potrebbero essere metodi non astratti di cui non va fatto l'override.
public abstract class InizializzazioneClasse {
    public void Inzializzazione() {
        // ... other code ...

        Classe nuovaClasse = creaClasse();
        nuovaClasse.paga();
    }

    /**
     * Subclasses will override this method in order to create specific button
     * objects.
     */
    public abstract Classe creaClasse();
}
