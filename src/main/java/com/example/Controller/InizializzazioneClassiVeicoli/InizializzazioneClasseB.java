package com.example.Controller.InizializzazioneClassiVeicoli;

import com.example.Controller.ClassiVeicoli.Classe;
import com.example.Controller.ClassiVeicoli.ClasseB;

/*Implementa l'interfaccia InizializzazioneClasse. Ha di fatti l'override del metodo creaClasse.
Questa servirà a istanziare un oggetto Classe di tipo ClasseB */
public class InizializzazioneClasseB implements InizializzazioneClasse {
    @Override
    public Classe creaClasse() {
        return new ClasseB();
    }
}
