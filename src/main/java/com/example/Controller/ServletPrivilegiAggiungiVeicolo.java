package com.example.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PrivilegiAggiungiVeicolo", value = "/PrivilegiAggiungiVeicolo")
public class ServletPrivilegiAggiungiVeicolo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //prende una sessione già esistente
        HttpSession session = request.getSession(false);
        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            request.getRequestDispatcher("Accedi.jsp").forward(request, response);
        }
        else if(session.getAttribute("ruolo").equals(1))
            request.getRequestDispatcher("protected_area_admin.jsp").forward(request, response);
        else
            request.getRequestDispatcher("AggiungiVeicolo.jsp").forward(request, response);
    }
}
