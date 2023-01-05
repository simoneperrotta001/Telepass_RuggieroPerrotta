package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.Timestamp;

@WebServlet("/viaggia")
public class ServletViaggia extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String targa=request.getParameter("targa");
        String casello1=request.getParameter("casello1");
        String casello2=request.getParameter("casello2");
        double tariffa= Double.parseDouble(request.getParameter("tariffa"));
        long now = System.currentTimeMillis();
        Timestamp oggi = new Timestamp(now);

        try{
            DatabaseTelepass.getInstance().doInsertEntra(targa,casello1,oggi);
            DatabaseTelepass.getInstance().doInsertEsci(targa,casello2,oggi, tariffa);
            request.setAttribute("messageViaggio", "Grazie per aver viaggiato con noi");
            request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
    }
}