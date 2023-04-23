package mensajes;

import java.net.InetAddress;

import utils.TipoMensaje;

public class EnviarDatosEmisor extends Mensaje {
	
	int port;
	
	InetAddress ip;
	
	String receptor;
	
	String file;

	public EnviarDatosEmisor(int port, InetAddress ip, String receptor, String file) {
		super(TipoMensaje.ENVIAR_DATOS_EMISOR);
		this.port = port;
		this.ip = ip;
		this.receptor = receptor;
		this.file = file;
	}

	public TipoMensaje getTipo() {
		return tipo;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
