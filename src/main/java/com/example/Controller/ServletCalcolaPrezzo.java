package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
/*Questa servlet viene invocata al fine di calcolare il prezzo della tratta che si vuole percorrere.
Ricordiamo che solo gli utenti possono accedere a questa funzionalità di calcola prezzo (infatti l'admin
non può accedervi poichè non può viaggiare).
Il prezzo oltre che a variare in base a quanti chilometri si devono percorrere varia anche in base alla
tipologia di classe con cui si viaggia.
Alla fine superati i controlli si invierà nella request il costo totale da pagare per la tratta,
insieme ad altri parametri.*/
@WebServlet("/calcolaprezzo")
public class ServletCalcolaPrezzo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String casello1=request.getParameter("caselloEntrata");//recuperiamo il casello da cui si entra
        String casello2=request.getParameter("caselloUscita");//recuperiamo il casello da cui si vuole uscire
        String targa=request.getParameter("targa");//recuperiamo la targa
        double costo_Tot;//variabile che conterrà il costo totale da pagare per la tratta

        //nel caso in cui i due caselli si equivalgano
        if(casello1.equals(casello2)) {
            //mandiamo alla pagina jsp un messaggio di errore settandolo nella request
            request.setAttribute("messagePercorso", "Assicurati di aver inserito il casello di uscita diverso da quello di entrata");
            //rimandiamo l'utente alla pagina simula percorso così da fargli re-inserire i caselli
            request.getRequestDispatcher("/SimulaPercorso.jsp").forward(request, response);
        }
        try{
            //ci prendiamo la classe del veicolo con cui si vuole viaggiare e la distanza tra i due caselli inseriti
            String classe = DatabaseTelepass.getInstance().getSingoloValore("SELECT ModelloVeicolo FROM VEICOLO WHERE TargaVeicolo='"+targa+"'","ModelloVeicolo");
            String distanzaS = DatabaseTelepass.getInstance().getSingoloValore("SELECT Distanza FROM DISTA WHERE (NomeCasello1='"+casello1+"' AND NomeCasello2='"+casello2+"') OR (NomeCasello1='"+casello2+"' AND NomeCasello2='"+casello1+"')","Distanza");
            double distanza = Double.parseDouble(distanzaS);
            InizializzazioneClasse inizializzazioneClasse;
            //se la classe è la A
            if(classe.equals("A")){
                //istanziamo il nostro factory per la classe A
                inizializzazioneClasse = new InizializzazioneClasseA();
                //istanziamo la nostra Classe (generica ma in questo caso il factory creerà una classe A grazie al factory)
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                //calcoliamo il costo da pagare in base alla distanza e alla classe
                costo_Tot= classeVeicolo.pagamento(distanza);
                //settiamo il costo come parametro della request
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            //se la classe è la B
            else if (classe.equals("B")) {
                //istanziamo il nostro factory per la classe B
                inizializzazioneClasse = new InizializzazioneClasseB();
                //istanziamo la nostra Classe (generica ma in questo caso il factory creerà una classe B grazie al factory)
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                //calcoliamo il costo da pagare in base alla distanza e alla classe
                costo_Tot= classeVeicolo.pagamento(distanza);
                //settiamo il costo come parametro della request
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            else if (classe.equals("3")) {
                //istanziamo il nostro factory per la classe 3
                inizializzazioneClasse = new InizializzazioneClasse3();
                //istanziamo la nostra Classe (generica ma in questo caso il factory creerà una classe 3 grazie al factory)
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                //calcoliamo il costo da pagare in base alla distanza e alla classe
                costo_Tot= classeVeicolo.pagamento(distanza);
                //settiamo il costo come parametro della request
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            else if (classe.equals("4")) {
                //istanziamo il nostro factory per la classe 4
                inizializzazioneClasse = new InizializzazioneClasse4();
                //istanziamo la nostra Classe (generica ma in questo caso il factory creerà una classe 4 grazie al factory)
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                //calcoliamo il costo da pagare in base alla distanza e alla classe
                costo_Tot= classeVeicolo.pagamento(distanza);
                //settiamo il costo come parametro della request
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            else {
                //istanziamo il nostro factory per la classe 5
                inizializzazioneClasse = new InizializzazioneClasse5();
                //istanziamo la nostra Classe (generica ma in questo caso il factory creerà una classe 5 grazie al factory)
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                //calcoliamo il costo da pagare in base alla distanza e alla classe
                costo_Tot= classeVeicolo.pagamento(distanza);
                //settiamo il costo come parametro della request
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            //settiamo tutti i restanti parametri della request
            request.setAttribute("casello1", casello1);//settiamo il casello di entrata
            request.setAttribute("casello2", casello2);//settiamo il casello di uscita
            request.setAttribute("targa", targa);//settiamo la targa
            request.setAttribute("distanza", distanza);//settiamo la distanza
            request.setAttribute("costo_tot", costo_Tot);//settiamo il costo totale da pagare
            // mandiamo l'utente alla pagina per il viaggio effettivo
            request.getRequestDispatcher("ViaggioUtente.jsp").forward(request, response);
        }
        //se ci sono stati errori nel recupero della classe o della distanza tra i caselli
        catch (Exception e) {
            //mandiamo in output un messaggio di errore
            System.out.println("errore nella connessione");
        }
    }
}
