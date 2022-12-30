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

        Connection connection=null;
        Statement stm=null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            stm= connection.createStatement();
            rs= stm.executeQuery("SELECT C.Username,C.CodiceTransponder, COUNT(V.TargaVeicolo) AS QUANTI, C.TransponderAttivo, C.Plus, C.Amministratore\n" +
                    "                    FROM VEICOLO V RIGHT JOIN CLIENTE C on V.CodiceTransponder=C.CodiceTransponder \n" +
                    "                    WHERE C.Username='"+username+"' AND C.Password='"+password+"' \n" +
                    "                    GROUP BY V.CodiceTransponder");
            if(rs.next()){
                int ruolo= rs.getInt("Amministratore");
                int Codice = rs.getInt("CodiceTransponder");
                int Attivo = rs.getInt("TransponderAttivo");
                int Plus = rs.getInt("Plus");
                int Quanti = rs.getInt("QUANTI");
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("ruolo", ruolo);
                session.setAttribute("attivo", Attivo);
                session.setAttribute("plus", Plus);
                session.setAttribute("quanti", Quanti);
                session.setAttribute("codice", Codice);
                if(ruolo==1){
                    response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/protected_area_admin.jsp");
                }
                else{
                    request.getRequestDispatcher("protected_area_utente.jsp").forward(request, response);
                }
            }
            else{
                request.setAttribute("errorMessage", "Invalid Credentials!");
                System.out.println("Invalid cred");
                response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/Accedi.jsp");
            }
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {System.out.println("rs non chiuso");}
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) { System.out.println("stm non chiuso");}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) { System.out.println("connection non chiuso");}
            }
        }

    }
}
