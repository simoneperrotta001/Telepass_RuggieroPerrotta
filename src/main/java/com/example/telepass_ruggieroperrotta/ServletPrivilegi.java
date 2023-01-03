package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "ServletPrivilegi", value = "/ServletPrivilegi")
public class ServletPrivilegi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        Connection connection=null;
        Statement stm=null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            stm= connection.createStatement();
            rs= stm.executeQuery("SELECT Amministratore, CodiceTransponder, TransponderAttivo, Plus FROM CLIENTE WHERE Username='"+username+"' AND Password='"+password+"'");
            if(rs.next()){
                int ruolo= rs.getInt("Amministratore");
                int Codice = rs.getInt("CodiceTransponder");
                int Attivo = rs.getInt("TransponderAttivo");
                int Plus = rs.getInt("Plus");
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("ruolo", ruolo);
                session.setAttribute("attivo", Attivo);
                session.setAttribute("plus", Plus);
                session.setAttribute("codice", Codice);
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
