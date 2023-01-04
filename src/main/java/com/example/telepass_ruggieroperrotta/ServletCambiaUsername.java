package com.example.telepass_ruggieroperrotta;

import ModelTelepass.DatabaseTelepass;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@WebServlet("/cambiausername")
public class ServletCambiaUsername extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nuovo=request.getParameter("username");
        HttpSession session = request.getSession(false);

        try{
            List Username = DatabaseTelepass.getInstance().getSingoloValore("SELECT Username FROM CLIENTE WHERE Username='\"+nuovo+\"'","Username");
            if(Username.get(0)!= null){
                request.setAttribute("messageUsernameUsato", "L'username scelto è gia utilizzato");
                request.getRequestDispatcher("/cambiausername.jsp").forward(request, response);
            }
            else{
                DatabaseTelepass.getInstance().doUpdate("UPDATE CLIENTE SET Username='"+nuovo+"'WHERE Username='"+session.getAttribute("username")+"'");
                session.setAttribute("username", nuovo);
                request.setAttribute("messageUsername", "Il tuo username è stato modificato correttamente");
                request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
            }
        }
        catch (Exception e) {
            System.out.println("errore nella connessione");
        }
    }
}
