package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/modificaUtenteSelezionato")
public class ServletModificaUtenteSelezionato extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");

        Connection connection=null;
        Statement stm=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT", "ROOT");
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT TransponderAttivo, Plus FROM CLIENTE WHERE Username='"+username+"'");
            if (rs.next()) {
                int Attivo = rs.getInt("TransponderAttivo");
                int Plus = rs.getInt("Plus");
                request.setAttribute("username", username);
                request.setAttribute("attivo", Attivo);
                request.setAttribute("plus", Plus);
                request.getRequestDispatcher("modificaUtenteSingolo.jsp").forward(request, response);
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
