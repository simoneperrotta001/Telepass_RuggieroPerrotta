package com.example.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletPrivilegi", value = "/ServletPrivilegi")
public class ServletPrivilegi extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //prende una sessione già esistente
        HttpSession session = request.getSession(false);
        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            request.getRequestDispatcher("Accedi.jsp").forward(request, response);
        }
        /*else{
            if(session.getAttribute())
        }*/
    }
}
