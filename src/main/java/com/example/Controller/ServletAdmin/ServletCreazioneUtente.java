package com.example.Controller.ServletAdmin;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
/**Questa servlet viene invocata per creare gli utenti.
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
        String password=request.getParameter("password");//prendiamo la password dalla request
        String username=request.getParameter("username");//prendiamo lo username dalla request
        int transponderAttivo= Integer.parseInt(request.getParameter("abbonamento"));//prendiamo l'abbonamento se attivo o meno dalla request
        String risultato;//prepariamo la variabile che conterrà i risutlati della query

        try{
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
                    DatabaseTelepass.getInstance().doInsertUtenti(nome, cognome, nascita, codiceContoCorrente, 0, username, password, transponderAttivo);
                    //mandiamo un messaggio di successo alla jsp chiamante
                    request.setAttribute("messageUtenteInserito", "Utente Inserito correttamente");
                    //rimandiamo l'admin alla sua area protetta
                    request.getRequestDispatcher("/protected_area_admin.jsp").forward(request, response);
                }
            }
        }
        //se ci sono stati errori durante le query
        catch (Exception e) {
            //mandiamo in output un messaggio di errore
            System.out.println("errore nella connessione");
        }
    }
}