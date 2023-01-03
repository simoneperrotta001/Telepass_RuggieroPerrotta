package com.example.telepass_ruggieroperrotta;

public class Classe2 implements Classe{
    double costoKm;

    public Classe2(){
        costoKm = 0.10;
    }

    @Override
    public double pagamento(double quantikm) {
        double costoTotale = costoKm*quantikm;
        costoTotale= Math.floor(costoTotale * 100) / 100;
        return costoTotale;
    }

    @Override
    public void definisciTipologia() {
        System.out.println("classe 2");
    }
}
