package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.print.DocFlavor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/creazioneutente")
public class ServletCreazioneUtente extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome=request.getParameter("nomeutente");
        String cognome=request.getParameter("cognomeutente");
        Date nascita= Date.valueOf(request.getParameter("nascita"));
        String codiceContoCorrente=request.getParameter("contocorrente");
        int plus= Integer.parseInt(request.getParameter("plus"));
        String password=request.getParameter("password");
        String username=request.getParameter("username");
        int transponderattivo= Integer.parseInt(request.getParameter("abbonamento"));
        int ruolo=0;

        Connection connection=null;
        PreparedStatement stm=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            stm = connection.prepareStatement("INSERT INTO Cliente(NomeCliente, CognomeCliente, NascitaCliente, CodiceContoCorrente, Plus, Password, Username, TransponderAttivo, Amministratore) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)");
            stm.setString(1,nome);
            stm.setString(2,cognome);
            stm.setDate(3, nascita);
            if(codiceContoCorrente.length()==12){stm.setString(4,codiceContoCorrente);}
            else{
                System.out.println("Le cifre che descrivono un codice di conto corrente sono 12.");
                throw new Exception();
            }
            stm.setInt(5, plus);
            stm.setString(6, password);
            stm.setString(7, username);
            stm.setInt(8, transponderattivo);
            stm.setInt(9, ruolo);
            stm.execute();
            request.setAttribute("messaggioUtente", "Utente Inserito correttamente");
            request.getRequestDispatcher("/protected_area_admin.jsp").forward(request, response);

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
                    connection.commit();
                    connection.close();
                } catch (Exception e) { System.out.println("connection non chiuso");}
            }
        }
    }
}
