package com.example.telepass_ruggieroperrotta;

public class Classe2 implements Classe{
    double costoKm;

    public void Classe2(){
        costoKm = 0.10;
    }

    @Override
    public void paga() {
        System.out.println("paga: " + costoKm + " * quantikm");
    }

    @Override
    public void definisciTipologia() {
        System.out.println("classe 2");
    }
}
