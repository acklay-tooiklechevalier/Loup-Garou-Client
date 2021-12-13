package iut.acklaytooiklechevalier.ihm;

import iut.acklaytooiklechevalier.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Connection extends JPanel implements ActionListener {
	private final JTextField txtIP;
	private final JTextField txtPort;
	private final JTextField txtPseudo;
	private final JButton btnPlay;

	public Connection() {
		setLayout(null);

		JLabel lblIP = new JLabel("Serveur IP :", JLabel.RIGHT);
		txtIP = new JTextField("localhost", 20);

		JLabel lblPort = new JLabel("Serveur Port :", JLabel.RIGHT);
		txtPort = new JTextField("6000", 4);

        JLabel lblPseudo = new JLabel("Choisir un Pseudo :", JLabel.RIGHT);
		txtPseudo = new JTextField("Guest" + (int)(Math.random()*100000000), 20);

        btnPlay = new JButton("Play");
		btnPlay.addActionListener(this);

        lblIP.setBounds(10, 10, 130, 20);
        txtIP.setBounds(lblIP.getWidth() + 10, lblIP.getY(), 120, 20);

        lblPort.setBounds(lblIP.getX(), lblIP.getY() + lblIP.getHeight() + 5, lblIP.getWidth(), lblIP.getHeight());
        txtPort.setBounds(lblPort.getWidth() + 10, lblPort.getY(), txtIP.getWidth(), txtIP.getHeight());

        lblPseudo.setBounds(lblPort.getX(), lblPort.getY() + lblPort.getHeight() + 5, lblIP.getWidth(), lblIP.getHeight());
        txtPseudo.setBounds(lblPseudo.getWidth() + 10, lblPseudo.getY(), txtIP.getWidth(), txtIP.getHeight());


        btnPlay.setBounds(lblPseudo.getX(), lblPseudo.getY() + lblPseudo.getHeight() + 5, lblIP.getWidth() + txtIP.getWidth(), lblIP.getHeight() + txtIP.getHeight());
		
		add(lblIP);
		add(txtIP);

		add(lblPort);
		add(txtPort);

        add(lblPseudo);
        add(txtPseudo);

		add(btnPlay);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPlay) {
			if (txtIP    .getText() != null || !txtIP    .getText().isEmpty() &&
			    txtPort  .getText() != null || !txtPort  .getText().isEmpty() &&
			    txtPseudo.getText() != null || !txtPseudo.getText().isEmpty()) {
					if(!txtPort.getText().matches("\\d+")) {
						JOptionPane.showMessageDialog(this, "Le port doit être composé excusivement de nombres", " Erreur Port Invalide ", JOptionPane.ERROR_MESSAGE);
					} else if(txtPseudo.getText().length()>16) {
						JOptionPane.showMessageDialog(this, "Le pseudo ne doit pas être composé de plus de 20 caractère", " Erreur Pseudo Invalide ", JOptionPane.ERROR_MESSAGE);
					} else if (Main.getInstance().initServeur(txtIP.getText(), Integer.parseInt(txtPort.getText()), txtPseudo.getText())) {
							System.out.println("connection réussi");
							Main.getInstance().getMenu().runGames();
					} else {
						JOptionPane.showMessageDialog(this, "Le serveur de Jeu est Introuvable\n Veuillez vérifer les information fourni", " Erreur Connection Impossible ", JOptionPane.ERROR_MESSAGE);
					}
				}
		}
	}
}
