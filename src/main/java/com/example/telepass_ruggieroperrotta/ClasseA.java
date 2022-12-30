package com.example.telepass_ruggieroperrotta;

public class ClasseA implements Classe{
    double costoKm;

    public ClasseA(){costoKm = 0.07;}

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
        System.out.println("classe A");
    }
}
