package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;

@WebServlet("/viaggia")
public class ServletViaggia extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String targa=request.getParameter("targa");
        String casello1=request.getParameter("casello1");
        String casello2=request.getParameter("casello2");
        double tariffa= Double.parseDouble(request.getParameter("tariffa"));
        double dist = Double.parseDouble(request.getParameter("distanza"));;

        long now = System.currentTimeMillis();
        Timestamp oggiEntrata = new Timestamp(now);
        Timestamp oggiUscita = aggiungiOreViaggio(oggiEntrata, dist);

        try{
            DatabaseTelepass.getInstance().doInsertEntra(targa,casello1,oggiEntrata);
            DatabaseTelepass.getInstance().doInsertEsci(targa,casello2,oggiUscita, tariffa);
            request.setAttribute("messageViaggio", "Grazie per aver viaggiato con noi");
            request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
    }

    public Timestamp aggiungiOreViaggio(Timestamp oggiEntrata, double dist){
        double time = dist / 100.0;
        int ore = (int)time;
        int minuti = (int) ((time - ore) * 60);

        Long milliSecInAnHour = new Long(60 * 60 * 1000);
        Long milliSecInAMinute = new Long (60 * 1000);
        Timestamp newTS = new Timestamp(oggiEntrata.getTime());
        long milliSecToAdd = milliSecInAnHour * ore + milliSecInAMinute * minuti;
        long newTimeMilliSec = newTS.getTime();
        newTS.setTime(newTimeMilliSec + milliSecToAdd);

        return newTS;
    }
}