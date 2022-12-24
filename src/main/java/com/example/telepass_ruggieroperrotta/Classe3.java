package com.example.telepass_ruggieroperrotta;

public class Classe3 implements Classe{
    double costoKm;

    public void Classe3(){
        costoKm = 0.11;
    }

    @Override
    public void paga() {
        System.out.println("paga: " + costoKm + " * quantikm");
    }

    @Override
    public void definisciTipologia() {
        System.out.println("classe 3");
    }
}
