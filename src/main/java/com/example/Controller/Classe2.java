package com.example.Controller;

public class Classe2 implements Classe{
    private double costoKm;

    public Classe2(){
        costoKm = 0.10;
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
