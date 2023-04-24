package mensajes;

import cliente.User;
import utils.TipoMensaje;

public class ActualizarArchivos extends Mensaje{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String file;
	
	public ActualizarArchivos(String file,User user) {
		super(TipoMensaje.ACTUALIZAR_ARCHIVOS);
		this.user = user;
		this.file = file;
	}

	@Override
	public TipoMensaje getTipo() {
		return tipo;
	}

	public String getArchivo() {
		return file;
	}
}
