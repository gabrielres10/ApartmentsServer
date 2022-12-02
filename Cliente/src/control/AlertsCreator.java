package control;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @Title AlertsCreator
 * @Description This class is responsible for creating alerts.
 * @author Juan Camilo González Torres
 * @version 1.0
 */
public class AlertsCreator {

	/**
	 * @Title loadAlert
	 * @Description This method build an alert with the parameters given.
	 * 
	 * @param type    AlertType, it can be:- <b>NONE</b> - <b>INFORMATION</b> -
	 *                <b>WARNING</b> - <b>CONFIRMATION</b> - <b>ERROR</b>
	 * @param content String, it's the <b>information/description</b> of alert.
	 * @param header  String, it's the <b>header</b> of alert.
	 * @param title   String, it's the <b>title</b> of alert.
	 */
	public static void loadAlert(AlertType type, String title, String middle, String bot) {
		Alert alert = new Alert(type);
		alert.setContentText(bot);
		alert.setHeaderText(middle);
		alert.setTitle(title);
		alert.show();
	}
}