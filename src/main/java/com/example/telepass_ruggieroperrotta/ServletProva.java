package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletProva", value = "/ServletProva")
public class ServletProva extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseTelepass databaseTelepass = DatabaseTelepass.getInstance();
        DatabaseTelepass.getInstance().doQuery("SELECT Distanza FROM Dista WHERE (NomeCasello1='Milano' AND NomeCasello2='Napoli') OR (NomeCasello1='Napoli' AND NomeCasello2='Milano');", "Distanza");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
