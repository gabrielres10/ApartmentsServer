package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ClientHandler;
import ui.RunServer;

/**
 * @Title PorterTerminalWindow
 * @Description This class is used to control the PorterTerminalWindow.
 */
public class PorterTerminalWindow implements Initializable {

	@FXML
	private Button receiveVisitorBTN;

	@FXML
	private ImageView closeBTN, minimizeBTN;

	/**
	 * @Title initialize
	 * @Description This method is used to initialize the window.
	 * @param location
	 *            The location used to resolve relative paths for the root object,
	 *            or <tt>null</tt> if the location is not known.
	 *
	 * @param resources
	 *            The resources used to localize the root object, or <tt>null</tt>
	 *            if the root object was not localized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public static Stage stage;

	/**
	 * @Title closeWindow
	 * @Description This method is used to close the window.
	 * @param event The event that triggered the method.
	 */
	@FXML
	void closeWindow(MouseEvent event) {
		stage.close();
	}

	/**
	 * @Title minimizeWindow
	 * @Description This method is used to minimize the window.
	 * @param event The event that triggered the method.
	 */
	@FXML
	void minimizeWindow(MouseEvent event) {
		stage.setIconified(true);
	}

	/**
	 * @Title receiveVisitor
	 * @Description This method is used to open the ReceiveVisitorWindow.
	 * @param event The event that triggered the method.
	 */
	@FXML
	void receiveVisitor(ActionEvent event) {

		if (ClientHandler.clientHandlers.size() != 0) {
			Launcher.launchReceiveVisitorWindow();
		} else {
			AlertsCreator.loadAlert(Alert.AlertType.WARNING, "�Careful!", "There is not any client connected",
					"Wait for a client to be connected");
		}
	}

}
