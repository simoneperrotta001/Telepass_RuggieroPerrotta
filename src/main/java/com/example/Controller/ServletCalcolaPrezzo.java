package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/calcolaprezzo")
public class ServletCalcolaPrezzo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String casello1=request.getParameter("caselloEntrata");
        String casello2=request.getParameter("caselloUscita");
        String targa=request.getParameter("targa");
        double costo_Tot;

        if(casello1.equals(casello2)) {
            request.setAttribute("messagePercorso", "Assicurati di aver inserito il casello di uscita diverso da quello di entrata");
            request.getRequestDispatcher("/SimulaPercorso.jsp").forward(request, response);
        }

        try{
            List classe = DatabaseTelepass.getInstance().getSingoloValore("SELECT ModelloVeicolo FROM VEICOLO WHERE TargaVeicolo='"+targa+"'","ModelloVeicolo");
            List distanza = DatabaseTelepass.getInstance().getSingoloValore("SELECT Distanza " + "FROM DISTA " + "WHERE (NomeCasello1='"+casello1+"' AND NomeCasello2='"+casello2+"') OR (NomeCasello1='"+casello2+"' AND NomeCasello2='"+casello1+"')","Distanza");
            InizializzazioneClasse inizializzazioneClasse;
            double dist = Double.parseDouble((String) distanza.get(0));

            if(classe.get(0).equals("A")){
                inizializzazioneClasse = new InizializzazioneClasseA();
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                costo_Tot= classeVeicolo.pagamento(dist);
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            else if (classe.get(0).equals("B")) {
                inizializzazioneClasse = new InizializzazioneClasseB();
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                costo_Tot= classeVeicolo.pagamento(dist);
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            else if (classe.get(0).equals("1")) {
                inizializzazioneClasse = new InizializzazioneClasse1();
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                costo_Tot= classeVeicolo.pagamento(dist);
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            else if (classe.get(0).equals("2")) {
                inizializzazioneClasse = new InizializzazioneClasse2();
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                costo_Tot= classeVeicolo.pagamento(dist);
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            else {
                inizializzazioneClasse = new InizializzazioneClasse3();
                Classe classeVeicolo = inizializzazioneClasse.creaClasse();
                costo_Tot= classeVeicolo.pagamento(dist);
                request.setAttribute("costokm", classeVeicolo.getCostoKm());
            }
            request.setAttribute("casello1", casello1);
            request.setAttribute("casello2", casello2);
            request.setAttribute("targa", targa);
            request.setAttribute("distanza", dist);
            request.setAttribute("costo_tot", costo_Tot);
            request.getRequestDispatcher("ViaggioUtente.jsp").forward(request, response);
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
    }
}
