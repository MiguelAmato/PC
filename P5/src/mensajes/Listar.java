package mensajes;

import utils.TipoMensaje;

public class Listar extends Mensaje {
	
	private static final long serialVersionUID = 1L;

	public Listar() {
		super(TipoMensaje.LISTAR);
	}

	public TipoMensaje getTipo() {
		return TipoMensaje.LISTAR;
	}

}
