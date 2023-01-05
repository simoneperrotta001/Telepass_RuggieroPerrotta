package com.example.Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ModelTelepass.DatabaseTelepass;
import java.util.List;

@WebServlet("/modificaUtenteSelezionato")
public class ServletModificaUtenteSelezionato extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("username");

        try {
            List parametri = DatabaseTelepass.getInstance().getDoppioValore("SELECT TransponderAttivo, Plus FROM CLIENTE WHERE Username='"+username+"'","TransponderAttivo","Plus");
            if (parametri.get(0) != null) {
                int Attivo = Integer.parseInt((String) parametri.get(0));
                int Plus = Integer.parseInt((String) parametri.get(1));
                request.setAttribute("username", username);
                request.setAttribute("attivo", Attivo);
                request.setAttribute("plus", Plus);
                request.getRequestDispatcher("modificaUtenteSingolo.jsp").forward(request, response);
            }
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
    }
}
