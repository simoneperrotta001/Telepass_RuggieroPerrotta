package com.example.telepass_ruggieroperrotta;

public class Classe3 implements Classe{
    private double costoKm;

    public  Classe3(){
        costoKm = 0.11;
    }

    @Override
    public double pagamento(double quantikm) {
        double costoTotale = costoKm*quantikm;
        costoTotale= Math.floor(costoTotale * 100) / 100;
        return costoTotale;
    }

    public double getCostoKm(){
        return costoKm;
    }
}
