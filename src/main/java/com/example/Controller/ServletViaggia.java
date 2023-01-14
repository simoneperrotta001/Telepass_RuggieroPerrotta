package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

/*Questa servlet viene invocata quando l'utente effettua un viaggio.
Questa si occupa di prendersi la data corrente, calcolare la data presunta di arrivo al casello di uscita
e inserire i record nella tabella entra ed esce del db.*/
@WebServlet("/viaggia")
public class ServletViaggia extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String targa=request.getParameter("targa");//setta la targa
        String casello1=request.getParameter("casello1");//setta il casello di entrata
        String casello2=request.getParameter("casello2");//setta il cssello di uscita
        double tariffa= Double.parseDouble(request.getParameter("tariffa"));//setta la tariffa da pagare
        double dist = Double.parseDouble(request.getParameter("distanza"));//setta la distanza da percorrere tra i due caselli

        long now = System.currentTimeMillis();
        Timestamp oggiEntrata = new Timestamp(now);//data e ora di entrata al casello1
        Timestamp oggiUscita = aggiungiOreViaggio(oggiEntrata, dist);//data e ora di uscita calcolata per il casello2

        try{
            Timestamp ultimo=java.sql.Timestamp.valueOf((String) DatabaseTelepass.getInstance().getSingoloValore("SELECT E.OrarioUscita\n" +
                    "FROM ESCE E JOIN VEICOLO V ON E.TargaVeicolo=V.TargaVeicolo JOIN CLIENTE C ON C.CodiceTransponder=V.CodiceTransponder\n" +
                    "WHERE V.TargaVeicolo='"+targa+"'\n" +
                    "ORDER BY E.OrarioUscita DESC\n" +
                    "LIMIT 1","OrarioUscita"));
            System.out.println(ultimo);
            if(ultimo != null)
            {
                System.out.println("ciao");
                int check = oggiEntrata.compareTo(ultimo);
                if(check>0){
                    //proviamo ad inserire il record in entra
                    DatabaseTelepass.getInstance().doInsertEntra(targa,casello1,oggiEntrata);
                    //proviamo ad inserire il record in esce
                    DatabaseTelepass.getInstance().doInsertEsci(targa,casello2,oggiUscita, tariffa);
                    //settiamo un messaggio di viaggio riuscito nella request
                    request.setAttribute("messageViaggio", "Grazie per aver viaggiato con noi");
                    //reindirizziamo l'utente alla sua area protetta
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("messageViaggioNonPossibile", "Non puoi iniziare un nuovo viaggio se l'ultimo non è terminato.");
                    //reindirizziamo l'utente alla sua area protetta
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
            }
            else{
                System.out.println("ciao else");
                //proviamo ad inserire il record in entra
                DatabaseTelepass.getInstance().doInsertEntra(targa,casello1,oggiEntrata);
                //proviamo ad inserire il record in esce
                DatabaseTelepass.getInstance().doInsertEsci(targa,casello2,oggiUscita, tariffa);
                //settiamo un messaggio di viaggio riuscito nella request
                request.setAttribute("messageViaggio", "Grazie per aver viaggiato con noi");
                //reindirizziamo l'utente alla sua area protetta
                request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
            }
        }
        //se vi sono stati errori nell'esecuzione delle query
        catch (Exception e) {
            //mandiamo in output un messaggio di errore
            System.out.println("errore nella connessione");
        }
    }
    /*Questo metodo si occupa di calcolare, seguendo una media di 100km/h il tempo
    di percorrenza che ci vorrà tra il casello di entrata e quello di uscita.*/
    public Timestamp aggiungiOreViaggio(Timestamp oggiEntrata, double dist){
        double time = dist / 100.0;//tempo generale
        int ore = (int)time;//calcolo delle ore
        int minuti = (int) ((time - ore) * 60);//calcolo dei minuti

        Long milliSecInAnHour = new Long(60 * 60 * 1000);
        Long milliSecInAMinute = new Long (60 * 1000);
        Timestamp newTS = new Timestamp(oggiEntrata.getTime());
        //millisecondi da aggiungere alla data corrente (quindi alla data di entrata) per calcolare la data di uscita
        long milliSecToAdd = milliSecInAnHour * ore + milliSecInAMinute * minuti;
        long newTimeMilliSec = newTS.getTime();
        newTS.setTime(newTimeMilliSec + milliSecToAdd);

        return newTS;//ritorniamo la data di uscita
    }
}