package ClientTelepass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
