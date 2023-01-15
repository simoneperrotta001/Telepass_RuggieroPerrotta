package com.example.Controller.ServletUtente;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
/*Questa servlet viene invocata al fine di cambiare username a un utente.
Ricordiamo che gli utenti possono cambiare username solo per il loro account (non può l'admin cambiare username).
Questa servlet si occupa di controllare se il cambio username è possibile, e se lo è, di inviare un
messaggio di successo alla jsp chiamante per l'avvenuta modifica allo username.*/
@WebServlet("/cambiausername")
public class ServletCambiaUsername extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String nuovo=request.getParameter("username");//ci prendiamo il nuovo username scelto dall'utente dalla request
        HttpSession session = request.getSession(false);//non creiamo una nuova sessione ma utilizziamo quella già esistente

        try{
            //controlliamo se lo username nuovo che l'utente ha inserito esiste già nel db
            String Username = DatabaseTelepass.getInstance().getSingoloValore("SELECT Username FROM CLIENTE WHERE Username='"+nuovo+"'","Username");
            //se lo username risulta essere già presente
            if(Username != null){
                //mandiamo un messaggio di errore alla jsp chiamante
                request.setAttribute("messageUsernameUsato", "L'username scelto è gia utilizzato");
                //rimandiamo l'utente alla pagina cambia username per farlo re-inserire
                request.getRequestDispatcher("/cambiausername.jsp").forward(request, response);
            }
            //se non risulta essere utilizzato
            else{
                //eseguiamo l'update sul db modificando l'utente con il nuovo username
                DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Username='"+nuovo+"'WHERE Username='"+session.getAttribute("username")+"'");
                //settiamo come variabile di sessione il nuovo username
                session.setAttribute("username", nuovo);
                //mandiamo un messaggio di successo alla jsp chiamante
                request.setAttribute("messageUsername", "Il tuo username è stato modificato correttamente");
                //rimandiamo l'utente alla sua area protetta
                request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
            }
        }
        //se ci sono stati errori durante le query fatte al db
        catch (Exception e) {
            //mandiamo in output un messaggio di errore
            System.out.println("errore nella connessione");
        }
    }
}