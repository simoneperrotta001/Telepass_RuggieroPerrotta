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

class MainPanel extends JPanel {
    //private InputPanel inputPanel = new InputPanel();
    private JLabel tipologiaVeicoloL = new JLabel("Inserisci classe del tuo veicolo (scelte possibili A,B,1,2,3): ");
    private JTextField tipologiaVeicolo = new JTextField(10);
    private JLabel nomeClienteL = new JLabel("Inserisci il tuo nome: ");
    private JTextField nomeCliente =  new JTextField(20);
    private JLabel cognomeClienteL = new JLabel("Inserisci il tuo cognome");
    private JTextField cognomeCliente = new JTextField(20);
    private JButton button = new JButton("Login");

    public MainPanel(final JFrame mainJFrame) {
        //tipologiaVeicoloL.setLayout(new BoxLayout(tipologiaVeicoloL, BoxLayout.PAGE_AXIS));
        tipologiaVeicoloL.setBounds(0,0,100,50);
        tipologiaVeicolo.setBounds(100,0,100,50);
        nomeClienteL.setBounds(0,0,100,50);
        nomeCliente.setBounds(100,250,100,50);

        add(tipologiaVeicoloL);
        add(tipologiaVeicolo);
        add(nomeClienteL);
        add(nomeCliente);
        add(cognomeClienteL);
        add(cognomeCliente);

        button.setBounds(100, 300, 90, 25);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        //aggiungiamo al bottone un evento, ovvero sull'azione del click del Login verrà lanciato questo evento che effettuerà dei controlli sui campi
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //se non è uno dei caratteri da noi stabiliti come accettabili, allora lancia un dialog di errore
                if(!tipologiaVeicolo.getText().equals("A") && !tipologiaVeicolo.getText().equals("a") && !tipologiaVeicolo.getText().equals("B") && !tipologiaVeicolo.getText().equals("b") && !tipologiaVeicolo.getText().equals("1") && !tipologiaVeicolo.getText().equals("2") && !tipologiaVeicolo.getText().equals("3")){
                    System.out.println("Inserimento sbagliato.");
                    JOptionPane.showMessageDialog(mainJFrame, "Inserimento sbagliato.");
                    //pulire campi e dare errore nel pannello
                }
                //se l'input è accettato allora proseguiamo con l'inserimento dei dati sul db
                else{
                    //connessione al module che inserisce dati sul db
                }
                //System.out.println(responseField.getText());
            }
        });
        add(button);
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