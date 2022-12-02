package control;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.RunServer;

/**
 * @Title Launcher
 * @Description This class is used to launch the server.
 */
public class Launcher {

	public static String visitorName;
	public static String mailFrom = "";

	public static final String emailFrom = "juank123ganzalez@gmail.com";
	public static final String password = "tubvasfylzpbxwce";
	public static final String emailTo = "gabrielrestrepo7@gmail.com";
	public static Properties properties = new Properties();
	public static Session session;
	public static MimeMessage correo;

	/**
	 * @Title sendEmail
	 * @Description This method is used to send an email.
	 */
	public static void sendEmail() throws MessagingException {
		// TODO Auto-generated method stub
		new Thread(() -> {
			properties.setProperty("mail.transport.protocol", "smtp");
			properties.setProperty("mail.host", "smtp.gmail.com");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.debug", "true");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.socketFactory.fallback", "false");
			Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailFrom, password);
				}
			});

			// session.setDebug(true);
			Transport transport;
			try {
				transport = session.getTransport();
				InternetAddress addressFrom = new InternetAddress(emailFrom);

				MimeMessage message = new MimeMessage(session);
				message.setSender(addressFrom);
				message.setSubject(mailFrom.toUpperCase() + " HAS PRESSED THE PANIC BUTTON!");
				message.setContent("Alert! Your friend " + mailFrom.toUpperCase() + "has pressed the panic button!", "text/plain");
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

				transport.connect();
				Transport.send(message);
				transport.close();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(MessagingException e) {
				e.printStackTrace();
			}
			

			JOptionPane.showMessageDialog(null, "Correo enviado!");
		}).start();

	}

	/**
	 * @Title launchDecideWindow
	 * @Description This method is used to launch the decide window.
	 * @param ans The answer of the user.
	 */
	public static void launchDecideWindow(String ans) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (ans.equals("true")) {
					AlertsCreator.loadAlert(Alert.AlertType.INFORMATION, "The client answered!",
							"The resident wants the person to pass", "Thanks!");
				} else {
					AlertsCreator.loadAlert(Alert.AlertType.INFORMATION, "The client answered!",
							"The resident does not want the person to pass", "Thanks!");
				}
			}
		});

	}

	/**
	 * @Title launchPorterTerminalWindow
	 * @Description This method is used to launch the porter terminal window.
	 */
	public static void launchPorterTerminalWindow() {
		// TODO Auto-generated method stub
		try {

			FXMLLoader loader = new FXMLLoader(RunServer.class.getResource("../view/PorterTerminalWindow.fxml"));

			Parent parent = (Parent) loader.load();

			Stage stage = new Stage();

			PorterTerminalWindow.stage = (stage);

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

	/**
	 * @Title launchReceiveVisitorWindow
	 * @Description This method is used to launch the receive visitor window.
	 */
	public static void launchReceiveVisitorWindow() {
		// TODO Auto-generated method stub
		try {

			FXMLLoader loader = new FXMLLoader(RunServer.class.getResource("../view/ReceiveVisitorWindow.fxml"));

			loader.setController(new ReceiveVisitorWindow());

			Parent parent = (Parent) loader.load();

			Stage stage = new Stage();

			ReceiveVisitorWindow.stage = (stage);

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
