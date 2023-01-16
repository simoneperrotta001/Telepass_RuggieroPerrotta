package com.example.Controller.InizializzazioneClassiVeicoli;

import com.example.Controller.ClassiVeicoli.Classe;
import com.example.Controller.ClassiVeicoli.ClasseA;

/**Implementa l'interfaccia InizializzazioneClasse. Ha di fatti l'override del metodo creaClasse.
Questa servirà a istanziare un oggetto Classe di tipo ClasseA */
public class InizializzazioneClasseA implements InizializzazioneClasse {
    @Override
    public Classe creaClasse() {
        return new ClasseA();
    }
}
