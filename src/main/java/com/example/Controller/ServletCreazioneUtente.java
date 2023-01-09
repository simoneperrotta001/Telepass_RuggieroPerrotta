package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
/*Questa servlet viene invocata per creare gli utenti.
Ricordiamo che solo l'admin può creare gli utenti (un utente non può registrarsi ma solo loggarsi).
Questa servlet si occupa di controllare tutti i parametri inseriti dall'admin per la creazione dell'utente.
Dopo aver superato tutti i controlli l'utente viene effettivamente inserito mandando un messaggio
di successo alla jsp chiamante.*/
@WebServlet("/creazioneutente")
public class ServletCreazioneUtente extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome=request.getParameter("nomeutente");//prendiamo il nome dalla request
        String cognome=request.getParameter("cognomeutente");//prendiamo il cognome dalla request
        Date nascita= Date.valueOf(request.getParameter("nascita"));//prendiamo la data di nascita dalla request
        String codiceContoCorrente=request.getParameter("contocorrente");//prendiamo il conto corrente dalla request
        int plus= Integer.parseInt(request.getParameter("plus"));//prendiamo il valore del plus se attivo o meno dalla request
        String password=request.getParameter("password");//prendiamo la password dalla request
        String username=request.getParameter("username");//prendiamo lo username dalla request
        int transponderAttivo= Integer.parseInt(request.getParameter("abbonamento"));//prendiamo l'abbonamento se attivo o meno dalla request
        String risultato;//prepariamo la variabile che conterrà i risutlati della query

        try{
            //se l'età risulta essere maggiorenne
            if(checkData(nascita)) {
                //se il codice conto corrente risulta essere di 12 cifre
                if (codiceContoCorrente.length() == 12) {
                    //proviamo a cercare se il codice conto corrente inserito già esiste nel db
                    risultato = DatabaseTelepass.getInstance().getSingoloValore("SELECT Username FROM CLIENTE WHERE CodiceContoCorrente='"+codiceContoCorrente+"'", "Username");
                    //se già esiste
                    if (risultato != null) {
                        //mandiamo un messaggio di errore alla jsp chiamante
                        request.setAttribute("messageContoUsato", "Conto già utilizzato per un altro transponder");
                        //rimandiamo l'admin alla pagina creazione utenti per far re-inserire l'utente
                        request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
                    }
                    //se non esiste
                    else{
                        //se lo username è lungo almeno 5 caratteri
                        if(username.length() > 4)
                        {
                            //controlliamo se lo username esiste già nel db
                            risultato = DatabaseTelepass.getInstance().getSingoloValore("SELECT Username FROM CLIENTE WHERE Username='"+username+"'", "Username");
                            //se già esiste lo username
                            if (risultato != null){
                                //mandiamo un messaggio di errore alla jsp chiamante
                                request.setAttribute("messageUsernameUsato", "Username già utilizzato. Inseriscine un altro");
                                //rimandiamo l'admin alla pagina creazione utenti per far re-inserire l'utente
                                request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
                            }
                            //se non esiste
                            else{
                                //proviamo ad inserire l'utente
                                DatabaseTelepass.getInstance().doInsertUtenti(nome, cognome, nascita, codiceContoCorrente, plus, username, password, transponderAttivo);
                                //mandiamo un messaggio di successo alla jsp chiamante
                                request.setAttribute("messageUtenteInserito", "Utente Inserito correttamente");
                                //rimandiamo l'admin alla sua area protetta
                                request.getRequestDispatcher("/protected_area_admin.jsp").forward(request, response);
                            }
                        }
                        //se lo username è lungo meno di 5 caratteri
                        else{
                            //mandiamo un messaggio di errore alla jsp chiamante
                            request.setAttribute("messageUsernameCorto", "Username troppo corto per essere inserito");
                            //rimandiamo l'admin alla pagina creazione utenti per far re-inserire l'utente
                            request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
                        }
                    }
                }
                //se il codice conto corrente non è lungo 12 caratteri
                else {
                    //mandiamo un messaggio di errore alla jsp chiamante
                    request.setAttribute("messageConto", "Le cifre che descrivono un codice di conto corrente sono 12.");
                    //rimandiamo l'admin alla pagina creazione utenti per far re-inserire l'utente
                    request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
                }
            }
            //se l'utente non risulta essere maggiorenne
            else{
                //mandiamo un messaggio di errore alla jsp chiamante
                request.setAttribute("messageData", "Data non valida");
                //rimandiamo l'admin alla pagina creazione utenti per far re-inserire l'utente
                request.getRequestDispatcher("/CreazioneUtenti.jsp").forward(request, response);
            }
        }
        //se ci sono stati errori durante le query
        catch (Exception e) {
            //mandiamo in output un messaggio di errore
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