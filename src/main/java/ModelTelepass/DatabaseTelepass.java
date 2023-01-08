package ModelTelepass;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
/* Questa classe fa parte della sezione Model del pattern MVC. Difatti questa ci permette di comunicare con la base di dati
esistente e di inserire, modificare, leggere e eliminare valori. Questa classe è implementata tramite il pattern Singleton
così dà avere un'unica istanza del db nel nostro programma backend senza dichiararne di più.
Ogni metodo di questa classe prevedrà la gestione delle eccezzioni, come eccezzioni nell'apertura delle connessioni, degli
statement. Tutte queste eccezzioni saranno gestite con dei catch, che stamperanno qualcosa in output per notificare
allo sviluppatore quali e dove sono gli errori qualora ci dovessero essere*/
public class DatabaseTelepass {
    private static DatabaseTelepass instance;//unica istanza del db
    public static Connection connection =null;//connessione da allocare ogni volta con il db
    private static Statement statement;//statement da allocare ogni volta per eseguire operazioni sul db
    private static PreparedStatement preparedStatement;//prepared statement da allocare ogni volta che serve per eseguire operazioni sul db
    private ResultSet resultSet;//conterrà i risultati delle query fatte dai vari metodi

    //come da definizione delle classi Singleton il costruttore risulta essere privato
    private DatabaseTelepass(){}

    /*punto di accesso globale all'istanza allocata per poter fare qualsiasi operazione sul db
    Se l'istanza non è stata creata, la crea, altrimenti ritorna semplicemente l'istanza*/
    public static DatabaseTelepass getInstance(){
        if(instance == null)
            instance = new DatabaseTelepass();

        return instance;
    }
    /*Questo metodo si occupa di creare la connessione con il db mysql Telepass con l'utilizzo dell'utente ROOT.
    Ricordiamo che quest'utente va creato in separata sede dal resto della creazione del db*/
    public void createConnection(){
        //proviamo ad aprire la connessione
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/telepass", "ROOT","ROOT");
        }//nel caso in cui non si sia aperta lanciamo l'eccezzione con un messaggio in output
        catch (Exception e){
            System.out.println("errore nell'apertura della connessione");
        }
    }
    //Questo metodo si occupa della creazione dello statement
    public void createStatement(){
        //proviamo ad aprire lo statement
        try {
            statement = connection.createStatement();
        }//nel caso in cui non si sia aperto lanciamo l'eccezzione con un messaggio in output
        catch (Exception e){
            System.out.println("errore nell'apertura dello statement");
        }
    }
    /*Questo metodo si occupa di fare il login (unico sia per admin che per utenti) grazie alla query passata come
    parametro stringa.
    Restiutuisce una lista di parametri che verranno usati come parametri di sessione per l'utente loggato*/
    public List doLogin(String sql){
        List parametri = new ArrayList();//lista che conterrà i parametri da restituire
        createConnection();//crea la connessione
        createStatement();//crea lo statement
        try{
            resultSet = statement.executeQuery(sql);//esegue la query passata come parametro
            //se ci sono risultati dall'esecuzione della query
            if(resultSet.next()) {
                parametri.add(resultSet.getInt("Amministratore"));//setta il ruolo come int
                parametri.add(resultSet.getInt("CodiceTransponder"));//setta il codice transponder
                parametri.add(resultSet.getInt("TransponderAttivo"));//setta se il transponder è attivo o meno con un int
                parametri.add(resultSet.getInt("Plus"));//setta se il plus è attivo o meno con un int
            }
            //se non ci dovessero essere utenti corrispondenti (dati inseriti nel form errati)
            else{
                //inseriamo come primo valore nella lista null così da inviare un "errore" alla servlet che chiamerà questo metodo
                parametri.add(null);
                System.out.println("quest'utente non esiste");
            }
        }//nel caso ci fosse stato un errore nell'esecuzione della query
        catch (Exception e){
            System.out.println("errore nell'esecuzione della query");
        }
        //a prescindere se ci sono stati errori o meno
        finally {
            try{
                //se ci sono stati risultati all'interno del resultSet, la chiudiamo
                if(resultSet!=null)
                    resultSet.close();
            }//se ci sono errori nella chiusura del resultSet
            catch (Exception e){
                //diamo un messaggio in output di errore
                System.out.println("errore nella chiusura del risultato della query");
            }
            closeStatement();//chiusura dello statement
            closeConnection();//chiusura della connessione

            return parametri;
        }
    }
    /*Questo metodo si occupa dell'inserimento degli utenti nel db.
    Vengono passati come parametri tutti i campi da inserire nella tabella del db "clienti" (ovvero gli utenti)*/
    public void doInsertUtenti(String nomeCliente, String cognomeCliente, Date nascitaCliente, String codiceContoCorrente, int plus, String username, String password, int transponderattivo) throws SQLException {
        createConnection();//crea la connessione
        try{
            //prepara il preparedStatement per l'inserimento di tutti i campi passati come parametri
            preparedStatement = connection.prepareStatement("INSERT INTO Cliente(NomeCliente, CognomeCliente, NascitaCliente, CodiceContoCorrente, Plus, Password, Username, TransponderAttivo, Amministratore) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0)");
            preparedStatement.setString(1,nomeCliente);//setta il nomeCliente
            preparedStatement.setString(2,cognomeCliente);//setta il cognomeCliente
            preparedStatement.setDate(3, nascitaCliente);//setta la nascitaCliente
            preparedStatement.setString(4,codiceContoCorrente);//setta il codiceContoCorrente
            preparedStatement.setInt(5, plus);//setta il plus
            preparedStatement.setString(6, password);//setta la password
            preparedStatement.setString(7, username);//setta lo username
            preparedStatement.setInt(8, transponderattivo);//setta se il transponder è attivo o meno
            preparedStatement.execute();//esegue lo statement
        }
        //se c'è stato un errore nella creazione del preparedStatement
        catch (Exception e){
            //lanciamo un messaggio di errore in output
            System.out.println("errore nell'inserimento dell' utenti");
        }
        //a prescindere se ci sono stati errori o meno
        finally {
            preparedStatement.close();//chiudiamo il preparedStatement
            closeConnection();//chiusura della connessione
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
    //Questo metodo si occupa della chiusura dello statement nel caso in cui fosse aperto
    public void closeStatement(){
        try{
            //se lo statement è aperto
            if(statement!=null)
                statement.close();//lo chiude
        }//se ci fosse un errore nella chiusura dello statement
        catch (Exception e){
            //diamo in output un messaggio di errore
            System.out.println("errore nella chiusura dello statement");
        }
    }
    //Questo metodo si occupa della chiusura della connessione nel caso in cui fosse aperta
    public void closeConnection(){
        try{
            //se la connessione è aperta
            if(connection!=null)
                connection.close();//la chiude
        }//se ci fosse un errore nella chiusura della connessione
        catch(Exception e){
            //diamo in output un messaggio di errore
            System.out.println("errore nella chiusura della connessione");
        }
    }
}