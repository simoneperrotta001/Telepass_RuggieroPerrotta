package com.example.Controller.ClassiVeicoli;
//interfaccia che ci servirà per implementare il factory per le classi specifiche di veicoli
public interface Classe {
    double getCostoKm();
    double pagamento(double quantikm);
}
