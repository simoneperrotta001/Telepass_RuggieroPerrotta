package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PrivilegiUtentePlus", value = "/PrivilegiUtentePlus")
public class ServletPrivilegiUtentePlus extends HttpServlet {
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
                List risultato = DatabaseTelepass.getInstance().getSingoloValore("SELECT Plus FROM CLIENTE WHERE CodiceTransponder='"+session.getAttribute("codice")+"'", "Plus");
                int plus = Integer.parseInt((String) risultato.get(0));
                if (plus==1) {
                    request.setAttribute("messagePlus", "Hai già l'abbonamento plus");
                    request.getRequestDispatcher("protected_area_utente.jsp").forward(request, response);
                }
            }
            catch (Exception e) {
                System.out.println("errore nella connessione");
            }
        }

    }
}