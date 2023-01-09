package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
/*Questa servlet viene invocata quando si compila il form per aggiungere un veicolo.
Ricordiamo che i veicoli possono essere aggiunti solo dagli utenti (l'admin non può aggiungere
veicoli poichè non ne ha di veicoli associati).
Quello che fa è controllare se la targa inserita esiste già nel db e se la query non riscontra
alcun risultato allora effettua la query di inserimento per inserire il veicolo inviando un
messaggio di successo alla jsp chiamante, altrimenti se dalla query precedente dovesse risultare
che la targa è già presente, si invia un messaggio di errore alla jsp chiamante*/
@WebServlet("/aggiungiVeicolo")
public class ServletAggiungiVeicolo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);//non creiamo una nuova sessione ma utilizziamo quella già presente
        String parametri;

        try {
            //controlliamo se la targa inserita risulta già nel db
            parametri = DatabaseTelepass.getInstance().getSingoloValore("SELECT TargaVeicolo FROM Veicolo WHERE TargaVeicolo = '"+request.getParameter("targa")+"'","TargaVeicolo");
            //se non c'è stato riscontro (la targa quindi non esiste)
            if(parametri == null){
                //proviamo a inserire il veicolo prendendoci i valori dalla request (form) e dalla session dell'utente loggato
                DatabaseTelepass.getInstance().doInsertVeicoli(request.getParameter("targa"),(int) session.getAttribute("codice"),request.getParameter("classe"));
                //mandiamo alla pagina jsp un messaggio di successo settandolo nella request
                request.setAttribute("messageVeicolo", "Veicolo Inserito correttamente");
                //rimandiamo l'utente alla sua area protetta
                request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
            }
            //se c'è stato riscontro (targa esiste)
            else{
                //mandiamo alla pagina jsp un messaggio di errore settandolo nella request
                request.setAttribute("messageTargaErrata", "Targa già registrata. Reinseriscila");
                //rimandiamo l'utente alla pagina di inserimento del veicolo per farglielo re-inserire
                request.getRequestDispatcher("/AggiungiVeicolo.jsp").forward(request, response);
            }
        }
        //se ci sono stati errori durante le query
        catch (Exception e) {
            //mandiamo in output un messaggio di errore
            System.out.println("errore nell'inserimento del veicolo");;
        }
    }
}