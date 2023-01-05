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
import java.time.LocalDate;
import java.time.Period;
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
                parametri.add(null);
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
    public void doInsertUtenti(String nomeCliente, String cognomeCliente, Date nascitaCliente, String codiceContoCorrente, int plus, String username, String password, int transponderattivo) throws SQLException {
        createConnection();
        try{
            System.out.println(nascitaCliente);
            preparedStatement = connection.prepareStatement("INSERT INTO Cliente(NomeCliente, CognomeCliente, NascitaCliente, CodiceContoCorrente, Plus, Password, Username, TransponderAttivo, Amministratore) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0)");
            preparedStatement.setString(1,nomeCliente);
            preparedStatement.setString(2,cognomeCliente);
            preparedStatement.setDate(3, nascitaCliente);
            preparedStatement.setString(4,codiceContoCorrente);
            preparedStatement.setInt(5, plus);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, username);
            preparedStatement.setInt(8, transponderattivo);
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
    public boolean checkData(Date nascita) {
        LocalDate now = LocalDate.now();
        Period diff = Period.between(nascita.toLocalDate(), now);
        if(diff.getYears()>=18)
        {
            return true;
        }
        else
            return false;
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
    public void doInsertEntra(String targa, String casello1, Timestamp oggi) throws SQLException {
        createConnection();
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO entra() VALUES (?, ?, ?)");
            preparedStatement.setString(1,targa);
            preparedStatement.setString(2,casello1);
            preparedStatement.setTimestamp(3,oggi);

            preparedStatement.execute();
        }
        catch (Exception e){
            System.out.println("errore nell'entrata del veicolo nel casello");
        }
        finally {
            preparedStatement.close();
            closeConnection();
        }
    }
    public void doInsertEsci(String targa, String casello2, Timestamp oggi, double tariffa) throws SQLException {
        createConnection();
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO esce() VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1,targa);
            preparedStatement.setString(2,casello2);
            preparedStatement.setTimestamp(3,oggi);
            preparedStatement.setDouble(4,tariffa);

            preparedStatement.execute();
        }
        catch (Exception e){
            System.out.println("errore nell'uscita del veicolo dal casello");
        }
        finally {
            preparedStatement.close();
            closeConnection();
        }
    }
    public void doUpdate(String sql){
        createConnection();
        createStatement();
        try{
            statement.executeUpdate(sql);
        }
        catch (Exception e){
            System.out.println("errore nell'esecuzione della query");
        }
        finally {
            closeStatement();
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
                risultati.add(null);
                System.out.println("non ci sono risultati");
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
    public List getDoppioValore(String sql, String campo1, String campo2) {
        List risultati = new ArrayList();
        createConnection();
        createStatement();
        try{
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                risultati.add(resultSet.getString(campo1));
                risultati.add(resultSet.getString(campo2));
            }
            else{
                risultati.add(null);
                System.out.println("non ci sono risultati");
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
    public List getUtente(String sql) {
        List risultati = new ArrayList();
        createConnection();
        createStatement();
        try{
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                risultati.add(resultSet.getString("NomeCliente"));
                risultati.add(resultSet.getString("CognomeCliente"));
                risultati.add(resultSet.getInt("TransponderAttivo"));
                risultati.add(resultSet.getInt("Plus"));
                risultati.add(resultSet.getInt("CodiceTransponder"));
            }
            else{
                risultati.add(null);
                System.out.println("non ci sono risultati");
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