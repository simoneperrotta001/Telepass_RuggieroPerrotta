package com.example.Controller;

public class ClasseA implements Classe{
    private double costoKm;

    public ClasseA(){costoKm = 0.07;}

    @Override
    public double pagamento(double quantikm) {
        double costoTotale = costoKm*quantikm;
        costoTotale= Math.floor(costoTotale * 100) / 100;
        return costoTotale;
    }

    @Override
    public double getCostoKm(){
        return costoKm;
    }
}
