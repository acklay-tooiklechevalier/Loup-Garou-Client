package iut.acklaytooiklechevalier;

import iut.acklaytooiklechevalier.ihm.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	private static Main instance;
	private static Menu menu;

	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private final Scanner sc = new Scanner(System.in);// pour lire à partir du clavier

	public Main() {
		instance = this;
		menu = new Menu();
	}

	public boolean initServeur(String ip, int port, String pseudo) {
		try {
			/*
			 * les informations du serveur ( port et adresse IP ou nom d'hote 127.0.0.1 est
			 * l'adresse local de la machine
			 */
			clientSocket = new Socket(ip, port);

			// flux pour envoyer
			out = new PrintWriter(clientSocket.getOutputStream());
			// flux pour recevoir
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			out.println(pseudo);
			out.flush();

			envoie();
			recevoir();

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void envoie() {
		Thread envoyer = new Thread(new Runnable() {
			String msg;

			@Override
			public void run() {
				while (!clientSocket.isClosed()) {
					msg = sc.nextLine();
					if (!msg.equals("")) {
						out.println(msg);
					}
					out.flush();
				}
			}
		});
		envoyer.start();
	}

	private void recevoir() {
		//a mettre le role dans le chat
		Thread recevoir = new Thread(new Runnable() {
			String msg;

			@Override
			public void run() {
				try {
					msg = in.readLine();
					while (msg != null) {
						System.out.println(msg);
						if (msg.startsWith("[+]")) {
							menu.getGames().setLblText(msg, "#00BB00");
						} else if (msg.startsWith("[-]")) {
							menu.getGames().setLblText(msg, "#BB0000");
						} else if (msg.startsWith("Serveur : ") || msg.startsWith("Erreur : ")) {
							menu.getGames().setLblText(msg, "#FF0000; font-weight: bold");
						} else if (msg.startsWith("Votre rôle est : ")) {
							menu.getGames().setLblText(msg, "#00AAFF; font-weight: bold");//a mettre le role dans le chat 
							menu.getGames().updateRole(msg.substring(17));
						} else if (msg.startsWith("La nuit tombe")) {
							menu.getGames().setLblText(msg, "#880000");
						} else if (msg.startsWith("Une nouvelle journée")) {
							menu.getGames().setLblText(msg, "#008800");
						} else if (msg.startsWith("Vous avez")) {
							menu.getGames().setLblText(msg, "#FF2222");
						} else if (msg.startsWith("Auncun mort")) {
							menu.getGames().setLblText(msg, "#22FF22");
						} else if (msg.startsWith("le joueur")) {
							menu.getGames().setLblText(msg, "#772222");
						} else if (msg.startsWith("Vous êtes mort !")) {
							menu.getGames().setLblText(msg, "#888888");
							menu.getGames().updateRole("mort");
						} else if (msg.startsWith("/v")) {
							menu.getGames().setLblText(msg.substring(2), "#000000");
						} else if (msg.startsWith("/m")) {
							menu.getGames().setLblText(msg, "#888888");
						} else {
							menu.getGames().setLblText(msg, "#000000");
						}
						msg = in.readLine();
					}
					System.out.println("Serveur déconecté");
					menu.deconnection();
					out.close();
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		recevoir.start();
	}

	public Menu getMenu() {
		return menu;
	}

	public static Main getInstance() {
		return instance;
	}

	public PrintWriter getOut() {
		return out;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public static void main(String[] args) {
		new Main();
	}
}
