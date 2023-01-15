package com.example.Controller.PrivilegioAccessoUtenti;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
/*Questa servlet viene invocata ogni qual volta si ha una pagina a cui possono accedere
solo gli utenti (all'inizio di ogni pagina jsp ci sarà un invocazione a questa servlet o a quella dell'accesso admin).
Questa controlla prima se si è loggati, se lo si è allora controlla se si è un admin,
e se sì reindirizza l'admin alla sua area protetta negando l'acesso alla pagina.
Dopodichè controlla se l'utente ha già il plus e se sì rimanda l'utente alla sua area protetta*/
@WebServlet(name = "PrivilegiUtente", value = "/PrivilegiUtente")
public class ServletPrivilegiUtente extends HttpServlet {
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
        //se si è già loggati
        else {
            //e si è un admin e non un utente
            if(session.getAttribute("ruolo").equals(1)){
                //mandiamo allora un messaggio di errore
                request.setAttribute("messageAdmin", "L'admin non può accedere alle pagine degli utenti");
                //rimandiamo l'admin alla sua area protetta
                request.getRequestDispatcher("protected_area_admin.jsp").forward(request, response);
            }
        }
    }
}
