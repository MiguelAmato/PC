package cliente;



import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import mensajes.ActualizarArchivos;
import mensajes.Conectar;
import mensajes.ConfirmarConectar;
import mensajes.DarListaArchivos;
import mensajes.DarListaClientes;
import mensajes.EnviarDatosEmisor;
import mensajes.FalloConexionP2P;
import mensajes.Mensaje;
import mensajes.SolicitarConexionP2P;
import mensajes.UsuarioRepetido;
import utils.Function1;
import utils.LockTicket;
import utils.TipoMensaje;

public class Client {
	
	public static final int SERVER_PORT = 5555;
	
	Socket clientSocket;
	
	private ServerSocket p2pSocket;
	
	private User info;
	
	private ObjectOutputStream out;
	
	private ObjectInputStream in;

    private ServerListener serverListener;
    
    private ClientView view;
    
    private Map<TipoMensaje, Function1<Mensaje>> mapaFunciones;
    
	private LockTicket lock;

	
	public Client(String name, File directorio) {
		info = new User(name, directorio);
		lock = new LockTicket();
		initMaps();
	}

	private void initMaps() {
		mapaFunciones = new HashMap<TipoMensaje, Function1<Mensaje>>();
		mapaFunciones.put(TipoMensaje.DAR_LISTA_CLIENTES, (x) -> getListaClientes((DarListaClientes) x));
		mapaFunciones.put(TipoMensaje.DAR_LISTA_ARCHIVOS, (x) -> getListaArchivos((DarListaArchivos)x));
		mapaFunciones.put(TipoMensaje.FALLO_CONEXION_P2P, (x) -> falloConexion((FalloConexionP2P)x));
		mapaFunciones.put(TipoMensaje.SOLICITAR_CONEXION_P2P, (x) -> conexionP2P((SolicitarConexionP2P)x));
		mapaFunciones.put(TipoMensaje.ENVIAR_DATOS_EMISOR, (x) -> recibirDatosEmisor((EnviarDatosEmisor)x));
		mapaFunciones.put(TipoMensaje.USUARIO_REPETIDO, (x) -> repetirRegistro((UsuarioRepetido)x));
	}



	public void start() {
		try {
			clientSocket = new Socket(info.getIp(), SERVER_PORT);
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
	
	public void probarNombre(String name) {
		User infoAux = info;
		info = new User(name, infoAux.getDirectorio());
		Conectar mensaje = new Conectar(info);
		try {
			out.writeObject(mensaje);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setView(ClientView view) {
		this.view = view;
	}

	public void desconectar() {
		
	}
	
	public User getInfo() {
		return info;
	}

	public void respuestaServer(Mensaje mensaje) {
		mapaFunciones.get(mensaje.getTipo()).apply(mensaje);
	}
		
	private void getListaClientes(DarListaClientes mensaje) {
		view.mostrarPanelLista(mensaje.getListaConectados());
	}
		
	

	private void getListaArchivos(DarListaArchivos mensaje) {
		view.mostrarPanelListaArchivos(mensaje.getListaArchivos());
	}
	
	private void falloConexion(FalloConexionP2P x) {
		view.falloDescarga();
	}


	private void conexionP2P(SolicitarConexionP2P x) {
		try {
			p2pSocket = new ServerSocket(0);
			
			enviarMensaje(new EnviarDatosEmisor(p2pSocket.getLocalPort(), info.getIp(), x.getReceptor(), x.getFileName()));
			System.out.println("Despues del serverSocket " + info.getIp() + p2pSocket.getLocalPort());
			
			Socket socket = p2pSocket.accept();
			System.out.println("Server acepto el socket ");
			
			new ClienteEmisor(socket, this, x.getFileName()).start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	private void recibirDatosEmisor(EnviarDatosEmisor mensaje) {
		try {
			
			Socket socket = new Socket(mensaje.getIp(), mensaje.getPort());
			System.out.println("Despues del socket " + mensaje.getIp() + mensaje.getPort());
			new ClienteReceptor(socket, mensaje.getFile(),this).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void repetirRegistro(UsuarioRepetido x) {
		view.repetirNombre();
	}

	public void actualizarLista(String file) {
		lock.takeLock();
		
		User aux = info;
		
		info = new User(aux.getNombre(),aux.getDirectorio());
		
		lock.releaseLock();
		
		ActualizarArchivos mensaje = new ActualizarArchivos(file,this.info);
		
		enviarMensaje(mensaje);
				
	}

}
