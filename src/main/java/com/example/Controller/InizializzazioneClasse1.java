package com.example.Controller;
/*Implementa l'interfaccia InizializzazioneClasse. Ha di fatti l'override del metodo creaClasse.
Questa servirà a istanziare un oggetto Classe di tipo Classe1 */
public class InizializzazioneClasse1 implements InizializzazioneClasse {
    @Override
    public Classe creaClasse() {
        return new Classe1();
    }
}
