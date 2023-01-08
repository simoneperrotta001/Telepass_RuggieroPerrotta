package com.example.Controller;
//definizione della tipologia di veicoli di classe1
public class Classe1 implements Classe{
    private double costoKm;//attributo che definisce il costo da pagare al km della classe1

    public Classe1(){
        costoKm = 0.09;
    }
    /*metodo che consente di calcolare il costo del pedaggio (in base a quantikm ci sono da percorrere,
    passato come parametro) per i veicoli di classe1.
    Inoltre il calcolo è fatto in modo tale da simulare l'effettivo funzionamento del telepass, quindi il costo
    viene approssimato ai 10 centesimi più vicini in base al costo. Es: 20.53 euro, sarà 20.50 da pagare.
    Essendo implementata l'interfaccia classe implementata viene fatto l'override dei metodi pagamento e getCostoKm*/
    @Override
    public double pagamento(double quantikm) {
        double costoTotale = costoKm*quantikm;
        costoTotale= Math.round(costoTotale * 10.0) / 10.0;
        return costoTotale;
    }
    //metodo getter che restituisce la tariffa al km inerente alla classe1
    public double getCostoKm(){
        return costoKm;
    }
}
