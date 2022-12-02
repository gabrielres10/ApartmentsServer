package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.mail.MessagingException;

import control.Launcher;

/**
 * @Title ClientHandler
 * @Description This class is used to handle the client's request.
 */
public class ClientHandler implements Runnable {

	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
	private Socket socket;
	private BufferedReader br;
	private BufferedWriter bw; //sends data to outputstream
	private String userName;
	private int apartmentNumber;
	private boolean chat = false;

	/**
	 * @Title ClientHandler
	 * @Description This is the constructor of the class.
	 * @param socket the socket of the client that is connected to the server
	 */
	public ClientHandler(Socket socket) {
		
		try {
			
			this.socket = socket;
			this.bw = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()) );
			this.br = new BufferedReader ( new InputStreamReader(socket.getInputStream()));
			this.userName = br.readLine();
			this.apartmentNumber = clientHandlers.size()+1;
			clientHandlers.add(this);
			broadcastMessage("Server: " + userName + " has entered the chat");
		} catch (IOException e) {
			closeEverything(socket, br, bw);
		}
		
	}

	/**
	 * @Title run
	 * @Description This method is used to run the thread.
	 */
	@Override
	public void run() {
		String msgFromClient = "";
		while(socket.isConnected()) {
			try {
				msgFromClient = br.readLine();
				broadcastMessage(msgFromClient);
				handleMessage(msgFromClient);
			} catch (IOException e) {
				closeEverything(socket, br, bw);
				break;
			}
		}
	}

	/**
	 * @Title handleMessage
	 * @Description This method is used to handle the message received from the client.
	 * @param message the message received from the client
	 */
	protected void handleMessage(String message) throws IOException {
		// TODO Auto-generated method stub
		String option = message.split("%")[0];
		switch (option) {
		case "VisitorAns":
			Launcher.launchDecideWindow(message.split("%")[1]);
			break;
		case "Visitor":
			Launcher.visitorName = message.split("%")[1];
			break;
		case "Mail":
			Launcher.mailFrom = message.split("%")[1];
			try {
				Launcher.sendEmail();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * @Title broadcastMessage
	 * @Description This method is used to broadcast the message to all the clients. (Sends the message to all the clients connected)
	 * @param msg the message to be sent
	 */
	public void broadcastMessage(String msg) {
		for (ClientHandler ch : clientHandlers) {
			try {
				if (!ch.userName.equals(userName)) {
					ch.bw.write(msg);
					ch.bw.newLine();
					ch.bw.flush();
				}
			} catch (IOException e) {
				closeEverything(socket, br, bw);
			}
		}
	}
	
	
	/**
	 * @Title sendDirectMessage
	 * @Description This method is used to send a direct message to a specific client. sends a message to a single apartment, by
	 * searching it with the number of apartment
	 * @param msg, String, message to send
	 * @param apartmentNumber, int, number of the apartment
	 */
	public void sendDirectMessage(String msg) {
		for (ClientHandler ch : clientHandlers) {
			try {
				if (ch.apartmentNumber == this.apartmentNumber) {
					ch.bw.write(msg);
					ch.bw.newLine();
					ch.bw.flush();
					break;
				}
			} catch (IOException e) {
				closeEverything(socket, br, bw);
			}
		}
	}

	/**
	 * @Title removeClientHandler
	 * @Description This method is used to remove a client handler from the list of client handlers.
	 */
	public void removeClientHandler() {
		clientHandlers.remove(this);
		broadcastMessage("SERVER: " + userName + " has left the chat");
	}
	
	/**
	 * @Title closeEverything
	 * @Description This method is used to close the socket, the buffered reader and the buffered writer.
	 * @param s the socket to be closed.
	 * @param br the buffered reader to be closed.
	 * @param bw the buffered writer to be closed.
	 */
	public void closeEverything(Socket s, BufferedReader br, BufferedWriter bw) {
		removeClientHandler();
		try {
			if(s != null)
			s.close();
			if(br != null)
			br.close();
			if(bw != null)
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Title getApartmentNumber
	 * @Description This method is used to get the apartment number of the client.
	 * @return the apartment number of the client.
	 */
	public int getApartmentNumber() {
		return apartmentNumber;
	}

}
