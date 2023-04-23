package mensajes;

import utils.TipoMensaje;

public class SolicitarConexionP2P extends Mensaje {

	private static final long serialVersionUID = 1L;
	
	private String fileName;
	
	String receptor;

	public SolicitarConexionP2P(String receptor, String fileName) {
		super(TipoMensaje.SOLICITAR_CONEXION_P2P);
		this.receptor = receptor;
		this.fileName = fileName;
	}

	public TipoMensaje getTipo() {
		return tipo;
	}

	public String getFileName() {
		return fileName;
	}
	
	public String getReceptor() {
		return receptor;
	}
}
