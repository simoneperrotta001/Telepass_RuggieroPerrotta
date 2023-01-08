package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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
        int transponderAttivo= Integer.parseInt(request.getParameter("abbonamento"));
        String risultato;

        try{
            if(checkData(nascita)) {
                if (codiceContoCorrente.length() == 12) {
                    risultato = DatabaseTelepass.getInstance().getSingoloValore("SELECT Username FROM CLIENTE WHERE CodiceContoCorrente='"+codiceContoCorrente+"'", "Username");
                    if (risultato != null) {
                        request.setAttribute("messageContoUsato", "Conto già utilizzato per un altro transponder");
                        request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
                    }
                    else{
                        if(username.length() > 4)
                        {
                            risultato = DatabaseTelepass.getInstance().getSingoloValore("SELECT Username FROM CLIENTE WHERE Username='"+username+"'", "Username");
                            if (risultato != null){
                                request.setAttribute("messageUsernameUsato", "Username già utilizzato. Inseriscine un altro");
                                request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
                            }
                            else{
                                DatabaseTelepass.getInstance().doInsertUtenti(nome, cognome, nascita, codiceContoCorrente, plus, username, password, transponderAttivo);
                                request.setAttribute("messageUtenteInserito", "Utente Inserito correttamente");
                                request.getRequestDispatcher("/protected_area_admin.jsp").forward(request, response);
                            }
                        }
                        else{
                            request.setAttribute("messageUsernameCorto", "Username troppo corto per essere inserito");
                            request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
                        }
                    }
                }
                else {
                    request.setAttribute("messageConto", "Le cifre che descrivono un codice di conto corrente sono 12.");
                    request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
                }
            }
            else{
                request.setAttribute("messageData", "Data non valida");
                request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
            }
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
    }

    /*questo metodo si occupa di fare il controllo sulla data di nascita degli utenti,
    che deve essere maggiore di 18 anni per poter essere registrato.*/
    public boolean checkData(Date nascita) {
        LocalDate now = LocalDate.now();
        Period diff = Period.between(nascita.toLocalDate(), now);
        //se l'età dell'utente è almeno 18 anni (maggiorenne)
        if(diff.getYears()>=18)
        {
            return true;
        }
        else
            return false;
    }
}