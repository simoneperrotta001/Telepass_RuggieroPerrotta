package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import java.io.IOException;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        DatabaseTelepass databaseTelepass = DatabaseTelepass.getInstance();
        DatabaseTelepass.getInstance().createConnection();
        DatabaseTelepass.getInstance().doQuery("SELECT * FROM CLIENTE WHERE NomeCasello1='' AND NomeCasello2='';")

    }
}
