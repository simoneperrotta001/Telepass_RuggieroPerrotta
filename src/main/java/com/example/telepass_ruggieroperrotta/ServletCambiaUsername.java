package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/cambiausername")
public class ServletCambiaUsername extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nuovo=request.getParameter("username");
        Connection connection=null;
        Statement stm=null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            HttpSession session = request.getSession(false);
            connection.setAutoCommit(false);
            stm= connection.createStatement();
            stm.executeUpdate("UPDATE CLIENTE SET Username='"+nuovo+"'WHERE Username='"+session.getAttribute("username")+"'");
            connection.commit();
            session.setAttribute("username", nuovo);
            request.setAttribute("messageUsername", "Il tuo username è stato modificato correttamente");
            request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
        finally {
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
