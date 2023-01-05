package ModelTelepass;

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
import java.util.List;

public class DatabaseTelepass {
    private static DatabaseTelepass instance;
    public static Connection connection =null;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private DatabaseTelepass(){

    }
    public static DatabaseTelepass getInstance(){
        if(instance == null)
            instance = new DatabaseTelepass();

        return instance;
    }
    public void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
        }
        catch (Exception e){
            System.out.println("errore nell'apertura della connessione");
        }
    }
    public void createStatement(){
        try {
            statement = connection.createStatement();
        }
        catch (Exception e){
            System.out.println("errore nell'apertura dello statemet");
        }
    }
    /*non dovrebbe servire più
    public ArrayList getToolBooths(){
        ArrayList<String> toolBooths = new ArrayList<>();

        createConnection();
        createStatement();
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
            closeStatement();
            closeConnection();
        }
        return toolBooths;
    }*/
    //processo di creazione della connessione effettuato nel punto di accesso globale getInstance così da farlo una volta sola
    public List doLogin(String sql) throws SQLException {
        List parametri = new ArrayList();
        createConnection();
        createStatement();
        try{
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                parametri.add(resultSet.getInt("Amministratore"));
                parametri.add(resultSet.getInt("CodiceTransponder"));
                parametri.add(resultSet.getInt("TransponderAttivo"));
                parametri.add(resultSet.getInt("Plus"));
            }
            else{
                System.out.println("quest'utente non esiste");
            }
        }
        catch (Exception e){
            System.out.println("errore nell'esecuzione della query");
        }
        finally {
            try{
                if(resultSet!=null)
                    resultSet.close();
            }
            catch (Exception e){
                System.out.println("errore nella chiusura del risultato della query");
            }

            closeStatement();
            closeConnection();

            return parametri;
        }
    }
    public void doInsertUtenti(String nomeCliente, String cognomeCliente, String nascitaCliente, String codiceContoCorrente, int plus, String password, int codicetransponder, String username, int transponderattivo) throws SQLException {
        createConnection();
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO Cliente(NomeCliente, CognomeCliente, NascitaCliente, CodiceContoCorrente, Plus, Password, CodiceTransponder, Username, TransponderAttivo, Amministratore) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,0)");
            preparedStatement.setString(1,nomeCliente);
            preparedStatement.setString(2,cognomeCliente);
            preparedStatement.setDate(3, Date.valueOf(nascitaCliente));
            if(codiceContoCorrente.length()==12)
                preparedStatement.setString(4,codiceContoCorrente);
            else{
                System.out.println("Le cifre che descrivono un codice di conto corrente sono 12");
                throw new Exception();
            }
            preparedStatement.setInt(5, plus);
            preparedStatement.setString(6, password);
            preparedStatement.setInt(7, codicetransponder);
            preparedStatement.setString(8, username);
            preparedStatement.setInt(9, transponderattivo);
            preparedStatement.execute();
        }
        catch (Exception e){
            System.out.println("errore nell'inserimento dell' utenti");
        }
        finally {
            preparedStatement.close();
            closeConnection();
        }
    }
    public void doInsertVeicoli(String targa, int cod, String classe) throws SQLException {
        createConnection();
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO Veicolo() VALUES (?, ?, ?)");
            if(targa.length()==7)
                preparedStatement.setString(1,targa);
            preparedStatement.setInt(2,cod);
            preparedStatement.setString(3,classe);

            preparedStatement.execute();
        }
        catch (Exception e){
            System.out.println("errore nell'inserimento del veicolo");
        }
        finally {
            preparedStatement.close();
            closeConnection();
        }
    }
    public List getSingoloValore(String sql, String campo){
        List risultati = new ArrayList();
        createConnection();
        createStatement();
        try{
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                risultati.add(resultSet.getString(campo));
            }
            else{
                System.out.println("non hai veicolo associati");
            }
        }
        catch (Exception e){
            System.out.println("errore nell'esecuzione della query");
        }
        finally {
            try{
                if(resultSet!=null)
                    resultSet.close();
            }
            catch (Exception e){
                System.out.println("errore nella chiusura del risultato della query");
            }
            closeStatement();
            closeConnection();

            return risultati;
        }
    }
    public void doUpdate(String sql){
        createConnection();
        createStatement();
        try{
            resultSet = statement.executeQuery(sql);
        }
        catch (Exception e){
            System.out.println("errore nell'esecuzione della query");
        }
        finally {
            closeStatement();
            closeConnection();
        }
    }
    public void closeConnection(){
        try{
            if(connection!=null)
                connection.close();
        }
        catch(Exception e){
            System.out.println("errore nella chiusura della connessione");
        }
    }
    public void closeStatement(){
        try{
            if(statement!=null)
                statement.close();
        }
        catch (Exception e){
            System.out.println("errore nella chiusura dello statement");
        }
    }



}

