package com.example.Controller.InizializzazioneClassiVeicoli;

import com.example.Controller.ClassiVeicoli.Classe;

/**Quest'interfaccia verrà utilizzata come factory per l'istanziazione delle varie classi.
difatti ha un solo metodo creaClasse che restituisce un oggetto di tipo interfaccia Classe (quindi che istanzia
e ritorna qualsiasi delle 5 classi esistenti) */
public interface InizializzazioneClasse {
    Classe creaClasse();
}
