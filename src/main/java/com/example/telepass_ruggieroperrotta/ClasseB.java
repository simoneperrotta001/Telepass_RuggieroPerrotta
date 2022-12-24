package com.example.telepass_ruggieroperrotta;

public class ClasseB implements Classe{
    double costoKm;

    public void ClasseB(){
        costoKm = 0.08;
    }

    @Override
    public void paga() {
        System.out.println("paga: " + costoKm + " * quantikm");
    }

    @Override
    public void definisciTipologia() {
        System.out.println("classe B");
    }
}
