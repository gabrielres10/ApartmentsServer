package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Title Server
 * @Description This class is responsible for creating the server. It is responsible for sending and receiving messages.
 */
public class Server {
	
	private ServerSocket serverSocket;

	/**
	 * @Title Server
	 * @Description This method is responsible for creating the server.
	 * @param serverSocket The server socket of the server.
	 */
	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	/**
	 * @Title startServer
	 * @Description This method is responsible for starting the server. It makes the server listen for clients and waits for them to connect.
	 */
	public void startServer() {
		
		try {
			
			while (!serverSocket.isClosed()) {
				
				Socket socket = serverSocket.accept();
				System.out.println("A client has connected");
				ClientHandler clientHandler = new ClientHandler(socket);
				Thread thread = new Thread(clientHandler);
				
				thread.start();
				
			}
			
		} catch (IOException e) {
			
		}
	}

	/**
	 * @Title closeServerSocket
	 * @Description This method is responsible for closing the server socket.
	 */
	public void closeServerSocket() {
		try {
			if (serverSocket != null) {
				serverSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
