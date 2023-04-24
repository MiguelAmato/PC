package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

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
		
		try {
			while (!clientSocket.isClosed()) {
 				Mensaje mensaje = (Mensaje)in.readObject();
 				
				client.respuestaServer(mensaje);
			}
		}
		catch(IOException e) {
			System.out.println("Cliente desconectado");	
		}
		catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
