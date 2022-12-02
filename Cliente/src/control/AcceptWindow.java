package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ui.RunClient;

/**
 * @Title AcceptWindow
 * @Description This class is responsible for controlling the accept window.
 */
public class AcceptWindow implements Initializable {

	private boolean decide;

	@FXML
	private Button acceptBTN;

	@FXML
	private Button denyBTN;

	@FXML
	private Label nameLabel;

	@FXML
	private ImageView closeBTN, minimizeBTN;

	private String visitorName;

	/**
	 * @param name The visitor's name.
	 * @Title AcceptWindow
	 * @Description This method is responsible for initializing the accept window. It is responsible for setting the visitor's name.
	 */
	public AcceptWindow(String name) {
		this.visitorName = name;
	}

	public static Stage stage;

	/**
	 * @param event
	 * @Title closeWindow
	 * @Description This method is responsible for closing the accept window.
	 */
	@FXML
	void closeWindow(MouseEvent event) {
		stage.close();
	}

	/**
	 * @Title minimizeWindow
	 * @Description This method is responsible for minimizing the accept window.
	 * @param event The event that triggers the method.
	 */
   	@FXML
   	void minimizeWindow(MouseEvent event) {
   		stage.setIconified(true);
   	}

	/**
	 * @Title decice
	 * @Description This method is responsible for deciding whether the visitor is accepted or not.
	 * @param event The event that triggers the method.
	 */
	@FXML
    void decide(ActionEvent event) {
		if(event.getSource().equals(acceptBTN)) {
			this.decide = true;
		}
		
		if(event.getSource().equals(denyBTN)) {
			this.decide = false;
		}
		
		sendAnswer();
	}

	/**
	 * @Title sendAnswer
	 * @Description This method is responsible for sending the answer to the server.
	 */
	public void sendAnswer() {
    	RunClient.client.sendMessage("VisitorAns%" + this.decide + "");
    }

	/**
	 * @Title initialize
	 * @Description This method is responsible for initializing the accept window.
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
		nameLabel.setText(this.visitorName);
	}

}
