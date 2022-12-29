package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("-------\nusername: "+ username +" passw: "+password );
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            Statement stm= connection.createStatement();
            ResultSet rs=stm.executeQuery("SELECT * FROM CLIENTE WHERE Username='"+username+"'AND Password='"+password+"'");
            int ruolo = rs.getInt("Amministratore");
            if (rs.next()) {
                if(ruolo==1){
                    //session ruolo e utente da settare
                    response.sendRedirect("protected_area_admin");
                }
                else{
                    //session ruolo e utente da settare
                    response.sendRedirect("protected_area_utenti");
                }
            }
            else{
                request.setAttribute("errorMessage", "Credenziali errate");
                request.getRequestDispatcher("Accedi.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            System.out.println("errore nell'apertura della connessione");
            request.setAttribute("errorMessage", "Connessione non riuscita");
            request.getRequestDispatcher("Accedi.jsp").forward(
                    request, response);
        }
    }
}
