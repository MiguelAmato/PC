package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cliente.User;
import mensajes.Mensaje;
import utils.Function;
import utils.TipoMensaje;


public class Server {
	
	public static final int PORT = 5555;
	
	private ServerSocket serverSocket;
	
	
	private Map<TipoMensaje, Function<Mensaje, ClientListener>> mapaFunciones; // Mapa que en base al mensaje llegado, ejecuta la funcion correspondiente

	private Map<String, User> mapaConectados;
	
	public Server() {
		initMap();
	}
	
	public void start() {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("CONEXION ESTABLECIDA");
			while(!serverSocket.isClosed()) {
				ClientListener cl = new ClientListener(serverSocket.accept(), this);
				cl.start();
			}
		} 
		catch (IOException e) {
			System.out.println(e.getMessage() + " (Server)");
		}
		
	}
	
	private void initMap() {
		mapaFunciones = new HashMap<TipoMensaje, Function<Mensaje, ClientListener>>() {
			private static final long serialVersionUID = 1L;
		{
			put(TipoMensaje.CONECTAR, (x, y) -> conectarCliente(x, y));
			put(TipoMensaje.LISTAR, (x, y) -> listaConectados(x, y));
			put(TipoMensaje.DESCARGAR, (x, y) -> descargarArchivo(x, y));
			put(TipoMensaje.DESCONECTAR, (x, y) -> desconectarCliente(x, y));
		}};
		
		mapaConectados = new HashMap<String, User>();
	}

	
	public void procesarMensaje(Mensaje mensajeRecibido, ClientListener listener) {
		mapaFunciones.get(mensajeRecibido.getTipo()).apply(mensajeRecibido, listener);
	}
	
	public void conectarCliente(Mensaje mensaje, ClientListener listener) {
		mapaConectados.put(mensaje.getOrigen().getNombre(), mensaje.getOrigen());
	}
	
	public void listaConectados(Mensaje mensaje, ClientListener listener) {
		Set<String> set = mapaConectados.keySet();
		listener.enviarLista(set);
	}

	public void descargarArchivo(Mensaje mensaje, ClientListener listener) {
	
	}

	public void desconectarCliente(Mensaje mensaje, ClientListener listener) {
	}
	
	

}
