package cliente;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import mensajes.Conectar;
import mensajes.Confirmar;
import mensajes.Listar;
import mensajes.Mensaje;
import utils.TipoMensaje;

public class Client {
	
	public static final int SERVER_PORT = 5555;
	
	int port;
	
	Socket clientSocket;
	
	private ServerSocket p2pSocket;
	
	private User info;
	
	private ObjectOutputStream out;
	
	private ObjectInputStream in;

    private ServerListener serverListener;
    
    private ClientView view;
	
	public Client(String name, String directorio) {
		info = new User(name, directorio);

	}

	public void start() {
		try {
			clientSocket = new Socket("192.168.155.160", SERVER_PORT);
			clientSocket.setSoTimeout(0);
			
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			
			
			Conectar mensaje = new Conectar(info);
			
			out.writeObject(mensaje);
			
			out.flush();
			
			escucharServidor();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void enviarMensaje(Mensaje mensaje) {
		try {
			out.writeObject(mensaje);
			out.flush();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void escucharServidor() throws IOException {
		//in = new ObjectInputStream(clientSocket.getInputStream());
		serverListener = new ServerListener(clientSocket,in, this);
		serverListener.start();
	}
	
	public void setView(ClientView view) {
		this.view = view;
	}

	public void mostrarMensaje(String mensaje) {
		
	}

	public void desconectar() {
		
	}
	
	public User getInfo() {
		return info;
	}

	public void respuestaServer(Confirmar mensaje) {
		if (mensaje.getTipo().equals(TipoMensaje.CONECTAR)) {
			
		}
		else if(mensaje.getTipo().equals(TipoMensaje.LISTAR)) {
			view.mostrarPanelLista(mensaje.getListaConectados());
		}
	}

	
		
}
