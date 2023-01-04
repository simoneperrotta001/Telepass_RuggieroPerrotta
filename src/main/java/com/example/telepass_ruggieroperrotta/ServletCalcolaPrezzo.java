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
            request.setAttribute("messagePercorso", "Assicurati di aver inserito il casello di uscita diverso da quello di entrata");
            request.getRequestDispatcher("/SimulaPercorso.jsp").forward(request, response);
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
                InizializzazioneClasse inizializzazioneClasse;
                if(classe.equals("A")){
                    inizializzazioneClasse = new InizializzazioneClasseA();
                    Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                    costo_Tot= classeVeicolo.pagamento(distanza);
                    request.setAttribute("costokm", classeVeicolo.getCostoKm());
                }
                else if (classe.equals("B")) {
                    inizializzazioneClasse = new InizializzazioneClasseB();
                    Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                    costo_Tot= classeVeicolo.pagamento(distanza);
                    request.setAttribute("costokm", classeVeicolo.getCostoKm());
                }
                else if (classe.equals("1")) {
                    inizializzazioneClasse = new InizializzazioneClasse1();
                    Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                    costo_Tot= classeVeicolo.pagamento(distanza);
                    request.setAttribute("costokm", classeVeicolo.getCostoKm());
                }
                else if (classe.equals("2")) {
                    inizializzazioneClasse = new InizializzazioneClasse2();
                    Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                    costo_Tot= classeVeicolo.pagamento(distanza);
                    request.setAttribute("costokm", classeVeicolo.getCostoKm());

                }
                else {
                    inizializzazioneClasse = new InizializzazioneClasse3();
                    Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                    costo_Tot= classeVeicolo.pagamento(distanza);
                    request.setAttribute("costokm", classeVeicolo.getCostoKm());
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
