package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


import mensajes.Mensaje;
import utils.TipoMensaje;

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
				
 				Mensaje mensaje = (Mensaje)in.readObject();
 				System.out.println(mensaje.getTipo());
 				if (mensaje.getTipo().equals(TipoMensaje.ENVIAR_DATOS_EMISOR)) {
 					int x = 0;
 				}
				client.respuestaServer(mensaje);
			}
			catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();;
				
			}
		}
	}

}
