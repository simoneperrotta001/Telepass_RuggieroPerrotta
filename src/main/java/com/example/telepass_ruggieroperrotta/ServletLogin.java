package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            Statement stm= connection.createStatement();
            ResultSet rs=stm.executeQuery("SELECT * FROM CLIENTE WHERE Username='"+username+"' AND Password='"+password+"'");
            if(rs.next()){
                int ruolo= rs.getInt("Amministratore");
                String Nome= rs.getString("NomeCliente");
                String Cognome= rs.getString("CognomeCliente");
                String nomeCompleto=Nome+Cognome;
                request.setAttribute("nomeCompleto", nomeCompleto);
                int Attivo = rs.getInt("TransponderAttivo");
                if(Attivo==1){
                    request.setAttribute("attivo", "ATTIVO");
                }
                else{
                    request.setAttribute("attivo", "NON ATTIVO");
                }

                int Plus = rs.getInt("Plus");
                if(Plus==1){
                    request.setAttribute("plus", "ATTIVO");
                }
                else{
                    request.setAttribute("plus", "NON ATTIVO");
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("ruolo", ruolo);
                if(ruolo==1){
                    response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/protected_area_admin.jsp");
                }
                else{
                    request.getRequestDispatcher("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/protected_area_utenti.jsp").forward(request, response);
                }
            }
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
/*
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            Statement stm= connection.createStatement();
            ResultSet rs=stm.executeQuery("SELECT * FROM CLIENTE");
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
        }*/
    }
}
