package server;

import java.io.*;
import java.net.*;
import java.util.Set;

import mensajes.Confirmar;
import mensajes.Mensaje;
import utils.TipoMensaje;



public class ClientListener extends Thread {
	
	private Socket clientSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
    
	Server server;

	public ClientListener(Socket clientSocket, Server server) {
		this.server = server;
		this.clientSocket = clientSocket;
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() { // cerrar el in y el out
		try {
			while(true) {		
	            
	            Mensaje mensajeRecibido = (Mensaje) in.readObject();
	            
	            server.procesarMensaje(mensajeRecibido, this);
	            
	            //in.close();
			}
	          
		} 
		catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage() + " (ClientListener)");
		}
		
	}

	public void enviarLista(Set<String> lista) {
		try {
			//out = new ObjectOutputStream(clientSocket.getOutputStream());
			
			Confirmar mensaje = new Confirmar(TipoMensaje.LISTAR);
			
			mensaje.listaConectados(lista);
			
			out.writeObject(mensaje);
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
 