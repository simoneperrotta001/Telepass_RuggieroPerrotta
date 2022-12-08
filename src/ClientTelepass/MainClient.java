package ClientTelepass;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//pattern mvc (model controller view) è un pattern per la suddivisione del codice in blocchi con funzionalità distinte
//model: si occupa della gestione dati
//view: visualizza i dati contenuti nel model e si occupa dell'interazione con gli utenti
//controller: riceve comandi dall'utente, talvolta dalla view, e modifica lo stato degli altri componenti
//abbiamo quindi un client che interagisce con il controller che si occupa di aggiornare le strutture dati sul model,
//che potranno anche collocarsi su un db e poi i dati vengono visualizzati sulla view che mostrerà i dati al client
public class MainClient {
    //questo metodo crea e mostra l'interfaccia grafica, con i vari campi presenti al suo interno
    private static void createAndShowGui() {
        JFrame frame = new JFrame("Dialog Example");//crea un nuovo frame
        MainPanel mainPanel = new MainPanel(frame);//creiamo un nuovo pannello JPanel

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//settiamo la nostra volontà di voler terminare il programma sulla chiusura dell'interfaccia
        frame.getContentPane().add(mainPanel);//aggiunge il pannello all'interfaccia
        frame.setSize(500,500);//impacchetta tutto senza dover specificare con setbounds la posizione degli elementi
        //frame.setLayout(null); //questo è il problema per il posizionamento dei componenti nel pannello
        frame.setLocationByPlatform(true);//lasciamo decidere al nativo sistema di finestre dove far apparire la finestra
        frame.setVisible(true);//lo rendiamo visibile
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();//invochiamo e mostriamo l'interfaccia grafica
            }
        });
    }
}

/*class InputPanel extends JPanel {
    private JTextField inputField = new JTextField(10);
    private JButton confirmBtn = new JButton("Confirm");
    private JButton cancelBtn = new JButton("Cancel");
    private boolean confirmed = false;

    public InputPanel() {
        add(inputField);
        add(confirmBtn);
        add(cancelBtn);

        confirmBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                confirmed = true;
                Window win = SwingUtilities.getWindowAncestor(InputPanel.this);
                win.setVisible(false);
            }
        });
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                confirmed = false;
                Window win = SwingUtilities.getWindowAncestor(InputPanel.this);
                win.setVisible(false);
            }
        });
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getInputFieldText() {
        return inputField.getText();
    }
}
*/