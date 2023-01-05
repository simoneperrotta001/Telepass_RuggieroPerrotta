package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/aggiungiVeicolo")
public class ServletAggiungiVeicolo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        //proviamo a inserire il veicolo prendendoci i valori dalla request (form) e dalla session dell'utente loggato
        try {
            DatabaseTelepass.getInstance().doInsertVeicoli(request.getParameter("targa"),(int) session.getAttribute("codice"),request.getParameter("classe"));
            request.setAttribute("messageVeicolo", "Veicolo Inserito correttamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
        }
    }
}