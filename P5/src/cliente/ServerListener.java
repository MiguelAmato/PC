package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import mensajes.Confirmar;
import mensajes.Mensaje;

public class ServerListener extends Thread {

	Socket clientSocket;
	
	ObjectInputStream in;
	
	Client client;
	
	public ServerListener(Socket clientSocket, ObjectInputStream in, Client client) throws IOException {
		this.in = in;
		this.clientSocket = clientSocket;
		this.client = client;
	}
	
	public void run() {
		
		while (true) {
			try {
 				Confirmar mensaje = (Confirmar)in.readObject();
				client.respuestaServer(mensaje);
			}
			catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();;
				
			}
		}
	}

}
