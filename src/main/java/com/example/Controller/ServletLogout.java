package com.example.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
/**Questa servlet viene invocata per effettuare il logout*/
@WebServlet("/logout")
public class ServletLogout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();//rendiamo non più valida la sessione
        //indirizziamo l'utente torniamo alla homepage principale del sito
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
