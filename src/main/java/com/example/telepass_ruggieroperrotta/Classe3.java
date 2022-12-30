package com.example.telepass_ruggieroperrotta;

public class Classe3 implements Classe{
    double costoKm;

    public  Classe3(){
        costoKm = 0.11;
    }

    @Override
    public void paga() {
        System.out.println("paga: " + costoKm + " * quantikm");
    }

    public double pagamento(double quantikm) {
        double costoTotale = costoKm*quantikm;
        costoTotale= Math.floor(costoTotale * 100) / 100;
        return costoTotale;
    }

    @Override
    public void definisciTipologia() {
        System.out.println("classe 3");
    }
}
