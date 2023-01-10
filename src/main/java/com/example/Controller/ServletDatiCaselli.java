package com.example.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormatSymbols;
/*Questa servlet viene invocata per mostrare i dati relativi alle entrate/uscite dai caselli
in un particolare mese dell'anno corrente.
Questa si occuperà di controllare quali statistiche si vogliono visionare, se entrata o uscita
e di recapitare il numero del mese scelto settandolo nella request e inoltrandolo alla
pagina di visualizzazione.*/
@WebServlet("/datiCaselli")
public class ServletDatiCaselli extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int azione= Integer.parseInt(request.getParameter("azione"));//prendiamo l'azione da svolgere (Es: rimuovere plus)
        int numMese= Integer.parseInt(request.getParameter("mese"));//prendiamo sempre dalla request lo username
        request.setAttribute("azione", azione);
        request.setAttribute("numMese", numMese);
        String mese=new DateFormatSymbols().getMonths()[numMese-1];
        request.setAttribute("mese", mese);
        request.getRequestDispatcher("/DatiCaselli.jsp").forward(request, response);
    }
}
