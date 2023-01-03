package com.example.telepass_ruggieroperrotta;

public class Classe1 implements Classe{
    double costoKm;

    public Classe1(){
        costoKm = 0.09;
    }

    @Override
    public double pagamento(double quantikm) {
        double costoTotale = costoKm*quantikm;
        costoTotale= Math.floor(costoTotale * 100) / 100;
        return costoTotale;
    }

    @Override
    public void definisciTipologia() {
        System.out.println("classe 1");
    }
}
