package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/aggiungiVeicolo")
public class ServletAggiungiVeicolo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        List parametri;

        //proviamo a inserire il veicolo prendendoci i valori dalla request (form) e dalla session dell'utente loggato
        try {
            parametri = DatabaseTelepass.getInstance().getSingoloValore("SELECT TargaVeicolo FROM Veicolo WHERE TargaVeicolo = '"+request.getParameter("targa")+"'","TargaVeicolo");
            if(parametri.get(0) == null){
                DatabaseTelepass.getInstance().doInsertVeicoli(request.getParameter("targa"),(int) session.getAttribute("codice"),request.getParameter("classe"));
                request.setAttribute("messageVeicolo", "Veicolo Inserito correttamente");
            }
            else{
                request.setAttribute("messageTargaErrata", "Targa già registrata. Reinseriscila");
                request.getRequestDispatcher("/AggiungiVeicolo.jsp").forward(request, response);
            }
        }
        catch (Exception e) {
            System.out.println("errore nell'inserimento del veicolo");;
        }
        finally {
            request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
        }
    }
}