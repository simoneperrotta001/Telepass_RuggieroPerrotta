package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PrivilegiUtenteVeicoli", value = "/PrivilegiUtenteVeicoli")
public class ServletPrivilegiUtenteVeicoli extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //prende una sessione già esistente
        HttpSession session = request.getSession(false);
        if (session.getAttribute("username") == null || session.getAttribute("username") == ""){
            request.setAttribute("messageAccedi", "Per accedere a quella pagina devi loggarti");
            request.getRequestDispatcher("Accedi.jsp").forward(request, response);
        }

        else if(session.getAttribute("ruolo").equals(1)){
                request.setAttribute("messageAdmin", "L'admin non può accedere alle pagine degli utenti");
                request.getRequestDispatcher("protected_area_admin.jsp").forward(request, response);
        }

        else{
            try{
                String risultato = DatabaseTelepass.getInstance().getSingoloValore("SELECT COUNT(*) AS QUANTI FROM VEICOLO WHERE CodiceTransponder='"+session.getAttribute("codice")+"'", "QUANTI");
                int quanti = Integer.parseInt(risultato);
                if (quanti>1) {
                    request.setAttribute("messageVeicoli", "Hai già 2 veicoli registrati");
                    request.getRequestDispatcher("protected_area_utente.jsp").forward(request, response);
                }
            }
            catch (Exception e) {
                System.out.println("errore nella connessione");
            }
        }
    }
}