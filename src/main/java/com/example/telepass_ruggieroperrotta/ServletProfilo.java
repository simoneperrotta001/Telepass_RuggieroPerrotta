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

@WebServlet("/profilo")
public class ServletProfilo extends HttpServlet {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            Statement stm= connection.createStatement();
            ResultSet rs=stm.executeQuery("SELECT * FROM CLIENTE WHERE Username='"+session.getAttribute("username")+"'");
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
                request.getRequestDispatcher("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/ProfiloUtente.jsp").forward(request, response);

            }
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
}

