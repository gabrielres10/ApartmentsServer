package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ClientHandler;

/**
 * @Title ReceiveVisitorWindow
 * @Description This class is used to control the ReceiveVisitorWindow.
 */
public class ReceiveVisitorWindow implements Initializable{

    @FXML
    private TextField nameTF;

    @FXML
    private ImageView closeBTN,minimizeBTN;
    
    @FXML
    private ChoiceBox<String> apartmentCB;
    
    @FXML
    private Button notifyBTN;

	/**
	 * @Title initialize
	 * @Description This method is used to initialize the window.
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
		fillApartmentCB();
	}

	/**
	 * @Title fillApartmentCB
	 * @Description This method is used to fill the apartment choice box.
	 */
	private void fillApartmentCB() {
		
		ObservableList<String> apartments = FXCollections.observableArrayList();
		for(ClientHandler cw : ClientHandler.clientHandlers) {
			apartments.add(cw.getApartmentNumber() + "");
		}
		apartmentCB.setItems(apartments);
	}
	
	/**
	 * @Title notify
	 * @Description This method is used to notify the visitor. This method notifies to a specific apartment that a visitor
	 * has arrived.
	 * @param event, When the button "notify" is clicked.
	 */
    @FXML
    void notify(ActionEvent event) {

    	int index = Integer.parseInt(apartmentCB.getValue()) - 1;
    	ClientHandler.clientHandlers.get(index).sendDirectMessage("Visitor%" + nameTF.getText());

    }
    
    public static Stage stage;

	/**
	 * @Title closeWindow
	 * @Description This method is used to close the window.
	 * @param event When the close button is clicked.
	 */
	@FXML
	void closeWindow(MouseEvent event) {
		stage.close();
	}

	/**
	 * @Title minimizeWindow
	 * @Description This method is used to minimize the window.
	 * @param event When the minimize button is clicked.
	 */
	@FXML
	void minimizeWindow(MouseEvent event) {
		stage.setIconified(true);
	}
    
    /**
	 * @Title clearTF
	 * @Description This method is used to clear the text fields.
     */
    private void clearTF() {
    	nameTF.clear();
    }

}
