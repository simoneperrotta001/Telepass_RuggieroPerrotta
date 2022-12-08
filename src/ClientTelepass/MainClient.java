package ClientTelepass;
import javax.swing.*;
//pattern mvc (model controller view) è un pattern per la suddivisione del codice in blocchi con funzionalità distinte
//model: si occupa della gestione dati
//view: visualizza i dati contenuti nel model e si occupa dell'interazione con gli utenti
//controller: riceve comandi dall'utente, talvolta dalla view, e modifica lo stato degli altri componenti
//abbiamo quindi un client che interagisce con il controller che si occupa di aggiornare le strutture dati sul model,
//che potranno anche collocarsi su un db e poi i dati vengono visualizzati sulla view che mostrerà i dati al client
public class MainClient {
    public static void main(String[] args) {
        JFrame f = new JFrame();//creazione di un nuovo frame
        f.setSize(900,500);//settaggio dimensioni del frame
        f.setLayout(null);
        JLabel m = new JLabel("ciao prova");
        m.setBounds(50, 50, 200, 30);
        JTextField scelta = new JTextField();
        scelta.setBounds(50, 100, 200, 30);
        f.add(m);
        f.add(scelta);
        f.setVisible(true);
    }
}