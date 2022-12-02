package ui;

import java.io.IOException;
import java.net.ServerSocket;

import control.Launcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Server;

/**
 * @Title RunServer
 * @Description This class is responsible for running the server. It creates the server and the chat window.
 */

public class RunServer extends Application{

	/**
	 * @Title main
	 * @Description This method is responsible for running the server. Where the thread is created and the server is created.
	 * @param args The arguments of the main method.
	 */
	public static void main(String [] args) throws IOException {
		
		Thread thread = new Thread(() -> {
			launch(args);
		});

		thread.start();
		
		ServerSocket serverSocket = new ServerSocket(60000);
		Server server = new Server(serverSocket);
		server.startServer();
	}

	/**
	 * @Title start
	 * @Description This method is responsible for starting the server.
	 * @param primaryStage the primary stage for this application, onto which
	 * the application scene can be set. The primary stage will be embedded in
	 * the browser if the application was launched as an applet.
	 * Applications may create other stages, if needed, but they will not be
	 * primary stages and will not be embedded in the browser.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// TODO Auto-generated method stub
		Launcher.launchPorterTerminalWindow();
	}
}
