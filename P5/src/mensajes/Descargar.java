package mensajes;

import utils.TipoMensaje;

public class Descargar extends Mensaje  {
	
	private static final long serialVersionUID = 1L;

	public Descargar() {
		super(TipoMensaje.DESCARGAR);
	}

	public TipoMensaje getTipo() {
		return TipoMensaje.DESCARGAR;
	}

}
