package mensajes;

import java.io.File;

import utils.TipoMensaje;

public class DarArchivo extends Mensaje {
	private static final long serialVersionUID = 1L;
	
	private File archivo;
	
	public DarArchivo(File archivo) {
		super(TipoMensaje.DAR_ARCHIVO);
		this.archivo = archivo;
	}

	@Override
	public TipoMensaje getTipo() {
		return tipo;
	}
	
	public File getArchivo() {
		return archivo;
	}

}
