
package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.*;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        DatabaseTelepass databaseTelepass = DatabaseTelepass.getInstance();
        System.out.println("-------\nusername: "+ username +" passw: "+password );

        if(databaseTelepass.CheckUtenti(username, password)){
            request.getSession().setAttribute("username",username);
            //if role == user
            response.sendRedirect("protected_area_utenti");
            //else
            //response.sendRedirect("protected_area_admin");
        }
        else{
            request.setAttribute("errorMessage", "Invalid Credentials!");
            request.getRequestDispatcher("Accedi.jsp").forward(
                    request, response);
        }

    }
}
