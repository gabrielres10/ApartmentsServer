package control;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.RunClient;

/**
 * @Title Launcher
 * @Description This class is responsible for launching the chat window.
 */
public class Launcher {

	/**
	 * @Title launchChatWindow
	 * @Description This method is responsible for launching the chat window.
	 */
	public static void launchChatWindow() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {

					FXMLLoader loader = new FXMLLoader(RunClient.class.getResource("../view/ChatWindow.fxml"));

					loader.setController(new ChatWindow());

					Parent parent = (Parent) loader.load();

					Stage stage = new Stage();

					ChatWindow.stage = (stage);

					Scene scene = new Scene(parent);

					stage.setScene(scene);

					// poniendo detallitos -------------

					scene.setFill(Color.TRANSPARENT);

					stage.setScene(scene);

					stage.initStyle(StageStyle.TRANSPARENT);

					stage.setScene(scene);

					stage.setResizable(false);

					// ----------------------

					stage.show();

				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * @Title launchAcceptWindow
	 * @Description This method is responsible for launching the accept window.
	 * @param name The name of the user who wants to chat with you.
	 */
	public static void launchAcceptWindow(String name) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {

					FXMLLoader loader = new FXMLLoader(RunClient.class.getResource("../view/AcceptWindow.fxml"));

					loader.setController(new AcceptWindow(name));

					Parent parent = (Parent) loader.load();

					Stage stage = new Stage();

					AcceptWindow.stage = (stage);

					Scene scene = new Scene(parent);

					stage.setScene(scene);

					// poniendo detallitos -------------

					scene.setFill(Color.TRANSPARENT);

					stage.setScene(scene);

					stage.initStyle(StageStyle.TRANSPARENT);

					stage.setScene(scene);

					stage.setResizable(false);

					// ----------------------

					stage.show();

				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * @Title launchApartmentTerminalWindow
	 * @Description This method is responsible for launching the apartment terminal window.
	 */
	public static void launchApartmentTerminalWindow() {
		// TODO Auto-generated method stub
		try {

			FXMLLoader loader = new FXMLLoader(RunClient.class.getResource("../view/ApartmentTerminalWindow.fxml"));

			Parent parent = (Parent) loader.load();

			Stage stage = new Stage();

			ApartmentTerminalWindow.stage = (stage);

			Scene scene = new Scene(parent);

			stage.setScene(scene);

			// poniendo detallitos -------------

			scene.setFill(Color.TRANSPARENT);

			stage.setScene(scene);

			stage.initStyle(StageStyle.TRANSPARENT);

			stage.setScene(scene);

			stage.setResizable(false);

			// ----------------------

			stage.show();

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
