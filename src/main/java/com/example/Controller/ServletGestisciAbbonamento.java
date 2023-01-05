package com.example.Controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ModelTelepass.DatabaseTelepass;

@WebServlet("/gestisciAbbonamento")
public class ServletGestisciAbbonamento extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String azione=request.getParameter("gestisci");
        HttpSession session = request.getSession(false);
        String utente=request.getParameter("username");
        int ruolo= (int) session.getAttribute("ruolo");

        if(azione.equals("0")){ //se si vuole disdire l'abbomento
            if(ruolo==0) //se lo fa l'utente
            {
                try{
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET TransponderAttivo=0, Plus=0 WHERE Username='"+session.getAttribute("username")+"'");
                    session.setAttribute("attivo", 0);
                    session.setAttribute("plus", 0);
                    request.setAttribute("messageRimuoviAbbonamento", "Hai rimosso correttamente il tuo abbonamento");
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione per la disdetta dell'abbonamento del cliente");
                }
            }
            else{ //se lo fa l'admin
                try{
                    if(utente==null)
                    {
                        request.setAttribute("messageInserisci", "Assicurati di aver inserito un valore nel campo selezionato");
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET TransponderAttivo=0, Plus=0 WHERE Username='"+utente+"'");
                    request.setAttribute("messageRimuoviAbbonamentoAdmin", "Hai rimosso correttamente l'abbonamento di "+utente);
                    request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione per la disdetta dell'abbonamento del cliente");
                }
            }
        }
        else if(azione.equals("1")){ //se si vuole aggiungere un abbonamento
            if(ruolo==0){ //se lo fa l'utente
                try{
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET TransponderAttivo=1 WHERE Username='"+session.getAttribute("username")+"'");
                    session.setAttribute("attivo", 1);
                    request.setAttribute("messageAggiungiAbbonamento", "Grazie per esserti abbonato");
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione per l'aggiunta dell'abbonamento al cliente");
                }
            }
            else{ //se lo fa l'admin
                try{
                    if(utente==null)
                    {
                        request.setAttribute("messageInserisci", "Assicurati di aver inserito un valore nel campo selezionato");
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET TransponderAttivo=1 WHERE Username='"+utente+"'");
                    request.setAttribute("messageAggiungiAbbonamentoAdmin", "Hai inserito correttamente l'abbonamento di "+utente);
                    request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione per l'aggiunta dell'abbonamento al cliente");
                }
            }
        } else if (azione.equals("2")) { //se si vuole rimuovere il plus
            if(ruolo==0) //se lo fa l'utente
            {
                try{
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Plus=0 WHERE Username='"+session.getAttribute("username")+"'");
                    session.setAttribute("plus", 0);
                    request.setAttribute("messageRimuoviPlus", "Hai rimosso correttamente il tuo plus");
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione nella rimozione del plus al cliente");
                }
            }
            else{ //se lo fa l'admin
                try{
                    if(utente==null)
                    {
                        request.setAttribute("messageInserisci", "Assicurati di aver inserito un valore nel campo selezionato");
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Plus=0 WHERE Username='"+utente+"'");
                    request.setAttribute("messageRimuoviPlusAdmin", "Hai rimosso correttamente il plus di "+utente);
                    request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione nella rimozione del plus al cliente");
                }
            }
        }
        else{ //se si vuole aggiungere un plus
            if(ruolo==0){ //se lo fa l'utente
                try{
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Plus=1 WHERE Username='"+session.getAttribute("username")+"'");
                    session.setAttribute("plus", 1);
                    request.setAttribute("messageAggiungiPlus", "Grazie per esserti abbonato a plus");
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione per l'aggiunta del plus al cliente");
                }
            }
            else{ //se lo fa l'admin
                try{
                    if(utente==null)
                    {
                        request.setAttribute("messageInserisci", "Assicurati di aver inserito un valore nel campo selezionato");
                        request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                    }
                    DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Plus=1 WHERE Username='"+utente+"'");
                    request.setAttribute("messageAggiungiPlusAdmin", "Hai inserito correttamente il plus di "+utente);
                    request.getRequestDispatcher("/GestisciTransponder.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione per l'aggiunta del plus al cliente");
                }
            }
        }
    }
}
