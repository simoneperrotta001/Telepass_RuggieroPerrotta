package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/calcolaprezzo")
public class ServletCalcolaPrezzo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String casello1=request.getParameter("caselloEntrata");
        String casello2=request.getParameter("caselloUscita");
        String targa=request.getParameter("targa");
        String classe =null;
        if(casello1.equals(casello2)) {
            response.sendRedirect("http://localhost:8080/Telepass_RuggieroPerrotta_war_exploded/SimulaPercorso.jsp");
        }
        Connection connection=null;
        Statement stm=null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
            stm= connection.createStatement();
            rs=stm.executeQuery("SELECT ModelloVeicolo FROM VEICOLO WHERE TargaVeicolo='"+targa+"'");
            if(rs.next()) {
                classe= rs.getString("ModelloVeicolo");
            }
            stm= connection.createStatement();
            rs=stm.executeQuery("SELECT Distanza " +
                    "FROM DISTA " +
                    "WHERE (NomeCasello1='"+casello1+"' AND NomeCasello2='"+casello2+"') OR (NomeCasello1='"+casello2+"' AND NomeCasello2='"+casello1+"')");
            if(rs.next()){
                double distanza= rs.getDouble("Distanza");
                double costo_Tot;
                if(classe.equals("A")){
                    ClasseA tipo = new ClasseA();
                    costo_Tot= tipo.pagamento(distanza);
                    request.setAttribute("costokm", tipo.costoKm);
                }
                else if (classe.equals("B")) {
                    ClasseB tipo = new ClasseB();
                    costo_Tot= tipo.pagamento(distanza);
                    request.setAttribute("costokm", tipo.costoKm);
                }
                else if (classe.equals("1")) {
                    Classe1 tipo = new Classe1();
                    costo_Tot= tipo.pagamento(distanza);
                    request.setAttribute("costokm", tipo.costoKm);
                }
                else if (classe.equals("2")) {
                    Classe2 tipo = new Classe2();
                    costo_Tot= tipo.pagamento(distanza);
                    request.setAttribute("costokm", tipo.costoKm);

                }
                else {
                    Classe3 tipo = new Classe3();
                    costo_Tot= tipo.pagamento(distanza);
                    request.setAttribute("costokm", tipo.costoKm);
                }
                request.setAttribute("casello1", casello1);
                request.setAttribute("casello2", casello2);
                request.setAttribute("targa", targa);
                request.setAttribute("distanza", distanza);
                request.setAttribute("costo_tot", costo_Tot);
                request.getRequestDispatcher("ViaggioUtente.jsp").forward(request, response);
            }
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
