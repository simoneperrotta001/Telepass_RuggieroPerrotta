package com.example.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PrivilegiAdmin", value = "/PrivilegiAdmin")
public class ServletPrivilegiAdmin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //prende una sessione già esistente
        HttpSession session = request.getSession(false);
        if (session.getAttribute("username") == null || session.getAttribute("username") == ""){
            request.setAttribute("messageAccedi", "Per accedere a quella pagina devi loggarti");
            request.getRequestDispatcher("Accedi.jsp").forward(request, response);
        }
        else {
            if(session.getAttribute("ruolo").equals(0)){
                request.setAttribute("messageUtente", "Non hai i permessi per accedere a quella pagina");
                request.getRequestDispatcher("protected_area_utente.jsp").forward(request, response);
            }
        }

    }
}

