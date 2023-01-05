package com.example.Controller;
//per l'interfaccia tutti i metodi sono astratti e vanno implementati, mentre per le classi astratte
//ci potrebbero essere metodi non astratti di cui non va fatto l'override.
public abstract class InizializzazioneClasse {
    /**
     * Subclasses will override this method in order to create specific button
     * objects.
     */
    public abstract Classe creaClasse();
}
