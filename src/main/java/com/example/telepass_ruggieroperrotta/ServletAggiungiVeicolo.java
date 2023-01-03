package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/aggiungiVeicolo")
public class ServletAggiungiVeicolo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targa=request.getParameter("targa");
        String classe=request.getParameter("classe");
        Connection connection=null;
        PreparedStatement stm=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            HttpSession session = request.getSession(false);
            connection.setAutoCommit(false);

            int cod = (int) session.getAttribute("codice");
            stm = connection.prepareStatement("INSERT INTO VEICOLO VALUES (?,?,?)");
            if(targa.length()==7)
                stm.setString(1,targa);
            else{
                System.out.println("Le cifre che descrivono una targa sono 7.");
                throw new Exception();
            }
            stm.setInt(2,cod);
            stm.setString(3,classe);
            stm.execute();
            connection.commit();
            request.setAttribute("messageVeicolo", "Veicolo Inserito correttamente");
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
