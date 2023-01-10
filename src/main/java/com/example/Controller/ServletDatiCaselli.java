package com.example.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ModelTelepass.DatabaseTelepass;

import java.io.IOException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.text.DateFormatSymbols;


@WebServlet("/datiCaselli")
public class ServletDatiCaselli extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int azione= Integer.parseInt(request.getParameter("azione"));//prendiamo l'azione da svolgere (Es: rimuovere plus)
        int numMese= Integer.parseInt(request.getParameter("mese"));//prendiamo sempre dalla request lo username


                /*List risultati=DatabaseTelepass.getInstance().getDoppioValore("SELECT NomeCasello, COUNT(NomeCasello) AS QUANTI" +
                                                                " FROM ENTRA " +
                                                                "WHERE YEAR(OrarioEntrata)=2023 AND MONTH(OrarioEntrata)='"+mese+"'"+
                                                                " GROUP BY NomeCasello"+
                                                                " ORDER BY NomeCasello","NomeCasello", "QUANTI");*/
        request.setAttribute("azione", azione);
        request.setAttribute("numMese", numMese);
        String mese=new DateFormatSymbols().getMonths()[numMese-1];
        request.setAttribute("mese", mese);
        request.getRequestDispatcher("/DatiCaselli.jsp").forward(request, response);
    }
}
