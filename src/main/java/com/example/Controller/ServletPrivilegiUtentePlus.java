package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
/*Questa servlet viene invocata per proteggere l'accesso alla pagina relativa
all'attivazione del plus.
Ricordiamo che a questa pagina non vi possono accedere:
-l'admin;
-un utente non loggato;
-un utente che è già abbonato al plus;*/
@WebServlet(name = "PrivilegiUtentePlus", value = "/PrivilegiUtentePlus")
public class ServletPrivilegiUtentePlus extends HttpServlet {
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
                //proviamo a prendere il valore del plus dell'utente che cerca di accedere alla pagina
                String risultato = DatabaseTelepass.getInstance().getSingoloValore("SELECT Plus FROM CLIENTE WHERE CodiceTransponder='"+session.getAttribute("codice")+"'", "Plus");
                int plus = Integer.parseInt(risultato);
                //se ha già il plus
                if (plus==1) {
                    //mandiamo allora un messaggio di errore
                    request.setAttribute("messagePlus", "Hai già l'abbonamento plus");
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