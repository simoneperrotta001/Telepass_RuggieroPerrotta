package com.example.Controller;
//definizione della tipologia di veicoli di classe2
public class Classe2 implements Classe{
    private double costoKm;//attributo che definisce il costo da pagare al km della classe2

    public Classe2(){
        costoKm = 0.10;
    }
    /*metodo che consente di calcolare il costo del pedaggio (in base a quantikm ci sono da percorrere,
    passato come parametro) per i veicoli di classe2.
    Inoltre il calcolo è fatto in modo tale da simulare l'effettivo funzionamento del telepass, quindi il costo
    viene approssimato ai 10 centesimi più vicini in base al costo. Es: 20.53 euro, sarà 20.50 da pagare.
    Essendo implementata l'interfaccia classe implementata viene fatto l'override dei metodi pagamento e getCostoKm*/
    @Override
    public double pagamento(double quantikm) {
        double costoTotale = costoKm*quantikm;
        costoTotale= Math.round(costoTotale * 10.0) / 10.0;
        return costoTotale;
    }
    //metodo getter che restituisce la tariffa al km inerente alla classe2
    public double getCostoKm(){
        return costoKm;
    }
}
