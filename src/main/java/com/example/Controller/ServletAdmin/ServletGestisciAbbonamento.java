package com.example.Controller.ServletAdmin;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ModelTelepass.DatabaseTelepass;
/**Questa servlet viene invocata per effettuare modifiche all'abbonamento di un utente qualsiasi.
Ricordiamo che in questo caso l'admin può modificare uno qualsiasi dei parametri inerenti all'abbonamento
di qualsiasi utente, mentre se è l'utente ad accedere a questa funzione potrà modificare solo il
suo di abbonamento.
In base alla funzionalità scelta e se è l'admin o l'utente a farlo, setta i parametri nella request
e esegue l'update dell'utente.*/
@WebServlet("/gestisciAbbonamento")
public class ServletGestisciAbbonamento extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String azione=request.getParameter("gestisci");//prendiamo l'azione da svolgere (Es: rimuovere plus)
        HttpSession session = request.getSession(false);//non creiamo una sessione ma prendiamo quella esistente
        String utente=request.getParameter("username");//prendiamo sempre dalla request lo username
        int ruolo= (int) session.getAttribute("ruolo");//prendiamo dai parametri di sessione il ruolo (chi sta facendo l'operazione)

        if(azione.equals("0")){ //se si vuole disdire l'abbomento
            if(ruolo==0) //se lo fa l'utente
            {
                try{
                    //si prova a fare l'update del cliente
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET TransponderAttivo=0, Plus=0 WHERE Username='"+session.getAttribute("username")+"'");
                    session.setAttribute("attivo", 0);//si aggiorna il valore dell'abbonamento in base all'operazione
                    session.setAttribute("plus", 0);//si aggiorna il valore del plus in base all'operazione
                    //mandiamo un messaggio tramite la request di abbonamento rimosso
                    request.setAttribute("messageRimuoviAbbonamento", "Hai rimosso correttamente il tuo abbonamento");
                    //rimandiamo l'utente alla sua area protetta
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                //se ci sono stati errori nell'update del cliente
                catch (Exception e) {
                    //mandiamo in output un messaggio di errore
                    System.out.println("errore nella connessione per la disdetta dell'abbonamento del cliente");
                }
            }
            else{ //se lo fa l'admin
                try{
                    //se non è stato passato l'utente da modificare
                    if(utente==null)
                    {
                        //mandiamo un messaggio di errore tramite la request
                        request.setAttribute("messageInserisci", "Assicurati di aver inserito un valore nel campo selezionato");
                        //rimandiamo l'admin alla pagina gestisci trasponder per ripetere l'operazione
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                    else{
                        //si prova a fare l'update del cliente
                        DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET TransponderAttivo=0, Plus=0 WHERE Username='"+utente+"'");
                        //mandiamo un messaggio tramite la request di abbonamento rimosso
                        request.setAttribute("messageRimuoviAbbonamentoAdmin", "Hai rimosso correttamente l'abbonamento di "+utente);
                        //rimandiamo l'admin alla stessa pagina per continuare a fare (se vuole) altre modifiche
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                }
                //se ci sono stati errori nell'update del cliente
                catch (Exception e) {
                    //mandiamo in output un messaggio di errore
                    System.out.println("errore nella connessione per la disdetta dell'abbonamento del cliente");
                }
            }
        }
        else if(azione.equals("1")){ //se si vuole aggiungere un abbonamento
            if(ruolo==0){ //se lo fa l'utente
                try{
                    //proviamo a fare l'update del cliente
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET TransponderAttivo=1 WHERE Username='"+session.getAttribute("username")+"'");
                    session.setAttribute("attivo", 1);//settiamo come parametro di sessione l'abbonamento attivo
                    //mandiamo un messaggio tramite la request di abbonamento aggiunto
                    request.setAttribute("messageAggiungiAbbonamento", "Grazie per esserti abbonato");
                    //rimandiamo l'utente alla sua area protetta
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                //se ci sono stati errori nell'update del cliente
                catch (Exception e) {
                    //mandiamo in output un messaggio di errore
                    System.out.println("errore nella connessione per l'aggiunta dell'abbonamento al cliente");
                }
            }
            else{ //se lo fa l'admin
                try{
                    //se non è stato passato l'utente da modificare
                    if(utente==null)
                    {
                        //mandiamo un messaggio di errore tramite la request
                        request.setAttribute("messageInserisci", "Assicurati di aver inserito un valore nel campo selezionato");
                        //rimandiamo l'admin alla pagina gestisci trasponder per ripetere l'operazione
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                    else{
                        //proviamo a fare l'update del cliente
                        DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET TransponderAttivo=1 WHERE Username='"+utente+"'");
                        //mandiamo un messaggio tramite la request di abbonamento aggiunto
                        request.setAttribute("messageAggiungiAbbonamentoAdmin", "Hai inserito correttamente l'abbonamento di "+utente);
                        //rimandiamo l'admin alla stessa pagina per continuare a fare (se vuole) altre modifiche
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                }
                //se ci sono stati errori nell'update del cliente
                catch (Exception e) {
                    //mandiamo in output un messaggio di errore
                    System.out.println("errore nella connessione per l'aggiunta dell'abbonamento al cliente");
                }
            }
        } else if (azione.equals("2")) { //se si vuole rimuovere il plus
            if(ruolo==0) //se lo fa l'utente
            {
                try{
                    //proviamo a fare l'update del cliente
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Plus=0 WHERE Username='"+session.getAttribute("username")+"'");
                    session.setAttribute("plus", 0);//settiamo come parametro di sessione il plus non attivo
                    //mandiamo un messaggio tramite la request di plus rimosso
                    request.setAttribute("messageRimuoviPlus", "Hai rimosso correttamente il tuo plus");
                    //rimandiamo l'utente alla sua area protetta
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                //se ci sono stati errori nell'update del cliente
                catch (Exception e) {
                    //mandiamo in output un messaggio di errore
                    System.out.println("errore nella connessione nella rimozione del plus al cliente");
                }
            }
            else{ //se lo fa l'admin
                try{
                    //se non è stato passato l'utente da modificare
                    if(utente==null)
                    {
                        //mandiamo un messaggio di errore tramite la request
                        request.setAttribute("messageInserisci", "Assicurati di aver inserito un valore nel campo selezionato");
                        //rimandiamo l'admin alla pagina gestisci trasponder per ripetere l'operazione
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                    else{
                        //proviamo a fare l'update del cliente
                        DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Plus=0 WHERE Username='"+utente+"'");
                        //mandiamo un messaggio tramite la request di plus rimosso
                        request.setAttribute("messageRimuoviPlusAdmin", "Hai rimosso correttamente il plus di "+utente);
                        //rimandiamo l'admin alla stessa pagina per continuare a fare (se vuole) altre modifiche
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                }
                //se ci sono stati errori nell'update del cliente
                catch (Exception e) {
                    //mandiamo in output un messaggio di errore
                    System.out.println("errore nella connessione nella rimozione del plus al cliente");
                }
            }
        }
        else{ //se si vuole aggiungere un plus
            if(ruolo==0){ //se lo fa l'utente
                try{
                    //proviamo a fare l'update del cliente
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Plus=1 WHERE Username='"+session.getAttribute("username")+"'");
                    session.setAttribute("plus", 1);//settiamo come parametro di sessione il plus attivo
                    //mandiamo un messaggio tramite la request di plus aggiunto
                    request.setAttribute("messageAggiungiPlus", "Grazie per esserti abbonato a plus");
                    //rimandiamo l'utente alla sua area protetta
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                //se ci sono stati errori nell'update del cliente
                catch (Exception e) {
                    //mandiamo in output un messaggio di errore
                    System.out.println("errore nella connessione per l'aggiunta del plus al cliente");
                }
            }
            else{ //se lo fa l'admin
                try{
                    //se non è stato passato l'utente da modificare
                    if(utente==null)
                    {
                        //mandiamo un messaggio di errore tramite la request
                        request.setAttribute("messageInserisci", "Assicurati di aver inserito un valore nel campo selezionato");
                        //rimandiamo l'admin alla pagina gestisci trasponder per ripetere l'operazione
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                    else{
                        //proviamo a fare l'update del cliente
                        DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Plus=1 WHERE Username='"+utente+"'");
                        //mandiamo un messaggio tramite la request di plus aggiunto
                        request.setAttribute("messageAggiungiPlusAdmin", "Hai inserito correttamente il plus di "+utente);
                        //rimandiamo l'admin alla stessa pagina per continuare a fare (se vuole) altre modifiche
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                }
                //se ci sono stati errori nell'update del cliente
                catch (Exception e) {
                    //mandiamo in output un messaggio di errore
                    System.out.println("errore nella connessione per l'aggiunta del plus al cliente");
                }
            }
        }
    }
}
