package iut.acklaytooiklechevalier.ihm;

import iut.acklaytooiklechevalier.Main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.IOException;

public class Menu extends JFrame implements WindowFocusListener {
	private final Connection connection;
	private Games games = null;

	public Menu() {
		setTitle("Loup-Garou");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		connection = new Connection();
		getContentPane().add(connection);
		addWindowFocusListener(this);

		setVisible(true);
	}

	public Connection getConnection() {
		return connection;
	}

	public Games getGames() {
		return games;
	}

	public void runGames() {
		games = new Games();
		getContentPane().remove(connection);
		getContentPane().add(games);
		setSize(getWidth()+1, getHeight());
		setSize(getWidth()-1, getHeight());
	}

	public void deconnection() {
		getContentPane().remove(games);
		getContentPane().add(connection);
		setSize(getWidth()+1, getHeight());
		setSize(getWidth()-1, getHeight());
		games = null;
		Main.getInstance().getOut().close();
		try {
			Main.getInstance().getClientSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "Vous avez été déconnecté du serveur de jeu", " Déconnection ", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		if (games != null)
			games.getTxtChatEnvoie().requestFocus();
	}

	@Override
	public void windowLostFocus(WindowEvent e) {

	}
}
