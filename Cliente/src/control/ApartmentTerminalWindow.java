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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Client;
import ui.RunClient;

/**
 * @Title ApartmentTerminalWindow
 * @Description This class is responsible for controlling the apartment terminal window.
 */
public class ApartmentTerminalWindow implements Initializable {

	private Client client;

	@FXML
	private Button chatBTN;

	@FXML
	private Button panicBTN;

	@FXML
	private TextArea optionsTF;

	@FXML
	private Button sendBTN;

	@FXML
    private ImageView closeBTN,minimizeBTN;
	
	public static Stage stage;

   	@FXML
   	void closeWindow(MouseEvent event) {
   		stage.close();
   	}

   	@FXML
   	void minimizeWindow(MouseEvent event) {
   		stage.setIconified(true);
   	}

	/**
	 * @Title initialize
	 * @Description This method is responsible for initializing the apartment terminal window.
	 * @param location
	 * The location used to resolve relative paths for the root object, or
	 * <tt>null</tt> if the location is not known.
	 *
	 * @param resources
	 * The resources used to localize the root object, or <tt>null</tt> if
	 * the root object was not localized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title chat
	 * @Description This method is responsible for opening the chat window.
	 * @param event The event that triggered the method. In this case, the button click.
	 */
	@FXML
	void chat(ActionEvent event) {
		Launcher.launchChatWindow();
	}


	/**
	 * @Title panic
	 * @Description This method is responsible for sending a panic message to the server. It is also responsible for opening the panic window.
	 * @param event The event that triggered the method. In this case, the button click.
	 */
	@FXML
	void panic(ActionEvent event) {
		RunClient.client.sendMessage("Mail%" + RunClient.client.getUserName());
	}

	/**
	 * @Title sendOption
	 * @Description This method is responsible for sending the option selected by the user to the server.
	 * @param event The event that triggered the method. In this case, the button click.
	 */
	@FXML
	void sendOption(ActionEvent event) {

	}

}
