package com.example.Controller;

public class ClasseB implements Classe{
    private double costoKm;

    public ClasseB(){
        costoKm = 0.08;
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
