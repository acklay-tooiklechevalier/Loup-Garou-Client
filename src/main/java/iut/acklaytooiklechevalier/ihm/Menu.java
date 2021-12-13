package iut.acklaytooiklechevalier.ihm;

import javax.swing.JFrame;

public class Menu extends JFrame {
	private final Connection connection;
	private Games games;

	public Menu() {
		setTitle("Loup-Garou");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		connection = new Connection();
		getContentPane().add(connection);

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
}
