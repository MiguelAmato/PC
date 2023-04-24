package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

import cliente.User;
import mensajes.Mensaje;
import mensajes.ActualizarArchivos;
import mensajes.EmpezarConexionP2P;
import mensajes.EnviarDatosEmisor;
import utils.Function2;
import utils.LockTicket;
import utils.TipoMensaje;


public class Server {
	
	public static final int PORT = 5555;
		
	private ServerSocket serverSocket;
	
	private ServerMaps<TipoMensaje, Function2<Mensaje, ClientListener>> mapaFunciones; // Mapa que en base al mensaje llegado, ejecuta la funcion correspondiente

	private ServerMaps<String, User> mapaConectados;
	
	private ServerMaps<String, List<User>> mapaArchivos;
	
	private ServerMaps<String, ClientListener> mapaOyentes;
	
	private LockTicket lock;
	
	public Server() {
		initMap();
		lock = new LockTicket();
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
		mapaFunciones = new ServerMaps<TipoMensaje, Function2<Mensaje, ClientListener>>();
		mapaFunciones.put(TipoMensaje.CONECTAR, (x, y) -> conectarCliente(x, y));
		mapaFunciones.put(TipoMensaje.LISTAR, (x, y) -> listaConectados(x, y));
		mapaFunciones.put(TipoMensaje.DESCARGAR, (x, y) -> iniciaDescargarArchivo(x, y));
		mapaFunciones.put(TipoMensaje.DESCONECTAR, (x, y) -> desconectarCliente(x, y));
		mapaFunciones.put(TipoMensaje.EMPEZAR_CONEXION_P2P, (x, y) -> empezarP2P((EmpezarConexionP2P)x, y));
		mapaFunciones.put(TipoMensaje.ENVIAR_DATOS_EMISOR, (x, y) -> enviarDatosEmisor((EnviarDatosEmisor)x, y));
		mapaFunciones.put(TipoMensaje.ACTUALIZAR_ARCHIVOS,(x,y) -> actualizarArchivos((ActualizarArchivos)x,y));
		mapaConectados = new ServerMaps<String, User>();
		mapaArchivos = new ServerMaps<String, List<User>>();
		mapaOyentes = new ServerMaps<String, ClientListener>();
	}



	public void procesarMensaje(Mensaje mensajeRecibido, ClientListener listener) {
		mapaFunciones.get(mensajeRecibido.getTipo()).apply(mensajeRecibido, listener);
		
	}
	
	public void conectarCliente(Mensaje mensaje, ClientListener listener) {
		
		if(mapaConectados.containsKey(mensaje.getOrigen().getNombre())) {
			
			listener.enviarUsuarioRepetido();
		}
		
		else {
			mapaConectados.put(mensaje.getOrigen().getNombre(), mensaje.getOrigen());
			for(String archivo : mensaje.getOrigen().getListaArchivos()) {
				if (!mapaArchivos.containsKey(archivo)) {
					mapaArchivos.put(archivo, new ArrayList<User>());
				}
				mapaArchivos.get(archivo).add(mensaje.getOrigen());
			}
			mapaOyentes.put(mensaje.getOrigen().getNombre(), listener);
			System.out.println("Añadido al mapa el cliente: " + mensaje.getOrigen().getNombre());
		}
	}
	
	public void listaConectados(Mensaje mensaje, ClientListener listener) {
		Set<String> set = mapaConectados.keySet();
		listener.enviarLista(set);
	}

	public void iniciaDescargarArchivo(Mensaje mensaje, ClientListener listener) {
		Set<String> set = mapaArchivos.keySet();
		listener.enviarListaArchivos(set);
	}
	
	
	private void empezarP2P(EmpezarConexionP2P mensaje, ClientListener listener) {
		String archivo = mensaje.getFileName();
		if(mapaArchivos.containsKey(archivo)) {
			mapaOyentes.get(mapaArchivos.get(archivo).get(0).getNombre()).enviarSolicitudDescarga(mensaje.getOrigen().getNombre(), archivo);
		}
		else {
			listener.enviarFalloConexion();
		}
	}
	
	private void enviarDatosEmisor(EnviarDatosEmisor mensaje, ClientListener y) {
		mapaOyentes.get(mensaje.getReceptor()).enviarDatosEmisor(mensaje);
	}
	
	private void actualizarArchivos(ActualizarArchivos mensaje, ClientListener listener) {
		mapaArchivos.get(mensaje.getArchivo()).add(mensaje.getOrigen());
	}

	public void desconectarCliente(Mensaje mensaje, ClientListener listener) {
	}
	
	private static class ServerMaps<C,V> {
		private Map<C,V> map;
		Semaphore s;
		
		public ServerMaps() {
			map = new HashMap<C,V>();
			this.s = new Semaphore(1);
		}
		
		private V get(C clave) {
			V valor;
			try {
				s.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			valor = map.get(clave);
			s.release();
			return valor;
		}
		
		private void put(C clave, V valor) {
			try {
				s.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(clave, valor);
			s.release();
		}
		
		public Set<C> keySet() {
			Set<C> keys;
			try {
				s.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			keys = map.keySet();
			s.release();
			return keys;
		}
		
		public boolean containsKey(C clave) {
			boolean ret = false;
			try {
				s.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ret = map.containsKey(clave);
			s.release();
			return ret;
		}
	}
	
	


}
