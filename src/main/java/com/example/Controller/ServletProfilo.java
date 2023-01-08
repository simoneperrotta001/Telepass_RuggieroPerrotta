package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.List;

@WebServlet("/profilo")
public class ServletProfilo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession(false);
            String parametro = DatabaseTelepass.getInstance().getSingoloValore("SELECT COUNT(*) AS QUANTI FROM contaquantiviaggi where CodiceTransponder='"+session.getAttribute("codice")+"'","QUANTI");
            if(parametro != null){
                int viaggi=Integer.parseInt(parametro);
                request.setAttribute("viaggi",viaggi); //quanti viaggi l'utente ha fatto con telepass
            }
            parametro = DatabaseTelepass.getInstance().getSingoloValore("SELECT COUNT(TargaVeicolo) AS VEICOLI FROM CLIENTE C JOIN VEICOLO V ON C.CodiceTransponder=V.CodiceTransponder WHERE C.CodiceTransponder='"+session.getAttribute("codice")+"'","VEICOLI");
            if(parametro != null){
                int veicoli=Integer.parseInt(parametro);
                request.setAttribute("veicoli",veicoli); //quanti veicoli ha l'utente
            }
            List parametri = DatabaseTelepass.getInstance().getUtente("SELECT NomeCliente,CognomeCliente,TransponderAttivo, Plus, CodiceTransponder FROM CLIENTE WHERE Username='"+session.getAttribute("username")+"'");
            if(parametri.get(0) != null){
                String nome= (String) parametri.get(0);
                String cognome= (String) parametri.get(1);
                String nomeCompleto=nome+" "+cognome;
                request.setAttribute("nomeCompleto", nomeCompleto);
                int attivo = (int) parametri.get(2);
                if(attivo==1){
                    request.setAttribute("attivo", "ATTIVO");
                }
                else {
                    request.setAttribute("attivo", "NON ATTIVO");
                }
                int plus = (int) parametri.get(3);

                if(plus==1){
                    request.setAttribute("plus", "ATTIVO");
                }
                else{
                    request.setAttribute("plus", "NON ATTIVO");
                }
                int transponder= (int) parametri.get(4);
                request.setAttribute("codiceTransponder", transponder);

                request.getRequestDispatcher("/ProfiloUtente.jsp").forward(request, response);
            }
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
    }
}