package com.example.telepass_ruggieroperrotta;

/*
    DatabaseTelepass databaseTelepass = DatabaseTelepass.getInstance();
    System.out.println("Query 1: ");
    DatabaseTelepass.getInstance().doQuery("SELECT Distanza FROM Dista WHERE (NomeCasello1='Milano' AND NomeCasello2='Napoli') OR (NomeCasello1='Napoli' AND NomeCasello2='Milano');", "Distanza");
    System.out.println("Query 2: ");
    DatabaseTelepass.getInstance().doQuery("SELECT Distanza FROM Dista WHERE NomeCasello1='Napoli' OR NomeCasello2='Napoli';", "Distanza");
    DatabaseTelepass.getInstance().doInsertUtenti("Mattia","Autiero","2022-10-22","000000000003",0,"ciao");
*/

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;//incorporati tutti in java.sql.*
import java.util.ArrayList;

public class DatabaseTelepass {
    private static DatabaseTelepass instance;
    public static Connection connection =null;
    private static Statement statement;
    private ResultSet resultSet;
    private DatabaseTelepass(){

    }
    public static DatabaseTelepass getInstance(){
        if(instance == null)
            instance = new DatabaseTelepass();

        return instance;
    }
    public ArrayList getToolBooths(){
        ArrayList<String> toolBooths = new ArrayList<>();

        createConnection();
        try{
            String sql="SELECT NomeCasello FROM Casello;";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                toolBooths.add(resultSet.getString("NomeCasello"));
            }
        }
        catch(Exception e){
            System.out.println("errore nel caricamento dei caselli.");
        }
        finally {
            closeConnection();
        }
        return toolBooths;
    }
    //processo di creazione della connessione effettuato nel punto di accesso globale getInstance così da farlo una volta sola
    public void createConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");

            System.out.println("connection2: "+connection);
            statement = connection.createStatement();
        }
        catch (Exception e){
            System.out.println("errore nell'apertura della connessione");
        }
        finally {
            System.out.println("connection3: "+connection);
        }
    }
    public void doQuery(String sql,String Column){
        createConnection();
        try{
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                System.out.println(resultSet.getFloat(Column));
            }
        }
        catch (Exception e){
            System.out.println("errore nell'esecuzione della query.");
        }
        finally {
            closeConnection();
        }
    }
    public void doInsertUtenti(String nomeCliente, String cognomeCliente, String nascitaCliente, String codiceContoCorrente, int plus, String password, int codicetransponder, String username, int transponderattivo) throws SQLException {
        createConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Cliente(NomeCliente, CognomeCliente, NascitaCliente, CodiceContoCorrente, Plus, Password, CodiceTransponder, Username, TransponderAttivo, Amministratore) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,0)");
            stmt.setString(1,nomeCliente);
            stmt.setString(2,cognomeCliente);
            stmt.setDate(3, Date.valueOf(nascitaCliente));
            if(codiceContoCorrente.length()==12)
                stmt.setString(4,codiceContoCorrente);
            else{
                System.out.println("Le cifre che descrivono un codice di conto corrente sono 12.");
                throw new Exception();
            }
            stmt.setInt(5, plus);
            stmt.setString(6, password);
            stmt.setInt(7, codicetransponder);
            stmt.setString(8, username);
            stmt.setInt(9, transponderattivo);
            stmt.execute();
        }
        catch (Exception e){
            System.out.println("errore nell'inserimento degli utenti");
        }
        finally {
            closeConnection();
        }
    }

    public  boolean CheckUtenti(String username, String password) {
        boolean status =false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        createConnection();
        try{
            stmt = connection.prepareStatement("SELECT * FROM Cliente WHERE Username=? AND Password=?");
            stmt.setString(1,username);
            stmt.setString(2,password);
            rs = stmt.executeQuery();
            status = rs.next();
        }
        catch (Exception e){
            System.out.println("errore nella ricerca dell utente");
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                closeConnection();
            }
        }

        return status;
    }
    public void closeConnection(){
        try{
            if(connection!=null)
                connection.close();
        }
        catch(Exception e){
            System.out.println("errore nella chiusura della connessione.");
        }
    }
}

