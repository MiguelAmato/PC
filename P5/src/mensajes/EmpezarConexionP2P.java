package mensajes;

import cliente.User;
import utils.TipoMensaje;

public class EmpezarConexionP2P extends Mensaje {

	private static final long serialVersionUID = 1L;
	
	private String fileName;

	public EmpezarConexionP2P(String fileName, User user) {
		super(TipoMensaje.EMPEZAR_CONEXION_P2P);
		this.fileName = fileName;
		this.user = user;
	}

	public TipoMensaje getTipo() {
		return tipo;
	}

	public String getFileName() {
		return fileName;
	}
}
