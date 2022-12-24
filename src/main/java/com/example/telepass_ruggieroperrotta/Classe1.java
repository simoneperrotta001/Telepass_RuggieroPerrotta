package com.example.telepass_ruggieroperrotta;

public class Classe1 implements Classe{
    double costoKm;

    public void Classe1(){
        costoKm = 0.09;
    }

    @Override
    public void paga() {
        System.out.println("paga: " + costoKm + " * quantikm");
    }

    @Override
    public void definisciTipologia() {
        System.out.println("classe 1");
    }
}
