package com.example.Controller.PrivilegioAccessoAdmin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
/*Questa servlet viene invocata ogni qual volta si ha una pagina a cui possono accedere
solo gli admin (all'inizio di ogni pagina jsp ci sarà un invocazione a questa servlet o a quella dell'accesso utenti.
Questa controlla prima se si è loggati, se lo si è allora controlla se si è un utente,
e se sì reindirizza l'utente alla sua area protetta negando l'acesso alla pagina*/
@WebServlet(name = "PrivilegiAdmin", value = "/PrivilegiAdmin")
public class ServletPrivilegiAdmin extends HttpServlet {
    /*Questa servlet ha come metodo di passaggio dati il GET, a differenza delle altre servlet che hanno come metodo il
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
        //se invece si è già loggati
        else {
            //e si è però un utente e non un admin
            if(session.getAttribute("ruolo").equals(0)){
                //mandiamo allora un messaggio di errore
                request.setAttribute("messageUtente", "Non hai i permessi per accedere a quella pagina");
                //rimandiamo l'utente alla sua area protetta
                request.getRequestDispatcher("protected_area_utente.jsp").forward(request, response);
            }
        }
    }
}