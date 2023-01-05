package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            List parametri = DatabaseTelepass.getInstance().doLogin("SELECT Amministratore, CodiceTransponder, TransponderAttivo, Plus FROM CLIENTE WHERE Username='"+request.getParameter("username")+"' AND Password='"+request.getParameter("password")+"'");
            if(parametri.get(0)!=null)
            {
                HttpSession session = request.getSession(true);
                int ruolo = (int) parametri.get(0);
                session.setAttribute("username", request.getParameter("username"));
                session.setAttribute("ruolo", parametri.get(0));
                session.setAttribute("codice", parametri.get(1));
                session.setAttribute("attivo", parametri.get(2));
                session.setAttribute("plus", parametri.get(3));

                if(ruolo==1){
                    request.setAttribute("successMessage", "Credenziali corrette");
                    request.getRequestDispatcher("protected_area_admin.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("successMessage", "Credenziali corrette");
                    request.getRequestDispatcher("protected_area_utente.jsp").forward(request, response);
                }
            }
            else{
                request.setAttribute("messageUtenteNonEsistente", "L'utente inserito non esiste");
                request.getRequestDispatcher("/Accedi.jsp").forward(request, response);
            }
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
    }
}