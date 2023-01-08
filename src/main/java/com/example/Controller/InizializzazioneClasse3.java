package com.example.Controller;
/*Implementa l'interfaccia InizializzazioneClasse. Ha di fatti l'override del metodo creaClasse.
Questa servirà a istanziare un oggetto Classe di tipo Classe3 */
public class InizializzazioneClasse3 implements InizializzazioneClasse{
    @Override
    public Classe creaClasse() {
        return new Classe3();
    }
}
