package mensajes;

import utils.TipoMensaje;

public class ConfirmarConectar extends Mensaje {

	private static final long serialVersionUID = 1L;
	
	private int port;

	public ConfirmarConectar(int port) {
		super(TipoMensaje.CONFIRMAR_CONECTAR);
		this.port = port;
	}

	public TipoMensaje getTipo() {
		return tipo;
	}
	
	/*
	public void puertoConexion(int port) {
		this.port = port;
	}*/
	
	public int getPort() {
		return port;
	}


}
