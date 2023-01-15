package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.List;
/*Questa servlet viene invocata quando un utente accede alla pagina del suo profilo.
Questa si occuperà quindi di prendersi tutti i dati che servono, metterli nella request
e mandarli alla pagina cosicchè possa mostrarli a schermo*/
@WebServlet("/profilo")
public class ServletProfilo extends HttpServlet {
    /*Questa servlet ha come metodo di passaggio dati il GET, a differenza delle altre servlet che hanno come metodo il
    POST. Questo perchè questa servlet per settare i dati da mandare a schermo non li invia tramite una form, ma sfrutta
    quelli che sono i parametri di sessione.*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        try{
            //proviamo a prenderci quanti viaggi l'utente ha fatto con telepass
            String parametro = DatabaseTelepass.getInstance().getSingoloValore("SELECT COUNT(*) AS QUANTI FROM contaquantiviaggi where CodiceTransponder='"+session.getAttribute("codice")+"'","QUANTI");
            //se risulta esserci un risultato
            if(parametro != null){
                int viaggi=Integer.parseInt(parametro);
                request.setAttribute("viaggi",viaggi); //settiamo quanti viaggi l'utente ha fatto con telepass
            }
            //proviamo a prenderci quanti veicoli ha associato l'utente
            parametro = DatabaseTelepass.getInstance().getSingoloValore("SELECT COUNT(TargaVeicolo) AS VEICOLI FROM CLIENTE C JOIN VEICOLO V ON C.CodiceTransponder=V.CodiceTransponder WHERE C.CodiceTransponder='"+session.getAttribute("codice")+"'","VEICOLI");
            //se risulta esserci un risultato
            if(parametro != null){
                int veicoli=Integer.parseInt(parametro);
                request.setAttribute("veicoli",veicoli); //settiamo quanti veicoli ha l'utente
            }
            //proviamo a prenderci tutte le informazioni relative all'utente
            List parametri = DatabaseTelepass.getInstance().getUtente("SELECT NomeCliente,CognomeCliente,TransponderAttivo, Plus, CodiceTransponder FROM CLIENTE WHERE Username='"+session.getAttribute("username")+"'");
            //se ci sono risultati
            if(parametri.get(0) != null){
                //settiamo tutti i parametri che ci servono nella request
                String nome= (String) parametri.get(0);
                String cognome= (String) parametri.get(1);
                String nomeCompleto=nome+" "+cognome;
                request.setAttribute("nomeCompleto", nomeCompleto);//settiamo nome completo
                int attivo = (int) parametri.get(2);
                if(attivo==1){
                    request.setAttribute("attivo", "ATTIVO");//settiamo abbonamento attivo
                }
                else {
                    request.setAttribute("attivo", "NON ATTIVO");//settiamo abbonamento non attivo
                }
                int plus = (int) parametri.get(3);
                if(plus==1){
                    request.setAttribute("plus", "ATTIVO");//settiamo plus attivo
                }
                else{
                    request.setAttribute("plus", "NON ATTIVO");//settiamo plus non attivo
                }
                int transponder= (int) parametri.get(4);
                request.setAttribute("codiceTransponder", transponder);
                //inviamo tutti i parametri nella request alla pagina profilo utente
                request.getRequestDispatcher("/ProfiloUtente.jsp").forward(request, response);
            }
        }
        //se vi sono stati errori nell'esecuzione della query
        catch (Exception e) {
            //mandiamo in output un messaggio di errore
            System.out.println("errore nella connessione");
        }
    }
}