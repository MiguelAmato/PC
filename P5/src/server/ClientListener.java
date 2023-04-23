package server;

import java.io.*;
import java.net.*;
import java.util.Set;

import mensajes.ConfirmarConectar;
import mensajes.DarListaArchivos;
import mensajes.DarListaClientes;
import mensajes.EnviarDatosEmisor;
import mensajes.FalloConexionP2P;
import mensajes.Mensaje;
import mensajes.SolicitarConexionP2P;
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
			
			DarListaClientes mensaje = new DarListaClientes(lista);
			
			out.writeObject(mensaje);
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarPuerto(int port) {
		try {
			
			ConfirmarConectar mensaje = new ConfirmarConectar(port);
			
			out.writeObject(mensaje);
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void enviarListaArchivos(Set<String> set) {
		try {
			DarListaArchivos mensaje = new DarListaArchivos(set);
			
			out.writeObject(mensaje);
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarFalloConexion() {
		try {
			FalloConexionP2P mensaje = new FalloConexionP2P();
			
			out.writeObject(mensaje);
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void enviarSolicitudDescarga(String nombre, String fileName) {
		try {
		
			SolicitarConexionP2P mensaje = new SolicitarConexionP2P(nombre, fileName);
			
			out.writeObject(mensaje);
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarDatosEmisor(EnviarDatosEmisor mensaje) {
		try {
			EnviarDatosEmisor mensaje1 = new EnviarDatosEmisor(mensaje.getPort(), mensaje.getIp(), mensaje.getReceptor(), mensaje.getFile());
			out.writeObject(mensaje1);
			System.out.println("Llegue");
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
 