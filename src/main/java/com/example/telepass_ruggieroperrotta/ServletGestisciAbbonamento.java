package com.example.telepass_ruggieroperrotta;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/gestisciAbbonamento")
public class ServletGestisciAbbonamento extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String azione=request.getParameter("gestisci");
        String utente=request.getParameter("username");
        Connection connection=null;
        Statement stm=null;
        ResultSet rs = null;
        if(azione.equals("0")){ //se si vuole disdire l'abbomento
            if(utente == null) //se lo fa l'utente
            {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
                    HttpSession session = request.getSession(false);
                    connection.setAutoCommit(false);
                    stm= connection.createStatement();
                    stm.executeUpdate("UPDATE CLIENTE SET TransponderAttivo=0, Plus=0 WHERE Username='"+session.getAttribute("username")+"'");
                    connection.commit();
                    session.setAttribute("attivo", 0);
                    session.setAttribute("plus", 0);
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione");
                }
                finally {
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (Exception e) { System.out.println("stm non chiuso");}
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Exception e) { System.out.println("connection non chiuso");}
                    }
                }
            }
            else{ //se lo fa l'admin
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
                    connection.setAutoCommit(false);
                    stm= connection.createStatement();
                    stm.executeUpdate("UPDATE CLIENTE SET TransponderAttivo=0, Plus=0 WHERE Username='"+utente+"'");
                    connection.commit();
                    request.getRequestDispatcher("/protected_area_admin.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione");
                }
                finally {
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (Exception e) { System.out.println("stm non chiuso");}
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Exception e) { System.out.println("connection non chiuso");}
                    }
                }
            }

        }
        else if(azione.equals("1")){ //se si vuole aggiungere un abbonamento
            if(utente == null){ //se lo fa l'utente
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
                    HttpSession session = request.getSession(false);
                    connection.setAutoCommit(false);
                    stm= connection.createStatement();
                    stm.executeUpdate("UPDATE CLIENTE SET TransponderAttivo=1 WHERE Username='"+session.getAttribute("username")+"'");
                    connection.commit();
                    session.setAttribute("attivo", 1);
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione");
                }
                finally {
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (Exception e) { System.out.println("stm non chiuso");}
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Exception e) { System.out.println("connection non chiuso");}
                    }
                }
            }
            else{ //se lo fa l'admin
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
                    connection.setAutoCommit(false);
                    stm= connection.createStatement();
                    stm.executeUpdate("UPDATE CLIENTE SET TransponderAttivo=1 WHERE Username='"+utente+"'");
                    connection.commit();
                    request.getRequestDispatcher("/protected_area_admin.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione");
                }
                finally {
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (Exception e) { System.out.println("stm non chiuso");}
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Exception e) { System.out.println("connection non chiuso");}
                    }
                }
            }
        } else if (azione.equals("2")) { //se si vuole rimuovere il plus
            if(utente == null) //se lo fa l'utente
            {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
                    HttpSession session = request.getSession(false);
                    connection.setAutoCommit(false);
                    stm= connection.createStatement();
                    stm.executeUpdate("UPDATE CLIENTE SET Plus=0 WHERE Username='"+session.getAttribute("username")+"'");
                    connection.commit();
                    session.setAttribute("plus", 0);
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione");
                }
                finally {
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (Exception e) { System.out.println("stm non chiuso");}
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Exception e) { System.out.println("connection non chiuso");}
                    }
                }
            }
            else{ //se lo fa l'admin
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
                    connection.setAutoCommit(false);
                    stm= connection.createStatement();
                    stm.executeUpdate("UPDATE CLIENTE SET Plus=0 WHERE Username='"+utente+"'");
                    connection.commit();
                    request.getRequestDispatcher("/protected_area_admin.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione");
                }
                finally {
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (Exception e) { System.out.println("stm non chiuso");}
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Exception e) { System.out.println("connection non chiuso");}
                    }
                }
            }

        }
        else{ //se si vuole aggiungere un plus
            if(utente == null){ //se lo fa l'utente
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
                    HttpSession session = request.getSession(false);
                    connection.setAutoCommit(false);
                    stm= connection.createStatement();
                    stm.executeUpdate("UPDATE CLIENTE SET Plus=1 WHERE Username='"+session.getAttribute("username")+"'");
                    connection.commit();
                    session.setAttribute("plus", 1);
                    request.getRequestDispatcher("/protected_area_utente.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione");
                }
                finally {
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (Exception e) { System.out.println("stm non chiuso");}
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Exception e) { System.out.println("connection non chiuso");}
                    }
                }
            }
            else{ //se lo fa l'admin
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
                    connection.setAutoCommit(false);
                    stm= connection.createStatement();
                    stm.executeUpdate("UPDATE CLIENTE SET Plus=1 WHERE Username='"+utente+"'");
                    connection.commit();
                    request.getRequestDispatcher("/protected_area_admin.jsp").forward(request, response);
                }
                catch (Exception e) {
                    System.out.println("errore nella connessione");
                }
                finally {
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (Exception e) { System.out.println("stm non chiuso");}
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Exception e) { System.out.println("connection non chiuso");}
                    }
                }
            }

        }
    }
}
