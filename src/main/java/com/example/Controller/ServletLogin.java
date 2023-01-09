package com.example.Controller;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
/*Questa servlet viene invocata quando un utente vuole loggarsi.
Dopo aver controllato i parametri necessari affinchè l'utente possa loggarsi,
settiamo tutti i parametri di sessione e il login avviene con successo.*/
@WebServlet("/login")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            //proviamo a fare il login cercando lo username e la password nel db
            List parametri = DatabaseTelepass.getInstance().doLogin("SELECT Amministratore, CodiceTransponder, TransponderAttivo, Plus FROM CLIENTE WHERE Username='"+request.getParameter("username")+"' AND Password='"+request.getParameter("password")+"'");
            //se c'è un risultato quindi lo username esiste e la password è corretta
            if(parametri.get(0)!=null)
            {
                //creiamo la sessione per l'utente loggato
                HttpSession session = request.getSession(true);
                //settiamo tutti i parametri di sessione che ci serviranno di seguito
                int ruolo = (int) parametri.get(0);//prendiamo il ruolo
                session.setAttribute("username", request.getParameter("username"));//settiamo il suo username dalla request
                session.setAttribute("ruolo", parametri.get(0));//settiamo il suo rulo
                session.setAttribute("codice", parametri.get(1));//settiamo il codice transponder
                session.setAttribute("attivo", parametri.get(2));//settiamo se l'abbonamento è attivo o meno
                session.setAttribute("plus", parametri.get(3));//settiamo se il plus è attivo o meno
                //se è l'admin ad essersi loggato restituiamo un messaggio e lo indirizziamo all'area admin protetta
                if(ruolo==1){
                    request.setAttribute("successMessage", "Credenziali corrette");
                    request.getRequestDispatcher("protected_area_admin.jsp").forward(request, response);
                }
                //se è l'utente ad essersi loggato restituiamo un altro messaggio e lo indirizziamo all'area utente protetta
                else{
                    request.setAttribute("successMessage", "Credenziali corrette");
                    request.getRequestDispatcher("protected_area_utente.jsp").forward(request, response);
                }
            }
            //se non si è trovato l'utente nel db restituiamo un messaggio di utente non esistente e rimandiamo alla pagina di accesso
            else{
                request.setAttribute("messageUtenteNonEsistente", "L'utente inserito non esiste");
                request.getRequestDispatcher("Accedi.jsp").forward(request, response);
            }
        }
        //se vi sono stati errori nell'esecuzione della query
        catch (Exception e) {
            //mandiamo in output un messaggio di errore
            System.out.println("errore nella connessione");
        }
    }
}