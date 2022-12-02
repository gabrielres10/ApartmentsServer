package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import control.ChatWindow;

import control.Launcher;
import javafx.scene.layout.VBox;

/**
 * @Title Client
 * @Description This class is responsible for creating the client. It is responsible for sending and receiving messages.
 */
public class Client {
	private Socket s;
	private BufferedReader br;
	private BufferedWriter bw; // sends data to outputstream
	private String userName;
	private int apartment;
	public static String message;

	/**
	 * @Title Client
	 * @Description This method is responsible for creating the client.
	 * @param socket The socket of the client.
	 * @param userName The username of the client.
	 */
	public Client(Socket socket, String userName) {
		this.s = socket;

		try {
			this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.userName = userName;
			bw.write(userName);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			closeEverything(s, br, bw);
		}
	}


	/**
	 * @Title sendMessage
	 * @Description This method is responsible for sending messages to the server.
	 */
	public void sendMessage() {
		try {
			bw.write(userName);
			bw.newLine();
			bw.flush();

			Scanner sc = new Scanner(System.in);
			while (s.isConnected()) {
				String msgToSend = sc.nextLine();
				bw.write(userName + ": " + msgToSend);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			closeEverything(s, br, bw);
		}
	}

	/**
	 * @Title sendMessage
	 * @Description This method is responsible for sending specific messages to the server.
	 * @param msgToSend The message to be sent to the server.
	 */
	public void sendMessage(String msgToSend) {
		try {
			bw.write(msgToSend);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			closeEverything(s, br, bw);
		}
	}

	/**
	 * @Title receiveMessage
	 * @Description This method is responsible for receiving messages from the server.
	 *@param vbox_messages The VBox that contains the messages.
	 */
	public void receiveMessage(VBox vbox_messages) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (s.isConnected()) {
					try {
						String receivedMsg = br.readLine();
						ChatWindow.addLabel(receivedMsg, vbox_messages);
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Error receiving message from the Server!");
						closeEverything(s, br, bw);
						break;
					}
				}
			}
		}).start();
	}

	/**
	 * @Title listenForMessage
	 * @Description This method is responsible for listening for messages from the server. This function waits for a message that is broadcasted by the server.
	 */
	public void listenForMessage() {
		// This thread waits for any message sent
		new Thread(new Runnable() {
			@Override
			public void run() {
				message = "";
				while (s.isConnected()) {
					try {
						message = br.readLine();
						handleMessage(message);
					} catch (IOException e) {
						closeEverything(s, br, bw);
					}
				}
			}
		}).start();
	}

	/**
	 * @Title handleMessage
	 * @Description This method is responsible for handling the messages received from the server.
	 * @param message The message received from the server.
	 */
	protected void handleMessage(String message) throws IOException {
		// TODO Auto-generated method stub
		String option = message.split("%")[0];
		switch (option) {
		case "Visitor":
			Launcher.launchAcceptWindow(message.split("%")[1]);
			break;
		case "Chat":
			Launcher.launchChatWindow();
			break;
		case "Mail":
			break;
		default:
			break;
		}
	}

	/**
	 * @Title closeEverything
	 * @Description This method is responsible for closing the socket, the buffered reader and the buffered writer.
	 * @param s The socket of the client.
	 * @param br The buffered reader of the client.
	 * @param bw The buffered writer of the client.
	 */
	public void closeEverything(Socket s, BufferedReader br, BufferedWriter bw) {
		try {
			if (s != null)
				s.close();
			if (br != null)
				br.close();
			if (bw != null)
				bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @Title chat
	 * @Description This method is responsible for listening for messages from the server and sending messages to the server.
	 */
	public void chat() {
		listenForMessage();
		sendMessage();
	}


	/**
	 * @Title getUserName
	 * @Description This method is responsible for returning the username of the client.
	 * @return The username of the client.
	 */
	public String getUserName() {
		// TODO Auto-generated method stub
		return this.userName;
	}

}
