package com.example.Controller.ServletAdmin;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ModelTelepass.DatabaseTelepass;
import java.util.List;
/**Questa servlet viene invocata per modificare i parametri di un utente selezionato.
Ricordiamo che a questa funzionalità può accedervi unicamente l'admin dalla lista utenti.
Selezionato uno specifico utente dalla lista utenti compariranno le operazioni possibili
da svolgere su quell'utente (Es: attivare plus, rimuovere abbonamento, ecc.)
Una volta scelta la funzione da svolgere verrà settata con tutti i parametri la request che
verrà inviata alla pagina jsp che farà riferimento sempre alla servlet di
GestisciAbbonamento per non ripetere le stesse operazioni in un'altra servlet.*/
@WebServlet("/modificaUtenteSelezionato")
public class ServletModificaUtenteSelezionato extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("username");//recuperiamo l'username dalla request

        try {
            //proviamo a prenderci i valori del plus e dell'abbonamento (se attivi o meno) dell'utente selezionato
            List parametri = DatabaseTelepass.getInstance().getDoppioValore("SELECT TransponderAttivo, Plus FROM CLIENTE WHERE Username='"+username+"'","TransponderAttivo","Plus");
            //se è stato trovato qualcosa
            if (parametri.get(0) != null) {
                int Attivo = Integer.parseInt((String) parametri.get(0));//ci prendiamo se l'abbonamento è attivo o meno
                int Plus = Integer.parseInt((String) parametri.get(1));//ci rpendiamo se il plus è attivo o meno
                request.setAttribute("username", username);//settiamo lo username nella request
                request.setAttribute("attivo", Attivo);//settiamo se l'abbonamento è attivo o meno nella request
                request.setAttribute("plus", Plus);//settiamo se il plus è attivo o meno nella request
                //mandiamo tutti i dati contenuti nella request a modifica utente singolo
                request.getRequestDispatcher("modificaUtenteSingolo.jsp").forward(request, response);
            }
        }
        //se ci sono stati errori nelle query
        catch (Exception e) {
            //mandiamo in output un messaggio di errore
            System.out.println("errore nella connessione");
        }
    }
}
