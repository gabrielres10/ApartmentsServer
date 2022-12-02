package control;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Title ChatWindow
 * @Description This class is responsible for controlling the chat window.
 */
public class ChatWindow implements Initializable {

	@FXML
	private Button button_send;
	@FXML
	private TextField tf_message;
	@FXML
	VBox vbox_messages;
	@FXML
	private ScrollPane sp_main;
	@FXML
	private AnchorPane ap_main;

	public static Client client;

	@FXML
	private ImageView closeBTN, minimizeBTN;

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
	 * @Description This method is responsible for initializing the chat window. It is responsible for creating the client.
	 * @param url
	 * The location used to resolve relative paths for the root object, or
	 * null if the location is not known.
	 *
	 * @param resourceBundle
	 * The resources used to localize the root object, or null if
	 * the root object was not localized.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		vbox_messages.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				sp_main.setVvalue((Double) newValue);
			}
		});

		ap_main.setPadding(new Insets(10, 10, 10, 10));
		client.receiveMessage(vbox_messages);

		button_send.setCursor(Cursor.HAND);
		button_send.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * @Title handle
			 * @Description This method is responsible for handling the send button. It is responsible for sending the message to the server.
			 * @param actionEvent the event which occurred
			 */
			@Override
			public void handle(ActionEvent actionEvent) {
				String messageToSend = tf_message.getText();
				if (!messageToSend.isEmpty()) {
					HBox hBox = new HBox();
					hBox.setAlignment(Pos.CENTER_RIGHT);

					hBox.setPadding(new Insets(5, 5, 5, 10));
					Text text = new Text(messageToSend);
					TextFlow textFlow = new TextFlow(text);
					textFlow.setStyle("-fx-color: rgb(239, 242, 255);" + "-fx-background-color: rgb(15, 125, 242);"
							+ "-fx-background-radius: 20px;");

					textFlow.setPadding(new Insets(5, 10, 5, 10));
					text.setFill(Color.color(0.934, 0.925, 0.996));

					hBox.getChildren().add(textFlow);
					vbox_messages.getChildren().add(hBox);

					client.sendMessage(messageToSend);
					tf_message.clear();
				}
			}
		});
	}

	/**
	 * @Title addLabel
	 * @Description This method is responsible for adding a label to the chat window. It is responsible for displaying the message received from the server.
	 * @param message the message received from the server.
	 * @param vBox the VBox to which the label is added to.
	 */
	public static void addLabel(String message, VBox vBox) {
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER_LEFT);
		hBox.setPadding(new Insets(5, 5, 5, 10));

		Text text = new Text(message);
		TextFlow textFlow = new TextFlow(text);

		textFlow.setId("textFlow");
		
		textFlow.setStyle("-fx-background-radius: 20px;"
				+ "	-fx-font-family: consolas;"
				+ "	-fx-font-size: 24.0;"
				+ "	-fx-font-weight: bolder;"
				+ "	-fx-background-color: mediumpurple;");

		textFlow.setPadding(new Insets(5, 10, 5, 10));
		hBox.getChildren().add(textFlow);

		Platform.runLater(new Runnable() {
			/**
			 * @Title run
			 * @Description This method is responsible for running the thread.
			 */
			@Override
			public void run() {
				vBox.getChildren().add(hBox);
			}
		});
	}
}