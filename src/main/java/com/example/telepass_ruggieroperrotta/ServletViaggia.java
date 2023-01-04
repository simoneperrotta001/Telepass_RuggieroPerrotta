package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/viaggia")
public class ServletViaggia extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targa=request.getParameter("targa");
        String casello1=request.getParameter("casello1");
        String casello2=request.getParameter("casello2");
        String tariffa=request.getParameter("tariffa");
        long now = System.currentTimeMillis();
        Timestamp oggi = new Timestamp(now);
        Connection connection=null;
        PreparedStatement stmentra=null;
        PreparedStatement stmesce=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            connection.setAutoCommit(false);

            stmentra = connection.prepareStatement("INSERT INTO ENTRA VALUES (?,?,?)");
            if(targa.length()==7)
                stmentra.setString(1,targa);
            else{
                System.out.println("Le cifre che descrivono una targa sono 7.");
                throw new Exception();
            }
            stmentra.setString(2,casello1);
            stmentra.setTimestamp(3,oggi);
            stmentra.execute();
            connection.commit();

            stmesce = connection.prepareStatement("INSERT INTO ESCE VALUES (?,?,?,?)");
            if(targa.length()==7)
                stmesce.setString(1,targa);
            else{
                System.out.println("Le cifre che descrivono una targa sono 7.");
                throw new Exception();
            }
            stmesce.setString(2,casello2);
            stmesce.setTimestamp(3,oggi);
            stmesce.setString(4,tariffa);
            stmesce.execute();
            connection.commit();
            request.setAttribute("messageViaggio", "Grazie per aver viaggiato con noi");
            request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
        finally {
            if (stmentra != null) {
                try {
                    stmentra.close();
                } catch (Exception e) { System.out.println("stm non chiuso");}
            }
            if (stmesce != null) {
                try {
                    stmesce.close();
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
