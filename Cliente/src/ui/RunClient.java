package ui;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import control.ChatWindow;
import control.Launcher;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Client;

/**
 * @Title RunClient.java
 * @Description This class is responsible for running the client. It creates the client and the chat window.
 **/
public class RunClient extends Application{

	public static Client client;


	/**
	 * @Title main
	 * @Description This method is responsible for running the client.
	 * @param args The arguments of the main method.
	 */

	public static void main (String [] args) throws UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your user name to chat");
		String username = sc.nextLine();
		Socket socket = new Socket("localhost", 60000);
		client = new Client(socket, username);
		ChatWindow.client = client;
		//client.listenForMessage();
		
		launch(args);
	}

	/**
	 * @Title start
	 * @Description This method is responsible for starting the chat window.
	 * @param primaryStage the primary stage for this application, onto which
	 * the application scene can be set.
	 * @throws Exception if something goes wrong during the start of the application.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Launcher.launchApartmentTerminalWindow();
	}
}
