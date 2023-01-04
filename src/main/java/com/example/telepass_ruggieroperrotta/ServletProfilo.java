package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.FileStore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/profilo")
public class ServletProfilo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection=null;
        Statement stm=null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            stm= connection.createStatement();
            HttpSession session = request.getSession(false);
            rs=stm.executeQuery("SELECT COUNT(*) AS QUANTI FROM contaquantiviaggi where CodiceTransponder='"+session.getAttribute("codice")+"'");
            if(rs.next()){
                int viaggi=rs.getInt("QUANTI");
                request.setAttribute("viaggi",viaggi); //quanti viaggi l'utente ha fatto con telepass
            }
            rs=stm.executeQuery("SELECT * FROM CLIENTE WHERE Username='"+session.getAttribute("username")+"'");
            if(rs.next()){
                String Nome= rs.getString("NomeCliente");
                String Cognome= rs.getString("CognomeCliente");
                String nomeCompleto=Nome+" "+Cognome;
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
                String Transponder= rs.getString("CodiceTransponder");
                request.setAttribute("codiceTransponder", Transponder);
                request.getRequestDispatcher("/ProfiloUtente.jsp").forward(request, response);

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

