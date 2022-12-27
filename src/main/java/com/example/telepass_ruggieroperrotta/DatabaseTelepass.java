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
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;*///incorporati tutti in java.sql.*
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DatabaseTelepass {
    private static DatabaseTelepass instance;
    private static Connection connection;
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
        ArrayList<String> toolBooths = new ArrayList<String>();
        String sql = null;

        createConnection();
        try{
            sql="SELECT NomeCasello FROM Casello;";
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT", "ROOT");
            statement = connection.createStatement();
        }
        catch (Exception e){
            System.out.println("errore nell'apertura della connessione");
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
    public void doInsertUtenti(String nomeCliente, String cognomeCliente, String nascitaCliente, String codiceContoCorrente, int plus, String password) throws SQLException {
        createConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Cliente(NomeCliente, CognomeCliente, NascitaCliente, CodiceContoCorrente, Plus, Password) VALUES (?, ?, ?, ?, ?, ?)");
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
            stmt.execute();
        }
        catch (Exception e){
            System.out.println("errore nell'inserimento degli utenti");
        }
        finally {
            closeConnection();
        }
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

