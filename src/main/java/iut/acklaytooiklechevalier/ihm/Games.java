package iut.acklaytooiklechevalier.ihm;

import iut.acklaytooiklechevalier.Main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Games extends JPanel implements ActionListener {
	private final JTextField txtChatEnvoie;
	private final JLabel lblChatReception;
	private final JLabel lblInfoRole;

	public Games() {
		setLayout(null);

		JButton btnEnvoie = new JButton(">>");
		btnEnvoie.addActionListener(this);
		txtChatEnvoie = new JTextField();
		txtChatEnvoie.addActionListener(this);

		/*
		 * Les JLabel permet l'utilisation d'un mode multi-ligne si le text contenu dedans est "cast" en HTML
		 * Il est donc possible d'utiliser tout les balise HTML existante
		 * Même si dans un usage web il n'est pas recommendé de mettre du style CSS directement dans les fichier HTML
		 * il sera bien pratique ici
		 */
		//<p style=\"color:#FF0000\";>test</p>
		lblChatReception = new JLabel("<html>");
		lblInfoRole = new JLabel(
			"<html>" + 
				"<body style=\"text-align: center\";>" + 
					"<h1> Préparation </h1>" + 
					"<p>Pendant le temps de prépartion vous pouvez discuter avec les autres joueur déjà connécté</p>" + 
					"<p>Il faut 8 joueurs pour commençer une partie de Loup-Garou</p>" +
				"</body>" +
			"</html>");

		btnEnvoie.setBounds(Main.getInstance().getMenu().getWidth()-40, Main.getInstance().getMenu().getHeight()-63, 30, 25);
		txtChatEnvoie.setBounds(10, btnEnvoie.getY()-4, btnEnvoie.getX(), 32);

		lblChatReception.setBounds(10, txtChatEnvoie.getY()-300, Main.getInstance().getMenu().getWidth()-20, 300);
		lblChatReception.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		lblChatReception.setVerticalAlignment(JLabel.BOTTOM);

		lblInfoRole.setBounds(10, 10, lblChatReception.getWidth(), lblChatReception.getY()-20);
		lblInfoRole.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		lblInfoRole.setVerticalAlignment(JLabel.CENTER);
		lblInfoRole.setHorizontalAlignment(JLabel.CENTER);

		txtChatEnvoie.requestFocus();

		add(btnEnvoie);
		add(txtChatEnvoie);
		add(lblChatReception);
		add(lblInfoRole);
	}

	public void setLblText(String s, String color) {
		lblChatReception.setText(lblChatReception.getText() + "<p style=\"color:" + color + "\";>" + s + "</p>");
	}

	public void updateRole(String role) {
		if ("Loup-Garou".equals(role)) {
			lblInfoRole.setText(
				"<html>" + 
					"<body style=\"text-align: center\";>" + 
						"<h1> Loup-Garou </h1>" + 
						"<p>Vous êtes un Loup-Garou votre but est de tuer tout les villageois du village</p><br>" + 
						"<p>Vous avez la possibilité de parler avec les autres loup pendant la nuit</p>" +
					"</body>" +
				"</html>");
		} else if ("Villageois".equals(role)) {
			lblInfoRole.setText(
				"<html>" + 
					"<body style=\"text-align: center\";>" + 
						"<h1> Villageois </h1>" + 
						"<p>Vous êtes un Villageois votre but est de tuer tout les loup-garou du village</p><br>" + 
						"<p>Vous profitez de la nuit pour vous reposer vous ne pouvez donc pas parler</p>" +
					"</body>" +
				"</html>");
		} else if ("Sorciere".equals(role)) {
			lblInfoRole.setText(
				"<html>" + 
					"<body style=\"text-align: center\";>" + 
						"<h1> Sorciere </h1>" + 
						"<p>Vous êtes une Sorciere votre but est de tuer tout les loup-garou du village</p><br>" + 
						"<p>Vous profitez de la nuit pour parfaire vos talent ce qui vous à permis de créer deux fioles magique, l'une d'elle contient un puissant remède qui permet de soigner les blessures faites par un loup, l'autre est son contraire permettant de mettre fin à la vie d'un membre du village</p>" +
					"</body>" +
				"</html>");
		} else if ("mort".equals(role)) {
			lblInfoRole.setText(
				"<html>" + 
					"<body style=\"text-align: center\";>" + 
						"<h1> Mort </h1>" + 
						"<p>Vous êtes mort</p><br>" + 
						"<p>Les fantômes errants dans le village ce qui vous permet d'interagir avec les autres mort</p>" +
					"</body>" +
				"</html>");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = txtChatEnvoie.getText();
		if (!msg.equals("")) {
			Main.getInstance().getOut().println(msg);
		}
		Main.getInstance().getOut().flush();
		txtChatEnvoie.requestFocus();
		txtChatEnvoie.setText("");
	}

}
