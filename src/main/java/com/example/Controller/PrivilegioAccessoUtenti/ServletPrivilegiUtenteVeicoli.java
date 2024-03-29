package com.example.Controller.PrivilegioAccessoUtenti;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
/**Questa servlet viene invocata per proteggere l'accesso alla pagina aggiungi veicoli.
Ricordiamo che a questa pagina non vi possono accedere:
-l'admin;
-un utente non loggato;
-un utente che ha già due veicoli associati;*/
@WebServlet(name = "PrivilegiUtenteVeicoli", value = "/PrivilegiUtenteVeicoli")
public class ServletPrivilegiUtenteVeicoli extends HttpServlet {
    /**Questa servlet ha come metodo di passaggio dati il GET, a differenza delle altre servlet che hanno come metodo il
    POST. Questo perchè questa servlet per settare i dati da mandare a schermo non li invia tramite una form, ma sfrutta
    quelli che sono i parametri di sessione.*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //prende una sessione già esistente
        HttpSession session = request.getSession(false);
        //se lo username nella sessione è vuoto significa che non si è ancora loggati
        if (session.getAttribute("username") == null || session.getAttribute("username") == ""){
            //mandiamo allora un messaggio di errore
            request.setAttribute("messageAccedi", "Per accedere a quella pagina devi loggarti");
            //rimandiamo l'utente alla pagina di accesso
            request.getRequestDispatcher("Accedi.jsp").forward(request, response);
        }
        //se si è già loggati e si è un admin
        else if(session.getAttribute("ruolo").equals(1)){
                //mandiamo allora un messaggio di errore
                request.setAttribute("messageAdmin", "L'admin non può accedere alle pagine degli utenti");
                //rimandiamo l'admin alla sua area protetta
                request.getRequestDispatcher("protected_area_admin.jsp").forward(request, response);
        }
        else{
            try{
                //proviamo a prenderci il valore di quanti veicolo ha già associato l'utente
                String risultato = DatabaseTelepass.getInstance().getSingoloValore("SELECT COUNT(*) AS QUANTI FROM VEICOLO WHERE CodiceTransponder='"+session.getAttribute("codice")+"'", "QUANTI");
                int quanti = Integer.parseInt(risultato);
                //se ha già associato 2 veicoli
                if (quanti==2) {
                    //mandiamo allora un messaggio di errore
                    request.setAttribute("messageVeicoli", "Hai già 2 veicoli registrati");
                    //rimandiamo l'utente alla sua area protetta
                    request.getRequestDispatcher("protected_area_utente.jsp").forward(request, response);
                }
            }
            //se vi sono stati errori nell'esecuzione della query
            catch (Exception e) {
                //mandiamo in output un messaggio di errore
                System.out.println("errore nella connessione");
            }
        }
    }
}